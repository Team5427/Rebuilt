package team5427.frc.robot.subsystems.indexer;

import static edu.wpi.first.units.Units.RadiansPerSecond;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.indexer.io.IndexerIO;
import team5427.frc.robot.subsystems.indexer.io.IndexerIOInputsAutoLogged;
import team5427.frc.robot.subsystems.indexer.io.IndexerIOTalonFX;
import team5427.lib.motors.MotorUtil;

public class IndexerSubsystem extends SubsystemBase {
  private AngularVelocity desiredVelocity;

  private IndexerIO io;
  private IndexerIOInputsAutoLogged inputsAutoLogged;

  private static IndexerSubsystem m_instance;

  public final Alert kIndexerVelocityOutOfBounds =
      new Alert("OutOfBounds", "Indexer Velocity Requested Out of Bounds", AlertType.kWarning);

  public static IndexerSubsystem getInstance() {
    if (m_instance == null) {
      m_instance = new IndexerSubsystem();
    }
    return m_instance;
  }

  private IndexerSubsystem() {
    inputsAutoLogged = new IndexerIOInputsAutoLogged();
    switch (Constants.currentMode) {
      case REAL:
        io = new IndexerIOTalonFX();
        break;
      case SIM:
        // TODO: Implement IndexerIOSim if needed
        break;
      default:
        break;
    }
    desiredVelocity = RadiansPerSecond.of(0.0);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);

    // Set desired motor velocity
    io.setIndexerMotorVelocity(desiredVelocity);

    Logger.processInputs("Indexer/Inputs", inputsAutoLogged);
    log();
  }

  public void setIndexerVelocity(AngularVelocity velocity) {
    if (Math.abs(velocity.in(RadiansPerSecond) * 60.0) > MotorUtil.kKrakenX60_MaxRPM) {
      kIndexerVelocityOutOfBounds.set(true);
    } else {
      kIndexerVelocityOutOfBounds.set(false);
      desiredVelocity = velocity;
    }
  }

  public void resetIndexerRotation() {
    io.setIndexerMotorRotation(Rotation2d.kZero);
  }

  public void disableIndexerMotor(boolean shouldDisable) {
    io.disableIndexerMotor(shouldDisable);
  }

  public AngularVelocity getIndexerVelocity() {
    return inputsAutoLogged.indexerMotorAngularVelocity;
  }

  public boolean isLeaderConnected() {
    return inputsAutoLogged.indexerMotorLeaderConnected;
  }

  public boolean isFollowerConnected() {
    return inputsAutoLogged.indexerMotorFollowerConnected;
  }

  public void log() {
    Logger.recordOutput("Indexer/DesiredAngularVelocity", desiredVelocity.in(RadiansPerSecond));
    Logger.recordOutput(
        "Indexer/ActualAngularVelocity",
        inputsAutoLogged.indexerMotorAngularVelocity.in(RadiansPerSecond));
    Logger.recordOutput("Indexer/FlywheelVelocity", inputsAutoLogged.indexerFlywheelLinearVelocity);
    Logger.recordOutput("Indexer/Current", inputsAutoLogged.indexerMotorCurrent);
  }
}
