package team5427.frc.robot.subsystems.shooter.io;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
  @AutoLog
  public static class ShooterIOInputs {
    // Shooter Left
    public boolean leftHoodMotorConnected = false;
    public boolean leftFlywheelMotorLeaderConnected = false;
    public boolean leftFlywheelMotorFollowerConnected = false;

    public double leftHoodMotorPositionRadians = 0.0;
    public double leftHoodMotorAngularVelocityRadiansPerSecond = 0.0;
    public AngularVelocity leftFlywheelMotorAngularSpeed = RotationsPerSecond.of(0.0);
    public LinearVelocity leftFlywheelMotorLinearSpeed = MetersPerSecond.of(0.0);

    // Shooter Right
    public boolean rightHoodMotorConnected = false;
    public boolean rightFlywheelMotorLeaderConnected = false;
    public boolean rightFlywheelMotorFollowerConnected = false;

    public double rightHoodMotorPositionRadians = 0.0;
    public double rightHoodMotorAngularVelocityRadiansPerSecond = 0.0;
    public AngularVelocity rightFlywheelMotorAngularSpeed = RotationsPerSecond.of(0.0);
    public LinearVelocity rightFlywheelMotorLinearSpeed = MetersPerSecond.of(0.0);
  }

  public void updateInputs(ShooterIOInputs inputs);

  // Shooter Left
  public default void setLeftHoodAngle(Rotation2d angle) {}

  public default void setLeftHoodAngle(Angle angle) {}

  public default void setLeftHoodAngle(double radians) {}

  public default void setLeftFlywheelSpeed(LinearVelocity velocity) {}

  public default void setLeftFlywheelSpeed(AngularVelocity velocity) {}

  public default void setLeftFlywheelSpeed(double rotationsPerSecond) {}

  public default void resetLeftHoodAngle(Rotation2d angle) {}

  // Shooter Right
  public default void setRightHoodAngle(Rotation2d angle) {}

  public default void setRightHoodAngle(Angle angle) {}

  public default void setRightHoodAngle(double radians) {}

  public default void setRightFlywheelSpeed(LinearVelocity velocity) {}

  public default void setRightFlywheelSpeed(AngularVelocity velocity) {}

  public default void setRightFlywheelSpeed(double rotationsPerSecond) {}

  public default void resetRightHoodAngle(Rotation2d angle) {}
}
