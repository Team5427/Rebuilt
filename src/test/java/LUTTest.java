import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import team5427.frc.robot.subsystems.shooter.AimingConstants;

public class LUTTest {

  @Test
  public void lutShouldExistAndContainEntries() {
    assertNotNull(AimingConstants.lut, "Generated LUT should not be null");
    assertNotNull(AimingConstants.lut.entries(), "LUT entries collection should not be null");
    assertTrue(
        AimingConstants.lut.entries().size() > 0,
        "Generated LUT should contain at least one entry");
  }

  @Test
  public void lutShouldContainReachableEntry() {
    for (var entry : AimingConstants.lut.entries()) {
      if (entry.reachable()) {
        System.out.println(entry);
        AimingConstants.shotCalc.loadLUTEntry(entry.distanceM(), entry.rpm(), entry.tof());
      }
    }
    System.out.println(AimingConstants.sim.findRPMForDistance(3.0));
    boolean hasReachable = AimingConstants.lut.entries().stream().anyMatch(e -> e.reachable());
    for (var e : AimingConstants.lut.entries()) {
      System.out.println(e);
    }
    assertTrue(hasReachable, "Generated LUT should contain at least one reachable entry");
  }

  @Test
  public void shotCalculatorShouldExist() {
    assertNotNull(AimingConstants.shotCalc, "ShotCalculator should be initialized");
  }

  @Test
  public void shootingTableShouldExist() {
    assertNotNull(AimingConstants.kShootingTable, "kShootingTable should be initialized");
  }
}
