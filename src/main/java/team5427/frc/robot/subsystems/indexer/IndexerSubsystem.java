package team5427.frc.robot.subsystems.indexer;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import lombok.Getter;
import lombok.Setter;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.indexer.io.IndexerIO;
import team5427.frc.robot.subsystems.indexer.io.IndexerIOInputsAutoLogged;
import team5427.frc.robot.subsystems.indexer.io.IndexerIOSim;
import team5427.frc.robot.subsystems.indexer.io.IndexerIOTalonFX;

public class IndexerSubsystem extends SubsystemBase {
  @Getter @Setter private LinearVelocity indexerVelocitySetpoint;
  @Getter @Setter private LinearVelocity hopperVelocitySetpoint;

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
        io = new IndexerIOSim();
        break;
      default:
        break;
    }
    indexerVelocitySetpoint = MetersPerSecond.of(0.0);
    hopperVelocitySetpoint = MetersPerSecond.of(0.0);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);
    hopperVelocitySetpoint = hopperVelocitySetpoint.div(3.0);
    io.setIndexerMotorVelocity(indexerVelocitySetpoint);
    io.setHopperMotorVelocity(hopperVelocitySetpoint);

    Logger.recordOutput("Indexer/IndexerLinearVelocity", indexerVelocitySetpoint);
    Logger.recordOutput("Indexer/HopperLinearVelocity", hopperVelocitySetpoint);

    Logger.processInputs("Indexer/Inputs", inputsAutoLogged);
  }

  public LinearVelocity getLeftIndexerVelocity() {
    return inputsAutoLogged.leftIndexerMotorLinearVelocity;
  }

  public LinearVelocity getRightIndexerVelocity() {
    return inputsAutoLogged.rightIndexerMotorLinearVelocity;
  }

  public LinearVelocity getHopperVelocity() {
    return inputsAutoLogged.hopperMotorLinearVelocity;
  }
}
