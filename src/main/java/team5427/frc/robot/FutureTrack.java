package team5427.frc.robot;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import lombok.Setter;
import team5427.lib.detection.tuples.Tuple2Plus;

public class FutureTrack {
  private MedianFilter xAccelerationFilter;
  private MedianFilter yAccelerationFilter;
  private MedianFilter omegaAccelerationFilter;
  @Setter private ChassisSpeeds currentRobotSpeed;

  @Setter private ChassisSpeeds wantedRobotSpeed;
  @Setter private Pose2d robotPose;
  private Tuple2Plus<Pose2d, ChassisSpeeds> futurePose =
      new Tuple2Plus<>(Pose2d.kZero, new ChassisSpeeds());

  private static int kSampleCounts = 5;

  private static FutureTrack m_instance = null;

  public void update() {
    ChassisSpeeds accel = wantedRobotSpeed.minus(currentRobotSpeed).div(Constants.kLoopSpeed);

    double xAccel = xAccelerationFilter.calculate(accel.vxMetersPerSecond);
    double yAccel = yAccelerationFilter.calculate(accel.vyMetersPerSecond);
    double omegaAccel = omegaAccelerationFilter.calculate(accel.omegaRadiansPerSecond);

    ChassisSpeeds estimatedSpeeds =
        new ChassisSpeeds(
            currentRobotSpeed.vxMetersPerSecond + xAccel * Constants.kLoopSpeed,
            currentRobotSpeed.vyMetersPerSecond + yAccel * Constants.kLoopSpeed,
            currentRobotSpeed.omegaRadiansPerSecond + omegaAccel * Constants.kLoopSpeed);

    futurePose =
        new Tuple2Plus<>(
            robotPose.exp(estimatedSpeeds.toTwist2d(Constants.kLoopSpeed)), estimatedSpeeds);
  }

  public static FutureTrack getInstance() {
    if (m_instance == null) {
      m_instance = new FutureTrack();
    }
    return m_instance;
  }

  private FutureTrack() {
    xAccelerationFilter = new MedianFilter(kSampleCounts);
    yAccelerationFilter = new MedianFilter(kSampleCounts);
    omegaAccelerationFilter = new MedianFilter(kSampleCounts);
    currentRobotSpeed = new ChassisSpeeds();
    wantedRobotSpeed = new ChassisSpeeds();
    robotPose = Pose2d.kZero;
  }

  public Pose2d getFutureTrackPose() {
    return futurePose.r;
  }

  public ChassisSpeeds getFutureTrackSpeeds() {
    return futurePose.t;
  }

  public Tuple2Plus<Pose2d, ChassisSpeeds> getFutureTrackResult() {
    return futurePose;
  }
}
