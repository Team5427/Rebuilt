package team5427.frc.robot.commands.chassis;

import com.pathplanner.lib.pathfinding.Pathfinding;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.RobotPose;

public class PathFindToPose extends Command {
  Pose2d targetPose;

  public PathFindToPose(Pose2d targetPose) {
    this.targetPose = targetPose;
  }

  @Override
  public void initialize() {
    Pathfinding.ensureInitialized();

    Pathfinding.setStartPosition(RobotPose.getInstance().getAdaptivePose().getTranslation());
    Pathfinding.setGoalPosition(targetPose.getTranslation());
  }
}
