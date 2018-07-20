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

package io.github.ma1uta.matrix.android.model.common.events.nested;

import com.google.gson.annotations.SerializedName;

/**
 * Public keys.
 */
public class PublicKeys {

    /**
     * An optional URL which can be fetched, with querystring public_key=public_key, to validate whether the key has been revoked.
     * The URL must return a JSON object containing a boolean property named 'valid'. If this URL is absent, the key must be
     * considered valid indefinitely.
     */
    @SerializedName("key_validity_url")
    public String keyValidityUrl;

    /**
     * Required. A base-64 encoded ed25519 key with which token may be signed.
     */
    public String publicKey;
}
