package com.erval.argos.core.application.port.out;

import java.util.Optional;

import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.application.port.in.queries.DeviceQueryUseCase.DeviceFilter;
import com.erval.argos.core.domain.device.Device;

/**
 * Persistence port for devices.
 */
public interface DeviceRepositoryPort {

    Device save(Device device);

    Optional<Device> findById(String id);

    void deleteById(String id);

    PageResult<Device> findByFilter(DeviceFilter filter, PageRequest pageRequest);
}
