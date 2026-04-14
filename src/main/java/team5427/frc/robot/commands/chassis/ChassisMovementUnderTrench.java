package team5427.frc.robot.commands.chassis;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.team4206.battleaid.common.TunedJoystick;
import org.team4206.battleaid.common.TunedJoystick.ResponseCurve;
import team5427.frc.robot.Constants.DriverConstants;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.subsystems.Swerve.DrivingConstants;
import team5427.frc.robot.subsystems.Swerve.SwerveSubsystem;

public class ChassisMovementUnderTrench extends Command {

  private SwerveSubsystem swerveSubsystem;
  private CommandXboxController joy;

  private TunedJoystick translationJoystick;
  private TunedJoystick rotationJoystick;

  private boolean isRed;

  public ChassisMovementUnderTrench(CommandXboxController driverJoystick) {

    swerveSubsystem = SwerveSubsystem.getInstance();
    joy = driverJoystick;
    translationJoystick = new TunedJoystick(joy.getHID());
    translationJoystick.useResponseCurve(ResponseCurve.LINEAR);

    rotationJoystick = new TunedJoystick(joy.getHID());
    rotationJoystick.useResponseCurve(ResponseCurve.QUADRATIC);

    translationJoystick.setDeadzone(DriverConstants.kDriverControllerJoystickDeadzone);
    rotationJoystick.setDeadzone(DriverConstants.kDriverControllerJoystickDeadzone);
    addRequirements(swerveSubsystem);
  }

  @Override
  public void execute() {
    if (DriverStation.isTeleop()) {
      isRed =
          DriverStation.getAlliance().isPresent()
              && DriverStation.getAlliance().get() == Alliance.Red;
      double vx = -translationJoystick.getLeftY();
      double vy = -translationJoystick.getLeftX();
      Rotation2d nearestLockedRotation =
          RobotPose.getInstance().getAdaptivePose().getRotation().getRadians() > 0
              ? Rotation2d.fromRadians(Math.PI/2.0)
              : Rotation2d.fromRadians(-Math.PI/2.0);
      if (isRed) {
        vx *= -1;
        vy *= -1;
      }

      double dampener = (joy.getRightTriggerAxis() * DrivingConstants.kDampenerDampeningAmount);

      ChassisSpeeds driverSpeeds =
          swerveSubsystem.getDriveSpeeds(vx, vy, nearestLockedRotation, dampener).times(0.75);

      if (joy.getLeftTriggerAxis() >= 0.1) {
        driverSpeeds = new ChassisSpeeds(0, 0, 0);
      }
      swerveSubsystem.setInputSpeeds(driverSpeeds);
      System.out.println("Trech");
    } else {
      swerveSubsystem.setInputSpeeds(new ChassisSpeeds(0, 0, 0));
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    swerveSubsystem.setInputSpeeds(new ChassisSpeeds());
  }
}
