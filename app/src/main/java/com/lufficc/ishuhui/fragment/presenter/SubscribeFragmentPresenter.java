package com.lufficc.ishuhui.fragment.presenter;

import android.support.annotation.NonNull;

import com.lufficc.ishuhui.data.source.comic.ComicsDataSource;
import com.lufficc.ishuhui.data.source.comic.ComicsRepository;
import com.lufficc.ishuhui.fragment.IView.IView;
import com.lufficc.ishuhui.model.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcc_luffy on 2016/2/1.
 */
public class SubscribeFragmentPresenter {
    private IView<List<Comic>> iView;
    public SubscribeFragmentPresenter(@NonNull IView<List<Comic>> iView) {
        this.iView = iView;
    }

    public void getSubscribedComics() {
        ComicsRepository.getInstance().getSubscribedComics(new ComicsDataSource.LoadComicsCallback() {
            @Override
            public void onComicLoaded(List<Comic> comics) {
                iView.onSuccess(comics);
            }

            @Override
            public void onComicsEmpty() {
                iView.onSuccess(new ArrayList<Comic>());
            }

            @Override
            public void onLoadedFailed(Throwable throwable) {
                iView.onFailure(throwable);
            }
        });
    }

    public void onDestroy() {
        iView = null;
    }

    public void refreshSubscribedComics() {
        ComicsRepository.getInstance().refreshSubscribedComics();
    }
}
