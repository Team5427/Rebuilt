package team5427.frc.robot.subsystems.shooter.io;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
  @AutoLog
  public static class ShooterIOInputs {
    // Shooter Left
    public double leftHoodMotorPositionRadians = 0.0;
    public double leftHoodMotorAngularVelocityRadiansPerSecond = 0.0;
    public AngularAcceleration leftFlywheelAngularAcceleration =
        RotationsPerSecondPerSecond.of(0.0);
    public AngularVelocity leftFlywheelMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public LinearVelocity leftFlywheelMotorLinearVelocity = MetersPerSecond.of(0.0);

    public Voltage leftFlywheelLeaderMotorVoltage = Volts.of(0);
    public Voltage leftFlywheelFollowerMotorVoltage = Volts.of(0);

    // Shooter Right
    public double rightHoodMotorPositionRadians = 0.0;
    public double rightHoodMotorAngularVelocityRadiansPerSecond = 0.0;
    public AngularAcceleration rightFlywheelAngularAcceleration =
        RotationsPerSecondPerSecond.of(0.0);
    public AngularVelocity rightFlywheelMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public LinearVelocity rightFlywheelMotorLinearVelocity = MetersPerSecond.of(0.0);

    public Voltage rightFlywheelLeaderMotorVoltage = Volts.of(0);
    public Voltage rightFlywheelFollowerMotorVoltage = Volts.of(0);
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
