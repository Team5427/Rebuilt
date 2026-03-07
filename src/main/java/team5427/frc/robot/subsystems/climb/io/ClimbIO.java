package team5427.frc.robot.subsystems.climb.io;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface ClimbIO {
  @AutoLog
  public static class ClimbIOInputs {
    public double climbPositionMeters = 0.0;
    public AngularVelocity climbAngularVelocity = RadiansPerSecond.of(0.0);
    public LinearVelocity climbLinearVelocity = MetersPerSecond.of(0.0);
    public Current climbCurrent = Amps.of(0.0);
    public Voltage climbVoltage = Volts.of(0.0);
  }

  public default void updateInputs(ClimbIOInputs inputs) {}

  public default void setClimbPosition(Distance position) {}

  public default void resetClimbPosition() {}
}
