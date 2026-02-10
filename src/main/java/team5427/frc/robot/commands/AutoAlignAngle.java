package team5427.frc.robot.commands;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import team5427.frc.robot.Constants;
import team5427.frc.robot.FieldConstants;
import team5427.frc.robot.FutureTrack;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.subsystems.Swerve.DrivingConstants;
import team5427.frc.robot.subsystems.Swerve.SwerveSubsystem;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;

public class AutoAlignAngle extends Command {
  RobotPose pose;
  FutureTrack futureTrack;
  SwerveSubsystem swerveSubsystem;

  public AutoAlignAngle() {
    pose = RobotPose.getInstance();
    futureTrack = FutureTrack.getInstance();
    swerveSubsystem = SwerveSubsystem.getInstance();
  }

  @Override
  public void initialize() {
    Translation2d hubTranslation = FieldConstants.Hub.innerCenterPoint.toTranslation2d();
    Pose2d futureTrackPose = futureTrack.getFutureTrackPose(Constants.kLoopSpeed);
    double flightTime =
        ShooterConstants.flightTimes.gettimeOfFlight(
            hubTranslation
                .plus(
                    new Translation2d(
                            futureTrackPose.getTranslation().getX(),
                            futureTrackPose.getTranslation().getY())
                        .rotateBy(Rotation2d.k180deg))
                .getDistance(pose.getAdaptivePose().getTranslation()));
    Angle deltaTheta =
        pose.getAdaptivePose()
            .getRotation()
            .getMeasure()
            .minus(
                Radians.of(
                    Math.atan(
                        (hubTranslation.getY()
                                - futureTrackPose.getY() * flightTime
                                - pose.getAdaptivePose().getY())
                            / (hubTranslation.getX()
                                - futureTrackPose.getX() * flightTime
                                - pose.getAdaptivePose().getX()))));
    swerveSubsystem.setInputSpeeds(
        new ChassisSpeeds(
            swerveSubsystem.getCurrentChassisSpeeds().vxMetersPerSecond,
            swerveSubsystem.getCurrentChassisSpeeds().vyMetersPerSecond,
            DrivingConstants.kRotationController.calculate(deltaTheta.in(Radians))));
  }
}
