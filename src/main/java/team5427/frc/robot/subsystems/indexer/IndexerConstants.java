package team5427.frc.robot.subsystems.indexer;

import static edu.wpi.first.units.Units.Inches;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Distance;
import team5427.lib.drivers.CANDeviceId;
import team5427.lib.motors.MotorConfiguration;

public final class IndexerConstants {
    public static final CANDeviceId kIndexerMasterMotorCanId = new CANDeviceId(12);
    public static final CANDeviceId kIndexerSlaveMotorCanId = new CANDeviceId(13);

    public static MotorConfiguration kIndexerMotorConfiguration = new MotorConfiguration();

    public static final Rotation2d kIndexerStartingRotation = Rotation2d.kZero;
    public static final Distance kIndexerFlywheelRadius = Inches.of(1.0);

}
