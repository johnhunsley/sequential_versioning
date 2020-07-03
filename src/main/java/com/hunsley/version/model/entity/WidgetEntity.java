package com.hunsley.version.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "widget")
public class WidgetEntity implements Serializable {

  @Id
  @Column(name = "id")
  private String guid;

  @Column(name = "serial_version_id")
  private String serialVersionId;

  @Column(name = "next_serial_version_id")
  private String nextSerialVersionId;

  @Column(name = "previous_serial_version_id")
  private String previousSerialVersionId;

  @Column(name = "name")
  private String name;

  @Column(name = "update_time")
  private Timestamp updateTime;

  @Column(name = "persistence_time")
  private Timestamp persistenceTime;
}
