package team5427.frc.robot.subsystems.climb;

import static edu.wpi.first.units.Units.Amp;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volt;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import lombok.Getter;
import lombok.Setter;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.Constants;
import team5427.frc.robot.subsystems.climb.io.ClimbIO;
import team5427.frc.robot.subsystems.climb.io.ClimbIOInputsAutoLogged;
import team5427.frc.robot.subsystems.climb.io.ClimbIOTalonFX;

public class ClimbSubsystem extends SubsystemBase {

  private ClimbIO io;
  private ClimbIOInputsAutoLogged inputsAutoLogged = new ClimbIOInputsAutoLogged();

  private Rotation2d climbHooksSetpoint;

  private static ClimbSubsystem m_instance;

  private boolean manualRunning = false;

  private Voltage manualRunVoltage = Volt.zero();

  public static enum ClimbStates {
    kStow,
    kPrep,
    kHook,
    kClimb,
  }

  @Getter @Setter private static ClimbStates climbState = ClimbStates.kStow;

  public static ClimbSubsystem getInstance() {
    if (m_instance == null) {
      m_instance = new ClimbSubsystem();
    }
    return m_instance;
  }

  private ClimbSubsystem() {
    switch (Constants.currentMode) {
      case REAL:
        io = new ClimbIOTalonFX();
        break;
      case SIM:
        io = new ClimbIOTalonFX();
        break;
      default:
        break;
    }

    climbHooksSetpoint = ClimbConstants.kStowPosition;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputsAutoLogged);
    // if (!manualRunning) {
    //   io.setHookVoltage(manualRunVoltage);
    // } else {
    io.setHookSetpoint(climbHooksSetpoint);
    // }

    Logger.processInputs("Climb", inputsAutoLogged);
  }

  public void setPosition(Rotation2d angle) {
    io.setHookPosition(angle);
  }

  public boolean isStalled() {
    return inputsAutoLogged.hookServoCurrent.in(Amp) >= 20
        && Math.abs(inputsAutoLogged.hookVelocity.in(RotationsPerSecond)) <= 0.01;
  }

  public void setSetpoint(Rotation2d setpoint) {
    climbHooksSetpoint = setpoint;
  }

  public void voltageRun(Voltage volts) {
    manualRunVoltage = volts;
  }

  public void manualRunVoltage(boolean manual) {
    this.manualRunning = manual;
  }

  public boolean getManualRunVoltage() {
    return this.manualRunning;
  }
}
