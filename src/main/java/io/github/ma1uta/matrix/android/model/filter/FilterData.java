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
 * JSON body request for filter api (create filter).
 */
public class FilterData {

    /**
     * Event formats.
     */
    public static class EventFormat {

        protected EventFormat() {
        }

        /**
         * Client.
         */
        public static final String CLIENT = "client";

        /**
         * Server or federation.
         */
        public static final String FEDERATION = "federation";
    }

    /**
     * List of event fields to include. If this list is absent then all fields are included. The entries may include '.' charaters
     * to indicate sub-fields. So ['content.body'] will include the 'body' field of the 'content' object. A literal '.' character
     * in a field name may be escaped using a '\'. A server may include more fields than were requested.
     */
    @SerializedName("event_fields")
    public List<String> eventFields;

    /**
     * The format to use for events. 'client' will return the events in a format suitable for clients. 'federation' will return the
     * raw event as receieved over federation. The default is 'client'. One of: ["client", "federation"]
     */
    @SerializedName("event_format")
    public String eventFormat;

    /**
     * The presence updates to include.
     */
    public Filter presence;

    /**
     * The user account data that isn't associated with rooms to include.
     */
    @SerializedName("account_data")
    public Filter accountData;

    /**
     * Filters to be applied to room data.
     */
    public RoomFilter room;
}
