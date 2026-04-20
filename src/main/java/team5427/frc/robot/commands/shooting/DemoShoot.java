package team5427.frc.robot.commands.shooting;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;

public class DemoShoot extends Command {
  private ShooterSubsystem shooterSubsystem;

  public DemoShoot() {
    shooterSubsystem = ShooterSubsystem.getInstance();
    addRequirements(shooterSubsystem);
  }

  @Override
  public void execute() {
    shooterSubsystem.setLeftShooterSpeed(ShooterConstants.kDemoShootingVelocity);
    shooterSubsystem.setRightShooterSpeed(ShooterConstants.kDemoShootingVelocity);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.setLeftShooterSpeed(MetersPerSecond.of(0.0));
    shooterSubsystem.setRightShooterSpeed(MetersPerSecond.of(0.0));
  }
}
