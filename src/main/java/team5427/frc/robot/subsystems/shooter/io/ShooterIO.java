package team5427.frc.robot.subsystems.shooter.io;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;

import static edu.wpi.first.units.Units.*;

import org.littletonrobotics.junction.AutoLog;

public interface ShooterIO {
  @AutoLog
  public static class ShooterIOInputs {
    // Shooter Left
    public boolean leftHoodMotorConnected = false;
    public boolean leftFlywheelMotorLeaderConnected = false;
    public boolean leftFlywheelMotorFollowerConnected = false;
    public boolean leftHoodMotorDisabled = false;
    public boolean leftFlywheelMotorLeaderDisabled = false;
    public boolean leftFlywheelMotorFollowerDisabled = false;


    public double leftHoodMotorPositionRadians = 0.0;
    public double leftHoodMotorAngularVelocityRadiansPerSecond = 0.0;
    public AngularAcceleration leftFlyWheelAngularAcceleration = RotationsPerSecondPerSecond.of(0.0);
    public AngularVelocity leftFlywheelMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public LinearVelocity leftFlywheelMotorLinearVelocity = MetersPerSecond.of(0.0);

    public Current leftFlywheelLeaderMotorCurrent = Amps.of(0);
    public Current leftFlywheelFollowerMotorCurrent = Amps.of(0);

    public Voltage leftFlywheelLeaderMotorVoltage = Volts.of(0);
    public Voltage leftFlywheelFollowerMotorVoltage = Volts.of(0);

    public Temperature leftFlywheelLeaderMotorTemperature = Celsius.of(0.0);
    public Temperature leftFlywheelFollowerMotorTemperature = Celsius.of(0.0);

    // Shooter Right
    public boolean rightHoodMotorConnected = false;
    public boolean rightFlywheelMotorLeaderConnected = false;
    public boolean rightFlywheelMotorFollowerConnected = false;
    public boolean rightHoodMotorDisabled = false;
    public boolean rightFlywheelMotorLeaderDisabled = false;
    public boolean rightFlywheelMotorFollowerDisabled = false;


    public double rightHoodMotorPositionRadians = 0.0;
    public double rightHoodMotorAngularVelocityRadiansPerSecond = 0.0;
   public AngularAcceleration rightFlyWheelAngularAcceleration = RotationsPerSecondPerSecond.of(0.0);

    public AngularVelocity rightFlywheelMotorAngularVelocity = RotationsPerSecond.of(0.0);
    public LinearVelocity rightFlywheelMotorLinearVelocity = MetersPerSecond.of(0.0);
    
    public Current rightFlywheelLeaderMotorCurrent = Amps.of(0);
    public Current rightFlywheelFollowerMotorCurrent = Amps.of(0);

    public Voltage rightFlywheelLeaderMotorVoltage = Volts.of(0);
    public Voltage rightFlywheelFollowerMotorVoltage = Volts.of(0);

    public Temperature rightFlywheelLeaderMotorTemperature = Celsius.of(0.0);
    public Temperature rightFlywheelFollowerMotorTemperature = Celsius.of(0.0);
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
