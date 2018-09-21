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

package io.github.ma1uta.matrix.gene.model.common.events.nested;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * JWK.
 */
public class JWK {

    /**
     * Required. Key type. Must be oct.
     */
    public String key;

    /**
     * Required. Key operations. Must at least contain encrypt and decrypt.
     */
    @SerializedName("key_opts")
    public List<String> keyOpts;

    /**
     * Required. Algorithm. Must be A256CTR.
     */
    public String alg;

    /**
     * Required. The key, encoded as urlsafe unpadded base64.
     */
    public String k;

    /**
     * Required. Extractable. Must be true. This is a W3C extension.
     */
    public Boolean ext;
}
