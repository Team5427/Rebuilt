package team5427.frc.robot.subsystems.climb;

import edu.wpi.first.math.geometry.Rotation2d;
import team5427.lib.drivers.CANDeviceId;
import team5427.lib.drivers.ComplexGearRatio;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.MotorConfiguration.IdleState;
import team5427.lib.motors.MotorConfiguration.MotorMode;
import team5427.lib.motors.MotorUtil;

public class ClimbConstants {
  public static final CANDeviceId kHookServoId = new CANDeviceId(28);

  public static final MotorConfiguration kServoConfiguration = new MotorConfiguration();

  static {
    kServoConfiguration.gearRatio = new ComplexGearRatio(1.0 / 3.0);
    kServoConfiguration.idleState = IdleState.kBrake;
    kServoConfiguration.isInverted = true;
    kServoConfiguration.mode = MotorMode.kServo;
    kServoConfiguration.withFOC = false;

    kServoConfiguration.currentLimit = 50;
    kServoConfiguration.supplyCurrentLimit = 40;

    kServoConfiguration.maxVelocity =
        kServoConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX60FOC_MaxRPM);
    kServoConfiguration.maxAcceleration = kServoConfiguration.maxVelocity * 3.0;

    kServoConfiguration.kP = 1.5;
  }

  public static final Rotation2d kStowPosition = Rotation2d.kZero;
  public static final Rotation2d kPrepPosition = Rotation2d.fromDegrees(45.0);
  public static final Rotation2d kActivePosition = Rotation2d.fromDegrees(120.0);
  public static final Rotation2d kL1Angle = Rotation2d.fromDegrees(180.0);
}
