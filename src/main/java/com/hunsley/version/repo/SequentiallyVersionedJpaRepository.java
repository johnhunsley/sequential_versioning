package com.hunsley.version.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface SequentiallyVersionedJpaRepository<T extends SequentiallyVersionedEntity> extends JpaRepository<T, String> {


  @Query("select s from SequentiallyVersionedEntity s where s.guid = :guid and s.nextSerialVersionId is null")
  T findCurrentByGuid(String guid);

  @Query("select s from SequentiallyVersionedEntity s where s.guid = :guid order by s.updateTime asc")
  List<T> findAllVersionsByGuid(String guid);
}
