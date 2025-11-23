package com.erval.argos.api.dto;

import com.erval.argos.core.domain.device.DeviceType;

/**
 * GraphQL input for filtering devices.
 */
public record DeviceFilterInput(
        String building,
        String room,
        DeviceType type,
        Boolean active) {
}
