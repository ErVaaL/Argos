package com.erval.argos.api.dto;

import com.erval.argos.core.domain.device.DeviceType;

/**
 * GraphQL input for creating devices.
 */
public record CreateDeviceInput(
        String name,
        DeviceType type,
        String building,
        String room) {
}
