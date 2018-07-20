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
 * This event is used to inform the room about which alias should be considered the canonical one. This could be for display purposes
 * or as suggestion to users which alias to use to advertise the room.
 * <p/>
 * A room with an m.room.canonical_alias event with an absent, null, or empty alias field should be treated the same as a room
 * with no m.room.canonical_alias event.
 */
public class RoomCanonicalAlias implements EventContent {

    /**
     * Required. The canonical alias.
     */
    public String alias;
}