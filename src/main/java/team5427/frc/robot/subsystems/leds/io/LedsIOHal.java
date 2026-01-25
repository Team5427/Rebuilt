package team5427.frc.robot.subsystems.leds.io;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;
import team5427.frc.robot.subsystems.leds.LedsConstants;

public class LedsIOHal implements LedsIO {
  private AddressableLED leds;
  private AddressableLEDBuffer buffer;
  private LEDPattern pattern;
  private Color color;

  public LedsIOHal() {
    leds = new AddressableLED(LedsConstants.kLightsChannel);
    buffer = new AddressableLEDBuffer(LedsConstants.kLightsLength);
    leds.setData(buffer);
  }

  @Override
  public void updateInputs(LightsIOInputs inputs) {
    inputs.colorHex = color.toHexString();
  }

  @Override
  public void setPWM(double pwm) {
    return;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
    pattern = LEDPattern.solid(color);
    pattern.applyTo(buffer);
  }
}
