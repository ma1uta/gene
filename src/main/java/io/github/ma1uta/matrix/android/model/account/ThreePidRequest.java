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
 * Request for adds contact information to the user's account.
 */
public class ThreePidRequest {

    /**
     * Required. The third party credentials to associate with the account.
     */
    @SerializedName("three_pid_creds")
    public ThreePidCred[] threePidCreds;

    /**
     * Whether the homeserver should also bind this third party identifier to the account's Matrix ID with the passed identity server.
     * Default: false.
     */
    public Boolean bind;
}
