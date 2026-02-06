package team5427.frc.robot.subsystems.shooter.io;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.ParentDevice;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
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
  private StatusSignal<AngularVelocity> leftFlywheelMotorAngularSpeed;
  private StatusSignal<Voltage> leftFlywheelMotorVoltage;

  private StatusSignal<Angle> rightHoodMotorPosition;
  private StatusSignal<AngularVelocity> rightHoodMotorAngularVelocity;
  private StatusSignal<AngularVelocity> rightFlywheelMotorAngularSpeed;
  private StatusSignal<Voltage> rightFlywheelMotorVoltage;

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
    leftFlywheelMotorAngularSpeed = leftFlywheelLeaderMotor.getTalonFX().getVelocity();
    leftFlywheelMotorVoltage = leftFlywheelLeaderMotor.getTalonFX().getMotorVoltage();

    rightHoodMotorPosition = rightHoodMotor.getTalonFX().getPosition();
    rightHoodMotorAngularVelocity = rightHoodMotor.getTalonFX().getVelocity();
    rightFlywheelMotorAngularSpeed = rightFlywheelLeaderMotor.getTalonFX().getVelocity();
    rightFlywheelMotorVoltage = rightFlywheelLeaderMotor.getTalonFX().getMotorVoltage();

    BaseStatusSignal.setUpdateFrequencyForAll(
        Constants.kHighPriorityUpdateFrequency,
        leftHoodMotorPosition,
        leftFlywheelMotorAngularSpeed,
        rightHoodMotorPosition,
        rightFlywheelMotorAngularSpeed);

    BaseStatusSignal.setUpdateFrequencyForAll(
        Constants.kMediumPriorityUpdateFrequency,
        leftHoodMotorAngularVelocity,
        rightHoodMotorAngularVelocity,
        leftFlywheelMotorVoltage,
        rightFlywheelMotorVoltage);

    ParentDevice.optimizeBusUtilizationForAll(
        leftHoodMotor.getTalonFX(),
        leftFlywheelLeaderMotor.getTalonFX(),
        leftFlywheelFollowerMotor.getTalonFX(),
        rightHoodMotor.getTalonFX(),
        rightFlywheelLeaderMotor.getTalonFX(),
        rightFlywheelFollowerMotor.getTalonFX());
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    BaseStatusSignal.refreshAll(
        leftHoodMotorPosition,
        leftFlywheelMotorAngularSpeed,
        rightHoodMotorPosition,
        rightFlywheelMotorAngularSpeed);

    BaseStatusSignal.refreshAll(
        leftHoodMotorAngularVelocity,
        rightHoodMotorAngularVelocity,
        leftFlywheelMotorVoltage,
        rightFlywheelMotorVoltage);

    inputs.leftHoodMotorConnected = leftHoodMotor.getTalonFX().isConnected();
    inputs.leftFlywheelMotorLeaderConnected = leftFlywheelLeaderMotor.getTalonFX().isConnected();
    inputs.leftFlywheelMotorFollowerConnected =
        leftFlywheelFollowerMotor.getTalonFX().isConnected();

    inputs.leftHoodMotorPositionRadians = leftHoodMotorPosition.getValue().in(Radians);
    inputs.leftHoodMotorAngularVelocityRadiansPerSecond =
        leftHoodMotorAngularVelocity.getValue().in(RadiansPerSecond);
    inputs.leftFlywheelMotorAngularSpeed = leftFlywheelMotorAngularSpeed.getValue();
    inputs.leftFlywheelMotorLinearSpeed =
        MetersPerSecond.of(
            leftFlywheelMotorAngularSpeed.getValue().in(RadiansPerSecond)
                * ((ShooterConstants.kTopFlywheelRadiusMeters
                        + ShooterConstants.kBottomFlywheelRadiusMeters)
                    / 2.0));
    inputs.leftFlywheelMotorVoltage = leftFlywheelMotorVoltage.getValue();

    inputs.rightHoodMotorConnected = rightHoodMotor.getTalonFX().isConnected();
    inputs.rightFlywheelMotorLeaderConnected = rightFlywheelLeaderMotor.getTalonFX().isConnected();
    inputs.rightFlywheelMotorFollowerConnected =
        rightFlywheelFollowerMotor.getTalonFX().isConnected();

    inputs.rightHoodMotorPositionRadians = rightHoodMotorPosition.getValue().in(Radians);
    inputs.rightHoodMotorAngularVelocityRadiansPerSecond =
        rightHoodMotorAngularVelocity.getValue().in(RadiansPerSecond);
    inputs.rightFlywheelMotorAngularSpeed = rightFlywheelMotorAngularSpeed.getValue();
    inputs.rightFlywheelMotorLinearSpeed =
        LinearVelocity.ofBaseUnits(
            rightFlywheelMotorAngularSpeed.getValue().in(RadiansPerSecond)
                * ((ShooterConstants.kTopFlywheelRadiusMeters
                        + ShooterConstants.kBottomFlywheelRadiusMeters)
                    / 2.0),
            MetersPerSecond);
    inputs.rightFlywheelMotorVoltage = rightFlywheelMotorVoltage.getValue();
  }

  @Override
  public void setLeftHoodAngle(Rotation2d angle) {
    leftHoodMotor.setSetpoint(angle);
  }

  @Override
  public void setLeftHoodAngle(double radians) {
    leftHoodMotor.setSetpoint(Rotation2d.fromRadians(radians));
  }

  @Override
  public void setLeftHoodAngle(Angle angle) {
    leftHoodMotor.setSetpoint(angle);
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
    leftHoodMotor.setSetpoint(angle);
  }

  @Override
  public void setRightHoodAngle(Rotation2d angle) {
    rightHoodMotor.setSetpoint(angle);
  }

  @Override
  public void setRightHoodAngle(double radians) {
    rightHoodMotor.setSetpoint(Rotation2d.fromRadians(radians));
  }

  @Override
  public void setRightHoodAngle(Angle angle) {
    rightHoodMotor.setSetpoint(angle);
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
    rightHoodMotor.setSetpoint(angle);
  }
}
