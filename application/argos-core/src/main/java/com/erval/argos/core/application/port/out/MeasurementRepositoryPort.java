package com.erval.argos.core.application.port.out;

import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.application.port.in.queries.MeasurementQueryUseCase.MeasurementFilter;
import com.erval.argos.core.domain.measurement.Measurement;

/**
 * Persistence port for measurements.
 */
public interface MeasurementRepositoryPort {

    Measurement save(Measurement measurement);

    PageResult<Measurement> findByFilter(MeasurementFilter filter, PageRequest pageRequest);

    void deleteByDeviceId(String deviceId);
}
