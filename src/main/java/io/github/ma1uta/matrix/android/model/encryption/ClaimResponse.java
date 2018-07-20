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

import java.util.Map;

/**
 * Response format.
 */
public class ClaimResponse {

    /**
     * If any remote homeservers could not be reached, they are recorded here. The names of the properties are the names of the
     * unreachable servers.
     * <p/>
     * If the homeserver could be reached, but the user or device was unknown, no failure is recorded. Instead, the corresponding
     * user or device is missing from the one_time_keys result.
     */
    public Map<String, Object> failures;

    /**
     * One-time keys for the queried devices. A map from user ID, to a map from &lt;algorithm&gt;:&lt;key_id&gt; to the key object.
     */
    @SerializedName("one_time_keys")
    public Map<String, Map<String, Object>> oneTimeKeys;
}
