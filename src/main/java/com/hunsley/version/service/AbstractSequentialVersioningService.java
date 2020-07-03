package com.hunsley.version.service;

import com.hunsley.version.model.view.SequentiallyVersioned;

public interface AbstractSequentialVersioningService<T extends SequentiallyVersioned> {

  T getCurrentVersion(String guid);

  T getAllVersions(String guid);

  void saveOrUpdate(T sequentiallyVersioned);

}
