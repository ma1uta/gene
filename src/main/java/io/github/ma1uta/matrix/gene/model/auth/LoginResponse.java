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
 * Response for the login request.
 *
 * @author ma1uta
 */
public class LoginResponse {

    /**
     * The fully-qualified Matrix ID that has been registered.
     */
    @SerializedName("user_id")
    public String userId;

    /**
     * An access token for the account. This access token can then be used to authorize other requests.
     */
    @SerializedName("access_token")
    public String accessToken;

    /**
     * The hostname of the homeserver on which the account has been registered.
     */
    @SerializedName("home_server")
    public String homeServer;

    /**
     * ID of the logged-in device. Will be the same as the corresponding parameter in the request, if one was specified.
     */
    @SerializedName("device_id")
    public String deviceId;
}
