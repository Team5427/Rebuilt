package team5427.frc.robot.io;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import team5427.frc.robot.Constants.DriverConstants;
import team5427.frc.robot.Superstructure;
import team5427.frc.robot.Superstructure.IndexerStates;
import team5427.frc.robot.Superstructure.IntakeStates;
import team5427.frc.robot.Superstructure.ShooterStates;
import team5427.frc.robot.commands.indexer.IndexShoot;
import team5427.frc.robot.commands.indexer.IndexStow;
import team5427.frc.robot.commands.intake.IntakeHome;
import team5427.frc.robot.commands.intake.IntakeIntaking;
import team5427.frc.robot.commands.intake.IntakeNeutral;
import team5427.frc.robot.commands.intake.IntakeStowed;
import team5427.frc.robot.subsystems.shooter.ShooterConstants;
import team5427.frc.robot.subsystems.shooter.ShooterSubsystem;

public class OperatorControls {
  private CommandXboxController joy;

  public OperatorControls() {
    joy = new CommandXboxController(DriverConstants.kOperatorJoystickPort);
    initalizeTriggers();
  }

  public OperatorControls(CommandXboxController joy) {
    this.joy = joy;
    initalizeTriggers();
  }

  // lt changes roller velocity to intaking brings out intake
  // a resets everything
  // lb oscillates between out to stow, reverts to out

  /** Made private to prevent multiple calls to this method */
  private void initalizeTriggers() {
    // Use command factories instead of inline InstantCommands
    joy.leftTrigger()
        .whileTrue(Superstructure.setIntakeStateCommand(IntakeStates.INTAKING))
        .onFalse(Superstructure.setIntakeStateCommand(IntakeStates.INTAKENEUTRAL));
    joy.leftBumper()
        .whileTrue(
            (Superstructure.setIntakeStateCommand(IntakeStates.STOWED)
                .withDeadline(new WaitCommand(.5))
                .andThen(
                    Superstructure.setIntakeStateCommand(IntakeStates.INTAKENEUTRAL)
                        .withDeadline(new WaitCommand(.5)))
                .repeatedly()));
    // joy.povLeft().onTrue(Superstructure.setClimbStateCommand(ClimbStates.L1));
    // .onFalse(Superstructure.setIntakeStateCommand(IntakeStates.INTAKENEUTRAL));
    // joy.leftBumper()
    //     .whileTrue(
    //         Superstructure.intakeStateIs(IntakeStates.STOWED).getAsBoolean()
    //             ? Superstructure.setIntakeStateCommand(IntakeStates.INTAKENEUTRAL)
    //             : Superstructure.setIntakeStateCommand(IntakeStates.STOWED))
    //     .onFalse(Superstructure.setIntakeStateCommand(IntakeStates.INTAKENEUTRAL));
    // joy.rightBumper()
    //     .whileTrue(Superstructure.setIntakeStateCommand(IntakeStates.HOMING))
    //     .onFalse(Superstructure.setIntakeStateCommand(IntakeStates.DISABLED));
    joy.rightTrigger()
        .whileTrue(Superstructure.setIndexerStateCommand(IndexerStates.INDEXING))
        .onFalse(Superstructure.setIndexerStateCommand(IndexerStates.STOWED));

    joy.a()
        .onTrue(
            Superstructure.setIntakeStateCommand(IntakeStates.STOWED)
                .alongWith(Superstructure.setIndexerStateCommand(IndexerStates.STOWED)));
    // to-do add disable climb

    Superstructure.shooterStateIs(ShooterStates.STOWED)
        .whileTrue(
            new InstantCommand(
                () -> {
                  ShooterSubsystem.getInstance()
                      .setLeftShooterAngle(ShooterConstants.kHoodHardstopPosition);
                  ShooterSubsystem.getInstance()
                      .setRightShooterAngle(ShooterConstants.kHoodHardstopPosition);
                  ShooterSubsystem.getInstance()
                      .setLeftShooterSpeed(ShooterConstants.kShooterStowVelocity);
                  ShooterSubsystem.getInstance()
                      .setRightShooterSpeed(ShooterConstants.kShooterStowVelocity);
                }));
    Superstructure.indexerStateIs(IndexerStates.STOWED).whileTrue(new IndexStow());
    Superstructure.indexerStateIs(IndexerStates.INDEXING).whileTrue(new IndexShoot());
    // Use class-level trigger factory methods instead of nested class references
    Superstructure.intakeStateIs(IntakeStates.INTAKING)
        .and(Superstructure.swerveStateIs(Superstructure.SwerveStates.INTAKE_ASSISTANCE).negate())
        .whileTrue(new IntakeIntaking());
    // Superstructure.climbStateIs(ClimbStates.L1).whileTrue(new ClimbToL1());
    Superstructure.intakeStateIs(IntakeStates.STOWED).whileTrue(new IntakeStowed());
    Superstructure.intakeStateIs(IntakeStates.INTAKENEUTRAL).whileTrue(new IntakeNeutral());
    Superstructure.intakeStateIs(IntakeStates.HOMING).whileTrue(new IntakeHome());
  }
}
