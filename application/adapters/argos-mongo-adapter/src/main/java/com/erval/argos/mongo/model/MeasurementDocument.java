package com.erval.argos.mongo.model;

import com.erval.argos.core.domain.measurement.Measurement;
import com.erval.argos.core.domain.measurement.MeasurementType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * MongoDB representation of a Measurement aggregate.
 * <p>
 * Stores measurement metadata and values to mirror domain invariants.
 */
@Data
@Document("measurements")
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDocument {

    @Id
    private String id;

    private String deviceId;
    private MeasurementType type;
    private double value;
    private Instant timestamp;

    /**
     * Creates a {@link MeasurementDocument} from a domain measurement.
     * <p>
     * The Mongo driver generates an {@code id}; therefore the id is left
     * {@code null}.
     *
     * @param m domain measurement
     * @return document ready for persistence
     */
    public static MeasurementDocument fromDomain(Measurement m) {
        return new MeasurementDocument(

                null,
                m.deviceId(),
                m.type(),
                m.value(),
                m.timestamp());
    }

    /**
     * Converts the document back to the immutable domain aggregate.
     *
     * @return a {@link Measurement} constructed from stored fields
     */
    public Measurement toDomain() {
        return new Measurement(
                deviceId,
                type,
                value,
                timestamp);
    }
}
