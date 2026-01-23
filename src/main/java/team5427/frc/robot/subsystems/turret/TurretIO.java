package team5427.frc.robot.subsystems.turret;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Celsius;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecondPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearAcceleration;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface TurretIO {

  @AutoLog
  public static class TurretIOInputs {
    public Rotation2d pivotMotorAngle = new Rotation2d();

    public AngularVelocity pivotMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public AngularAcceleration pivotMotorAngularAcceleration = RotationsPerSecondPerSecond.of(0.0);

    public LinearVelocity pivotMotorLinearVelocity = MetersPerSecond.of(0.0);
    public LinearAcceleration pivotMotorLinearAcceleration = MetersPerSecondPerSecond.of(0.0);

    public Temperature pivotMotorTemperature = Celsius.of(0.0);
    public Current pivotMotorCurrent = Amps.of(0.0);
    public Voltage pivotMotorVoltage = Volts.of(0.0);

    public boolean pivotMotorIsConnected = false;

    public Rotation2d turretMotorAngle = new Rotation2d();

    public Temperature turretMotorTemperature = Celsius.of(0.0);
    public Current turretMotorCurrent = Amps.of(0.0);
    public Voltage turretMotorVoltage = Volts.of(0.0);

    public boolean turretMotorIsConnected = false;
  }

  public void updateInputs(TurretIOInputs inputs);

  public void setPivotRotation(Rotation2d rotation);

  public Rotation2d getPivotRotation();

  public void resetPivot(Rotation2d resetAngle);

  public void disablePivot();

  public void setTurretRotation(Rotation2d rotation);

  public void resetTurret(Rotation2d resetAngle);

  public void setTurretVelocity(AngularVelocity angularVelocity);

  public void disableTurret();
}
