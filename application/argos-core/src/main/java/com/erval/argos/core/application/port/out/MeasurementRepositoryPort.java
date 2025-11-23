package com.erval.argos.core.application.port.out;

import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.application.port.in.queries.MeasurementQueryUseCase.MeasurementFilter;
import com.erval.argos.core.domain.measurement.Measurement;

import java.util.Optional;

/**
 * Persistence port for measurements.
 */
public interface MeasurementRepositoryPort {

    Measurement save(Measurement measurement);

    PageResult<Measurement> findByFilter(MeasurementFilter filter, PageRequest pageRequest);

    Optional<Measurement> findById(String id);

    PageResult<Measurement> findAll(PageRequest pageRequest);

    void deleteById(String id);

    void deleteByDeviceId(String deviceId);
}
