package team5427.frc.robot.subsystems.shooter;

import team5427.lib.tables.LookUpTable;

public final class AimingConstants {
  public static final LookUpTable kShootingTable = new LookUpTable();

  static {
    kShootingTable.addPivotAngle(1.0, 17.0);
    kShootingTable.addPivotAngle(1.25, 17.0);
    kShootingTable.addPivotAngle(1.5, 17.0);
    kShootingTable.addPivotAngle(1.75, 17.0);
    kShootingTable.addPivotAngle(2.0, 17.0);
    kShootingTable.addPivotAngle(2.25, 17.0);
    kShootingTable.addPivotAngle(2.5, 17.0);
    kShootingTable.addPivotAngle(2.75, 17.0);
    kShootingTable.addPivotAngle(3.0, 17.0);
    kShootingTable.addPivotAngle(3.25, 17.0);
    kShootingTable.addPivotAngle(3.5, 17.0);
    kShootingTable.addPivotAngle(3.75, 17.0);
    kShootingTable.addPivotAngle(4.0, 17.0);
    kShootingTable.addPivotAngle(4.25, 19.25);
    kShootingTable.addPivotAngle(4.5, 18.5);
    kShootingTable.addPivotAngle(4.75, 18.0);
    kShootingTable.addPivotAngle(5.0, 17.0);
    
 

    kShootingTable.addFlyWheelSpeed(1.0, 2.15);
    kShootingTable.addFlyWheelSpeed(1.25, 2.15);
    kShootingTable.addFlyWheelSpeed(1.5, 2.15);
    kShootingTable.addFlyWheelSpeed(1.75, 2.15);
    kShootingTable.addFlyWheelSpeed(2.0, 2.8
    );
    kShootingTable.addFlyWheelSpeed(2.25, 2.8);
    kShootingTable.addFlyWheelSpeed(2.5, 2.8);
    kShootingTable.addFlyWheelSpeed(2.75, 2.8);
    kShootingTable.addFlyWheelSpeed(3.0, 3.0);
    kShootingTable.addFlyWheelSpeed(3.25, 3.0);
    kShootingTable.addFlyWheelSpeed(3.5, 3.2);

    kShootingTable.addFlyWheelSpeed(3.75, 3.25);
    kShootingTable.addFlyWheelSpeed(4.0, 3.4);
    kShootingTable.addFlyWheelSpeed(4.25, 3.6);
    kShootingTable.addFlyWheelSpeed(4.5, 3.8);
    kShootingTable.addFlyWheelSpeed(4.75, 3.85);
    kShootingTable.addFlyWheelSpeed(5.0, 4.1);

    kShootingTable.addTimeOfFlight(1.0, 0.15);
    kShootingTable.addTimeOfFlight(1.25, 0.15);
    kShootingTable.addTimeOfFlight(1.5, 0.15);
    kShootingTable.addTimeOfFlight(1.75, 0.15);

    kShootingTable.addTimeOfFlight(2.0, 0.25);
    kShootingTable.addTimeOfFlight(2.25, 0.25);
    kShootingTable.addTimeOfFlight(2.5, 0.25);
    kShootingTable.addTimeOfFlight(2.75, 0.25);

    kShootingTable.addTimeOfFlight(3.0, 0.75);
    kShootingTable.addTimeOfFlight(3.25, 0.75);
    kShootingTable.addTimeOfFlight(3.5, 0.75); 
    kShootingTable.addTimeOfFlight(3.75, 0.58); 

    kShootingTable.addTimeOfFlight(4.0, 0.65); 
    kShootingTable.addTimeOfFlight(4.25, 0.68);
    kShootingTable.addTimeOfFlight(4.5, 0.73);
    kShootingTable.addTimeOfFlight(4.75, 0.85);

    kShootingTable.addTimeOfFlight(5.0, 1.1);

  }
}
