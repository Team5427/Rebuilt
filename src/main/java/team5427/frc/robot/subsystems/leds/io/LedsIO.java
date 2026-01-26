package team5427.frc.robot.subsystems.leds.io;

import edu.wpi.first.wpilibj.util.Color;
import org.littletonrobotics.junction.AutoLog;

public interface LedsIO {
  @AutoLog
  public static class LightsIOInputs {
    public String colorHex = Color.kBlack.toHexString();
  }

  public void updateInputs(LightsIOInputs inputs);

  public void setPWM(double pwm);

  public void setColor(Color color);
}
