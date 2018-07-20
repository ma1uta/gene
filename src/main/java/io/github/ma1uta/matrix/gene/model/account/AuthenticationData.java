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

package io.github.ma1uta.matrix.gene.model.account;

import com.google.gson.annotations.SerializedName;

/**
 * Authentication Data.
 */
public class AuthenticationData {

    /**
     * AuthType.
     */
    public String type;

    /**
     * Session.
     */
    public String session;

    /**
     * User id.
     */
    public String user;

    /**
     * Password.
     */
    public String password;

    /**
     * 3Pid address type.
     */
    public String medium;

    /**
     * 3Pid address.
     */
    public String address;

    /**
     * Capthca response.
     */
    public String response;

    /**
     * Auth token.
     */
    public String token;

    /**
     * Transaction id.
     */
    @SerializedName("txn_id")
    public String txnId;

    /**
     * OAuth uri.
     */
    public String uri;

    /**
     * 3Pids credentials.
     */
    public ThreePidCred[] threepidCreds;
}
