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
 * Application service for device-related use cases.
 */
public record DeviceService(DeviceRepositoryPort repo) implements DeviceCommandUseCase, DeviceQueryUseCase {

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

    @Override
    public void deleteDevice(String id) {
        repo.deleteById(id);
    }

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

    @Override
    public PageResult<Device> findDevices(DeviceFilter filter, PageRequest pageRequest) {
        return repo.findByFilter(filter, pageRequest);
    }
}
