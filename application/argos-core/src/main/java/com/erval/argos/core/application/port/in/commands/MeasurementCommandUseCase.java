package com.erval.argos.core.application.port.in.commands;

import java.time.Instant;

import com.erval.argos.core.domain.measurement.Measurement;
import com.erval.argos.core.domain.measurement.MeasurementType;

/**
 * Use case for ingesting measurements from devices or an IoT gateway.
 */
public interface MeasurementCommandUseCase {

    /**
     * Stores a new measurement for a device.
     *
     * @param command command describing the measurement
     * @return stored measurement
     */
    Measurement createMeasurement(CreateMeasurementCommand command);

    /**
     * Command used for creating a measurement.
     *
     * @param deviceId  ID of the device that produced the measurement
     * @param type      type of the measurement
     * @param value     value of the measurement
     * @param timestamp timestamp when the measurement was taken
     */
    record CreateMeasurementCommand(
            String deviceId,
            MeasurementType type,
            double value,
            Instant timestamp) {
    }

}
