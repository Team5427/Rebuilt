package team5427.frc.robot.subsystems.indexer.io;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.frc.robot.subsystems.indexer.io.IndexerIO.IndexerIOInputs;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.SteelTalonFX;

public class IndexerIOTalonFX implements IndexerIO {
  private SteelTalonFX rightIndexerMotor;
  private SteelTalonFX leftIndexerMotor;
  private SteelTalonFX hopperMotor;

  private StatusSignal<AngularVelocity> leftIndexerMotorAngularVelocity;
  private StatusSignal<AngularVelocity> rightIndexerMotorAngularVelocity;
  private StatusSignal<AngularVelocity> hopperMotorAngularVelocity;

  private StatusSignal<Current> leftIndexerMotorCurrent;
  private StatusSignal<Current> rightIndexerMotorCurrent;
  private StatusSignal<Current> hopperMotorCurrent;

  private StatusSignal<Voltage> leftIndexerMotorVoltage;
  private StatusSignal<Voltage> rightIndexerMotorVoltage;
  private StatusSignal<Voltage> hopperMotorVoltage;

  public IndexerIOTalonFX() {
    rightIndexerMotor = new SteelTalonFX(IndexerConstants.kIndexerRightMotorCanId);
    leftIndexerMotor = new SteelTalonFX(IndexerConstants.kIndexerLeftMotorCanId);
    hopperMotor = new SteelTalonFX(IndexerConstants.kHopperMotorCanId);

    rightIndexerMotor.apply(IndexerConstants.kIndexerMotorConfiguration);
    MotorConfiguration leftIndexerMotorConfiguration = new MotorConfiguration(IndexerConstants.kIndexerMotorConfiguration);
    leftIndexerMotorConfiguration.isInverted = false;
    leftIndexerMotor.apply(leftIndexerMotorConfiguration);

    hopperMotor.apply(IndexerConstants.kHopperMotorConfiguration);
    // leftIndexerMotor
    //     .getTalonFX()
    //     .setControl(
    //         new Follower(
    //             IndexerConstants.kIndexerRightMotorCanId.getDeviceNumber(),
    //             MotorAlignmentValue.Opposed));

    rightIndexerMotor.setEncoderPosition(0);
    leftIndexerMotor.setEncoderPosition(0);
    hopperMotor.setEncoderPosition(0);
    leftIndexerMotorAngularVelocity = leftIndexerMotor.getTalonFX().getVelocity();
    rightIndexerMotorAngularVelocity = rightIndexerMotor.getTalonFX().getVelocity();
    hopperMotorAngularVelocity = hopperMotor.getTalonFX().getVelocity();

    leftIndexerMotorCurrent = leftIndexerMotor.getTalonFX().getStatorCurrent();
    leftIndexerMotorVoltage = leftIndexerMotor.getTalonFX().getMotorVoltage();

    hopperMotorCurrent = hopperMotor.getTalonFX().getStatorCurrent();
    hopperMotorVoltage = hopperMotor.getTalonFX().getMotorVoltage();

    rightIndexerMotorCurrent = rightIndexerMotor.getTalonFX().getStatorCurrent();
    rightIndexerMotorVoltage = rightIndexerMotor.getTalonFX().getMotorVoltage();
    BaseStatusSignal.refreshAll(
        leftIndexerMotorAngularVelocity,
        rightIndexerMotorAngularVelocity,
        leftIndexerMotorCurrent,
        rightIndexerMotorCurrent,
        leftIndexerMotorVoltage,
        rightIndexerMotorVoltage,
        hopperMotorAngularVelocity,
        hopperMotorCurrent,
        hopperMotorVoltage);
  }

  public void updateInputs(IndexerIOInputs inputs) {
    BaseStatusSignal.refreshAll(
        leftIndexerMotorAngularVelocity,
        rightIndexerMotorAngularVelocity,
        leftIndexerMotorCurrent,
        rightIndexerMotorCurrent,
        leftIndexerMotorVoltage,
        rightIndexerMotorVoltage,
        hopperMotorAngularVelocity,
        hopperMotorCurrent,
        hopperMotorVoltage);

    inputs.leftIndexerMotorConnected = leftIndexerMotor.getTalonFX().isConnected();
    inputs.rightIndexerMotorConnected = rightIndexerMotor.getTalonFX().isConnected();
    inputs.hopperMotorConnected = hopperMotor.getTalonFX().isConnected();

    inputs.leftIndexerMotorAngularVelocity = leftIndexerMotorAngularVelocity.getValue();
    inputs.leftIndexerMotorLinearVelocity =
        MetersPerSecond.of(
            leftIndexerMotor.getConversionFactorFromRotations()
                * leftIndexerMotorAngularVelocity.getValue().in(RotationsPerSecond));
    inputs.leftIndexerMotorCurrent = leftIndexerMotorCurrent.getValue();
    inputs.leftIndexerMotorVoltage = leftIndexerMotorVoltage.getValue();

    inputs.hopperMotorAngularVelocity = hopperMotorAngularVelocity.getValue();
    inputs.hopperMotorLinearVelocity =
        MetersPerSecond.of(
            hopperMotor.getConversionFactorFromRotations()
                * hopperMotorAngularVelocity.getValue().in(RotationsPerSecond));
    inputs.hopperMotorCurrent = hopperMotorCurrent.getValue();
    inputs.hopperMotorVoltage = hopperMotorVoltage.getValue();

    inputs.rightIndexerMotorAngularVelocity = rightIndexerMotorAngularVelocity.getValue();
    inputs.rightIndexerMotorLinearVelocity =
        MetersPerSecond.of(
            rightIndexerMotor.getConversionFactorFromRotations()
                * rightIndexerMotorAngularVelocity.getValue().in(RotationsPerSecond));
    inputs.rightIndexerMotorCurrent = rightIndexerMotorCurrent.getValue();
    inputs.rightIndexerMotorVoltage = rightIndexerMotorVoltage.getValue();
  }

  @Override
  public void setIndexerMotorVelocity(LinearVelocity velocity) {
    rightIndexerMotor.setSetpoint(velocity);
    leftIndexerMotor.setSetpoint(velocity);
  }

  @Override
  public void setLeftIndexerMotorVelocity(LinearVelocity velocity) {
    leftIndexerMotor.setSetpoint(velocity);
  }

  @Override
  public void setRightIndexerMotorVelocity(LinearVelocity velocity) {
    rightIndexerMotor.setSetpoint(velocity);
  }

  @Override
  public void setHopperMotorVelocity(LinearVelocity velocity) {
    hopperMotor.setSetpoint(velocity);
  }
}
