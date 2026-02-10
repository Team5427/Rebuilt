package team5427.frc.robot.subsystems.shooter.io;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.lib.motors.MotorConfiguration;
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
  private StatusSignal<AngularVelocity> leftFlywheelMotorAngularVelocity;
  private StatusSignal<AngularAcceleration> leftFlyWheelMotorAngularAcceleration;

  private StatusSignal<Current> leftFlywheelLeaderMotorCurrent;
  private StatusSignal<Current> leftFlywheelFollowerMotorCurrent;

  private StatusSignal<Voltage> leftFlywheelLeaderMotorVoltage;
  private StatusSignal<Voltage> leftFlywheelFollowerMotorVoltage;

  private StatusSignal<Temperature> leftFlywheelLeaderMotorTemperature;
  private StatusSignal<Temperature> leftFlywheelFollowerMotorTemperature;

  private StatusSignal<Angle> rightHoodMotorPosition;
  private StatusSignal<AngularVelocity> rightHoodMotorAngularVelocity;
  private StatusSignal<AngularVelocity> rightFlywheelMotorAngularVelocity;
    private StatusSignal<AngularAcceleration> rightFlyWheelMotorAngularAcceleration;

  
  private StatusSignal<Current> rightFlywheelLeaderMotorCurrent;
  private StatusSignal<Current> rightFlywheelFollowerMotorCurrent;

  private StatusSignal<Voltage> rightFlywheelLeaderMotorVoltage;
  private StatusSignal<Voltage> rightFlywheelFollowerMotorVoltage;

  private StatusSignal<Temperature> rightFlywheelLeaderMotorTemperature;
  private StatusSignal<Temperature> rightFlywheelFollowerMotorTemperature;

  private boolean isLeftFlyWheelLeaderMotorDisabled = false;
  private boolean isLeftFlyWheelFollowerMotorDisabled = false;
  private boolean isRightFlyWheelLeaderMotorDisabled = false;
  private boolean isRightFlyWheelFollowerMotorDisabled = false;
  private boolean isLeftHoodMotorDisabled = false;
  private boolean isRightHoodMotorDisabled = false;

  public ShooterIOTalonFX() {
    leftHoodMotor = new SteelTalonFX(ShooterConstants.kLeftHoodMotorCanId);
    leftFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelLeaderMotorCanId);
    leftFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelFollowerMotorCanId);

    leftHoodMotor.apply(ShooterConstants.kHoodMotorConfiguration);
    leftFlywheelLeaderMotor.apply(ShooterConstants.kFlywheelMotorConfiguration);
    leftFlywheelFollowerMotor.apply(
        new MotorConfiguration(ShooterConstants.kFlywheelMotorConfiguration));

    leftHoodMotor.setEncoderPosition(0.0);
    leftFlywheelLeaderMotor.setEncoderPosition(0.0);
    leftFlywheelFollowerMotor.setEncoderPosition(0.0);

    leftFlywheelFollowerMotor
        .getTalonFX()
        .setControl(
            new Follower(
                leftFlywheelLeaderMotor.getTalonFX().getDeviceID(), MotorAlignmentValue.Aligned));

    rightHoodMotor = new SteelTalonFX(ShooterConstants.kRightHoodMotorCanId);
    rightFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelLeaderMotorCanId);
    rightFlywheelFollowerMotor =
        new SteelTalonFX(ShooterConstants.kRightFlywheelFollowerMotorCanId);

    rightHoodMotor.apply(ShooterConstants.kHoodMotorConfiguration);
    rightFlywheelLeaderMotor.apply(ShooterConstants.kFlywheelMotorConfiguration);
    rightFlywheelFollowerMotor.apply(
        new MotorConfiguration(ShooterConstants.kFlywheelMotorConfiguration));

    rightHoodMotor.setEncoderPosition(0.0);
    rightFlywheelLeaderMotor.setEncoderPosition(0.0);
    rightFlywheelFollowerMotor.setEncoderPosition(0.0);

    rightFlywheelFollowerMotor
        .getTalonFX()
        .setControl(
            new Follower(
                rightFlywheelLeaderMotor.getTalonFX().getDeviceID(), MotorAlignmentValue.Aligned));

    leftHoodMotorPosition = leftHoodMotor.getTalonFX().getPosition();
    leftHoodMotorAngularVelocity = leftHoodMotor.getTalonFX().getVelocity();
    leftFlywheelMotorAngularVelocity = leftFlywheelLeaderMotor.getTalonFX().getVelocity();

    leftFlywheelLeaderMotorCurrent = leftFlywheelLeaderMotor.getTalonFX().getStatorCurrent();
    leftFlywheelFollowerMotorCurrent = leftFlywheelFollowerMotor.getTalonFX().getStatorCurrent();

    leftFlywheelLeaderMotorVoltage = leftFlywheelLeaderMotor.getTalonFX().getMotorVoltage();
    leftFlywheelFollowerMotorVoltage = leftFlywheelFollowerMotor.getTalonFX().getMotorVoltage();

    leftFlywheelLeaderMotorTemperature = leftFlywheelLeaderMotor.getTalonFX().getDeviceTemp();
    leftFlywheelFollowerMotorTemperature = leftFlywheelFollowerMotor.getTalonFX().getDeviceTemp();
  
    rightHoodMotorPosition = rightHoodMotor.getTalonFX().getPosition();
    rightHoodMotorAngularVelocity = rightHoodMotor.getTalonFX().getVelocity();
    rightFlywheelMotorAngularVelocity = rightFlywheelLeaderMotor.getTalonFX().getVelocity();

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
    inputs.leftFlywheelMotorFollowerConnected = leftFlywheelFollowerMotor.getTalonFX().isConnected();
    inputs.leftFlywheelMotorLeaderConnected = leftFlywheelLeaderMotor.getTalonFX().isConnected();
    inputs.leftHoodMotorConnected = leftHoodMotor.getTalonFX().isConnected();
    inputs.rightFlywheelMotorFollowerConnected = rightFlywheelFollowerMotor.getTalonFX().isConnected();
    inputs.rightFlywheelMotorFollowerConnected = rightFlywheelLeaderMotor.getTalonFX().isConnected();
    inputs.rightHoodMotorConnected = rightHoodMotor.getTalonFX().isConnected();


    inputs.leftFlywheelMotorFollowerDisabled = isLeftFlyWheelFollowerMotorDisabled;
    inputs.leftFlywheelMotorLeaderDisabled = isLeftFlyWheelLeaderMotorDisabled;
    inputs.leftHoodMotorDisabled = isLeftHoodMotorDisabled;
    inputs.rightFlywheelMotorFollowerDisabled = isLeftFlyWheelFollowerMotorDisabled;
    inputs.rightFlywheelMotorLeaderDisabled = isLeftFlyWheelLeaderMotorDisabled;
    inputs.rightHoodMotorDisabled = isLeftHoodMotorDisabled;
    BaseStatusSignal.refreshAll(leftFlyWheelMotorAngularAcceleration, rightFlyWheelMotorAngularAcceleration, leftHoodMotorPosition, rightHoodMotorPosition);

    BaseStatusSignal.refreshAll(
        leftFlywheelMotorAngularVelocity,
        rightFlywheelMotorAngularVelocity,
        leftFlywheelFollowerMotorCurrent,
        leftFlywheelLeaderMotorCurrent,
        leftHoodMotorAngularVelocity,
        rightHoodMotorAngularVelocity
        );

    BaseStatusSignal.refreshAll(
        leftFlywheelFollowerMotorTemperature, leftFlywheelLeaderMotorTemperature, rightFlywheelFollowerMotorTemperature, rightFlywheelLeaderMotorTemperature, leftFlywheelFollowerMotorVoltage, leftFlywheelLeaderMotorVoltage, rightFlywheelFollowerMotorVoltage, rightFlywheelLeaderMotorVoltage);

//     inputs.leftHoodMotorPositionRadians =
//         Rotation2d.fromRotations(pivotMotorPosition.getValue().in(Rotation));
//     inputs.pivotMotorAngularVelocity = pivotMotorAngularVelocity.getValue();
//     inputs.pivotMotorAngularAcceleration = pivotMotorAngularAcceleration.getValue();
//     inputs.pivotMotorCurrent = pivotMotorCurrent.getValue();
//     inputs.pivotMotorVoltage = pivotMotorVoltage.getValue();
//     inputs.pivotMotorTemperature = pivotMotorTemperature.getValue();

//     inputs.rollerMotorAngularVelocity = rollerMotorAngularVelocity.getValue();
//     inputs.rollerMotorAngularAcceleration = rollerMotorAngularAcceleration.getValue();
//     inputs.rollerMotorCurrent = rollerMotorCurrent.getValue();
//     inputs.rollerMotorLinearVelocity =
//         MetersPerSecond.of(rollerMotor.getEncoderVelocity(rollerMotorAngularVelocity));
//     inputs.rollerMotorLinearAcceleration =
//         MetersPerSecondPerSecond.of(
//             rollerMotor.getEncoderAcceleration(rollerMotorAngularAcceleration));
//     inputs.rollerMotorTemperature = rollerMotorTemperature.getValue();
//     inputs.rollerMotorVoltage = rollerMotorVoltage.getValue();


  }
}
