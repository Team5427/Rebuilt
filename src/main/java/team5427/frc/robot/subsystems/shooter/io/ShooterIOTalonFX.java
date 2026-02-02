package team5427.frc.robot.subsystems.shooter.io;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.lib.motors.SteelTalonFX;

public class ShooterIOTalonFX implements ShooterIO {

  // Shooter Left
  private SteelTalonFX leftHoodMotor;
  private SteelTalonFX leftFlywheelLeaderMotor;
  private SteelTalonFX leftFlywheelFollowerMotor;

  // Shooter Right
  private SteelTalonFX rightHoodMotor;
  private SteelTalonFX rightFlywheelLeaderMotor;
  private SteelTalonFX rightFlywheelFollowerMotor;

  private StatusSignal<Angle> leftHoodMotorPosition;
  private StatusSignal<AngularVelocity> leftHoodMotorAngularVelocity;
  private StatusSignal<AngularVelocity> leftFlywheelMotorAngularSpeed;

  private StatusSignal<Current> leftFlywheelLeaderMotorCurrent;
  private StatusSignal<Current> leftFlywheelFollowerMotorCurrent;

  private StatusSignal<Voltage> leftFlywheelLeaderMotorVoltage;
  private StatusSignal<Voltage> leftFlywheelFollowerMotorVoltage;

  private StatusSignal<Temperature> leftFlywheelLeaderMotorTemperature;
  private StatusSignal<Temperature> leftFlywheelFollowerMotorTemperature;

  private StatusSignal<Angle> rightHoodMotorPosition;
  private StatusSignal<AngularVelocity> rightHoodMotorAngularVelocity;
  private StatusSignal<AngularVelocity> rightFlywheelMotorAngularSpeed;
  
  private StatusSignal<Current> rightFlywheelLeaderMotorCurrent;
  private StatusSignal<Current> rightFlywheelFollowerMotorCurrent;

  private StatusSignal<Voltage> rightFlywheelLeaderMotorVoltage;
  private StatusSignal<Voltage> rightFlywheelFollowerMotorVoltage;

  private StatusSignal<Temperature> rightFlywheelLeaderMotorTemperature;
  private StatusSignal<Temperature> rightFlywheelFollowerMotorTemperature;

  public ShooterIOTalonFX() {
    leftHoodMotor = new SteelTalonFX(ShooterConstants.kLeftHoodMotorCanId);
    leftFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelLeaderMotorCanId);
    leftFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelFollowerMotorCanId);

    leftHoodMotor.apply(ShooterConstants.kHoodMotorConfiguration);
    leftFlywheelLeaderMotor.apply(ShooterConstants.kFlywheelMotorConfiguration);
    leftFlywheelFollowerMotor.apply(ShooterConstants.kFlywheelMotorConfiguration);

    leftHoodMotor.setEncoderPosition(0.0);
    leftFlywheelLeaderMotor.setEncoderPosition(0.0);
    leftFlywheelFollowerMotor.setEncoderPosition(0.0);

    rightHoodMotor = new SteelTalonFX(ShooterConstants.kRightHoodMotorCanId);
    rightFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelLeaderMotorCanId);
    rightFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelFollowerMotorCanId);

    rightHoodMotor.apply(ShooterConstants.kHoodMotorConfiguration);
    rightFlywheelLeaderMotor.apply(ShooterConstants.kFlywheelMotorConfiguration);
    rightFlywheelFollowerMotor.apply(ShooterConstants.kFlywheelMotorConfiguration);

    rightHoodMotor.setEncoderPosition(0.0);
    rightFlywheelLeaderMotor.setEncoderPosition(0.0);
    rightFlywheelFollowerMotor.setEncoderPosition(0.0);
  
    leftHoodMotorPosition = leftHoodMotor.getTalonFX().getPosition();
    leftHoodMotorAngularVelocity = leftHoodMotor.getTalonFX().getVelocity();
    leftFlywheelMotorAngularSpeed = leftFlywheelLeaderMotor.getTalonFX().getVelocity();

    leftFlywheelLeaderMotorCurrent = leftFlywheelLeaderMotor.getTalonFX().getStatorCurrent();
    leftFlywheelFollowerMotorCurrent = leftFlywheelFollowerMotor.getTalonFX().getStatorCurrent();

    leftFlywheelLeaderMotorVoltage = leftFlywheelLeaderMotor.getTalonFX().getMotorVoltage();
    leftFlywheelFollowerMotorVoltage = leftFlywheelFollowerMotor.getTalonFX().getMotorVoltage();

    leftFlywheelLeaderMotorTemperature = leftFlywheelLeaderMotor.getTalonFX().getDeviceTemp();
    leftFlywheelFollowerMotorTemperature = leftFlywheelFollowerMotor.getTalonFX().getDeviceTemp();
  
    rightHoodMotorPosition = rightHoodMotor.getTalonFX().getPosition();
    rightHoodMotorAngularVelocity = rightHoodMotor.getTalonFX().getVelocity();
    rightFlywheelMotorAngularSpeed = rightFlywheelLeaderMotor.getTalonFX().getVelocity();

    rightFlywheelLeaderMotorCurrent = rightFlywheelLeaderMotor.getTalonFX().getStatorCurrent();
    rightFlywheelFollowerMotorCurrent = rightFlywheelFollowerMotor.getTalonFX().getStatorCurrent();

    rightFlywheelLeaderMotorVoltage = rightFlywheelLeaderMotor.getTalonFX().getMotorVoltage();
    rightFlywheelFollowerMotorVoltage = rightFlywheelFollowerMotor.getTalonFX().getMotorVoltage();

    rightFlywheelLeaderMotorTemperature = rightFlywheelLeaderMotor.getTalonFX().getDeviceTemp();
    rightFlywheelFollowerMotorTemperature = rightFlywheelFollowerMotor.getTalonFX().getDeviceTemp();
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateInputs'");
  }
}
