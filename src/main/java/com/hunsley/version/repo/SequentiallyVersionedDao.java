package com.hunsley.version.repo;

import static java.lang.String.format;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public abstract class SequentiallyVersionedDao<T extends SequentiallyVersionedEntity> {
  protected final SequentiallyVersionedJpaRepository<T> sequentiallyVersionedJpaRepository;
  private final Clock clock;

  public SequentiallyVersionedDao(SequentiallyVersionedJpaRepository<T> sequentiallyVersionedJpaRepository,
                                  Clock clock) {
    this.sequentiallyVersionedJpaRepository = sequentiallyVersionedJpaRepository;
    this.clock = clock;
  }

  public final T saveOrUpdate(final T entity) {
    return entity.getSerialVersionId() == null ? save(entity) : update(entity);
  }

  /**
   *
   * @param entity
   * @return The given entity of type T associated to the session
   */
  protected final T save(final T entity) {
    entity.setSerialVersionId(generateSerialVersionId());
    String guid = entity.getGuid();

    if (StringUtils.isEmpty(guid)) {
      guid = generateSerialVersionId();
      entity.setGuid(guid);
    }

    if (!CollectionUtils.isEmpty(sequentiallyVersionedJpaRepository.findAllVersionsByGuid(guid))) {
      throw new RuntimeException(format("Persistent entity of type %s already has guid %s",
          entity.getClass().getName(), guid));
    }

    entity.setPersistenceTime(Timestamp.valueOf(ZonedDateTime.now(clock).toLocalDateTime()));
    return sequentiallyVersionedJpaRepository.save(entity);
  }

  protected final T update(final T entity) {

    // check guid already exists - get all by guid
    if (CollectionUtils.isEmpty(sequentiallyVersionedJpaRepository.findAllVersionsByGuid(entity.getGuid()))) {
      throw new RuntimeException(format("Attempt to update an entity with guid %s which does not exist",
          entity.getGuid()));
    }
    //clone entity

    T clone = SerializationUtils.clone(entity);
    clone.setSerialVersionId(generateSerialVersionId());

    // get the update time

    //order all by guid and find where the clone goes

    //update each entity and persist the new clone
  }


  private String generateSerialVersionId() {
    UUID serialVersionId ;

    do {
      serialVersionId = UUID.randomUUID();
    } while (sequentiallyVersionedJpaRepository.findById(serialVersionId.toString()).isPresent());

    return serialVersionId.toString();
  }
}
