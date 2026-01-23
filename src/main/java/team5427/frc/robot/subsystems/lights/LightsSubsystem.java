package team5427.frc.robot.subsystems.lights;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.subsystems.lights.io.LightsIO;
import team5427.frc.robot.subsystems.lights.io.LightsIOBlinkin;
import team5427.frc.robot.subsystems.lights.io.LightsIOInputsAutoLogged;

public class LightsSubsystem extends SubsystemBase {

  private LightsIO lights;
  private LightsIOInputsAutoLogged inputsAutoLogged;

  private double pattern;

  private static LightsSubsystem m_instance;

  public static LightsSubsystem getInstance() {
    if (m_instance == null) m_instance = new LightsSubsystem();
    return m_instance;
  }

  private LightsSubsystem() {
    lights = new LightsIOBlinkin();
    inputsAutoLogged = new LightsIOInputsAutoLogged();
  }

  @Override
  public void periodic() {
    lights.setPWM(pattern);

    Logger.processInputs("Lights", inputsAutoLogged);
  }

  public void setPattern(double pattern) {
    this.pattern = pattern;
  }

  public double getPattern() {
    return pattern;
  }
}
