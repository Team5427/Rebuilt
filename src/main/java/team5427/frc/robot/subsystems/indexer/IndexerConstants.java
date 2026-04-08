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
  public static final CANDeviceId kIndexerLeftMotorCanId = new CANDeviceId(26);
  public static final CANDeviceId kIndexerRightMotorCanId = new CANDeviceId(27);
  public static final CANDeviceId kHopperMotorCanId = new CANDeviceId(28);

  public static MotorConfiguration kIndexerMotorConfiguration = new MotorConfiguration();
  public static final Distance kIndexerFlywheelRadius = Inches.of(1.0);
  public static MotorConfiguration kHopperMotorConfiguration = new MotorConfiguration();
  public static final Distance kHopperFlywheelRadius = Inches.of(0.5);

  public static final LinearVelocity kIndexerStowedVelocity = MetersPerSecond.of(0.0);

  static {
    kHopperMotorConfiguration.gearRatio = new ComplexGearRatio((18.0 / 24.0));
    kHopperMotorConfiguration.mode = MotorMode.kFlywheel;
    kHopperMotorConfiguration.idleState = IdleState.kCoast;
    kHopperMotorConfiguration.isArm = false;
    kHopperMotorConfiguration.isInverted = true;
    kHopperMotorConfiguration.currentLimit = 30;
    kHopperMotorConfiguration.supplyCurrentLimit = 15;
    kHopperMotorConfiguration.finalDiameterMeters = kHopperFlywheelRadius.times(2.0).in(Meters);
    kHopperMotorConfiguration.withFOC = true;
    kHopperMotorConfiguration.maxVelocity =
        kHopperMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM) / 5.0;
    kHopperMotorConfiguration.maxAcceleration = kHopperMotorConfiguration.maxVelocity * 3.0;

    kHopperMotorConfiguration.kP = 0.2;
    kHopperMotorConfiguration.kV = 0.5;
    kHopperMotorConfiguration.kS = 0.5;
  }

  static {
    // kP = .9
    // kV = .1
    // kA = .1
    kIndexerMotorConfiguration.gearRatio = new ComplexGearRatio(1.0);
    kIndexerMotorConfiguration.mode = MotorMode.kFlywheel;
    kIndexerMotorConfiguration.idleState = IdleState.kBrake;
    kIndexerMotorConfiguration.isArm = false;
    kIndexerMotorConfiguration.isInverted = false;
    kIndexerMotorConfiguration.currentLimit = 55;
    kIndexerMotorConfiguration.supplyCurrentLimit = 30;
    kIndexerMotorConfiguration.finalDiameterMeters = kIndexerFlywheelRadius.times(2.0).in(Meters);
    kIndexerMotorConfiguration.withFOC = false;
    kIndexerMotorConfiguration.maxVelocity =
        kIndexerMotorConfiguration.getStandardMaxVelocity(MotorUtil.kKrakenX44FOC_MaxRPM) / 5.0;
    kIndexerMotorConfiguration.maxAcceleration = kIndexerMotorConfiguration.maxVelocity * 3.0;
    kIndexerMotorConfiguration.kP = .4;
    kIndexerMotorConfiguration.kV = .1;
    kIndexerMotorConfiguration.kA = .1;
    kIndexerMotorConfiguration.kS = .1;
  }
}
