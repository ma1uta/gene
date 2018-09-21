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

package io.github.ma1uta.matrix.gene.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Authenticates the user, and issues an access token they can use to authorize themself in subsequent requests.
 *
 * @author ma1uta
 */
public class LoginRequest {

    /**
     * Required. The login type being used. One of: ["m.login.password", "m.login.token"].
     */
    public String type;

    /**
     * Identification information for the user.
     */
    public Identifier identifier;

    /**
     * Required when type is m.login.password. The user's password.
     */
    public CharSequence password;

    /**
     * Required when type is m.login.token. The login token.
     */
    public String token;

    /**
     * ID of the client device. If this does not correspond to a known client device, a new device will be created.
     * The server will auto-generate a device_id if this is not specified.
     */
    @SerializedName("device_id")
    public String deviceId;

    /**
     * A display name to assign to the newly-created device. Ignored if device_id corresponds to a known device.
     */
    @SerializedName("initial_device_display_name")
    public String initialDeviceDisplayName;
}
