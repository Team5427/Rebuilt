package team5427.frc.robot.io;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public final class DriverProfiles {

  public static DriverState kSelectedDriverState = DriverState.A_E;

  public static enum DriverModeType {
    SINGLE,
    DUAL
  }

  public static enum DriverState {
    /** Just Eric */
    ERIC(DriverModeType.SINGLE),
    /** Single Person Testing */
    TEST_SINGLE(DriverModeType.SINGLE),
    /** Eric and Anoushka */
    A_E(DriverModeType.DUAL),
    TEST_DUAL(DriverModeType.DUAL);

    public final DriverModeType modeType;

    DriverState(DriverModeType modeType) {
      this.modeType = modeType;
    }
  }

  public static class DriverTriggers {
    public static final Trigger kIsMode(DriverModeType mode) {
      return new Trigger(
          () -> {
            return kSelectedDriverState.modeType == mode;
          });
    }

    public static final Trigger kIsState(DriverState state) {
      return new Trigger(
          () -> {
            return kSelectedDriverState == state;
          });
    }
  }
}
