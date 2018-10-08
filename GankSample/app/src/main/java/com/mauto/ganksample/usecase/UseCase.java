package com.mauto.ganksample.usecase;

import com.mauto.ganksample.presenter.IView;
import com.mauto.ganksample.presenter.RenderAdapter;
import com.mauto.ganksample.repository.model.BaseModel;

public abstract class UseCase<T extends BaseModel> {
    public abstract void renderUi(IView<T> view);
    public void renderUi(RenderAdapter<T> adapter) {};
    protected abstract void fetchData();
}
