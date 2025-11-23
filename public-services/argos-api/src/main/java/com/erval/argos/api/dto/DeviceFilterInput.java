package com.erval.argos.api.dto;

import com.erval.argos.core.domain.device.DeviceType;

public record DeviceFilterInput(
        String building,
        String room,
        DeviceType type,
        Boolean active) {
}
