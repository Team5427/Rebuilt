package team5427.frc.robot.commands.indexer;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.indexer.IndexerConstants;
import team5427.frc.robot.subsystems.indexer.IndexerSubsystem;

public class IndexerEject extends Command {
  private IndexerSubsystem indexerSubsystem;

  public IndexerEject() {
    indexerSubsystem = IndexerSubsystem.getInstance();
    addRequirements(indexerSubsystem);
  }

  @Override
  public void initialize() {
    indexerSubsystem.periodic();
  }

  @Override
  public void execute() {
    indexerSubsystem.setIndexerVelocitySetpoint(
        IndexerConstants.kIndexerStowedVelocity.plus(MetersPerSecond.of(-2.0)));
    indexerSubsystem.setHopperVelocitySetpoint(MetersPerSecond.of(-2.0));
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
