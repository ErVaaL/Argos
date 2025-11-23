package com.erval.argos.core.application.port.in.commands;

import com.erval.argos.core.domain.device.Device;
import com.erval.argos.core.domain.device.DeviceType;

/**
 * Use cases related to creating, updating and deleting devices.
 */
public interface DeviceCommandUseCase {

    Device createDevice(CreateDeviceCommand cmd);

    Device updateDevice(String id, UpdateDeviceCommand cmd);

    void deleteDevice(String id);

    /**
     * Command used for device creation.
     *
     * @param name     device name
     * @param type     device type
     * @param building building where the device is located
     * @param room     room where the device is located
     */
    record CreateDeviceCommand(
            String name,
            DeviceType type,
            String building,
            String room) {
    }

    /**
     * Command used for device update.
     * Fields may be null if they should not be changed.
     *
     * @param name     device name
     * @param type     device type
     * @param building building where the device is located
     * @param room     room where the device is located
     * @param active   whether the device is active
     */
    record UpdateDeviceCommand(
            String name,
            DeviceType type,
            String building,
            String room,
            Boolean active) {
    }

}
