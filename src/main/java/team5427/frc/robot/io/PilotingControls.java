package team5427.frc.robot.io;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.Constants.DriverConstants;
import team5427.frc.robot.FieldConstants;
import team5427.frc.robot.RobotPose;
import team5427.frc.robot.Superstructure;
import team5427.frc.robot.Superstructure.ShooterStates;
import team5427.frc.robot.Superstructure.SwerveStates;
import team5427.frc.robot.commands.chassis.ChassisMovementUnderTower;
import team5427.frc.robot.commands.chassis.ChassisMovementUnderTrench;
import team5427.frc.robot.commands.chassis.ControlledChassisMovement;
import team5427.frc.robot.commands.chassis.RawChassisMovement;
import team5427.frc.robot.commands.shooting.MoveWhileFerry;
import team5427.frc.robot.commands.shooting.MoveWhileShoot;
import team5427.frc.robot.commands.shooting.WindupShooter;
import team5427.frc.robot.io.DriverProfiles.DriverState;
import team5427.frc.robot.subsystems.Swerve.SwerveSubsystem;
import team5427.frc.robot.subsystems.vision.io.QuestNav;

public class PilotingControls {
  private CommandXboxController joy;
  private Trigger autonTrigger;
  private Trigger disabledTrigger;

  public PilotingControls() {
    joy = new CommandXboxController(DriverConstants.kDriverJoystickPort);
    initalizeTriggers();
  }

  public PilotingControls(CommandXboxController joy) {
    this.joy = joy;
    initalizeTriggers();
  }

  /** Made private to prevent multiple calls to this method */
  private void initalizeTriggers() {

    disabledTrigger = new Trigger(DriverStation::isDisabled);
    autonTrigger = new Trigger(DriverStation::isAutonomous);

    disabledTrigger.onTrue(Superstructure.fullResetAllStates().ignoringDisable(true));

    // Swerve State Control Bindings

    // Toggle controlled driving with left bumper
    DriverProfiles.DriverTriggers.kIsState(DriverState.A_E)
        .and(joy.leftBumper())
        .toggleOnTrue(Superstructure.setSwerveStateCommand(SwerveStates.CONTROLLED_DRIVING))
        .toggleOnFalse(Superstructure.setSwerveStateCommand(SwerveStates.RAW_DRIVING));

    // Toggle auto align with right bumper
    DriverProfiles.DriverTriggers.kIsState(DriverState.TEST_DUAL)
        .and(joy.rightBumper())
        .toggleOnTrue(Superstructure.setSwerveStateCommand(SwerveStates.AUTO_ALIGN))
        .toggleOnFalse(Superstructure.setSwerveStateCommand(SwerveStates.CONTROLLED_DRIVING));

    joy.a()
        .and(autonTrigger.negate())
        .and(disabledTrigger.negate())
        .whileTrue(Superstructure.setSwerveStateCommand(SwerveStates.AUTO_ALIGN))
        .toggleOnFalse(Superstructure.setSwerveStateCommand(SwerveStates.RAW_DRIVING));

    // joy.b()
    //     .whileTrue(
    //
    // WheelRadiusCharacterization.wheelRadiusCharacterization(SwerveSubsystem.getInstance()));

    joy.rightBumper()
        .whileTrue(Superstructure.setSwerveStateCommand(SwerveStates.AUTO_TARGETING))
        .whileTrue(Superstructure.setShooterStateCommand(ShooterStates.AUTO_ALIGN_SHOOTING))
        .onFalse(Superstructure.setSwerveStateCommand(SwerveStates.RAW_DRIVING))
        .onFalse(Superstructure.setShooterStateCommand(ShooterStates.STOWED));

    joy.leftBumper()
        .whileTrue(Superstructure.setSwerveStateCommand(SwerveStates.AUTO_TARGETING))
        .onFalse(Superstructure.setSwerveStateCommand(SwerveStates.RAW_DRIVING))
        .onFalse(Superstructure.setShooterStateCommand(ShooterStates.STOWED));

    // Auto mode state management
    autonTrigger
        .onTrue(Superstructure.setSwerveStateCommand(SwerveStates.AUTON))
        .onFalse(Superstructure.setSwerveStateCommand(SwerveStates.RAW_DRIVING));

    // Disabled mode state management
    disabledTrigger.onTrue(Superstructure.setSwerveStateCommand(SwerveStates.DISABLED));

    disabledTrigger
        .negate()
        .and(autonTrigger.negate())
        .onTrue(Superstructure.setSwerveStateCommand(SwerveStates.RAW_DRIVING));

    // State Based Command Bindings

    // Raw driving mode
    Superstructure.swerveStateIs(SwerveStates.RAW_DRIVING)
        .and(autonTrigger.negate())
        .and(disabledTrigger.negate())
        .whileTrue(new RawChassisMovement(joy));

    // Controlled driving mode
    Superstructure.swerveStateIs(SwerveStates.CONTROLLED_DRIVING)
        .and(autonTrigger.negate())
        .and(disabledTrigger.negate())
        .whileTrue(new ControlledChassisMovement(joy));

    // Auto align mode
    // Superstructure.swerveStateIs(SwerveStates.AUTO_ALIGN)
    //     .and(disabledTrigger.negate()).and(autonTrigger)
    //     .whileTrue(new MoveChassisToPose(joy, ));

    // Auto Targetting Mode

    Superstructure.swerveStateIs(SwerveStates.AUTO_TARGETING)
        .and(Superstructure.shooterStateIs(ShooterStates.AUTO_ALIGN_SHOOTING))
        .and(disabledTrigger.negate())
        .whileTrue(new MoveWhileShoot(joy));

    Superstructure.swerveStateIs(SwerveStates.AUTO_TARGETING)
        .and(Superstructure.shooterStateIs(ShooterStates.AUTO_ALIGN_SHOOTING).negate())
        .and(
            new Trigger(
                () -> {
                  return RobotPose.getInstance().getAdaptivePose().getX() >= 5.15
                      && RobotPose.getInstance().getAdaptivePose().getX() <= 11.32;
                }))
        .and(disabledTrigger.negate())
        .whileTrue(Superstructure.setShooterStateCommand(ShooterStates.FERRY_SHOOTING));

    Superstructure.swerveStateIs(SwerveStates.AUTO_TARGETING)
        .and(Superstructure.shooterStateIs(ShooterStates.FERRY_SHOOTING))
        .and(disabledTrigger.negate())
        .whileTrue(new MoveWhileFerry(joy));

    Superstructure.swerveStateIs(SwerveStates.AUTO_TARGETING)
        .and(Superstructure.shooterStateIs(ShooterStates.FERRY_SHOOTING).negate())
        .and(Superstructure.shooterStateIs(ShooterStates.AUTO_ALIGN_SHOOTING).negate())
        .and(
            new Trigger(
                () -> {
                double distanceToTrench =
                Math.min(
                    Math.min(
                        FieldConstants.LeftTrench.openingTopLeft
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation()),
                        FieldConstants.RightTrench.openingTopRight
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation())),
                    Math.min(
                        FieldConstants.LeftTrench.oppOpeningTopLeft
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation()),
                        FieldConstants.RightTrench.oppOpeningTopRight
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation())
                    )
                );

                double distanceToTower =
                Math.min(
                    FieldConstants.Tower.centerPoint.getDistance(
                        RobotPose.getInstance().getAdaptivePose().getTranslation()),
                    FieldConstants.Tower.oppCenterPoint.getDistance(
                        RobotPose.getInstance().getAdaptivePose().getTranslation()));
                  return distanceToTrench >= distanceToTower;
                }))
        .and(disabledTrigger.negate())
        .whileTrue(new ChassisMovementUnderTrench(joy));

    Superstructure.swerveStateIs(SwerveStates.AUTO_TARGETING)
        .and(Superstructure.shooterStateIs(ShooterStates.FERRY_SHOOTING).negate())
        .and(Superstructure.shooterStateIs(ShooterStates.AUTO_ALIGN_SHOOTING).negate())
        .and(
            new Trigger(
                () -> {
                  double distanceToTrench =
                Math.min(
                    Math.min(
                        FieldConstants.LeftTrench.openingTopLeft
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation()),
                        FieldConstants.RightTrench.openingTopRight
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation())),
                    Math.min(
                        FieldConstants.LeftTrench.oppOpeningTopLeft
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation()),
                        FieldConstants.RightTrench.oppOpeningTopRight
                            .toTranslation2d()
                            .getDistance(RobotPose.getInstance().getAdaptivePose().getTranslation())
                    )
                );

                double distanceToTower =
                Math.min(
                    FieldConstants.Tower.centerPoint.getDistance(
                        RobotPose.getInstance().getAdaptivePose().getTranslation()),
                    FieldConstants.Tower.oppCenterPoint.getDistance(
                        RobotPose.getInstance().getAdaptivePose().getTranslation()));

                Logger.recordOutput("Local/Trench", distanceToTrench);
                Logger.recordOutput("Local/Tower", distanceToTower);
                return distanceToTrench < distanceToTower;
                }))
        .and(disabledTrigger.negate())
        .whileTrue(new ChassisMovementUnderTower(joy));

    Superstructure.shooterStateIs(ShooterStates.WINDUP)
        .and(disabledTrigger.negate())
        .whileTrue(new WindupShooter());

    // Utility Bindings
    joy.a()
        .and(Constants.ModeTriggers.kSim)
        .onTrue(
            new InstantCommand(
                    () ->
                        QuestNav.getInstance()
                            .setPose(new Pose2d(10 * Math.random(), 4, Rotation2d.kZero)))
                .ignoringDisable(true));

    joy.y()
        .and(Constants.ModeTriggers.kSim)
        .onTrue(
            new InstantCommand(
                () -> {
                  Pose2d pose =
                      SwerveSubsystem.getInstance()
                          .getKDriveSimulation()
                          .getSimulatedDriveTrainPose();

                  SwerveSubsystem.getInstance().resetGyro(Rotation2d.kZero);

                  pose =
                      new Pose2d(
                          pose.getX(),
                          pose.getY(),
                          SwerveSubsystem.getInstance().getGyroRotation());
                  RobotPose.getInstance().resetHeading(Rotation2d.kZero);
                  SwerveSubsystem.getInstance().getKDriveSimulation().setSimulationWorldPose(pose);
                }));

    joy.y()
        .and(Constants.ModeTriggers.kReal)
        .onTrue(
            new InstantCommand(
                () -> {
                  SwerveSubsystem.getInstance()
                      .resetGyro(
                          DriverStation.getAlliance().isPresent()
                                  && DriverStation.getAlliance().get() == Alliance.Red
                              ? Rotation2d.k180deg
                              : Rotation2d.kZero);
                  RobotPose.getInstance()
                      .resetHeading(SwerveSubsystem.getInstance().getGyroRotation());
                }));
  }
}
