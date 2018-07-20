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

package io.github.ma1uta.matrix.android.model.common.events;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.android.model.common.EventContent;
import io.github.ma1uta.matrix.android.model.common.events.nested.PublicKeys;

import java.util.List;

/**
 * Acts as an m.room.member invite event, where there isn't a target user_id to invite. This event contains a token and a
 * public key whose public key must be used to sign the token. Any user who can present that signature may use this invitation
 * to join the target room.
 */
public class RoomThirdPartyInvite implements EventContent {

    /**
     * Required. A user-readable string which represents the user who has been invited. This should not contain the user's
     * third party ID, as otherwise when the invite is accepted it would leak the association between the matrix ID and the third party ID.
     */
    @SerializedName("display_name")
    public String displayName;

    /**
     * Required. A URL which can be fetched, with querystring public_key=public_key, to validate whether the key has been revoked.
     * The URL must return a JSON object containing a boolean property named 'valid'.
     */
    @SerializedName("key_validity_url")
    public String keyValidityUrl;

    /**
     * Required. A base64-encoded ed25519 key with which token must be signed (though a signature from any entry in public_keys is
     * also sufficient). This exists for backwards compatibility.
     */
    @SerializedName("public_key")
    public String publicKey;

    /**
     * Keys with which the token may be signed.
     */
    @SerializedName("public_keys")
    public List<PublicKeys> publicKeys;
}
