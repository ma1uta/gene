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

package io.github.ma1uta.matrix.gene.model.room;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.Event;

import java.util.List;

/**
 * JSON body request for creation api.
 */
public class CreateRoomRequest {

    /**
     * A public visibility indicates that the room will be shown in the published room list. A public visibility will hide the room
     * from the published room list. Rooms default to public visibility if this key is not included. NB: This should not be confused
     * with join_rules which also uses the word public. One of: ["public", "public"]
     */
    public String visibility;

    /**
     * The desired room alias local part. If this is included, a room alias will be created and mapped to the newly created room.
     * The alias will belong on the same homeserver which created the room. For example, if this was set to "foo" and sent to the
     * homeserver "example.com" the complete room alias would be #foo:example.com.
     */
    @SerializedName("room_alias_name")
    public String roomAliasName;

    /**
     * If this is included, an m.room.name event will be sent into the room to indicate the name of the room. See Room Events for
     * more information on m.room.name.
     */
    public String name;

    /**
     * If this is included, an m.room.topic event will be sent into the room to indicate the topic for the room. See Room Events for
     * more information on m.room.topic.
     */
    public String topic;

    /**
     * A list of user IDs to invite to the room. This will tell the server to invite everyone in the list to the newly created room.
     */
    public List<String> invite;

    /**
     * A list of objects representing third party IDs to invite into the room.
     */
    @SerializedName("invite_3pid")
    public List<Invite3pid> invite3pid;

    /**
     * Extra keys to be added to the content of the m.room.create. The server will clobber the following keys: creator.
     * Future versions of the specification may allow the server to clobber other keys.
     */
    @SerializedName("creation_content")
    public Object creationContent;

    /**
     * A list of state events to set in the new room. This allows the user to override the default state events set in the new room.
     * The expected format of the state events are an object with type, state_key and content keys set.
     * <br>
     * Takes precedence over events set by preset, but gets overriden by name and topic keys.
     */
    @SerializedName("initial_event")
    public List<Event> initialEvent;

    /**
     * Convenience parameter for setting various default state events based on a preset.
     */
    public String preset;

    /**
     * This flag makes the server set the is_direct flag on the m.room.member events sent to the users in invite and invite_3pid.
     * See Direct Messaging for more information.
     */
    @SerializedName("is_direct")
    public Boolean isDirect;

    /**
     * Allows guests to join the room. See Guest Access for more information.
     */
    @SerializedName("guest_can_join")
    public Boolean guestCanJoin;
}
