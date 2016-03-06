package com.lybeat.huaban.ui;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Author: lybeat
 * Date: 2016/3/2
 */
public class BaseFragment extends Fragment {

    public boolean isHasTabLayout() {
        return false;
    }

    public interface LoadTabLayout {
        void loadTabLayout(View view);
    }
}