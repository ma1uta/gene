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

package io.github.ma1uta.matrix.android.model.encryption;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Device Keys.
 */
public class DeviceKeys {

    /**
     * Required. The ID of the user the device belongs to. Must match the user ID used when logging in.
     */
    @SerializedName("user_id")
    public String userId;

    /**
     * Required. The ID of the device these keys belong to. Must match the device ID used when logging in.
     */
    @SerializedName("device_id")
    public String deviceId;

    /**
     * Required. The encryption algorithms supported by this device.
     */
    public List<String> algorithms;

    /**
     * Required. Public identity keys. The names of the properties should be in the format &lt;algorithm&gt;:&lt;device_id&gt;.
     * The keys themselves should be encoded as specified by the key algorithm.
     */
    public Map<String, String> keys;

    /**
     * Required. Signatures for the device key object. A map from user ID, to a map from (algorithm):(device_id) to the signature.
     * <p/>
     * The signature is calculated using the process described at Signing JSON.
     */
    public Map<String, Map<String, String>> signatures;

    /**
     * Additional data added to the device key information by intermediate servers, and not covered by the signatures.
     */
    public UnsignedDeviceInfo unsignedDeviceInfo;
}
