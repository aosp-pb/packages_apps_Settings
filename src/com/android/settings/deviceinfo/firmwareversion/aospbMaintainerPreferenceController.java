/*
 * Copyright (C) 2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class aospbMaintainerPreferenceController extends BasePreferenceController {

    private static final String TAG = "aospbMaintainerPreferenceController";
    private static final String MAINTAINER_STRING = "ro.custom.maintainer";

    public aospbMaintainerPreferenceController(Context context, String key) {
        super(context, key);
    }

    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    public CharSequence getSummary() {
        String maintainer = SystemProperties.get(MAINTAINER_STRING,
                mContext.getResources().getString(R.string.aospb_maintainer));

        if (TextUtils.isEmpty(maintainer)) {
            maintainer = mContext.getString(R.string.device_info_default);
        }
        return maintainer;
    }
}
