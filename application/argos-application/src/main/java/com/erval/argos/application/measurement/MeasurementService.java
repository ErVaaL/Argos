package com.erval.argos.application.measurement;

import java.time.Instant;

import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.application.port.in.commands.MeasurementCommandUseCase;
import com.erval.argos.core.application.port.in.queries.MeasurementQueryUseCase;
import com.erval.argos.core.application.port.out.DeviceRepositoryPort;
import com.erval.argos.core.application.port.out.MeasurementRepositoryPort;
import com.erval.argos.core.domain.measurement.Measurement;

public record MeasurementService(MeasurementRepositoryPort measurementRepo, DeviceRepositoryPort deviceRepo)
        implements MeasurementCommandUseCase, MeasurementQueryUseCase {

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

    @Override
    public PageResult<Measurement> findMeasurements(MeasurementFilter filter, PageRequest pageRequest) {
        return measurementRepo().findByFilter(filter, pageRequest);
    }

}
