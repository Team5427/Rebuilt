package team5427.frc.robot.commands.turret;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Rotations;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.Odometry;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.subsystems.turret.TurretConstants;
import team5427.frc.robot.subsystems.turret.TurretSubsystem;
import team5427.frc.robot.subsystems.vision.VisionSubsystem;
import team5427.lib.motors.SimpleSparkMax;

public class AutoAlignTurret extends Command {
  private TurretSubsystem turretSubsystem;
  private VisionSubsystem visionSubsystem;

  private Pose2d targetPose;

  public AutoAlignTurret() {
    turretSubsystem = TurretSubsystem.getInstance();
    visionSubsystem = VisionSubsystem.getInstance();
    addRequirements((Subsystem)turretSubsystem, (Subsystem)visionSubsystem);
  }

  @Override
  public void initialize() {
    turretSubsystem.setPivotRotation(null, targetPose);

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
