package team5427.frc.robot;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import lombok.Setter;

public class FutureTrack {
  private MedianFilter xAccelerationFilter;
  private MedianFilter yAccelerationFilter;
  private MedianFilter omegaAccelerationFilter;
  @Setter private ChassisSpeeds currentRobotSpeed;
  @Setter private ChassisSpeeds wantedRobotSpeed;
  @Setter private Pose2d robotPose;

  private static int kSampleCounts = 5;

  private static FutureTrack m_instance = null;

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

  /**
   * @param timeStep - the total number of loop cycles ahead to calculate by
   */
  public Pose2d getFutureTrackPose(double timeStep) {
    ChassisSpeeds averagedAccel =
        wantedRobotSpeed.minus(currentRobotSpeed).div(Constants.kLoopSpeed * timeStep);
    double xAccel = xAccelerationFilter.calculate(averagedAccel.vxMetersPerSecond);
    double yAccel = yAccelerationFilter.calculate(averagedAccel.vyMetersPerSecond);
    double omegaAccel = omegaAccelerationFilter.calculate(averagedAccel.omegaRadiansPerSecond);
    ChassisSpeeds estimatedSpeeds =
        new ChassisSpeeds(
            currentRobotSpeed.vxMetersPerSecond + xAccel * Constants.kLoopSpeed * timeStep,
            currentRobotSpeed.vyMetersPerSecond + yAccel * Constants.kLoopSpeed * timeStep,
            currentRobotSpeed.omegaRadiansPerSecond + omegaAccel * Constants.kLoopSpeed * timeStep);

    return robotPose.exp(estimatedSpeeds.toTwist2d(Constants.kLoopSpeed * timeStep));
  }

  /**
   * @return returns <strong>{@link #getFutureTrackPose(double)}</strong> with a timestep of 1
   */
  public Pose2d getFutureTrackPose() {
    return getFutureTrackPose(1);
  }
}
