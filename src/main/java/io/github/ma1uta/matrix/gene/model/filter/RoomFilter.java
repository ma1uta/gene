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

package io.github.ma1uta.matrix.gene.model.filter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Room filter.
 */
public class RoomFilter {

    /**
     * A list of room IDs to exclude. If this list is absent then no rooms are excluded. A matching room will be excluded even if it
     * is listed in the 'rooms' filter. This filter is applied before the filters in ephemeral, state, timeline or account_data.
     */
    @SerializedName("not_rooms")
    public List<String> notRooms;

    /**
     * A list of room IDs to include. If this list is absent then all rooms are included. This filter is applied before the filters
     * in ephemeral, state, timeline or account_data.
     */
    public List<String> rooms;

    /**
     * The events that aren't recorded in the room history, e.g. typing and receipts, to include for rooms.
     */
    public RoomEventFilter ephemeral;

    /**
     * Include rooms that the user has left in the sync, default false.
     */
    @SerializedName("include_leave")
    public Boolean includeLeave;

    /**
     * The state events to include for rooms.
     */
    public RoomEventFilter state;

    /**
     * The message and state update events to include for rooms.
     */
    public RoomEventFilter timeline;

    /**
     * The per user account data to include for rooms.
     */
    @SerializedName("account_data")
    public RoomEventFilter accountData;
}
