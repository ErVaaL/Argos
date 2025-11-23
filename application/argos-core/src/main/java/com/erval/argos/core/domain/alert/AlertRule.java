package com.erval.argos.core.domain.alert;

import com.erval.argos.core.domain.measurement.MeasurementType;
import jdk.dynalink.linker.ConversionComparator;

public record AlertRule(String id, String deviceId, MeasurementType type, double threshold, ConversionComparator.Comparison comparison, boolean active) {
}
