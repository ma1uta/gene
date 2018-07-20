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
 * This event controls whether a user can see the events that happened in a room from before they joined.
 */
public class RoomHistoryVisibility implements EventContent {

    /**
     * Required. Who can see the room history. One of: ["invited", "joined", "shared", "world_readable"].
     */
    @SerializedName("history_visibility")
    public String historyVisibility;
}
