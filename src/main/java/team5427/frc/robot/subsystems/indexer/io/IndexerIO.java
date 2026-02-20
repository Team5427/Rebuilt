package team5427.frc.robot.subsystems.indexer.io;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volt;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface IndexerIO {
  @AutoLog
  public static class IndexerIOInputs {
    public boolean leftIndexerMotorConnected = false;
    public boolean rightIndexerMotorConnected = false;

    public AngularVelocity leftIndexerMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public AngularVelocity rightIndexerMotorAngularVelocity = RotationsPerSecond.of(0.0);

    public LinearVelocity leftIndexerMotorLinearVelocity = MetersPerSecond.of(0.0);
    public LinearVelocity rightIndexerMotorLinearVelocity = MetersPerSecond.of(0.0);

    public Current leftIndexerMotorCurrent = Amps.of(0.0);
    public Current rightIndexerMotorCurrent = Amps.of(0.0);

    public Voltage leftIndexerMotorVoltage = Volts.of(0.0);
    public Voltage rightIndexerMotorVoltage = Volt.of(0.0);
  }

  public default void updateInputs(IndexerIOInputs inputs) {}

  public default void setRightIndexerMotorVelocity(LinearVelocity velocity) {}

  public default void setLeftIndexerMotorVelocity(LinearVelocity velocity) {}
}
