package com.hunsley.version.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class SequentiallyVersionedEntity implements Serializable {

  @Id
  @Column(name = "id")
  private String guid;

  @Column(name = "serial_version_id")
  private String serialVersionId;

  @Column(name = "next_serial_version_id")
  private String nextSerialVersionId;

  @Column(name = "previous_serial_version_id")
  private String previousSerialVersionId;

  @Column(name = "update_time")
  private Timestamp updateTime;

  @Column(name = "persistence_time")
  private Timestamp persistenceTime;
}
