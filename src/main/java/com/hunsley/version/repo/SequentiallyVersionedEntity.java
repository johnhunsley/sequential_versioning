package com.hunsley.version.repo;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class SequentiallyVersionedEntity implements Serializable, Comparable<SequentiallyVersionedEntity> {


  @Column(name = "guid", nullable = false)
  private String guid;

  @Id
  @Column(name = "serial_version_id")
  private String serialVersionId;

  @Column(name = "next_serial_version_id", nullable = false)
  private String nextSerialVersionId;

  @Column(name = "previous_serial_version_id", nullable = false)
  private String previousSerialVersionId;

  @Column(name = "update_time", nullable = false)
  private Timestamp updateTime;

  @Column(name = "persistence_time", nullable = false)
  private Timestamp persistenceTime;

  @Override
  public int compareTo(SequentiallyVersionedEntity that) {
    return this.updateTime.compareTo(that.updateTime);
  }
}
