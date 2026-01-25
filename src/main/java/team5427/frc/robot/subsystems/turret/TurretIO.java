package team5427.frc.robot.subsystems.turret;

import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface TurretIO {

  @AutoLog
  public static class TurretIOInputs {
    public Rotation2d turretMotorAngle = Rotation2d.kZero;

    public AngularVelocity turretMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public Voltage turretMotorVoltage = Volts.of(0.0);

    public boolean turretMotorIsConnected = false;
  }

  public void updateInputs(TurretIOInputs inputs);

  public void setTurretRotation(Rotation2d rotation);

  public void resetTurret(Rotation2d resetAngle);

  public void disableTurret();
}
