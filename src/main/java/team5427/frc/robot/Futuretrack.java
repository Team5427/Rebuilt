package team5427.frc.robot;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import lombok.Setter;

public class Futuretrack {
  private MedianFilter xAccelerationFilter;
  private MedianFilter yAccelerationFilter;
  private MedianFilter omegaAccelerationFilter;
  @Setter private ChassisSpeeds currentRobotSpeed;
  @Setter private ChassisSpeeds wantedRobotSpeed;
  @Setter private Pose2d robotPose;

  private static int kSampleCounts = 5;

  private static Futuretrack m_instance = null;

  public static Futuretrack getInstance() {
    if (m_instance == null) {
      m_instance = new Futuretrack();
    }
    return m_instance;
  }

  private Futuretrack() {
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
  public Pose2d getFuturetrackPose(double timeStep) {
    ChassisSpeeds averagedAccel =
        wantedRobotSpeed.minus(currentRobotSpeed).div(Constants.kLoopSpeed * timeStep);
    double xAccel = xAccelerationFilter.calculate(averagedAccel.vxMetersPerSecond);
    double yAccel = yAccelerationFilter.calculate(averagedAccel.vyMetersPerSecond);
    double omegaAccel = omegaAccelerationFilter.calculate(averagedAccel.omegaRadiansPerSecond);
    ChassisSpeeds estimatedSpeeds =
        new ChassisSpeeds(
            currentRobotSpeed.vxMetersPerSecond + xAccel * Constants.kLoopSpeed,
            currentRobotSpeed.vyMetersPerSecond + yAccel * Constants.kLoopSpeed,
            currentRobotSpeed.omegaRadiansPerSecond + omegaAccel * Constants.kLoopSpeed);

    return robotPose.exp(estimatedSpeeds.toTwist2d(Constants.kLoopSpeed));
  }

  /**
   * @return returns <strong>{@link #getFuturetrackPose(double)}</strong> with a timestep of 1
   */
  public Pose2d getFuturetrackPose() {
    return getFuturetrackPose(1);
  }
}
