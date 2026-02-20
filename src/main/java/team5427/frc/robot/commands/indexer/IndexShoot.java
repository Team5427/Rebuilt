package team5427.frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.frc.robot.subsystems.indexer.IndexerSubsystem;

public class IndexShoot extends Command {
  private IndexerSubsystem indexerSubsystem;

  public IndexShoot() {
    indexerSubsystem = IndexerSubsystem.getInstance();
  }

  @Override
  public void execute() {
    indexerSubsystem.setLeftIndexerVelocitySetpoint(IndexerConstants.kIndexerIndexingVelocity);
    indexerSubsystem.setRightIndexerVelocitySetpoint(IndexerConstants.kIndexerIndexingVelocity);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    indexerSubsystem.setLeftIndexerVelocitySetpoint(IndexerConstants.kIndexerStowedVelocity);
    indexerSubsystem.setRightIndexerVelocitySetpoint(IndexerConstants.kIndexerStowedVelocity);
  }
}
