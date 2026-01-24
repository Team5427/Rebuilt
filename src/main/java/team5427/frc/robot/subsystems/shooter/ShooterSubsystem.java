package team5427.frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.Supplier;
import org.ironmaple.simulation.drivesims.SwerveDriveSimulation;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private LinearVelocity outVelocity;

  private ShooterIOTalonFX io = new ShooterIOTalonFX();
  private ShooterIOInputsAutoLogged inputsAutoLogged;

  public static ShooterSubsystem m_instance;

  public static ShooterSubsystem getInstance(
      Supplier<SwerveDriveSimulation> swerveDriveSimulationSupplier) {
    if (m_instance == null) {
      m_instance = new ShooterSubsystem();
    }
    return m_instance;
  }

  private ShooterSubsystem() {
    // Constructor for IntakeSubsystem
    // Initialize any necessary components or configurations here

    inputsAutoLogged = new ShooterIOInputsAutoLogged();
    switch (Constants.currentMode) {
      case REAL:
        io = new ShooterIOTalonFX();
        break;
      case SIM:
        io = null;
        break;
      default:
        break;
    }
    outVelocity = MetersPerSecond.of(0.0);
  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);
    Logger.processInputs("Shooter/Inputs", inputsAutoLogged);
    log();
  }

  public void setIntakingSpeed(LinearVelocity speed) {
    outVelocity = speed;
  }

  public void disableRollerMotor1() {
    io.disableRoller1();
  }

  public void disableRollerMotor2() {
    io.disableRoller2();
  }

  public boolean isRollerMotor1Disabled() {
    return inputsAutoLogged.rollerMotor1IsConnected;
  }

  public boolean isRoller2MotorDisabled() {
    return inputsAutoLogged.rollerMotor2IsConnected;
  }

  public void log() {
    Logger.recordOutput("Shooter/ShootingSpeed", outVelocity.in(MetersPerSecond));
  }
}
