package team5427.frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.frc.robot.subsystems.indexer.IndexerSubsystem;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;

public class IndexShoot extends Command {
  private IndexerSubsystem indexerSubsystem;

  public IndexShoot() {
    indexerSubsystem = IndexerSubsystem.getInstance();
  }

  @Override
  public void execute() {
    indexerSubsystem.setIndexerVelocitySetpoint(
        ShooterSubsystem.getInstance().getCurrentLeftShooterSpeed());
    indexerSubsystem.setHopperVelocitySetpoint(
        ShooterSubsystem.getInstance().getCurrentLeftShooterSpeed());
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
