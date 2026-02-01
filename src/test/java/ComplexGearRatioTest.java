import org.junit.jupiter.api.Test;
import team5427.lib.systems.swerve.SwerveUtil;

public class ComplexGearRatioTest {

  @Test
  public void printGearRatios() {
    System.out.println(
        "MK4 Mathematical Gear Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK4.getMathematicalGearRatio()
            + ", Sensor to Mechanism Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK4.getSensorToMechanismRatio());
    System.out.println(
        "MK4i Mathematical Gear Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK4i.getMathematicalGearRatio()
            + ", Sensor to Mechanism Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK4i.getSensorToMechanismRatio());
    System.out.println(
        "MK4n Mathematical Gear Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK4n.getMathematicalGearRatio()
            + ", Sensor to Mechanism Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK4n.getSensorToMechanismRatio());
    System.out.println(
        "MK5n Mathematical Gear Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK5n.getMathematicalGearRatio()
            + ", Sensor to Mechanism Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK5n.getSensorToMechanismRatio());
    System.out.println(
        "MK5i Mathematical Gear Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK5i.getMathematicalGearRatio()
            + ", Sensor to Mechanism Ratio: "
            + SwerveUtil.kSDSSteerGearRatioMK5i.getSensorToMechanismRatio());
  }
}
