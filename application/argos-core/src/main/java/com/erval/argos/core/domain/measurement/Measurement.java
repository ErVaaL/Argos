package com.erval.argos.core.domain.measurement;

import java.time.Instant;

/**
 * Single measurement produced by a device at a given moment in time.
 * <p>
 * Payload expectations:
 * <ul>
 * <li>{@code deviceId}: source device identifier</li>
 * <li>{@code type}: metric category (e.g., {@link MeasurementType#TEMP})</li>
 * <li>{@code value}: numeric reading captured at {@code timestamp}</li>
 * </ul>
 */
public record Measurement(String deviceId, MeasurementType type, double value, Instant timestamp) {
}
