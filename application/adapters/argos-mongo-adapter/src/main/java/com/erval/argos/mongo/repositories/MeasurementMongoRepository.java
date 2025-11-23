package com.erval.argos.mongo.repositories;

import com.erval.argos.mongo.model.MeasurementDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for storing {@link MeasurementDocument} instances.
 * <p>
 * Uses generated CRUD methods; domain-specific filtering is implemented via the
 * adapter.
 */
@Repository
public interface MeasurementMongoRepository extends MongoRepository<MeasurementDocument, String> {

}
