package team5427.frc.robot.subsystems.indexer.io;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.frc.robot.subsystems.indexer.io.IndexerIO.IndexerIOInputs;
import team5427.lib.motors.SteelTalonFX;

public class IndexerIOTalonFX implements IndexerIO {
  private SteelTalonFX rightIndexerMotor;
  private SteelTalonFX leftIndexerMotor;

  private StatusSignal<AngularVelocity> leftIndexerMotorAngularVelocity;
  private StatusSignal<AngularVelocity> rightIndexerMotorAngularVelocity;

  private StatusSignal<Current> leftIndexerMotorCurrent;
  private StatusSignal<Current> rightIndexerMotorCurrent;

  private StatusSignal<Voltage> leftIndexerMotorVoltage;
  private StatusSignal<Voltage> rightIndexerMotorVoltage;

  public IndexerIOTalonFX() {
    rightIndexerMotor = new SteelTalonFX(IndexerConstants.kIndexerLeftMotorCanId);
    leftIndexerMotor = new SteelTalonFX(IndexerConstants.kIndexerRightMotorCanId);

    rightIndexerMotor.apply(IndexerConstants.kIndexerMotorConfiguration);
    leftIndexerMotor.apply(IndexerConstants.kIndexerMotorConfiguration);

    rightIndexerMotor.setEncoderPosition(0);
    leftIndexerMotor.setEncoderPosition(0);
    leftIndexerMotorAngularVelocity = leftIndexerMotor.getTalonFX().getVelocity();
    rightIndexerMotorAngularVelocity = rightIndexerMotor.getTalonFX().getVelocity();

    leftIndexerMotorCurrent = leftIndexerMotor.getTalonFX().getStatorCurrent();
    leftIndexerMotorVoltage = leftIndexerMotor.getTalonFX().getMotorVoltage();

    rightIndexerMotorCurrent = rightIndexerMotor.getTalonFX().getStatorCurrent();
    rightIndexerMotorVoltage = rightIndexerMotor.getTalonFX().getMotorVoltage();
    BaseStatusSignal.refreshAll(
        leftIndexerMotorAngularVelocity,
        rightIndexerMotorAngularVelocity,
        leftIndexerMotorCurrent,
        rightIndexerMotorCurrent,
        leftIndexerMotorVoltage,
        rightIndexerMotorVoltage);
  }

  public void updateInputs(IndexerIOInputs inputs) {
    BaseStatusSignal.refreshAll(
        leftIndexerMotorAngularVelocity,
        rightIndexerMotorAngularVelocity,
        leftIndexerMotorCurrent,
        rightIndexerMotorCurrent,
        leftIndexerMotorVoltage,
        rightIndexerMotorVoltage);
    inputs.leftIndexerMotorConnected = leftIndexerMotor.getTalonFX().isConnected();
    inputs.rightIndexerMotorConnected = rightIndexerMotor.getTalonFX().isConnected();

    inputs.leftIndexerMotorAngularVelocity = leftIndexerMotorAngularVelocity.getValue();
    inputs.leftIndexerMotorLinearVelocity =
        MetersPerSecond.of(
            leftIndexerMotor.getConversionFactorFromRotations()
                * leftIndexerMotorAngularVelocity.getValue().in(RotationsPerSecond));
    inputs.leftIndexerMotorCurrent = leftIndexerMotorCurrent.getValue();
    inputs.leftIndexerMotorVoltage = leftIndexerMotorVoltage.getValue();

    inputs.rightIndexerMotorAngularVelocity = rightIndexerMotorAngularVelocity.getValue();
    inputs.rightIndexerMotorLinearVelocity =
        MetersPerSecond.of(
            rightIndexerMotor.getConversionFactorFromRotations()
                * rightIndexerMotorAngularVelocity.getValue().in(RotationsPerSecond));
    inputs.rightIndexerMotorCurrent = rightIndexerMotorCurrent.getValue();
    inputs.rightIndexerMotorVoltage = rightIndexerMotorVoltage.getValue();
  }
}
