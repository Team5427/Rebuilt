package team5427.frc.robot.commands.intake;

import static edu.wpi.first.units.Units.Volt;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.intake.IntakeSubsystem;

public class IntakeHome extends Command {
  IntakeSubsystem intakeSubsystem;

  public IntakeHome() {
    intakeSubsystem = IntakeSubsystem.getInstance();
    addRequirements(intakeSubsystem);
  }

  @Override
  public void execute() {
    intakeSubsystem.setIntakingVoltage(Volt.of(-2.5));
  }

  @Override
  public boolean isFinished() {
    return intakeSubsystem.isPivotSpeedZero();
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.resetPivotMotorPosition(Rotation2d.kZero);
  }
}
