package com.mauto.ganksample;

import android.app.Application;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;

import java.io.File;

/**
 * Created by haohuidong on 18-9-26.
 */

public class GankApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initGlide();
    }

    private DiskCache mGlideDiskCache;
    private void initGlide() {
        File cachePath = new File(getExternalCacheDir() + "glide/");
        cachePath.mkdirs();
        mGlideDiskCache = DiskLruCacheWrapper.get(cachePath, 100 * 1024 * 1024);
        new GlideBuilder()
                .setDiskCache(new DiskCache.Factory() {
                    @Override
                    public DiskCache build() {
                        return mGlideDiskCache;
                    }
                });
    }
}
