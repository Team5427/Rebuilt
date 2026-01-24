package team5427.frc.robot.subsystems.shooter;

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

public interface ShooterIO {

  @AutoLog
  public static class ShooterIOInputs {
    public Rotation2d rollerMotor1Angle = new Rotation2d();

    public AngularVelocity rollerMotor1AngularVelocity = RotationsPerSecond.of(0.0);
    public AngularAcceleration rollerMotor1AngularAcceleration =
        RotationsPerSecondPerSecond.of(0.0);

    public LinearVelocity rollerMotor1LinearVelocity = MetersPerSecond.of(0.0);
    public LinearAcceleration rollerMotor1LinearAcceleration = MetersPerSecondPerSecond.of(0.0);

    public Rotation2d rollerMotor2Angle = new Rotation2d();

    public AngularVelocity rollerMotor2AngularVelocity = RotationsPerSecond.of(0.0);
    public AngularAcceleration rollerMotor2AngularAcceleration =
        RotationsPerSecondPerSecond.of(0.0);

    public LinearVelocity rollerMotor2LinearVelocity = MetersPerSecond.of(0.0);
    public LinearAcceleration rollerMotor2LinearAcceleration = MetersPerSecondPerSecond.of(0.0);

    public Temperature rollerMotor1Temperature = Celsius.of(0.0);
    public Current rollerMotor1Current = Amps.of(0.0);
    public Voltage rollerMotor1Voltage = Volts.of(0.0);

    public boolean rollerMotor1IsConnected = false;

    public Temperature rollerMotor2Temperature = Celsius.of(0.0);
    public Current rollerMotor2Current = Amps.of(0.0);
    public Voltage rollerMotor2Voltage = Volts.of(0.0);

    public boolean rollerMotor2IsConnected = false;
  }

  public void updateInputs(ShooterIOInputs inputs);

  public void setRoller1Rotation(Rotation2d rotation);

  public void setRoller1Speed(AngularVelocity velocity);

  public void setRoller1Speed(LinearVelocity velocity);

  public void resetRoller1(Rotation2d resetAngle);

  public void disableRoller1();

  public void setRoller2Rotation(Rotation2d rotation);

  public void setRoller2Speed(AngularVelocity velocity);

  public void setRoller2Speed(LinearVelocity velocity);

  public void resetRoller2(Rotation2d resetAngle);

  public void disableRoller2();
}
