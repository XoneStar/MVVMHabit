package me.goldze.mvvmhabit.base;

import android.app.Application;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import org.jetbrains.annotations.NotNull;

/**
 * Created by goldze on 2018/9/30.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }


    private ViewModelFactory(Application application) {
        mApplication = application;
    }

    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> cls) {
        T t = cls.cast(new BaseViewModel(mApplication));
        if (t == null) {
            throw new NullPointerException("Created ViewModel is NULL!");
        }
        return t;
    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }
}
