package com.mauto.usecase.executor;

import io.reactivex.Scheduler;


public interface PostExecutor {
  Scheduler getScheduler();
}
