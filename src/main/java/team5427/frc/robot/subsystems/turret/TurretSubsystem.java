package team5427.frc.robot.subsystems.turret;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.RobotPose;

public class TurretSubsystem extends SubsystemBase {
  private Rotation2d currentAngle;

  private TurretIOTalonFX io = new TurretIOTalonFX();
  private TurretIOInputsAutoLogged inputsAutoLogged;

  public static TurretSubsystem m_instance;

  private TurretSubsystem() {
    // Constructor for IntakeSubsystem
    // Initialize any necessary components or configurations here

    inputsAutoLogged = new TurretIOInputsAutoLogged();
    switch (Constants.currentMode) {
      case REAL:
        io = new TurretIOTalonFX();
        break;
      case SIM:
        io = null;
        break;
      default:
        break;
    }
  }

  public static TurretSubsystem getInstance() {
    if (m_instance == null) {
      m_instance = new TurretSubsystem();
    }
    return m_instance;
  }

  @Override
  public void periodic() {

    io.updateInputs(inputsAutoLogged);
    Logger.processInputs("Turret/Inputs", inputsAutoLogged);
    log();
  }

  public Distance getDiagonalDistance(Pose2d targetPose) {
    double x1 = RobotPose.getInstance().getAdaptivePose().getMeasureX().magnitude();
    double y1 = RobotPose.getInstance().getAdaptivePose().getMeasureY().magnitude();

    double x2 = targetPose.getMeasureX().magnitude();
    double y2 = targetPose.getMeasureY().magnitude();

    return Meters.of(Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0)));
  }

  public void setTurretRotation(Rotation2d setpoint) {
    io.setTurretRotation(setpoint);
  }

  public void resetTurret(Rotation2d resetAngle) {
    io.resetTurret(resetAngle);
  }

  public void disableTurret() {
    io.disableTurret();
  }

  public void log() {}
}
