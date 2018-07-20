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

import java.util.List;

/**
 * Public rooms chunk.
 */
public class PublicRoomsChunk {

    /**
     * Aliases of the room. May be empty.
     */
    public List<String> aliases;

    /**
     * The canonical alias of the room, if any.
     */
    @SerializedName("canonical_alias")
    public String canonicalAlias;

    /**
     * The name of the room, if any.
     */
    public String name;

    /**
     * Required. The number of members joined to the room.
     */
    @SerializedName("num_joined_members")
    public Long numJoinedMembers;

    /**
     * Required. The ID of the room.
     */
    @SerializedName("room_id")
    public String roomId;

    /**
     * The topic of the room, if any.
     */
    public String topic;

    /**
     * Required. Whether the room may be viewed by guest users without joining.
     */
    @SerializedName("world_readable")
    public Boolean worldReadable;

    /**
     * Required. Whether guest users may join the room and participate in it. If they can, they will be subject to ordinary power
     * level rules like any other user.
     */
    @SerializedName("guest_can_join")
    public Boolean guestCanJoin;

    /**
     * The URL for the room's avatar, if one is set.
     */
    @SerializedName("avatar_url")
    public String avatarUrl;
}
