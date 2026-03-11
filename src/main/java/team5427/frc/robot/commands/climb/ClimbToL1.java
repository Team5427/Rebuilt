package team5427.frc.robot.commands.climb;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.climb.ClimbConstants;
import team5427.frc.robot.subsystems.climb.ClimbSubsystem;

public class ClimbToL1 extends Command {

  ClimbSubsystem instance;

  public ClimbToL1() {
    instance = ClimbSubsystem.getInstance();
    addRequirements(instance);
  }

  @Override
  public void execute() {
    instance.setPosition(ClimbConstants.kL1Angle);
    instance.setPosition(Rotation2d.kZero);
  }

  @Override
  public void end(boolean interrupted) {
    instance.setPosition(Rotation2d.kZero);
  }
}
