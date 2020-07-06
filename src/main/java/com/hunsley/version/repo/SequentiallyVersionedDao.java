package com.hunsley.version.repo;

import static java.lang.String.format;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.UUID;
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
  private final T save(final T entity) {
    final String serialVersionId = generateRandomUUID();

    if (sequentiallyVersionedJpaRepository.findById(serialVersionId).isPresent()) {
      throw new RuntimeException(format("Persistent entity of type %s already has version %s",
          entity.getClass().getName(), serialVersionId));
    }

    String guid = entity.getGuid();

    if (StringUtils.isEmpty(guid)) {
      guid = generateRandomUUID();
      entity.setGuid(guid);
    }

    if (!sequentiallyVersionedJpaRepository.findAllVersionsByGuid(guid).isEmpty()) {
      throw new RuntimeException(format("Persistent entity of type %s already has guid %s",
          entity.getClass().getName(), guid));
    }

    entity.setPersistenceTime(Timestamp.valueOf(ZonedDateTime.now(clock).toLocalDateTime()));
    return sequentiallyVersionedJpaRepository.save(entity);
  }

  private final T update(final T entity) {

    // check guid already exists - get all by guid

    //clone entity

    //set serialVersionId

    // get the update time

    //order all by guid and find where the clone goes

    //update each entity and persist the new clone
  }

  private static String generateRandomUUID() {
    return UUID.randomUUID().toString();
  }
}
