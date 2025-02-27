/*
 * Copyright (C) 2021 Fusion OS
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
import android.content.Intent;
import android.net.Uri;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class aospbVersionPreferenceController extends BasePreferenceController {

    private static final String TAG = "aospbVersionPreferenceController";
    private static final String DEVICE_PROPERTY = "ro.custom.device";
    private static final String BUILD_TYPE_PROPERTY = "ro.build.type";
    private static final String BUILD_INCREMENTAL_PROPERTY = "ro.custom.incremental";
    private static final String AOSPB_WEBSITE_URL = "https://github.com/aospb-project";

    public aospbVersionPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    // @Override
    // public CharSequence getSummary() {
    //     String deviceProperty = SystemProperties.get(DEVICE_PROPERTY,
    //             this.mContext.getString(R.string.device_info_default));
    //     String buildTypeProperty = SystemProperties.get(BUILD_TYPE_PROPERTY,
    //             this.mContext.getString(R.string.device_info_default));
    //     String buildIncrementalProperty = SystemProperties.get(BUILD_INCREMENTAL_PROPERTY,
    //             this.mContext.getString(R.string.device_info_default));
    //     String showVersion = deviceProperty + " | " + buildTypeProperty + " | " + buildIncrementalProperty + " | " + dateProperty;
    //     return showVersion;
    // }

    @Override
    public void updateState(Preference preference) {
        super.updateState(preference);

        String deviceProperty = SystemProperties.get(DEVICE_PROPERTY,
                this.mContext.getString(R.string.device_info_default));
        String buildTypeProperty = SystemProperties.get(BUILD_TYPE_PROPERTY,
                this.mContext.getString(R.string.device_info_default));
        String buildIncrementalProperty = SystemProperties.get(BUILD_INCREMENTAL_PROPERTY,
                this.mContext.getString(R.string.device_info_default));

        String showVersion = deviceProperty + " | " + buildTypeProperty + " | " + buildIncrementalProperty;

        preference.setSummary(showVersion);

        // Listener to open the URL
        preference.setOnPreferenceClickListener(pref -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AOSPB_WEBSITE_URL));
            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                mContext.startActivity(intent);
            }
            return true;
        });
    }
}
