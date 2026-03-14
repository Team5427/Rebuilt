package team5427.frc.robot.subsystems.vision;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Distance;

public class VisionConstants {
  public static final String kRightCamName = "rightCamA";
  public static final String kLeftCamName = "leftCamC";

  public static final int kCameraCount = 2;

  public static final double kMaxAmbiguity = 0.15; // previous 0.2

  public static final Distance kMaxZHeight = Meters.of(1);

  public static final AprilTagFieldLayout kAprilTagLayout =
      AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltAndymark);

  // Left side of bot (Intake as Front)
  // Robot to camera
  public static final Transform3d kRightCamTransform =
      new Transform3d(
          Units.inchesToMeters(-13.315),
          Units.inchesToMeters(-8.813),
          Units.inchesToMeters(11.655),
          new Rotation3d(0, Units.degreesToRadians(20), Units.degreesToRadians(180.0))); // 0 20 180

  // Right side of bot (Intake as Front)
  // Robot to camera
  public static final Transform3d kLeftCamTransform =
      new Transform3d(
          Units.inchesToMeters(-13.315),
          Units.inchesToMeters(8.813),
          Units.inchesToMeters(11.655),
          new Rotation3d(0, Units.degreesToRadians(20), Units.degreesToRadians(180.0))); // 0 20 180

  public static final Transform3d kQuestCameraTransform =
      new Transform3d(
          0.192, 0.358, Units.inchesToMeters(8.098), new Rotation3d(Rotation2d.kCCW_90deg));

  public static Transform3d[] kCameraTransforms = new Transform3d[kCameraCount];

  static {
    kCameraTransforms[1] = kRightCamTransform;
    kCameraTransforms[0] = kLeftCamTransform;
  }

  public static final Distance kCameraMaxRange = Meters.of(4.0);

  // Standard deviation baselines, for 1 meter distance and 1 tag
  // (Adjusted automatically based on distance and # of tags)
  /** Larger stddev equals more doubt in Meters */
  public static double kLinearStdDevBaseline = Units.inchesToMeters(4); // 5

  /** Larger stddev equals more doubt in Radians */
  public static double kAngularStdDevBaseline = Units.degreesToRadians(16); // 30

  public static double[] kCameraStdDevFactors =
      new double[] {
        1.0, // Swerve Cam
        1.0 // Intake Cam
      };

  public static double kQuestStdDevBaseline = 0.001;
  public static String kQuestLoggingDirectory = "Quest/";
}
