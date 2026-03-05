package team5427.frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.intake.IntakeConstants;
import team5427.frc.robot.subsystems.intake.IntakeSubsystem;

public class IntakeOscillating extends Command {
  private IntakeSubsystem subsystem;

  public IntakeOscillating() {
    subsystem = IntakeSubsystem.getInstance();
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (subsystem.getIntakingRotation2d().getDegrees()
        == IntakeConstants.kPivotIntakeRotation.getDegrees()) {
      subsystem.setIntakingRotation(IntakeConstants.kPivotMiddlePointRotation);
    } else {
      subsystem.setIntakingRotation(IntakeConstants.kPivotIntakeRotation);
    }
    // subsystem.simulateIntaking(true);
  }

  @Override
  public boolean isFinished() {
    return false; // change this for the method that you did for Hw that finds out if the game piece
    // is intaked
  }

  @Override
  public void end(boolean interrupted) {}
}
