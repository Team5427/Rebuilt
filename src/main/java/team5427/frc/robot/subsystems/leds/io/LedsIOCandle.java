package team5427.frc.robot.subsystems.leds.io;

import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.signals.RGBWColor;
import edu.wpi.first.wpilibj.util.Color;
import team5427.frc.robot.subsystems.leds.LedsConstants;

public class LedsIOCandle implements LedsIO {
  private CANdle candle;
  private Color color;

  public LedsIOCandle() {
    candle = new CANdle(LedsConstants.kLightsChannel);
    candle.getConfigurator().apply(LedsConstants.candleConfig);
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
    SolidColor colorControl = new SolidColor(0, LedsConstants.kLightsLength);
    colorControl.withColor(RGBWColor.fromHex(color.toHexString()).get());
    candle.setControl(colorControl);
  }
}
