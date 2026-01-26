package team5427.frc.robot.subsystems.leds.io;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.util.Color;
import team5427.frc.robot.subsystems.leds.LedsConstants;

public class LedsIOBlinkin implements LedsIO {
  private Spark blinkin;
  private Color color;

  public LedsIOBlinkin() {
    blinkin = new Spark(LedsConstants.kLightsChannel);
  }

  @Override
  public void updateInputs(LightsIOInputs inputs) {
    inputs.colorHex = color.toHexString();
  }

  @Override
  public void setPWM(double pwm) {
    blinkin.set(pwm);
  }

  @Override
  public void setColor(Color color) {
    this.color = Color.kRed;
    blinkin.set(color.red);
  }
}
