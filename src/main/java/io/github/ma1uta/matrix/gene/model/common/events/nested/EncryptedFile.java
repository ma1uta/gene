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

import java.util.Map;

/**
 * Encrypted file.
 */
public class EncryptedFile {

    /**
     * Required. The URL to the file.
     */
    public String url;

    /**
     * Required. A JSON Web Key object.
     */
    public JWK key;

    /**
     * Required. The Initialisation Vector used by AES-CTR, encoded as unpadded base64.
     */
    public String iv;

    /**
     * Required. A map from an algorithm name to a hash of the ciphertext, encoded as unpadded base64.
     * Clients should support the SHA-256 hash, which uses the key sha256.
     */
    public Map<String, String> hashes;

    /**
     * Required. Version of the encrypted attachments protocol. Must be v2.
     */
    public String v;
}
