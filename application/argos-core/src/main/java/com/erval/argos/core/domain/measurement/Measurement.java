package com.erval.argos.core.domain.measurement;

import java.time.Instant;

/**
 * Single measurement produced by a device at a given moment in time.
 */
public record Measurement(String deviceId, MeasurementType type, double value, Instant timestamp) {
}
