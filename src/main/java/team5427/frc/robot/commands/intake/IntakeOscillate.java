package team5427.frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import team5427.frc.robot.Superstructure;
import team5427.frc.robot.Superstructure.IntakeStates;

public class IntakeOscillate {
  public static Command getIntakeOscillateCommand() {
    return (Superstructure.setIntakeStateCommand(IntakeStates.STOWED)
        .withDeadline(new WaitCommand(.5))
        .andThen(
            Superstructure.setIntakeStateCommand(IntakeStates.INTAKENEUTRAL)
                .withDeadline(new WaitCommand(.5)))
        .repeatedly());
  }
}
