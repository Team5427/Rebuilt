package team5427.frc.robot.subsystems.indexer;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import lombok.Getter;
import lombok.Setter;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.indexer.io.IndexerIO;
import team5427.frc.robot.subsystems.indexer.io.IndexerIOInputsAutoLogged;
import team5427.frc.robot.subsystems.indexer.io.IndexerIOTalonFX;

public class IndexerSubsystem extends SubsystemBase {
  @Getter @Setter private LinearVelocity leftIndexerVelocitySetpoint;
  @Getter @Setter private LinearVelocity rightIndexerVelocitySetpoint;

  private IndexerIO io;
  private IndexerIOInputsAutoLogged inputsAutoLogged;

  private static IndexerSubsystem m_instance;

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
    rightIndexerVelocitySetpoint = MetersPerSecond.of(0.0);
    leftIndexerVelocitySetpoint = MetersPerSecond.of(0.0);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);

    io.setLeftIndexerMotorVelocity(leftIndexerVelocitySetpoint);
    io.setRightIndexerMotorVelocity(rightIndexerVelocitySetpoint);

    Logger.processInputs("Indexer/Inputs", inputsAutoLogged);
    log();
  }

  public LinearVelocity getLeftIndexerVelocity() {
    return inputsAutoLogged.leftIndexerMotorLinearVelocity;
  }

  public LinearVelocity getRightIndexerVelocity() {
    return inputsAutoLogged.rightIndexerMotorLinearVelocity;
  }

  public void log() {
    Logger.recordOutput("Indexer/AngularVelocitySetpoint", setpointVelocity.in(RadiansPerSecond));
    Logger.recordOutput(
        "Indexer/ActualAngularVelocity",
        inputsAutoLogged.indexerMotorAngularVelocity.in(RadiansPerSecond));
    Logger.recordOutput("Indexer/FlywheelVelocity", inputsAutoLogged.indexerFlywheelLinearVelocity);
    Logger.recordOutput("Indexer/Current", inputsAutoLogged.indexerMotorCurrent);
  }
}
