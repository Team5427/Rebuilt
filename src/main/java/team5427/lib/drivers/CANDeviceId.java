package team5427.lib.drivers;

import com.ctre.phoenix6.CANBus;

// Credit 254
public class CANDeviceId {

  protected final int mDeviceNumber;
  protected final CANBus mBus;

  public CANDeviceId(int deviceNumber, CANBus bus) {
    mDeviceNumber = deviceNumber;
    mBus = bus;
  }

  public CANDeviceId(int deviceNumber, String bus) {
    mDeviceNumber = deviceNumber;
    mBus = new CANBus(bus);
  }

  public CANDeviceId(int deviceNumber) {
    this(deviceNumber, "");
  }

  public int getDeviceNumber() {
    return mDeviceNumber;
  }

  public CANBus getBus() {
    return mBus;
  }

  public boolean equals(CANDeviceId other) {
    return other.mDeviceNumber == mDeviceNumber && other.mBus == mBus;
  }
}
