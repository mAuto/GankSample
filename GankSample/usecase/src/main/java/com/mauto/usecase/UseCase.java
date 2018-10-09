package com.mauto.usecase;


import com.mauto.tools.Preconditions;
import com.mauto.usecase.executor.PostExecutor;
import com.mauto.usecase.executor.TaskExecutor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, P> {

  private final TaskExecutor taskExecutor;
  private final PostExecutor postExecutor;
  private final CompositeDisposable disposables;

  public UseCase(TaskExecutor taskExecutor, PostExecutor postExecutor) {
    this.taskExecutor = taskExecutor;
    this.postExecutor = postExecutor;
    this.disposables = new CompositeDisposable();
  }

  public UseCase(PostExecutor postExecutor) {
    this.taskExecutor = null;
    this.postExecutor = postExecutor;
    this.disposables = new CompositeDisposable();
  }

  protected abstract Observable<T> buildUseCaseObservable(P params);

  public void execute(DisposableObserver<T> observer, P params) throws NullPointerException {
    Preconditions.checkNotNull(observer);

    Scheduler taskScheduler;
    if (taskExecutor != null)
      taskScheduler = Schedulers.from(taskExecutor);
    else
      taskScheduler = Schedulers.newThread();// Schedulers.io()，用哪个是个问题

    final Observable<T> observable = this.buildUseCaseObservable(params)
        .subscribeOn(taskScheduler)
        .observeOn(postExecutor.getScheduler());
    addDisposable(observable.subscribeWith(observer));
  }

  public void dispose() {
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
  }

  private void addDisposable(Disposable disposable) {
    if (disposable == null || disposables == null)
      return;
    disposables.add(disposable);
  }
}
