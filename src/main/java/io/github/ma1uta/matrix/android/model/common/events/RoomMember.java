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
import io.github.ma1uta.matrix.android.model.common.events.nested.Invite;

/**
 * Adjusts the membership state for a user in a room. It is preferable to use the membership APIs (/rooms/&lt;room id&gt;/invite etc)
 * when performing membership actions rather than adjusting the state directly as there are a restricted set of valid transformations.
 * For example, user A cannot force user B to join a room, and trying to force this state change directly will fail.
 */
public class RoomMember implements EventContent {

    /**
     * The avatar URL for this user, if any. This is added by the homeserver.
     */
    @SerializedName("avatar_url")
    public String avatarUrl;

    /**
     * The display name for this user, if any. This is added by the homeserver.
     */
    public String displayname;

    /**
     * Required. The membership state of the user. One of: ["invite", "join", "knock", "leave", "ban"].
     */
    public String membership;

    /**
     * Flag indicating if the room containing this event was created with the intention of being a direct chat. See Direct Messaging.
     */
    @SerializedName("is_direct")
    public Boolean isDirect;

    /**
     * Third-party invites.
     */
    @SerializedName("third_party_invite")
    public Invite thirdPartyInvite;
}
