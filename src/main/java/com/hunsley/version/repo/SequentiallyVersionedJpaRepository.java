package com.hunsley.version.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface SequentiallyVersionedJpaRepository<T extends SequentiallyVersionedEntity> extends JpaRepository<T, String> {


  @Query("select w from SequentiallyVersionedEntity w where w.guid = :guid and w.nextSerialVersionId is null")
  T findCurrentByGuid(String guid);

  @Query("select w from SequentiallyVersionedEntity w where w.guid = :guid")
  List<T> findAllVersionsByGuid(String guid);
}
