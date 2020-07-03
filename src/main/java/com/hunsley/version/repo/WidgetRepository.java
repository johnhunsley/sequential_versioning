package com.hunsley.version.repo;

import com.hunsley.version.model.entity.WidgetEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WidgetRepository extends JpaRepository<WidgetEntity, String> {


  @Query("select w from WidgetEntity w where w.guid = :guid and w.nextSerialVersionId is null")
  WidgetEntity findCurrentByGuid(String guid);

  @Query("select w from WidgetEntity w where w.guid = :guid")
  List<WidgetEntity> findAllVersionsByGuid(String guid);
}
