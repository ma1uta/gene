/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.matrix.android.model.device;

import com.google.gson.annotations.SerializedName;

/**
 * Device.
 */
public class Device {

    /**
     * Required. Identifier of this device.
     */
    @SerializedName("device_id")
    public String deviceId;

    /**
     * Display name set by the user for this device. Absent if no name has been set.
     */
    @SerializedName("display_name")
    public String displayName;

    /**
     * The IP address where this device was last seen. (May be a few minutes out of date, for efficiency reasons).
     */
    @SerializedName("last_seen_ip")
    public String lastSeenIp;

    /**
     * The timestamp (in milliseconds since the unix epoch) when this devices was last seen. (May be a few minutes out of date,
     * for efficiency reasons).
     */
    @SerializedName("last_seen_ts")
    public Long lastSeenTs;
}
