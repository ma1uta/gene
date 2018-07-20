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

package io.github.ma1uta.matrix.android.model.account;

import com.google.gson.annotations.SerializedName;

/**
 * Request for register for an account on this homeserver.
 */
public class RegisterRequest {

    /**
     * Additional authentication information for the user-interactive authentication API. Note that this information is not used
     * to define how the registered user should be authenticated, but is instead used to authenticate the register call itself.
     * It should be left empty, or omitted, unless an earlier call returned an response with status code 401.
     */
    public AuthenticationData auth;

    /**
     * If true, the server binds the email used for authentication to the Matrix ID with the ID Server.
     */
    @SerializedName("bind_email")
    public Boolean bindEmail;

    /**
     * The basis for the localpart of the desired Matrix ID. If omitted, the homeserver MUST generate a Matrix ID local part.
     */
    public String username;

    /**
     * The desired password for the account.
     */
    public String password;

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
