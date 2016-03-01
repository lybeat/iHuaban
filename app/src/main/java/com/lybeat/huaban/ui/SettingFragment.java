package com.lybeat.huaban.ui;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.lybeat.huaban.R;

/**
 * Author: lybeat
 * Date: 2016/2/28
 */
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_setting);
    }
}
