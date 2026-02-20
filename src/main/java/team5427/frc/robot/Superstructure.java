package team5427.frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import java.util.function.BooleanSupplier;
import org.littletonrobotics.junction.Logger;

public final class Superstructure {
  public static final String dashboardKey = "/Superstructure";

  // State Variables, accessible only with getters

  private static SwerveStates kSelectedSwerveState = SwerveStates.DISABLED;
  private static SwerveStates kPreviousSwerveState = SwerveStates.DISABLED;

  private static IntakeStates kSelectedIntakeState = IntakeStates.STOWED;
  private static IntakeStates kPreviousIntakeState = IntakeStates.STOWED;

  private static ShooterStates kSelectedShooterState = ShooterStates.DISABLED;
  private static ShooterStates kPreviousShooterState = ShooterStates.DISABLED;

  private static IndexerStates kSelectedIndexerState = IndexerStates.DISABLED;
  private static IndexerStates kPreviousIndexerState = IndexerStates.DISABLED;

  // Swerve States Enum
  public static enum SwerveStates {
    RAW_DRIVING,
    CONTROLLED_DRIVING,
    AUTO_ALIGN,
    AUTO_TARGETING,
    INTAKE_ASSISTANCE,
    AUTON,
    DISABLED
  }

  // Intake States Enum
  public static enum IntakeStates {
    INTAKING,
    HOMING,
    DISABLED,
    STOWED,
    OUTAKING
  }

  // Shooter States Enum
  public static enum ShooterStates {
    RAW_SHOOTING,
    FERRY_SHOOTING,
    AUTO_ALIGN_SHOOTING,
    STOW,
    DISABLED
  }

  public static enum IndexerStates {
    INDEXING,
    STOWED,
    DISABLED
  }

  // Getter Methods

  public static synchronized SwerveStates getSelectedSwerveState() {
    return kSelectedSwerveState;
  }

  public static synchronized SwerveStates getPreviousSwerveState() {
    return kPreviousSwerveState;
  }

  public static synchronized IntakeStates getSelectedIntakeState() {
    return kSelectedIntakeState;
  }

  public static synchronized IntakeStates getPreviousIntakeState() {
    return kPreviousIntakeState;
  }

  public static synchronized ShooterStates getSelectedShooterState() {
    return kSelectedShooterState;
  }

  public static synchronized ShooterStates getPreviousShooterState() {
    return kPreviousShooterState;
  }

  public static synchronized IndexerStates getSelectedIndexerState() {
    return kSelectedIndexerState;
  }

  public static synchronized IndexerStates getPreviousIndexerState() {
    return kPreviousIndexerState;
  }

  // State Request Methods

  /**
   * Allows you to request a new Swerve State and if it is different than the current one, the
   * current and previous state will be replaced accordingly.
   *
   * @param newState The SwerveState that you are requesting
   */
  public static synchronized void requestSwerveState(SwerveStates newState) {
    if (kSelectedSwerveState != newState) {
      kPreviousSwerveState = kSelectedSwerveState;
      kSelectedSwerveState = newState;
      Logger.recordOutput(dashboardKey + "/SwerveState", newState.toString());
      Logger.recordOutput(dashboardKey + "/PreviousSwerveState", kPreviousSwerveState.toString());
    }
  }

  /**
   * Allows you to request a new Intake State and if it is different than the current one, the
   * current and previous state will be replaced accordingly.
   *
   * @param newState The IntakeState that you are requesting
   */
  public static synchronized void requestIntakeState(IntakeStates newState) {
    if (kSelectedIntakeState != newState) {
      kPreviousIntakeState = kSelectedIntakeState;
      kSelectedIntakeState = newState;
      Logger.recordOutput(dashboardKey + "/IntakeState", newState.toString());
      Logger.recordOutput(dashboardKey + "/PreviousIntakeState", kPreviousIntakeState.toString());
    }
  }

  /**
   * Allows you to request a new Shooter State and if it is different than the current one, the
   * current and previous state will be replaced accordingly.
   *
   * @param newState The ShooterState that you are requesting
   */
  public static synchronized void requestShooterState(ShooterStates newState) {
    if (kSelectedShooterState != newState) {
      kPreviousShooterState = kSelectedShooterState;
      kSelectedShooterState = newState;
      Logger.recordOutput(dashboardKey + "/ShooterState", newState.toString());
      Logger.recordOutput(dashboardKey + "/PreviousShooterState", kPreviousShooterState.toString());
    }
  }

  /**
   * Allows you to request a new Indexer State and if it is different than the current one, the
   * current and previous state will be replaced accordingly.
   *
   * @param newState The IndexerState that you are requesting
   */
  public static synchronized void requestIndexerState(IndexerStates newState) {
    if (kSelectedIndexerState != newState) {
      kPreviousIndexerState = kSelectedIndexerState;
      kSelectedIndexerState = newState;
      Logger.recordOutput(dashboardKey + "/IndexerState", newState.toString());
      Logger.recordOutput(dashboardKey + "/PreviousIndexerState", kPreviousIndexerState.toString());
    }
  }

  // Command factories return command that change state
  /**
   * Builds a {@link Command} that switches the swerve subsystem to the supplied state.
   *
   * @param state Desired {@link SwerveStates} target.
   * @return One-shot command that applies the new state.
   */
  public static synchronized Command setSwerveStateCommand(SwerveStates state) {
    return Commands.runOnce(() -> requestSwerveState(state))
        .withName("SetSwerveState(" + state.toString() + ")");
  }

  /**
   * Builds a {@link Command} that switches the intake subsystem to the supplied state.
   *
   * @param state Desired {@link IntakeStates} target.
   * @return One-shot command that applies the new state.
   */
  public static synchronized Command setIntakeStateCommand(IntakeStates state) {
    return Commands.runOnce(() -> requestIntakeState(state))
        .withName("SetIntakeState(" + state.toString() + ")");
  }

  /**
   * Builds a {@link Command} that switches the shooter subsystem to the supplied state.
   *
   * @param state Desired {@link ShooterStates} target.
   * @return One-shot command that applies the new state.
   */
  public static synchronized Command setShooterStateCommand(ShooterStates state) {
    return Commands.runOnce(() -> requestShooterState(state))
        .withName("SetShooterState(" + state.toString() + ")");
  }

  /**
   * Builds a {@link Command} that switches the indexer subsystem to the supplied state.
   *
   * @param state Desired {@link IndexerStates} target.
   * @return One-shot command that applies the new state.
   */
  public static synchronized Command setIndexerStateCommand(IndexerStates state) {
    return Commands.runOnce(() -> requestIndexerState(state))
        .withName("SetIndexerState(" + state.toString() + ")");
  }

  // Trigger factory creates trigger for any state condition
  /**
   * Creates a trigger that is active when the current swerve state equals the supplied state.
   *
   * @param state State to compare against the currently selected swerve state.
   * @return Trigger that reflects the state match.
   */
  public static synchronized Trigger swerveStateIs(SwerveStates state) {
    return new Trigger(() -> kSelectedSwerveState == state);
  }

  /**
   * Creates a trigger that is active when the current swerve state matches any of the supplied
   * states.
   *
   * @param states Acceptable {@link SwerveStates} values.
   * @return Trigger that fires while the current state is any of the provided values.
   */
  public static synchronized Trigger swerveStateIsAnyOf(SwerveStates... states) {
    return new Trigger(
        () -> {
          for (SwerveStates state : states) {
            if (kSelectedSwerveState == state) return true;
          }
          return false;
        });
  }

  /**
   * Creates a trigger that is active when the current intake state equals the supplied state.
   *
   * @param state State to compare against the currently selected intake state.
   * @return Trigger that reflects the state match.
   */
  public static synchronized Trigger intakeStateIs(IntakeStates state) {
    return new Trigger(() -> kSelectedIntakeState == state);
  }

  /**
   * Creates a trigger that is active when the current intake state matches any of the supplied
   * states.
   *
   * @param states Acceptable {@link IntakeStates} values.
   * @return Trigger that fires while the current state is any of the provided values.
   */
  public static synchronized Trigger intakeStateIsAnyOf(IntakeStates... states) {
    return new Trigger(
        () -> {
          for (IntakeStates state : states) {
            if (kSelectedIntakeState == state) return true;
          }
          return false;
        });
  }

  public static synchronized Trigger shooterStateIs(ShooterStates state) {
    return new Trigger(() -> kSelectedShooterState == state);
  }

  public static synchronized Trigger shooterStateIsAnyOf(ShooterStates... states) {
    return new Trigger(
        () -> {
          for (ShooterStates state : states) {
            if (kSelectedShooterState == state) return true;
          }
          return false;
        });
  }

  public static synchronized Trigger indexerStateIs(IndexerStates state) {
    return new Trigger(() -> kSelectedIndexerState == state);
  }

  public static synchronized Trigger indexerStateIsAnyOf(IndexerStates... states) {
    return new Trigger(
        () -> {
          for (IndexerStates state : states) {
            if (kSelectedIndexerState == state) return true;
          }
          return false;
        });
  }

  // Transition Triggers detect state changes

  /**
   * Trigger that becomes active the first cycle a new swerve state is selected.
   *
   * @param state Destination state to monitor.
   * @return Trigger that detects the transition into the supplied state.
   */
  public static synchronized Trigger swerveStateChangedTo(SwerveStates state) {
    return new Trigger(() -> kSelectedSwerveState == state && kPreviousSwerveState != state);
  }

  /**
   * Trigger that becomes active when a previously selected swerve state is exited.
   *
   * @param state Source state to monitor.
   * @return Trigger that detects the transition away from the supplied state.
   */
  public static synchronized Trigger swerveStateChangedFrom(SwerveStates state) {
    return new Trigger(() -> kPreviousSwerveState == state && kSelectedSwerveState != state);
  }

  /**
   * Trigger that becomes active the first cycle a new intake state is selected.
   *
   * @param state Destination state to monitor.
   * @return Trigger that detects the transition into the supplied state.
   */
  public static synchronized Trigger intakeStateChangedTo(IntakeStates state) {
    return new Trigger(() -> kSelectedIntakeState == state && kPreviousIntakeState != state);
  }

  /**
   * Trigger that becomes active when a previously selected intake state is exited.
   *
   * @param state Source state to monitor.
   * @return Trigger that detects the transition away from the supplied state.
   */
  public static synchronized Trigger intakeStateChangedFrom(IntakeStates state) {
    return new Trigger(() -> kPreviousIntakeState == state && kSelectedIntakeState != state);
  }

  /**
   * Trigger that becomes active the first cycle a new shooter state is selected.
   *
   * @param state Destination state to monitor.
   * @return Trigger that detects the transition into the supplied state.
   */
  public static synchronized Trigger shooterStateChangedTo(ShooterStates state) {
    return new Trigger(() -> kSelectedShooterState == state && kPreviousShooterState != state);
  }

  /**
   * Trigger that becomes active when a previously selected shooter state is exited.
   *
   * @param state Source state to monitor.
   * @return Trigger that detects the transition away from the supplied state.
   */
  public static synchronized Trigger shooterStateChangedFrom(ShooterStates state) {
    return new Trigger(() -> kPreviousShooterState == state && kSelectedShooterState != state);
  }

  // Compound - Combine multiple conditions

  /**
   * Wraps an arbitrary boolean supplier in a {@link Trigger}.
   *
   * @param condition Supplier evaluated each cycle.
   * @return Trigger that mirrors the supplier's value.
   */
  public static synchronized Trigger when(BooleanSupplier condition) {
    return new Trigger(condition);
  }

  // Validation - Prevent invalid state combinations

  /**
   * Determines whether the swerve subsystem may transition between the supplied states.
   *
   * @param from Current state.
   * @param to Desired target state.
   * @return {@code true} if the transition is allowed, {@code false} otherwise.
   */
  public static synchronized boolean canTransitionSwerve(SwerveStates from, SwerveStates to) {
    if (from == SwerveStates.DISABLED
        && (to == SwerveStates.AUTO_ALIGN || to == SwerveStates.AUTO_TARGETING)) {
      return false;
    }
    return true;
  }

  /**
   * Requests a new swerve state only if {@link #canTransitionSwerve(SwerveStates, SwerveStates)}
   * approves the transition. Invalid requests are logged to the dashboard.
   *
   * @param newState Desired target state.
   */
  public static synchronized void requestSwerveStateValidated(SwerveStates newState) {
    if (canTransitionSwerve(kSelectedSwerveState, newState)) {
      requestSwerveState(newState);
    } else {
      Logger.recordOutput(
          dashboardKey + "/InvalidTransition",
          kSelectedSwerveState.toString() + " -> " + newState.toString());
    }
  }

  // Logging

  /** Publishes the current and previous swerve and intake states to AdvantageScope. */
  public static synchronized void logStates() {
    Logger.recordOutput(dashboardKey + "/SwerveState", kSelectedSwerveState.toString());
    Logger.recordOutput(dashboardKey + "/IntakeState", kSelectedIntakeState.toString());
    Logger.recordOutput(dashboardKey + "/PreviousSwerveState", kPreviousSwerveState.toString());
    Logger.recordOutput(dashboardKey + "/PreviousIntakeState", kPreviousIntakeState.toString());
    Logger.recordOutput(dashboardKey + "/ShooterState", kSelectedShooterState.toString());
    Logger.recordOutput(dashboardKey + "/PreviousShooterState", kPreviousShooterState.toString());
    Logger.recordOutput(dashboardKey + "/IndexerState", kSelectedIndexerState.toString());
    Logger.recordOutput(dashboardKey + "/PreviousIndexerState", kPreviousIndexerState.toString());
  }

  // Static Trigger Constants

  public static final class SwerveTriggers {
    public static final Trigger kRawDriving = swerveStateIs(SwerveStates.RAW_DRIVING);
    public static final Trigger kControlledDriving = swerveStateIs(SwerveStates.CONTROLLED_DRIVING);
    public static final Trigger kAutoAlign = swerveStateIs(SwerveStates.AUTO_ALIGN);
    public static final Trigger kAutoTargeting = swerveStateIs(SwerveStates.AUTO_TARGETING);
    public static final Trigger kIntakeAssistance = swerveStateIs(SwerveStates.INTAKE_ASSISTANCE);
    public static final Trigger kAuton = swerveStateIs(SwerveStates.AUTON);
    public static final Trigger kDisabled = swerveStateIs(SwerveStates.DISABLED);
  }

  public static final class IntakeTriggers {
    public static final Trigger kIntaking = intakeStateIs(IntakeStates.INTAKING);
    public static final Trigger kHoming = intakeStateIs(IntakeStates.HOMING);
    public static final Trigger kDisabled = intakeStateIs(IntakeStates.DISABLED);
    public static final Trigger kStowed = intakeStateIs(IntakeStates.STOWED);
    public static final Trigger kOutaking = intakeStateIs(IntakeStates.OUTAKING);
  }

  public static final class ShooterTriggers {
    public static final Trigger kRawShooting = shooterStateIs(ShooterStates.RAW_SHOOTING);
    public static final Trigger kFerryShooting = shooterStateIs(ShooterStates.FERRY_SHOOTING);
    public static final Trigger kAutoAlignShooting =
        shooterStateIs(ShooterStates.AUTO_ALIGN_SHOOTING);
    public static final Trigger kStowShooter = shooterStateIs(ShooterStates.STOW);
    public static final Trigger kDisabled = shooterStateIs(ShooterStates.DISABLED);
  }

  public static final class IndexerTriggers {
    public static final Trigger kIndexing = indexerStateIs(IndexerStates.INDEXING);
    public static final Trigger kStowed = indexerStateIs(IndexerStates.STOWED);
    public static final Trigger kDisabled = indexerStateIs(IndexerStates.DISABLED);
  }
}
