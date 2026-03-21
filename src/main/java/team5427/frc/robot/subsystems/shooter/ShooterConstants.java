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

  public static final MotorConfiguration kRightHoodMotorConfiguration = new MotorConfiguration();
  public static final MotorConfiguration kLeftHoodMotorConfiguration = new MotorConfiguration();

  public static final MotorConfiguration kRightFlywheelMotorConfiguration =
      new MotorConfiguration();
  public static final MotorConfiguration kLeftFlywheelMotorConfiguration = new MotorConfiguration();

  public static final ComplexGearRatio kHoodMotorGearRatio =
      new ComplexGearRatio((12.0 / 30.0), (28.0 / 324.0));
  public static final ComplexGearRatio kFlywheelMotorGearRatio =
      new ComplexGearRatio((30.0 / 36.0));

  public static final double kTopFlywheelRadiusMeters = Units.inchesToMeters(1.0);
  public static final double kBottomFlywheelRadiusMeters = Units.inchesToMeters(2.0); // 2.0

  public static final Rotation2d kHoodHardstopPosition = Rotation2d.fromDegrees(28.01);
  public static final Rotation2d kHoodMaximumRotation = Rotation2d.fromDegrees(62.227);

  public static final LinearVelocity kShooterStowVelocity = MetersPerSecond.of(1.0);

  static {
    kRightHoodMotorConfiguration.gearRatio = kHoodMotorGearRatio;
    kRightHoodMotorConfiguration.isArm = true;
    kRightHoodMotorConfiguration.idleState = IdleState.kBrake;
    kRightHoodMotorConfiguration.isInverted = false;
    kRightHoodMotorConfiguration.mode = MotorMode.kServo;
    kRightHoodMotorConfiguration.withFOC = true;

    kRightHoodMotorConfiguration.maxVelocity =
        kRightHoodMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
    kRightHoodMotorConfiguration.maxAcceleration = kRightHoodMotorConfiguration.maxVelocity;

    kRightHoodMotorConfiguration.altV = kRightHoodMotorConfiguration.maxVelocity / 2.0;
    kRightHoodMotorConfiguration.altA = kRightHoodMotorConfiguration.maxAcceleration;
    kRightHoodMotorConfiguration.altJ = 1000.0;

    kRightHoodMotorConfiguration.kP = 800.02000045776367;
    kRightHoodMotorConfiguration.kD = 0;
    kRightHoodMotorConfiguration.kI = 0;

    // kHoodMotorConfiguration.kV = 1.13;
    // kHoodMotorConfiguration.kA = 0.01;
    // kHoodMotorConfiguration.kS = 0.0;
    // kHoodMotorConfiguration.kG = 0.18;

    kRightHoodMotorConfiguration.currentLimit = 20;
  }

  static {
    kLeftHoodMotorConfiguration.gearRatio = kHoodMotorGearRatio;
    kLeftHoodMotorConfiguration.isArm = true;
    kLeftHoodMotorConfiguration.idleState = IdleState.kBrake;
    kLeftHoodMotorConfiguration.isInverted = false;
    kLeftHoodMotorConfiguration.mode = MotorMode.kServo;
    kLeftHoodMotorConfiguration.withFOC = true;

    kLeftHoodMotorConfiguration.maxVelocity =
        kLeftHoodMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
    kLeftHoodMotorConfiguration.maxAcceleration = kLeftHoodMotorConfiguration.maxVelocity;

    kLeftHoodMotorConfiguration.altV = kRightHoodMotorConfiguration.maxVelocity / 2.0;
    kLeftHoodMotorConfiguration.altA = kRightHoodMotorConfiguration.maxAcceleration;
    kLeftHoodMotorConfiguration.altJ = 1000.0;

    kLeftHoodMotorConfiguration.kP = 800.02000045776367;
    kLeftHoodMotorConfiguration.kD = 0;
    kLeftHoodMotorConfiguration.kI = 0;

    // kHoodMotorConfiguration.kV = 1.13;
    // kHoodMotorConfiguration.kA = 0.01;
    // kHoodMotorConfiguration.kS = 0.0;
    // kHoodMotorConfiguration.kG = 0.18;

    kLeftHoodMotorConfiguration.currentLimit = 20;
  }

  static {
    kRightFlywheelMotorConfiguration.gearRatio = kFlywheelMotorGearRatio;
    kRightFlywheelMotorConfiguration.isArm = false;
    kRightFlywheelMotorConfiguration.idleState = IdleState.kCoast;
    kRightFlywheelMotorConfiguration.isInverted = true;
    kRightFlywheelMotorConfiguration.mode = MotorMode.kFlywheel;
    kRightFlywheelMotorConfiguration.withFOC = true;
    kRightFlywheelMotorConfiguration.finalDiameterMeters = kBottomFlywheelRadiusMeters * 2.0;

    kRightFlywheelMotorConfiguration.maxVelocity =
        kRightFlywheelMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM);
    kRightFlywheelMotorConfiguration.maxAcceleration =
        kRightFlywheelMotorConfiguration.maxVelocity * 2.0;

    kRightFlywheelMotorConfiguration.altV = kRightFlywheelMotorConfiguration.maxVelocity / 2.0;
    kRightFlywheelMotorConfiguration.altA = kRightFlywheelMotorConfiguration.maxAcceleration;
    kRightFlywheelMotorConfiguration.altJ = 1000.0;

    kRightFlywheelMotorConfiguration.kP = 0.07; // .03
    kRightFlywheelMotorConfiguration.kI = 0.0;
    kRightFlywheelMotorConfiguration.kD = 0.0;

    kRightFlywheelMotorConfiguration.kV = 0.062; // .07
    kRightFlywheelMotorConfiguration.kA = 0.05;
    kRightFlywheelMotorConfiguration.kS = 0.1;
    kRightFlywheelMotorConfiguration.kG = 0.0;
    kRightFlywheelMotorConfiguration.kFF = 0.0;

    kRightFlywheelMotorConfiguration.currentLimit = 140;
  }

  static {
    kLeftFlywheelMotorConfiguration.gearRatio = kFlywheelMotorGearRatio;
    kLeftFlywheelMotorConfiguration.isArm = false;
    kLeftFlywheelMotorConfiguration.idleState = IdleState.kCoast;
    kLeftFlywheelMotorConfiguration.isInverted = false;
    kLeftFlywheelMotorConfiguration.mode = MotorMode.kFlywheel;
    kLeftFlywheelMotorConfiguration.withFOC = true;
    kLeftFlywheelMotorConfiguration.finalDiameterMeters = kBottomFlywheelRadiusMeters * 2.0;

    kLeftFlywheelMotorConfiguration.maxVelocity =
        kLeftFlywheelMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM);
    kLeftFlywheelMotorConfiguration.maxAcceleration =
        kLeftFlywheelMotorConfiguration.maxVelocity * 2.0;

    kLeftFlywheelMotorConfiguration.altV = kLeftFlywheelMotorConfiguration.maxVelocity / 2.0;
    kLeftFlywheelMotorConfiguration.altA = kLeftFlywheelMotorConfiguration.maxAcceleration;
    kLeftFlywheelMotorConfiguration.altJ = 1000.0;

    kLeftFlywheelMotorConfiguration.kP = 0.22; // .031
    kLeftFlywheelMotorConfiguration.kI = 0.0;
    kLeftFlywheelMotorConfiguration.kD = 0.0;

    kLeftFlywheelMotorConfiguration.kV = 0.017; // 0.077
    kLeftFlywheelMotorConfiguration.kA = 0.05; // 0.05
    kLeftFlywheelMotorConfiguration.kS = 0.1; // 0.15
    kLeftFlywheelMotorConfiguration.kG = 0.0;
    kLeftFlywheelMotorConfiguration.kFF = 0.0;

    kLeftFlywheelMotorConfiguration.currentLimit = 140;
  }
}
