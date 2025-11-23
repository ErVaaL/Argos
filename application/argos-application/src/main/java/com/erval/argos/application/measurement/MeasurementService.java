package com.erval.argos.application.measurement;

import java.time.Instant;

import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.application.port.in.commands.MeasurementCommandUseCase;
import com.erval.argos.core.application.port.in.queries.MeasurementQueryUseCase;
import com.erval.argos.core.application.port.out.DeviceRepositoryPort;
import com.erval.argos.core.application.port.out.MeasurementRepositoryPort;
import com.erval.argos.core.domain.measurement.Measurement;

/**
 * Application service coordinating measurement commands and queries.
 */
public record MeasurementService(MeasurementRepositoryPort measurementRepo, DeviceRepositoryPort deviceRepo)
        implements MeasurementCommandUseCase, MeasurementQueryUseCase {

    /**
     * Creates a measurement for a device, defaulting timestamp to now when absent.
     *
     * @param cmd incoming measurement data
     * @return persisted measurement
     * @throws IllegalArgumentException if the device referenced by the command is
     *                                  missing
     */
    @Override
    public Measurement createMeasurement(CreateMeasurementCommand cmd) {
        deviceRepo().findById(cmd.deviceId())
                .orElseThrow(() -> new IllegalArgumentException("Device not found: " + cmd.deviceId()));

        Instant timestamp = cmd.timestamp() != null
                ? cmd.timestamp()
                : Instant.now();

        Measurement measurement = new Measurement(
                cmd.deviceId(),
                cmd.type(),
                cmd.value(),
                timestamp);

        return measurementRepo().save(measurement);
    }

    /**
     * Finds measurements matching the given filter and pagination settings.
     *
     * @param filter      criteria to apply
     * @param pageRequest pagination parameters
     * @return measurements matching the filter wrapped in a paginated result
     */
    @Override
    public PageResult<Measurement> findMeasurements(MeasurementFilter filter, PageRequest pageRequest) {
        return measurementRepo().findByFilter(filter, pageRequest);
    }

}
