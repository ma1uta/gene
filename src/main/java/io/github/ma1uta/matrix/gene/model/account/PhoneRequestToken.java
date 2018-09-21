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
 * Request for the proxies the identity server API validate/msisdn/requestToken.
 */
public class PhoneRequestToken {

    /**
     * The ID server to send the onward request to as a hostname with an appended colon and port number if the port is not the default.
     */
    @SerializedName("id_server")
    public String idServer;

    /**
     * Required. Client-generated secret string used to protect this session.
     */
    @SerializedName("client_secret")
    public String clientSecret;

    /**
     * Required. The two-letter uppercase ISO country code that the number in phone_number should be parsed as if it were dialled from.
     */
    public String country;

    /**
     * Required. The phone number to validate.
     */
    public String phone;

    /**
     * Required. Used to distinguish protocol level retries from requests to re-send the email.
     */
    @SerializedName("send_attempt")
    public String sendAttempt;
}
