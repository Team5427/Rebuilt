import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import org.junit.jupiter.api.Test;
import team5427.frc.robot.FutureTrack;

public class FutureTrackTest {

  @Test
  public void trackPose() {
    FutureTrack.getInstance().setCurrentRobotSpeed(new ChassisSpeeds(0, 0, 0));
    FutureTrack.getInstance().setRobotPose(Pose2d.kZero);
    FutureTrack.getInstance().setWantedRobotSpeed(new ChassisSpeeds(1, 1, 1));
    Pose2d pose = FutureTrack.getInstance().getFutureTrackPose();
    System.out.println(pose);
    // assertEquals(Pose2d.kZero, pose);
  }
}
