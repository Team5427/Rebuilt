package team5427.frc.robot.subsystems.turret;

import static edu.wpi.first.units.Units.Rotation;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import team5427.lib.motors.SteelTalonFX;

public class TurretIOTalonFX implements TurretIO {

  private SteelTalonFX turretMotor;

  private StatusSignal<Angle> turretMotorAngle;
  private StatusSignal<AngularVelocity> turretMotorAngularVelocity;
  private StatusSignal<Voltage> turretMotorVoltage;

  private boolean turretMotorIsConnected = false;

  public TurretIOTalonFX() {

    turretMotor = new SteelTalonFX(null);

    turretMotorAngle = turretMotor.getTalonFX().getPosition();
    turretMotorAngularVelocity = turretMotor.getTalonFX().getVelocity();
    turretMotorVoltage = turretMotor.getTalonFX().getMotorVoltage();

    turretMotorIsConnected = turretMotor.getTalonFX().isConnected();
  }

  @Override
  public void updateInputs(TurretIOInputs inputs) {

    inputs.turretMotorIsConnected = turretMotor.getTalonFX().isConnected();

    BaseStatusSignal.refreshAll(turretMotorAngle, turretMotorVoltage, turretMotorAngularVelocity);

    inputs.turretMotorAngle = Rotation2d.fromRotations(turretMotorAngle.getValue().in(Rotation));
    inputs.turretMotorAngularVelocity = turretMotorAngularVelocity.getValue();
    inputs.turretMotorVoltage = turretMotorVoltage.getValue();
  }

  @Override
  public void setTurretRotation(Rotation2d rotation) {
    turretMotor.setSetpoint(rotation);
  }

  @Override
  public void resetTurret(Rotation2d resetAngle) {
    turretMotor.setEncoderPosition(resetAngle);
  }

  @Override
  public void disableTurret() {
    turretMotor.getTalonFX().disable();
  }
}
