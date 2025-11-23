package com.erval.argos.api.dto;

import com.erval.argos.core.domain.device.DeviceType;

/**
 * GraphQL input for updating devices.
 */
public record UpdateDeviceInput(
        String name,
        DeviceType type,
        String building,
        String room,
        Boolean active) {
}
