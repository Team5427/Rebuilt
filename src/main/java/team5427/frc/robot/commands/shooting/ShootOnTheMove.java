package team5427.frc.robot.commands.shooting;

import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.team4206.battleaid.common.TunedJoystick;
import org.team4206.battleaid.common.TunedJoystick.ResponseCurve;
import team5427.frc.robot.Constants.DriverConstants;
import team5427.frc.robot.FieldConstants;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.subsystems.Swerve.DrivingConstants;
import team5427.frc.robot.subsystems.Swerve.SwerveSubsystem;
import team5427.frc.robot.subsystems.shooter.AimingConstants;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;
import team5427.lib.kinematics.shooter.ShotCalculator;

public class ShootOnTheMove extends Command {
  private SwerveSubsystem swerveSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private CommandXboxController joy;
  private TunedJoystick translationJoystick;
  private Translation2d hubCenter;
  private Translation2d hubForward;

  public ShootOnTheMove(CommandXboxController joy) {
    swerveSubsystem = SwerveSubsystem.getInstance();
    shooterSubsystem = ShooterSubsystem.getInstance();
    this.joy = joy;
    translationJoystick = new TunedJoystick(joy.getHID());
    translationJoystick.useResponseCurve(ResponseCurve.LINEAR);
    translationJoystick.setDeadzone(DriverConstants.kDriverControllerJoystickDeadzone);
    hubCenter = FieldConstants.Hub.innerCenterPoint.toTranslation2d();
    hubForward = new Translation2d(1, 0); // which way the hub faces
    addRequirements(swerveSubsystem, shooterSubsystem);
  }

  @Override
  public void execute() {

    double vx = 0;
    double vy = 0;
    if (DriverStation.isTeleop()) {
      vx = -translationJoystick.getLeftY();
      vy = -translationJoystick.getLeftX();

      if (DriverStation.getAlliance().isPresent()
          && DriverStation.getAlliance().get().equals(Alliance.Red)) {
        vx *= -1;
        vy *= -1;
      }
    }

    ShotCalculator.ShotInputs inputs =
        new ShotCalculator.ShotInputs(
            RobotPose.getInstance().getAdaptivePose(),
            swerveSubsystem.getCurrentFieldChassisSpeeds(),
            swerveSubsystem.getCurrentChassisSpeeds(),
            hubCenter,
            hubForward,
            0.9, // vision confidence, 0 to 1
            swerveSubsystem.getGyroRotation3d().getY(), // pitch for tilt gate (0.0 if no gyro)
            swerveSubsystem.getGyroRotation3d().getX() // roll for tilt gate (0.0 if no gyro)
            );

    ShotCalculator.LaunchParameters shot = AimingConstants.shotCalc.calculate(inputs);
    if (shot.isValid() && shot.confidence() > 50) {
      AngularVelocity vel = RPM.of(shot.rpm());
      shooterSubsystem.setLeftShooterSpeed(vel);
      shooterSubsystem.setRightShooterSpeed(vel);
      ChassisSpeeds driveSpeeds = new ChassisSpeeds();
      if (DriverStation.isTeleop()) {

        double dampener = (joy.getRightTriggerAxis() * DrivingConstants.kDampenerDampeningAmount);

        driveSpeeds = swerveSubsystem.getDriveSpeeds(vx, vy, shot.driveAngle(), dampener);

        if (joy.getLeftTriggerAxis() >= 0.1) {
          driveSpeeds = new ChassisSpeeds(0, 0, 0);
        }
      } else {
        driveSpeeds = swerveSubsystem.getDriveSpeeds(vx, vy, shot.driveAngle(), 0);
      }
      driveSpeeds.omegaRadiansPerSecond += shot.driveAngularVelocityRadPerSec();
      swerveSubsystem.setInputSpeeds(driveSpeeds);
    }
  }
}
