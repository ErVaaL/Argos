package com.erval.argos.api.controllers;

import java.util.Set;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.erval.argos.api.dto.CreateDeviceInput;
import com.erval.argos.api.dto.CreateMeasurementInput;
import com.erval.argos.api.dto.DeviceFilterInput;
import com.erval.argos.api.dto.MeasurementFilterInput;
import com.erval.argos.api.dto.PageRequestInput;
import com.erval.argos.api.dto.UpdateDeviceInput;
import com.erval.argos.core.application.PageRequest;
import com.erval.argos.core.application.PageResult;
import com.erval.argos.core.application.port.in.commands.DeviceCommandUseCase;
import com.erval.argos.core.application.port.in.commands.MeasurementCommandUseCase;
import com.erval.argos.core.application.port.in.queries.DeviceQueryUseCase;
import com.erval.argos.core.application.port.in.queries.MeasurementQueryUseCase;
import com.erval.argos.core.domain.device.Device;
import com.erval.argos.core.domain.measurement.Measurement;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
/**
 * GraphQL controller exposing queries and mutations for devices and measurements.
 */
public class DeviceGraphqlController {

    private static final String DEFAULT_DEVICE_SORT = "name";
    private static final String DEFAULT_MEASUREMENT_SORT = "timestamp";

    private final DeviceCommandUseCase deviceCommandUseCase;
    private final DeviceQueryUseCase deviceQueryUseCase;
    private final MeasurementCommandUseCase measurementCommandUseCase;
    private final MeasurementQueryUseCase measurementQueryUseCase;

    @QueryMapping
    public PageResult<Device> devices(
            @Argument("filter") DeviceFilterInput filter,
            @Argument("page") PageRequestInput pageInput) {
        PageRequest pageRequest = GraphqlMapper.toPageRequest(pageInput, DEFAULT_DEVICE_SORT);
        return deviceQueryUseCase.findDevices(GraphqlMapper.toDeviceFilter(filter), pageRequest);
    }

    @QueryMapping
    public PageResult<Measurement> measurements(
            @Argument("filter") MeasurementFilterInput filter,
            @Argument("page") PageRequestInput pageInput) {
        PageRequest pageRequest = normalizeMeasurementSort(GraphqlMapper.toPageRequest(pageInput, DEFAULT_MEASUREMENT_SORT));
        return measurementQueryUseCase.findMeasurements(GraphqlMapper.toMeasurementFilter(filter), pageRequest);
    }

    @MutationMapping
    public Device createDevice(@Argument("input") CreateDeviceInput input) {
        return deviceCommandUseCase.createDevice(GraphqlMapper.toCreateDeviceCommand(input));
    }

    @MutationMapping
    public Device updateDevice(@Argument("id") String id, @Argument("input") UpdateDeviceInput input) {
        return deviceCommandUseCase.updateDevice(id, GraphqlMapper.toUpdateDeviceCommand(input));
    }

    @MutationMapping
    public Boolean deleteDevice(@Argument("id") String id) {
        deviceCommandUseCase.deleteDevice(id);
        return true;
    }

    @MutationMapping
    public Measurement createMeasurement(@Argument("input") CreateMeasurementInput input) {
        return measurementCommandUseCase.createMeasurement(GraphqlMapper.toCreateMeasurementCommand(input));
    }

    private PageRequest normalizeMeasurementSort(PageRequest pageRequest) {
        Set<String> allowed = Set.of("timestamp", "value", "type", "deviceId");
        if (pageRequest.sortBy() != null && allowed.contains(pageRequest.sortBy())) {
            return pageRequest;
        }
        return new PageRequest(pageRequest.page(), pageRequest.size(), DEFAULT_MEASUREMENT_SORT, pageRequest.direction());
    }
}
