import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

import static edu.wpi.first.units.Units.MetersPerSecond;

import org.junit.jupiter.api.Test;
import team5427.frc.robot.FutureTrack;
import team5427.frc.robot.commands.shooting.MoveWhileShoot;
import team5427.lib.detection.tuples.Tuple2Plus;

public class ImpartedVelocityTest {

  @Test
  public void impartedVelocity() {
    MoveWhileShoot shoot = new MoveWhileShoot();
    shoot.setFutureTrackResult(new Tuple2Plus<Pose2d,ChassisSpeeds>(Pose2d.kZero, new ChassisSpeeds(0,0,0)));
    System.out.println(shoot.calculateImpartedVelocity(new Rotation2d(Math.PI/4.0), MetersPerSecond.of(1.0)));
    
  }
}

