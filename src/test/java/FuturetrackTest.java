import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import org.junit.jupiter.api.Test;
import team5427.frc.robot.Futuretrack;

public class FuturetrackTest {

  @Test
  public void trackPose() {
    Futuretrack.getInstance().setCurrentRobotSpeed(new ChassisSpeeds(1, 1, 1));
    Futuretrack.getInstance().setRobotPose(Pose2d.kZero);
    Futuretrack.getInstance().setWantedRobotSpeed(new ChassisSpeeds(2, 2, 2));
    System.out.println(Futuretrack.getInstance().getFuturetrackPose());
  }
}
