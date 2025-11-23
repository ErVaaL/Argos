package com.erval.argos.api.dto;

import com.erval.argos.core.domain.measurement.MeasurementType;

/**
 * GraphQL input used to filter measurements.
 */
public record MeasurementFilterInput(
        String deviceId,
        MeasurementType type,
        String from,
        String to) {
}
