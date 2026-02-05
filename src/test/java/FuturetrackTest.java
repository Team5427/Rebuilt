import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import org.junit.jupiter.api.Test;
import team5427.frc.robot.Futuretrack;

public class FuturetrackTest {

  @Test
  public void trackPose() {
    Futuretrack.getInstance().setCurrentRobotSpeed(new ChassisSpeeds(0, 0, 0));
    Futuretrack.getInstance().setRobotPose(Pose2d.kZero);
    Futuretrack.getInstance().setWantedRobotSpeed(new ChassisSpeeds(1, 1, 1));
    Pose2d pose = Futuretrack.getInstance().getFuturetrackPose();
    System.out.println(pose);
    assertEquals(Pose2d.kZero, pose);
  }
}
