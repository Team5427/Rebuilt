package team5427.frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
    // if (Superstructure.intakeStateIs(IntakeStates.STOWED).getAsBoolean()) {
    //   Superstructure.setIntakeStateCommand(IntakeStates.INTAKENEUTRAL);
    //   subsystem.setIntakingRotation(IntakeConstants.kPivotIntakeRotation);

    // } else if(Superstructure.intakeStateIs(IntakeStates.INTAKENEUTRAL).getAsBoolean()) {
    //   Superstructure.setIntakeStateCommand(IntakeStates.STOWED);
    //   subsystem.setIntakingRotation(IntakeConstants.kPivotIntakeRotation.div(2.0));
    // }
    // subsystem.simulateIntaking(true);
    subsystem.setIntakingRotation(IntakeConstants.kPivotStartingRotation);
    new WaitCommand(0.3);
    subsystem.setIntakingRotation(IntakeConstants.kPivotIntakeRotation.div(2.0));
    new WaitCommand(0.3);
  }

  @Override
  public boolean isFinished() {
    return false; // change this for the method that you did for Hw that finds out if the game piece
    // is intaked
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.setIntakingRotation(IntakeConstants.kPivotStartingRotation);
  }
}
