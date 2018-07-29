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

import io.github.ma1uta.matrix.gene.model.common.EventContent;

/**
 * A room has an opaque room ID which is not human-friendly to read. A room alias is human-friendly, but not all rooms have room aliases.
 * The room name is a human-friendly string designed to be displayed to the end-user. The room name is not unique, as multiple rooms can
 * have the same room name set.
 * <br>
 * A room with an m.room.name event with an absent, null, or empty name field should be treated the same as a room with no
 * m.room.name event.
 * <br>
 * An event of this type is automatically created when creating a room using /createRoom with the name key.
 */
public class RoomName implements EventContent {

    /**
     * Required. The name of the room. This MUST NOT exceed 255 bytes.
     */
    public String name;
}
