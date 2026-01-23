package team5427.frc.robot.subsystems.Swerve.gyro;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import org.littletonrobotics.junction.AutoLog;

public interface GyroIO {
  @AutoLog
  public static class GyroIOInputs {
    public boolean connected = false;
    public Rotation2d yawPosition = new Rotation2d();
    public double[] odometryYawTimestamps = new double[0];
    public Rotation2d[] odometryYawPositions = new Rotation2d[] {};
    public AngularVelocity yawVelocity = RadiansPerSecond.of(0);
  }

  public default void updateInputs(GyroIOInputs inputs) {}

  public default void resetGyroYawAngle(Rotation2d angle) {}
}
