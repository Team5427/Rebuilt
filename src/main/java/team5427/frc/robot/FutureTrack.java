package team5427.frc.robot;

import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import java.util.function.Supplier;
import lombok.Setter;
import org.littletonrobotics.junction.Logger;
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
  private Supplier<ChassisSpeeds> currentSpeedSupplier;
  private Supplier<ChassisSpeeds> wantedSpeedSupplier;
  private static int kSampleCounts = 3;

  private static FutureTrack m_instance = null;

  public void update() {
    wantedRobotSpeed = wantedSpeedSupplier.get();
    currentRobotSpeed = currentSpeedSupplier.get();
    robotPose = RobotPose.getInstance().getAdaptivePose();
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

  public void log() {
    Logger.recordOutput("FutureTrack/pose", futurePose.r);
    Logger.recordOutput("FutureTrack/speeds", futurePose.t);
    Logger.recordOutput("FutureTrack/wantedSpeeds", wantedRobotSpeed);
    Logger.recordOutput("FutureTrack/currentSpeeds", currentRobotSpeed);
  }

  public static FutureTrack getInstance() {
    return m_instance;
  }

  public static FutureTrack getInstance(
      Supplier<ChassisSpeeds> currentSpeedSupplier, Supplier<ChassisSpeeds> wantedSpeedSupplier) {
    if (m_instance == null) {
      m_instance = new FutureTrack(currentSpeedSupplier, wantedSpeedSupplier);
    }
    return m_instance;
  }

  private FutureTrack(
      Supplier<ChassisSpeeds> currentSpeedSupplier, Supplier<ChassisSpeeds> wantedSpeedSupplier) {
    xAccelerationFilter = new MedianFilter(kSampleCounts);
    yAccelerationFilter = new MedianFilter(kSampleCounts);
    omegaAccelerationFilter = new MedianFilter(kSampleCounts);
    this.currentSpeedSupplier = currentSpeedSupplier;
    this.wantedSpeedSupplier = wantedSpeedSupplier;
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
