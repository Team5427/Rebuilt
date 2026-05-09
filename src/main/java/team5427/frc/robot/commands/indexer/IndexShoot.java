package team5427.frc.robot.commands.indexer;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.FieldConstants;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.Superstructure;
import team5427.frc.robot.Superstructure.ShooterStates;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.frc.robot.subsystems.indexer.IndexerSubsystem;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;

public class IndexShoot extends Command {
  private IndexerSubsystem indexerSubsystem;

  public IndexShoot() {
    indexerSubsystem = IndexerSubsystem.getInstance();
    addRequirements(indexerSubsystem);
  }

  @Override
  public void initialize() {
    indexerSubsystem.periodic();
  }

  @Override
  public void execute() {
    double errorDegrees =
        Superstructure.shooterStateIs(ShooterStates.FERRY_SHOOTING).getAsBoolean()
            ? 0
            : RobotPose.getInstance().getAdaptivePose().getRotation().getDegrees()
                - IndexerSubsystem.getInstance().getTargetHeading().getDegrees();
    double errorMeters = errorDegrees * indexerSubsystem.getTargetDistance();

    // left errorMeters+0.224>0.6096 || <-0.6096

    indexerSubsystem.setLeftIndexerMotorVelocity(
        errorMeters - ShooterConstants.kShooterHorizontalOffsetMeters < FieldConstants.Hub.width / 2
                && errorMeters - ShooterConstants.kShooterHorizontalOffsetMeters
                    > -1 * FieldConstants.Hub.width / 2
            ? ShooterSubsystem.getInstance().getLeftShooterVelocity()
            : MetersPerSecond.of(0));
    indexerSubsystem.setRightIndexerMotorVelocity(
        errorMeters + ShooterConstants.kShooterHorizontalOffsetMeters < FieldConstants.Hub.width / 2
                && errorMeters + ShooterConstants.kShooterHorizontalOffsetMeters
                    > -1 * FieldConstants.Hub.width / 2
            ? ShooterSubsystem.getInstance().getLeftShooterVelocity()
            : MetersPerSecond.of(0));
    indexerSubsystem.setHopperVelocitySetpoint(
        ShooterSubsystem.getInstance().getRightShooterVelocity());
    // indexerSubsystem.setIndexerVelocitySetpoint(MetersPerSecond.of(-2.0));

    // indexerSubsystem.setHopperVelocitySetpoint(MetersPerSecond.of(-2.0));
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    indexerSubsystem.setIndexerVelocitySetpoint(IndexerConstants.kIndexerStowedVelocity);
    indexerSubsystem.setHopperVelocitySetpoint(IndexerConstants.kIndexerStowedVelocity);
  }
}
