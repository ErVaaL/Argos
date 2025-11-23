package com.erval.argos.api.dto;

import com.erval.argos.core.domain.measurement.MeasurementType;

/**
 * GraphQL input for creating measurements.
 */
public record CreateMeasurementInput(
        String deviceId,
        MeasurementType type,
        double value,
        String timestamp) {
}
