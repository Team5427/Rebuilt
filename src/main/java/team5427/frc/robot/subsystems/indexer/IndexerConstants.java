package team5427.frc.robot.subsystems.indexer;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import team5427.lib.drivers.CANDeviceId;
import team5427.lib.drivers.ComplexGearRatio;
import team5427.lib.motors.MotorConfiguration;
import team5427.lib.motors.MotorConfiguration.IdleState;
import team5427.lib.motors.MotorConfiguration.MotorMode;
import team5427.lib.motors.MotorUtil;

public final class IndexerConstants {
  public static final CANDeviceId kIndexerLeftMotorCanId = new CANDeviceId(12);
  public static final CANDeviceId kIndexerRightMotorCanId = new CANDeviceId(13);

  public static MotorConfiguration kIndexerMotorConfiguration = new MotorConfiguration();
  public static final Distance kIndexerFlywheelRadius = Inches.of(1.0);

  public static final LinearVelocity kIndexerStowedVelocity = MetersPerSecond.of(0.0);
  public static final LinearVelocity kIndexerIndexingVelocity = MetersPerSecond.of(2.0);

  static {
    // kP = .9
    // kV = .1
    // kA = .1
    kIndexerMotorConfiguration.gearRatio = new ComplexGearRatio(1.0);
    kIndexerMotorConfiguration.mode = MotorMode.kFlywheel;
    kIndexerMotorConfiguration.idleState = IdleState.kBrake;
    kIndexerMotorConfiguration.isArm = false;
    kIndexerMotorConfiguration.isInverted = false;
    kIndexerMotorConfiguration.currentLimit = 40;
    kIndexerMotorConfiguration.finalDiameterMeters = kIndexerFlywheelRadius.times(2.0).in(Meters);
    kIndexerMotorConfiguration.withFOC = false;
    kIndexerMotorConfiguration.maxVelocity =
        kIndexerMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44_MaxRPM);
    kIndexerMotorConfiguration.maxAcceleration = kIndexerMotorConfiguration.maxVelocity * 3.0;
    kIndexerMotorConfiguration.kP = .9;
    kIndexerMotorConfiguration.kV = .1;
    kIndexerMotorConfiguration.kA = .1;
    kIndexerMotorConfiguration.kS = .1;
  }
}
