package team5427.frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.intake.IntakeConstants;
import team5427.frc.robot.subsystems.intake.IntakeSubsystem;

public class IntakeEject extends Command {
  private IntakeSubsystem subsystem;

  public IntakeEject() {
    subsystem = IntakeSubsystem.getInstance();
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    subsystem.setIntakingRotation(IntakeConstants.kPivotIntakeRotation);
    subsystem.setIntakingSpeed(IntakeConstants.kRollerNeutralVelocity.times(-1.0).times(2.0));
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
