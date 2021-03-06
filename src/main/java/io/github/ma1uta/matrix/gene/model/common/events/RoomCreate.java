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

package io.github.ma1uta.matrix.gene.model.common.events;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.EventContent;

/**
 * This is the first event in a room and cannot be changed. It acts as the root of all other events.
 */
public class RoomCreate implements EventContent {

    /**
     * Required. The user_id of the room creator. This is set by the homeserver.
     */
    public String creator;

    /**
     * Whether users on other servers can join this room. Defaults to ``true`` if key does not exist.
     */
    @SerializedName("m.federate")
    public Boolean federate;

    /**
     * The version of the room. Defaults to "1" if the key does not exist.
     */
    @SerializedName("room_version")
    public String roomVersion;
}
