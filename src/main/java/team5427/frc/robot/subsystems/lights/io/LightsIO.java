package team5427.frc.robot.subsystems.lights.io;

import org.littletonrobotics.junction.AutoLog;

public interface LightsIO {
  @AutoLog
  public static class LightsIOInputs {
    public int handle = 0;
  }

  public void updateInputs(LightsIOInputs inputs);

  public void setPWM(double pwm);
}
