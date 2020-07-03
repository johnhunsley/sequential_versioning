package com.hunsley.version.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.SortedSet;
import lombok.Data;

@Data
public abstract class SequentiallyVersioned implements Serializable {

  protected String guid;

  protected String serialVersionId;

  protected LocalDateTime updatedTime;

  protected SortedSet<? extends SequentiallyVersioned> versionHistory;

  public SortedSet<? extends SequentiallyVersioned> getVersionHistory() {
    return Collections.unmodifiableSortedSet(versionHistory);
  }

  public void setVersionHistory(SortedSet<? extends SequentiallyVersioned> versionHistory) {
    throw new UnsupportedOperationException("Cannot set the version history");
  }
}
