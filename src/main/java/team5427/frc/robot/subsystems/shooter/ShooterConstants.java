package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.LinearVelocity;
import team5427.lib.drivers.CANDeviceId;
import team5427.lib.drivers.ComplexGearRatio;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.MotorConfiguration.IdleState;
import team5427.lib.motors.MotorConfiguration.MotorMode;
import team5427.lib.motors.MotorUtil;

public final class ShooterConstants {

  public static final Transform3d kRobotToShooterTransform =
      new Transform3d(
              Units.inchesToMeters(5.547244),
              0,
              Units.inchesToMeters(14.75),
              new Rotation3d(0, 0, Math.PI))
          .inverse();

  public static final CANDeviceId kLeftHoodMotorCanId = new CANDeviceId(20);
  public static final CANDeviceId kLeftFlywheelLeaderMotorCanId = new CANDeviceId(21);
  public static final CANDeviceId kLeftFlywheelFollowerMotorCanId = new CANDeviceId(22);

  public static final CANDeviceId kRightHoodMotorCanId = new CANDeviceId(23);
  public static final CANDeviceId kRightFlywheelLeaderMotorCanId = new CANDeviceId(24);
  public static final CANDeviceId kRightFlywheelFollowerMotorCanId = new CANDeviceId(25);

  public static final MotorConfiguration kHoodMotorConfiguration = new MotorConfiguration();
  public static final MotorConfiguration kFlywheelMotorConfiguration = new MotorConfiguration();

  public static final ComplexGearRatio kHoodMotorGearRatio = new ComplexGearRatio((28.0 / 324.0));
  public static final ComplexGearRatio kFlywheelMotorGearRatio =
      new ComplexGearRatio((30.0 / 36.0));

  public static final double kTopFlywheelRadiusMeters = Units.inchesToMeters(1.0);
  public static final double kBottomFlywheelRadiusMeters = Units.inchesToMeters(2.0);

  public static final Rotation2d kHoodHardstopPosition = Rotation2d.fromDegrees(180.0 - 162.2820);
  public static final Rotation2d kHoodMaximumRotation = Rotation2d.fromDegrees(62.227);

  public static final LinearVelocity kShooterStowVelocity = MetersPerSecond.of(1.0);

  static {
    kHoodMotorConfiguration.gearRatio = kHoodMotorGearRatio;
    kHoodMotorConfiguration.isArm = true;
    kHoodMotorConfiguration.idleState = IdleState.kBrake;
    kHoodMotorConfiguration.isInverted = false;
    kHoodMotorConfiguration.mode = MotorMode.kServo;
    kHoodMotorConfiguration.withFOC = true;

    kHoodMotorConfiguration.maxVelocity =
        kHoodMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
    kHoodMotorConfiguration.maxAcceleration = kHoodMotorConfiguration.maxVelocity;

    kHoodMotorConfiguration.altV = kHoodMotorConfiguration.maxVelocity / 2.0;
    kHoodMotorConfiguration.altA = kHoodMotorConfiguration.maxAcceleration;
    kHoodMotorConfiguration.altJ = 1000.0;

    kHoodMotorConfiguration.kP = 800.02000045776367;
    kHoodMotorConfiguration.kD = 0;
    kHoodMotorConfiguration.kI = 0;

    // kHoodMotorConfiguration.kV = 1.13;
    // kHoodMotorConfiguration.kA = 0.01;
    // kHoodMotorConfiguration.kS = 0.0;
    // kHoodMotorConfiguration.kG = 0.18;

    kHoodMotorConfiguration.currentLimit = 20;
  }

  static {
    kFlywheelMotorConfiguration.gearRatio = kFlywheelMotorGearRatio;
    kFlywheelMotorConfiguration.isArm = false;
    kFlywheelMotorConfiguration.idleState = IdleState.kCoast;
    kFlywheelMotorConfiguration.isInverted = true;
    kFlywheelMotorConfiguration.mode = MotorMode.kFlywheel;
    kFlywheelMotorConfiguration.withFOC = false;
    kFlywheelMotorConfiguration.finalDiameterMeters =
        kTopFlywheelRadiusMeters + kBottomFlywheelRadiusMeters;

    kFlywheelMotorConfiguration.maxVelocity =
        kFlywheelMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
    kFlywheelMotorConfiguration.maxAcceleration = kFlywheelMotorConfiguration.maxVelocity * 2.0;

    kFlywheelMotorConfiguration.altV = kFlywheelMotorConfiguration.maxVelocity / 2.0;
    kFlywheelMotorConfiguration.altA = kFlywheelMotorConfiguration.maxAcceleration;
    kFlywheelMotorConfiguration.altJ = 1000.0;

    kFlywheelMotorConfiguration.kP = 0.08;
    kFlywheelMotorConfiguration.kI = 0.0;
    kFlywheelMotorConfiguration.kD = 0.0;

    kFlywheelMotorConfiguration.kV = 0.37;
    kFlywheelMotorConfiguration.kA = 0.33;
    kFlywheelMotorConfiguration.kS = 0.0;
    kFlywheelMotorConfiguration.kG = 0.0;
    kFlywheelMotorConfiguration.kFF = 0.0;

    kFlywheelMotorConfiguration.currentLimit = 40;
  }
}
