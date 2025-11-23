package com.erval.argos.core.domain.device;

/**
 * @param name     Human-readable name, e.g., "Room 101 CO2 sensor"
 * @param type     Type of device, e.g. TEMP, HUMIDITY, CO2, MOTION
 * @param building Building identifier, e.g. "A" or "Main"
 * @param room     Room identifier within the building, e.g. "101"
 * @param active   Whether the device is currently active
 */
public record Device(String id, String name, DeviceType type, String building, String room, boolean active) {
}
