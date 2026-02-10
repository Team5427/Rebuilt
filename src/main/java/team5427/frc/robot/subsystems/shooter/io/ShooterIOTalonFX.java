package team5427.frc.robot.subsystems.shooter.io;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
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

  private StatusSignal<Voltage> leftFlywheelLeaderMotorVoltage;
  private StatusSignal<Voltage> leftFlywheelFollowerMotorVoltage;

  private StatusSignal<Angle> rightHoodMotorPosition;
  private StatusSignal<AngularVelocity> rightHoodMotorAngularVelocity;
  private StatusSignal<AngularVelocity> rightFlywheelMotorAngularVelocity;
  private StatusSignal<AngularAcceleration> rightFlyWheelMotorAngularAcceleration;

  private StatusSignal<Voltage> rightFlywheelLeaderMotorVoltage;
  private StatusSignal<Voltage> rightFlywheelFollowerMotorVoltage;

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

    leftFlywheelLeaderMotorVoltage = leftFlywheelLeaderMotor.getTalonFX().getMotorVoltage();
    leftFlywheelFollowerMotorVoltage = leftFlywheelFollowerMotor.getTalonFX().getMotorVoltage();

    rightHoodMotorPosition = rightHoodMotor.getTalonFX().getPosition();
    rightHoodMotorAngularVelocity = rightHoodMotor.getTalonFX().getVelocity();
    rightFlywheelMotorAngularVelocity = rightFlywheelLeaderMotor.getTalonFX().getVelocity();

    rightFlywheelLeaderMotorVoltage = rightFlywheelLeaderMotor.getTalonFX().getMotorVoltage();
    rightFlywheelFollowerMotorVoltage = rightFlywheelFollowerMotor.getTalonFX().getMotorVoltage();
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    BaseStatusSignal.refreshAll(
        leftFlyWheelMotorAngularAcceleration,
        rightFlyWheelMotorAngularAcceleration,
        leftHoodMotorPosition,
        rightHoodMotorPosition);

    BaseStatusSignal.refreshAll(
        leftFlywheelMotorAngularVelocity,
        rightFlywheelMotorAngularVelocity,
        leftHoodMotorAngularVelocity,
        rightHoodMotorAngularVelocity);

    BaseStatusSignal.refreshAll(
        leftFlywheelFollowerMotorVoltage,
        leftFlywheelLeaderMotorVoltage,
        rightFlywheelFollowerMotorVoltage,
        rightFlywheelLeaderMotorVoltage);

    inputs.leftHoodMotorPositionRadians = leftHoodMotorPosition.getValue().in(Radians);
    inputs.leftHoodMotorAngularVelocityRadiansPerSecond =
        leftHoodMotorAngularVelocity.getValue().in(RadiansPerSecond);
    inputs.leftFlywheelAngularAcceleration = leftFlyWheelMotorAngularAcceleration.getValue();
    inputs.leftFlywheelMotorAngularVelocity = leftFlywheelMotorAngularVelocity.getValue();
    inputs.leftFlywheelMotorLinearVelocity =
        MetersPerSecond.of(
            (ShooterConstants.kTopFlywheelRadiusMeters
                    + ShooterConstants.kBottomFlywheelRadiusMeters)
                * leftFlywheelMotorAngularVelocity.getValue().in(RadiansPerSecond));
    inputs.leftFlywheelLeaderMotorVoltage = leftFlywheelLeaderMotorVoltage.getValue();
    inputs.leftFlywheelFollowerMotorVoltage = leftFlywheelFollowerMotorVoltage.getValue();

    inputs.rightHoodMotorPositionRadians = rightHoodMotorPosition.getValue().in(Radians);
    inputs.rightHoodMotorAngularVelocityRadiansPerSecond =
        rightHoodMotorAngularVelocity.getValue().in(RadiansPerSecond);
    inputs.rightFlywheelAngularAcceleration = rightFlyWheelMotorAngularAcceleration.getValue();
    inputs.rightFlywheelMotorAngularVelocity = rightFlywheelMotorAngularVelocity.getValue();
    inputs.rightFlywheelMotorLinearVelocity =
        MetersPerSecond.of(
            (ShooterConstants.kTopFlywheelRadiusMeters
                    + ShooterConstants.kBottomFlywheelRadiusMeters)
                * rightFlywheelMotorAngularVelocity.getValue().in(RadiansPerSecond));
    inputs.rightFlywheelLeaderMotorVoltage = rightFlywheelLeaderMotorVoltage.getValue();
    inputs.rightFlywheelFollowerMotorVoltage = rightFlywheelFollowerMotorVoltage.getValue();
  }

  @Override
  public void setLeftHoodAngle(Rotation2d angle) {
    leftHoodMotor.setSetpoint(angle);
  }

  @Override
  public void setLeftHoodAngle(Angle angle) {
    leftHoodMotor.setSetpoint(angle);
  }

  @Override
  public void setLeftHoodAngle(double radians) {
    leftHoodMotor.setSetpoint(Radians.of(radians));
  }

  @Override
  public void setLeftFlywheelSpeed(LinearVelocity velocity) {
    leftFlywheelLeaderMotor.setSetpoint(velocity);
  }

  @Override
  public void setLeftFlywheelSpeed(AngularVelocity velocity) {
    leftFlywheelLeaderMotor.setSetpoint(velocity);
  }

  @Override
  public void setLeftFlywheelSpeed(double rotationsPerSecond) {
    leftFlywheelLeaderMotor.setSetpoint(RotationsPerSecond.of(rotationsPerSecond));
  }

  @Override
  public void resetLeftHoodAngle(Rotation2d angle) {
    leftHoodMotor.setEncoderPosition(angle);
  }

  @Override
  public void setRightHoodAngle(Rotation2d angle) {
    rightHoodMotor.setSetpoint(angle);
  }

  @Override
  public void setRightHoodAngle(Angle angle) {
    rightHoodMotor.setSetpoint(angle);
  }

  @Override
  public void setRightHoodAngle(double radians) {
    rightHoodMotor.setSetpoint(Radians.of(radians));
  }

  @Override
  public void setRightFlywheelSpeed(LinearVelocity velocity) {
    rightFlywheelLeaderMotor.setSetpoint(velocity);
  }

  @Override
  public void setRightFlywheelSpeed(AngularVelocity velocity) {
    rightFlywheelLeaderMotor.setSetpoint(velocity);
  }

  @Override
  public void setRightFlywheelSpeed(double rotationsPerSecond) {
    rightFlywheelLeaderMotor.setSetpoint(RotationsPerSecond.of(rotationsPerSecond));
  }

  @Override
  public void resetRightHoodAngle(Rotation2d angle) {
    rightHoodMotor.setEncoderPosition(angle);
  }
}
