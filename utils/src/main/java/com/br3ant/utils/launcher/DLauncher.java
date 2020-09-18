package com.br3ant.utils.launcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


/**
 * <pre>
 *     copyright: datedu
 *     @author : houqiqi
 *     e-mail : xxx@xx
 *     time   : 2019-11-21
 *     desc   : Activity跳转封装类，把OnActivityResult方式改为Callback方式
 *     version: 1.0
 * </pre>
 */
public class DLauncher {
    private static final String TAG = "DLauncher";
    private Context mContext;

    private DRouterX mRouterFragmentX;

    public static DLauncher init(Fragment fragment) {
        return init(fragment.requireActivity());
    }

    public static DLauncher init(Activity activity) {
        return init((FragmentActivity) activity);
    }

    public static DLauncher init(FragmentActivity activity) {
        return new DLauncher(activity);
    }


    private DLauncher(FragmentActivity activity) {
        mContext = activity;
        mRouterFragmentX = getRouterFragmentX(activity);
    }

    private DRouterX getRouterFragmentX(FragmentActivity activity) {
        DRouterX routerFragment = findRouterFragmentX(activity);
        if (routerFragment == null) {
            routerFragment = DRouterX.newInstance();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(routerFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return routerFragment;
    }

    private DRouterX findRouterFragmentX(FragmentActivity activity) {
        return (DRouterX) activity.getSupportFragmentManager().findFragmentByTag(TAG);
    }

    public void startActivityForResult(Class<?> clazz, Callback callback) {
        Intent intent = new Intent(mContext, clazz);
        startActivityForResult(intent, callback);
    }

    public void startActivityForResult(Intent intent, Callback callback) {
        if (mRouterFragmentX != null) {
            mRouterFragmentX.startActivityForResult(intent, callback);
        } else {
            throw new RuntimeException("please do init first!");
        }
    }

    public interface Callback {
        void onActivityResult(int resultCode, @Nullable Intent data);
    }
}
