package team5427.frc.robot.subsystems.indexer.io;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import org.littletonrobotics.junction.AutoLog;

public interface IndexerIO {
  @AutoLog
  public static class IndexerIOInputs {
    public AngularVelocity indexerMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public LinearVelocity indexerFlywheelLinearVelocity = MetersPerSecond.of(0.0);

    public Current indexerMotorCurrent = Amps.of(0.0);

    public boolean indexerMotorLeaderConnected = false;
    public boolean indexerMotorFollowerConnected = false;
  }

  public default void updateInputs(IndexerIOInputsAutoLogged inputs) {}

  public default void setIndexerMotorRotation(Rotation2d rotation) {}

  public default void setIndexerMotorVelocity(AngularVelocity velocity) {}

  public default void disableIndexerMotor(boolean shouldDisable) {}
}
