package team5427.frc.robot.commands.chassis;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.Constants;
import team5427.frc.robot.RobotPose;

public class ResetChassisPose extends Command {
    @Override
    public void initialize() {
        switch (DriverStation.getAlliance().get()) {
            case Blue:
                RobotPose.getInstance().resetAllPose(new Pose2d(0.701, 0.432, new Rotation2d(Degrees.of(180))));
                break;
        
            default:
                RobotPose.getInstance().resetAllPose(new Pose2d(15.826, 7.625, new Rotation2d(Degrees.of(0))));
                break;
        }
    }
}
