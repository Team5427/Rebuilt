package team5427.frc.robot.subsystems.indexer;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.math.geometry.Rotation2d;
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
  @Getter private LinearVelocity leftIndexerVelocitySetpoint;
  @Getter private LinearVelocity rightIndexerVelocitySetpoint;
  @Getter private LinearVelocity hopperVelocitySetpoint;

  @Getter @Setter private Rotation2d targetHeading;
  @Getter @Setter private double targetDistance;

  private IndexerIO io;
  private IndexerIOInputsAutoLogged inputsAutoLogged;

  private static IndexerSubsystem m_instance;

  public void setIndexerVelocitySetpoint(LinearVelocity velocity) {
    this.leftIndexerVelocitySetpoint = velocity;
    io.setLeftIndexerMotorVelocity(leftIndexerVelocitySetpoint);
    this.rightIndexerVelocitySetpoint = velocity;
    io.setRightIndexerMotorVelocity(rightIndexerVelocitySetpoint);
  }

  public void setLeftIndexerMotorVelocity(LinearVelocity velocity) {
    this.leftIndexerVelocitySetpoint = velocity;
    io.setLeftIndexerMotorVelocity(leftIndexerVelocitySetpoint);
  }

  public void setRightIndexerMotorVelocity(LinearVelocity velocity) {
    this.rightIndexerVelocitySetpoint = velocity;
    io.setRightIndexerMotorVelocity(rightIndexerVelocitySetpoint);
  }

  public void setHopperVelocitySetpoint(LinearVelocity velocity) {
    this.hopperVelocitySetpoint = velocity;
    hopperVelocitySetpoint = hopperVelocitySetpoint.div(4.0);

    io.setHopperMotorVelocity(hopperVelocitySetpoint);
  }

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
    leftIndexerVelocitySetpoint = MetersPerSecond.of(0);
    rightIndexerVelocitySetpoint = MetersPerSecond.of(0);
    hopperVelocitySetpoint = MetersPerSecond.of(0);
    targetHeading = new Rotation2d();
    targetDistance = 0;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);

    Logger.recordOutput("Indexer/LeftIndexerLinearVelocity", leftIndexerVelocitySetpoint);
    Logger.recordOutput("Indexer/RightIndexerLinearVelocity", rightIndexerVelocitySetpoint);
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
