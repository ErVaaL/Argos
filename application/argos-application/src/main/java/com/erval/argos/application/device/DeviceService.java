package com.erval.argos.application.device;

import java.util.UUID;

import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.application.port.in.commands.DeviceCommandUseCase;
import com.erval.argos.core.application.port.in.queries.DeviceQueryUseCase;
import com.erval.argos.core.application.port.out.DeviceRepositoryPort;
import com.erval.argos.core.domain.device.Device;

import com.erval.argos.core.domain.device.DeviceType;

/**
 * Application service orchestrating device commands and queries.
 */
public record DeviceService(DeviceRepositoryPort repo) implements DeviceCommandUseCase, DeviceQueryUseCase {

    /**
     * Creates a new device using the provided command data.
     *
     * @param cmd incoming device data
     * @return the persisted device
     */
    @Override
    public Device createDevice(CreateDeviceCommand cmd) {
        String id = UUID.randomUUID().toString();

        Device device = new Device(
                id,
                cmd.name(),
                cmd.type(),
                cmd.building(),
                cmd.room(),
                true);

        return repo.save(device);
    }

    /**
     * Removes a device by id.
     *
     * @param id identifier of the device to delete
     */
    @Override
    public void deleteDevice(String id) {
        repo.deleteById(id);
    }

    /**
     * Updates a device with new values, keeping existing fields when a command
     * value is null.
     *
     * @param id  identifier of the device to update
     * @param cmd incoming updates
     * @return the updated device
     * @throws IllegalArgumentException if the device does not exist
     */
    @Override
    public Device updateDevice(String id, UpdateDeviceCommand cmd) {
        Device existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Device not found: " + id));

        String name = cmd.name() != null ? cmd.name() : existing.name();
        DeviceType type = cmd.type() != null ? cmd.type() : existing.type();
        String building = cmd.building() != null ? cmd.building() : existing.building();
        String room = cmd.room() != null ? cmd.room() : existing.room();
        boolean active = cmd.active() != null ? cmd.active() : existing.active();

        Device updated = new Device(
                existing.id(),
                name,
                type,
                building,
                room,
                active);

        return repo.save(updated);
    }

    /**
     * Finds devices that match the given filter and pagination settings.
     *
     * @param filter      criteria to apply when searching
     * @param pageRequest pagination parameters
     * @return matching devices wrapped in a paginated result
     */
    @Override
    public PageResult<Device> findDevices(DeviceFilter filter, PageRequest pageRequest) {
        return repo.findByFilter(filter, pageRequest);
    }
}
