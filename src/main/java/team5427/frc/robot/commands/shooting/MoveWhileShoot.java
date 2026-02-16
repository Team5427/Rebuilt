package team5427.frc.robot.commands.shooting;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.littletonrobotics.junction.Logger;
import org.team4206.battleaid.common.TunedJoystick;
import org.team4206.battleaid.common.TunedJoystick.ResponseCurve;
import team5427.frc.robot.Constants.DriverConstants;
import team5427.frc.robot.FieldConstants;
import team5427.frc.robot.FutureTrack;
import team5427.frc.robot.subsystems.Swerve.DrivingConstants;
import team5427.frc.robot.subsystems.Swerve.SwerveSubsystem;
import team5427.frc.robot.subsystems.shooter.AimingConstants;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;
import team5427.lib.detection.tuples.Tuple2Plus;

public class MoveWhileShoot extends Command {
  private SwerveSubsystem swerveSubsystem;
  private ShooterSubsystem shooter;
  private CommandXboxController joy;

  private TunedJoystick translationJoystick;

  private FutureTrack futureTrack;

  private Translation3d target = Translation3d.kZero;

  private Tuple2Plus<Pose2d, ChassisSpeeds> futureTrackResult;

  private boolean isRed;

  private static final int kMaxConvergenceIterations = 10;

  private static final double kTofConvergenceThreshold = 0.01;

  public MoveWhileShoot(CommandXboxController driverJoystick) {
    swerveSubsystem = SwerveSubsystem.getInstance();
    shooter = ShooterSubsystem.getInstance();
    joy = driverJoystick;

    translationJoystick = new TunedJoystick(joy.getHID());
    translationJoystick.useResponseCurve(ResponseCurve.LINEAR);
    translationJoystick.setDeadzone(DriverConstants.kDriverControllerJoystickDeadzone);

    futureTrack = FutureTrack.getInstance();
    futureTrackResult = futureTrack.getFutureTrackResult();

    addRequirements(swerveSubsystem, shooter);
  }

  public MoveWhileShoot() {}

  @Override
  public void initialize() {
    futureTrackResult = futureTrack.getFutureTrackResult();
    isRed =
        DriverStation.getAlliance().isPresent()
            && DriverStation.getAlliance().get() == Alliance.Red;
    target = isRed ? FieldConstants.Hub.oppTopCenterPoint : FieldConstants.Hub.topCenterPoint;
  }

  @Override
  public void execute() {
    if (DriverStation.isTeleop()) {
      double vx = -translationJoystick.getRightY();
      double vy = -translationJoystick.getRightX();

      if (isRed) {
        vx *= -1;
        vy *= -1;
      }

      double dampener = (joy.getRightTriggerAxis() * DrivingConstants.kDampenerDampeningAmount);

      futureTrackResult = futureTrack.getFutureTrackResult();

      Pose2d futurePose = futureTrackResult.r;
      ChassisSpeeds currentSpeeds =
          ChassisSpeeds.fromRobotRelativeSpeeds(
              swerveSubsystem.getCurrentChassisSpeeds(), futurePose.getRotation());

      Translation3d shooterFieldPos =
          ShooterConstants.kRobotToShooterTransform
              .getTranslation()
              .plus(new Translation3d(futurePose.getTranslation()));

      Translation3d virtualTarget = target;
      double tof = 0.0;
      double prevTof = Double.MAX_VALUE;

      for (int i = 0; i < kMaxConvergenceIterations; i++) {
        double distance = virtualTarget.minus(shooterFieldPos).getNorm();

        tof = AimingConstants.kShootingTable.getTimeOfFlight(distance);

        if (Math.abs(tof - prevTof) < kTofConvergenceThreshold) {
          break;
        }
        prevTof = tof;

        double driftX = currentSpeeds.vxMetersPerSecond * tof;
        double driftY = currentSpeeds.vyMetersPerSecond * tof;

        virtualTarget =
            new Translation3d(target.getX() - driftX, target.getY() - driftY, target.getZ());
      }

      double finalDistance = virtualTarget.minus(shooterFieldPos).getNorm();
      Rotation2d shooterAngle =
          Rotation2d.fromDegrees(AimingConstants.kShootingTable.getPivotAngle(finalDistance));
      LinearVelocity shooterVelocity =
          MetersPerSecond.of(AimingConstants.kShootingTable.getFlyWheelSpeed(finalDistance));

      shooter.setShooterAngle(shooterAngle);
      shooter.setShooterSpeed(shooterVelocity);

      Translation2d shooterFieldPos2d = shooterFieldPos.toTranslation2d();
      Translation2d virtualTarget2d = virtualTarget.toTranslation2d();
      Translation2d toTarget = virtualTarget2d.minus(shooterFieldPos2d);
      Rotation2d targetHeading = toTarget.getAngle().plus(Rotation2d.k180deg);

      ChassisSpeeds driveSpeeds = swerveSubsystem.getDriveSpeeds(vx, vy, targetHeading, dampener);

      if (joy.getLeftTriggerAxis() >= 0.1) {
        driveSpeeds = new ChassisSpeeds(0, 0, 0);
      }
      swerveSubsystem.setInputSpeeds(driveSpeeds);

      Logger.recordOutput(
          "MoveWhileShoot/VirtualTarget", new Pose2d(virtualTarget2d, targetHeading));
      Logger.recordOutput(
          "MoveWhileShoot/ShooterFieldPos", new Pose2d(shooterFieldPos2d, targetHeading));
      Logger.recordOutput("MoveWhileShoot/Distance", finalDistance);
      Logger.recordOutput("MoveWhileShoot/TOF", tof);
      Logger.recordOutput("MoveWhileShoot/PivotAngleDeg", shooterAngle.getDegrees());
      Logger.recordOutput("MoveWhileShoot/FlywheelSpeedMps", shooterVelocity.in(MetersPerSecond));
      Logger.recordOutput("MoveWhileShoot/TargetHeadingDeg", targetHeading.getDegrees());
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

  public void setFutureTrackResult(Tuple2Plus<Pose2d, ChassisSpeeds> result) {
    this.futureTrackResult = result;
  }

  public Translation3d calculateImpartedVelocity(
      Rotation2d kShootingAngle, LinearVelocity shootingVelocity) {
    Pose3d ballPose =
        new Pose3d(
            ShooterConstants.kRobotToShooterTransform
                .getTranslation()
                .plus(new Translation3d(futureTrackResult.r.getTranslation())),
            new Rotation3d(futureTrackResult.r.getRotation())
                .plus(ShooterConstants.kRobotToShooterTransform.getRotation()));

    Translation3d impartedVelocity =
        new Translation3d(
            shootingVelocity.in(MetersPerSecond),
            ballPose.getRotation().plus(new Rotation3d(0, kShootingAngle.getRadians(), 0)));

    double tangentialSpeed =
        futureTrackResult.t.omegaRadiansPerSecond
            * ShooterConstants.kRobotToShooterTransform.getTranslation().getNorm();

    return impartedVelocity.plus(
        new Translation3d(
            tangentialSpeed + futureTrackResult.t.vxMetersPerSecond,
            tangentialSpeed + futureTrackResult.t.vyMetersPerSecond,
            0));
  }
}
