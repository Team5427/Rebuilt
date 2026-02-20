package team5427.frc.robot.commands.shooting;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;

public class ChangeShooterHoodAngleAndSpeed extends Command {
  private ShooterSubsystem shooterSubsystem;
  private CommandXboxController joy;

  public ChangeShooterHoodAngleAndSpeed(CommandXboxController controller) {
    shooterSubsystem = ShooterSubsystem.getInstance();
    joy = controller;

    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (joy.povUp().getAsBoolean()) {
      shooterSubsystem.setLeftShooterSpeed(
          shooterSubsystem.getLeftShooterVelocity().plus(MetersPerSecond.of(0.1)));
    }

    if (joy.povDown().getAsBoolean()) {
      shooterSubsystem.setLeftShooterSpeed(
          shooterSubsystem.getLeftShooterVelocity().minus(MetersPerSecond.of(0.1)));
    }

    if (joy.povRight().getAsBoolean()) {
      shooterSubsystem.setLeftShooterAngle(
          shooterSubsystem.getLeftShooterAngle().plus(Rotation2d.fromDegrees(0.1)));
    }

    if (joy.povLeft().getAsBoolean()) {
      shooterSubsystem.setLeftShooterAngle(
          shooterSubsystem.getLeftShooterAngle().minus(Rotation2d.fromDegrees(0.1)));
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
