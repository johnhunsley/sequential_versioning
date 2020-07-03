package com.hunsley.version.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.SortedSet;
import lombok.Data;

@Data
public class Widget implements Serializable {

  private String guid;

  private String serialVersionId;

  private String name;

  private LocalDateTime updatedTime;

  private SortedSet<Widget> versionHistory;
}
