package team5427.frc.robot.subsystems.turret;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearAcceleration;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.SteelTalonFX;
import team5427.frc.robot.subsystems.turret.TurretConstants;

public class TurretIOTalonFX implements TurretIO {

  private SteelTalonFX pivotMotor;
  private SteelTalonFX turretMotor;

  private StatusSignal<Angle> pivotMotorAngle;
  private StatusSignal<AngularVelocity> pivotMotorAngularVelocity;
  private StatusSignal<AngularAcceleration> pivotMotorAngularAcceleration;
  private LinearVelocity pivotMotorLinearVelocity;
  private LinearAcceleration pivotMotorLinearAcceleration;
  private StatusSignal<Temperature> pivotMotorTemperature;
  private StatusSignal<Current> pivotMotorCurrent;
  private StatusSignal<Voltage> pivotMotorVoltage;

  private boolean pivotMotorIsConnected = false;

  private StatusSignal<Angle> turretMotorAngle;
  private StatusSignal<Temperature> turretMotorTemperature;
  private StatusSignal<Current> turretMotorCurrent;
  private StatusSignal<Voltage> turretMotorVoltage;

  private boolean turretMotorIsConnected = false;

  public TurretIOTalonFX() {
    pivotMotor = new SteelTalonFX(null);

    pivotMotorAngle = pivotMotor.getTalonFX().getPosition();

    pivotMotorTemperature = pivotMotor.getTalonFX().getDeviceTemp();
    pivotMotorCurrent = pivotMotor.getTalonFX().getStatorCurrent();
    pivotMotorVoltage = pivotMotor.getTalonFX().getMotorVoltage();
    pivotMotorIsConnected = pivotMotor.getTalonFX().isConnected();

    turretMotor = new SteelTalonFX(null);

    turretMotorAngle = turretMotor.getTalonFX().getPosition();

    turretMotorTemperature = turretMotor.getTalonFX().getDeviceTemp();
    turretMotorCurrent = turretMotor.getTalonFX().getStatorCurrent();
    turretMotorVoltage = turretMotor.getTalonFX().getMotorVoltage();
    turretMotorIsConnected = turretMotor.getTalonFX().isConnected();
  }

  @Override
  public void updateInputs(TurretIOInputs inputs) {
    inputs.pivotMotorIsConnected = pivotMotor.getTalonFX().isConnected();

    BaseStatusSignal.refreshAll(
        pivotMotorAngle,
        pivotMotorAngularVelocity,
        pivotMotorAngularAcceleration,
        pivotMotorTemperature,
        pivotMotorVoltage,
        pivotMotorCurrent);

    inputs.pivotMotorAngle =
        Rotation2d.fromRadians(pivotMotor.getTalonFX().getPosition().getValue().magnitude());
    inputs.pivotMotorTemperature = pivotMotor.getTalonFX().getDeviceTemp().getValue();
    inputs.pivotMotorCurrent = pivotMotor.getTalonFX().getStatorCurrent().getValue();
    inputs.pivotMotorVoltage = pivotMotor.getTalonFX().getMotorVoltage().getValue();

    inputs.turretMotorIsConnected = turretMotor.getTalonFX().isConnected();

    BaseStatusSignal.refreshAll(
        turretMotorAngle, turretMotorTemperature, turretMotorVoltage, turretMotorCurrent);

    inputs.turretMotorAngle =
        Rotation2d.fromRadians(turretMotor.getTalonFX().getPosition().getValue().magnitude());
    inputs.turretMotorTemperature = turretMotor.getTalonFX().getDeviceTemp().getValue();
    inputs.turretMotorCurrent = turretMotor.getTalonFX().getStatorCurrent().getValue();
    inputs.turretMotorVoltage = turretMotor.getTalonFX().getMotorVoltage().getValue();

    inputs.turretMotorIsConnected = turretMotor.getTalonFX().isConnected();
  }

  @Override
  public void setPivotRotation(Rotation2d rotation) {
    pivotMotor.setSetpoint(rotation);
    
  }

    @Override
  public Rotation2d getPivotRotation() {
    return Rotation2d.fromRadians(pivotMotor.getTalonFX().getPosition().getValue().magnitude());
  }

  @Override
  public void resetPivot(Rotation2d resetAngle) {
    pivotMotor.setSetpoint(resetAngle);
  }

  @Override
  public void disablePivot() {
    pivotMotor.getTalonFX().disable();
  }

  @Override
  public void setTurretRotation(Rotation2d rotation) {
    turretMotor.setSetpoint(rotation);
  }

  @Override
  public void setTurretVelocity(AngularVelocity angularVelocity){
    turretMotor.setSetpoint(angularVelocity);
  }

  @Override
  public void resetTurret(Rotation2d resetAngle) {
    turretMotor.setSetpoint(resetAngle);
  }

  @Override
  public void disableTurret() {
    turretMotor.getTalonFX().disable();
  }

  public Rotation2d wrapAngle(Rotation2d rotation){
    if(rotation.getRadians()>Math.PI){
        return Rotation2d.fromRotations(rotation.getRadians() - 2*Math.PI);
    }
    return Rotation2d.fromRotations(rotation.getRadians() + 2*Math.PI);
  }

}
