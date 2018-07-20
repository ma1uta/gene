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

package io.github.ma1uta.matrix.android.model.filter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Room event filter.
 */
public class RoomEventFilter {

    /**
     * The maximum number of events to return.
     */
    public Long limit;

    /**
     * A list of sender IDs to exclude. If this list is absent then no senders are excluded. A matching sender will be excluded even
     * if it is listed in the 'senders' filter.
     */
    @SerializedName("not_senders")
    public List<String> notSenders;

    /**
     * A list of event types to exclude. If this list is absent then no event types are excluded. A matching type will be excluded even
     * if it is listed in the 'types' filter. A '*' can be used as a wildcard to match any sequence of characters.
     */
    @SerializedName("not_types")
    public List<String> notTypes;

    /**
     * A list of senders IDs to include. If this list is absent then all senders are included.
     */
    public List<String> senders;

    /**
     * A list of event types to include. If this list is absent then all event types are included. A '*' can be used as a wildcard to
     * match any sequence of characters.
     */
    public List<String> types;

    /**
     * A list of room IDs to exclude. If this list is absent then no rooms are excluded. A matching room will be excluded even if it is
     * listed in the 'rooms' filter.
     */
    @SerializedName("not_rooms")
    public List<String> notRooms;

    /**
     * A list of room IDs to include. If this list is absent then all rooms are included.
     */
    public List<String> rooms;

    /**
     * If true, includes only events with a url key in their content. If false, excludes those events.
     */
    @SerializedName("contains_url")
    public Boolean containsUrl;
}
