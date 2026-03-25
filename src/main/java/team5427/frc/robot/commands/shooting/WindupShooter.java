package team5427.frc.robot.commands.shooting;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.FieldConstants;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.subsystems.shooter.AimingConstants;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;

public class WindupShooter extends Command {
  private ShooterSubsystem shooter;
  private Translation3d target;
  private boolean isRed;

  public WindupShooter(Translation3d target) {
    shooter = ShooterSubsystem.getInstance();
    this.target = target;
    // addRequirements(shooter);
  }

  public WindupShooter() {
    shooter = ShooterSubsystem.getInstance();
    isRed =
        DriverStation.getAlliance().isPresent()
            && DriverStation.getAlliance().get() == Alliance.Red;
    target = isRed ? FieldConstants.Hub.oppTopCenterPoint : FieldConstants.Hub.topCenterPoint;
    System.out.println("Called");
    // addRequirements(shooter);
  }

  @Override
  public void execute() {
    LinearVelocity vel =
        MetersPerSecond.of(
            AimingConstants.kShootingTable.getFlyWheelSpeed(
                RobotPose.getInstance()
                    .getAdaptivePose()
                    .getTranslation()
                    .minus(target.toTranslation2d())
                    .getNorm()));
    shooter.setLeftShooterSpeed(vel);
    shooter.setRightShooterSpeed(vel);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
