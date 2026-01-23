package team5427.frc.robot.subsystems.lights.io;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import team5427.frc.robot.subsystems.lights.LightsConstants;

public class LightsIOBlinkin implements LightsIO {
  private Spark blinkin;

  public LightsIOBlinkin() {
    blinkin = new Spark(LightsConstants.kLightsChannel);
  }

  @Override
  public void updateInputs(LightsIOInputs inputs) {
    inputs.handle = blinkin.getPwmHandle();
  }

  @Override
  public void setPWM(double pwm) {
    blinkin.set(pwm);
  }
}
