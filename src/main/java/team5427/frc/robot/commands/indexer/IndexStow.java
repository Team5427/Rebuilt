package team5427.frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.frc.robot.subsystems.indexer.IndexerSubsystem;

public class IndexStow extends Command {
  private IndexerSubsystem indexerSubsystem;

  public IndexStow() {
    indexerSubsystem = IndexerSubsystem.getInstance();
  }

  @Override
  public void execute() {
    indexerSubsystem.setLeftIndexerVelocitySetpoint(IndexerConstants.kIndexerStowedVelocity);
    indexerSubsystem.setRightIndexerVelocitySetpoint(IndexerConstants.kIndexerStowedVelocity);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
