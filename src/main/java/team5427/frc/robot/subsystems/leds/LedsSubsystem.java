package team5427.frc.robot.subsystems.leds;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;
import team5427.frc.robot.subsystems.leds.io.LedsIO;
import team5427.frc.robot.subsystems.leds.io.LedsIOCandle;
import team5427.frc.robot.subsystems.leds.io.LightsIOInputsAutoLogged;

public class LedsSubsystem extends SubsystemBase {

  private LedsIO lights;
  private LightsIOInputsAutoLogged inputsAutoLogged;

  private Color pattern;

  private static LedsSubsystem m_instance;

  public static LedsSubsystem getInstance() {
    if (m_instance == null) m_instance = new LedsSubsystem();
    return m_instance;
  }

  private LedsSubsystem() {
    lights = new LedsIOCandle();
    inputsAutoLogged = new LightsIOInputsAutoLogged();
  }

  @Override
  public void periodic() {
    lights.setColor(pattern);

    Logger.processInputs("Leds", inputsAutoLogged);
  }

  public void setPattern(Color pattern) {
    this.pattern = pattern;
  }

  public Color getPattern() {
    return pattern;
  }
}
