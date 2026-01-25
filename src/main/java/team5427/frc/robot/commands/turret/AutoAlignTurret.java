package team5427.frc.robot.commands.turret;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import team5427.frc.robot.subsystems.turret.TurretSubsystem;
import team5427.frc.robot.subsystems.vision.VisionSubsystem;

public class AutoAlignTurret extends Command {
  private TurretSubsystem turretSubsystem;
  private VisionSubsystem visionSubsystem;

  private Pose2d targetPose;
  private Pose3d aprilTagPose;

  public AutoAlignTurret() {
    turretSubsystem = TurretSubsystem.getInstance();
    visionSubsystem = VisionSubsystem.getInstance();
    addRequirements((Subsystem) turretSubsystem, (Subsystem) visionSubsystem);
  }

  @Override
  public void initialize() {
    turretSubsystem.setPivotRotation(null, targetPose);
    Distance distanceToPose = turretSubsystem.getDiagonalDistance(targetPose);
    turretSubsystem.setTurretRotation(distanceToPose, aprilTagPose);
  }

  @Override
  public boolean isFinished() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    // TODO Auto-generated method stub
    super.end(interrupted);
  }
}
