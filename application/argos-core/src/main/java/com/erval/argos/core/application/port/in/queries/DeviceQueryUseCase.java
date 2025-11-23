package com.erval.argos.core.application.port.in.queries;

import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.domain.device.Device;
import com.erval.argos.core.domain.device.DeviceType;

/**
 * Use case for reading devices, used by REST / GraphQL layers.
 */
public interface DeviceQueryUseCase {

    PageResult<Device> findDevices(DeviceFilter filter, PageRequest pageRequest);

    /**
     * Filter criteria for querying devices.
     * All fields are optional. Null means "no filter" for that field.
     *
     * @param building the building to filter by
     * @param room     the room to filter by
     * @param type     the device type to filter by
     * @param active   the active status to filter by
     */
    record DeviceFilter(
            String building,
            String room,
            DeviceType type,
            Boolean active) {
    }
}
