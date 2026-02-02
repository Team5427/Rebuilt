package team5427.frc.robot.subsystems.shooter.io;

import com.ctre.phoenix6.StatusSignal;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
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

  private StatusSignal<Boolean> leftHoodMotorConnected;
  private StatusSignal<Boolean> leftFlywheelMotorLeaderConnected;
  private StatusSignal<Boolean> leftFlywheelMotorFollowerConnected;

  private StatusSignal<Double> leftHoodMotorPositionRadians;
  private StatusSignal<Double> leftHoodMotorAngularVelocityRadiansPerSecond;
  private StatusSignal<AngularVelocity> leftFlywheelMotorAngularSpeed;
  private StatusSignal<LinearVelocity> leftFlywheelMotoLinearSpeed;

  private StatusSignal<Boolean> rightHoodMotorConnected;
  private StatusSignal<Boolean> rightFlywheelMotorLeaderConnected;
  private StatusSignal<Boolean> rightFlywheelMotorFollowerConnected;

  private StatusSignal<Double> rightHoodMotorPositionRadians;
  private StatusSignal<Double> rightHoodMotorAngularVelocityRadiansPerSecond;
  private StatusSignal<AngularVelocity> rightFlywheelMotorAngularSpeed;
  private StatusSignal<LinearVelocity> rightFlywheelMotoLinearSpeed;

  public ShooterIOTalonFX() {
    leftHoodMotor = new SteelTalonFX(ShooterConstants.kLeftHoodMotorCanId);
    leftFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelLeaderMotorCanId);
    leftFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kLeftFlywheelFollowerMotorCanId);

    rightHoodMotor = new SteelTalonFX(ShooterConstants.kRightHoodMotorCanId);
    rightFlywheelLeaderMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelLeaderMotorCanId);
    rightFlywheelFollowerMotor = new SteelTalonFX(ShooterConstants.kRightFlywheelFollowerMotorCanId);


  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateInputs'");
  }
}
