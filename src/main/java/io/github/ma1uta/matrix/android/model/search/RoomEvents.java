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

package io.github.ma1uta.matrix.android.model.search;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.android.model.filter.Filter;

import java.util.List;

/**
 * Room events.
 */
public class RoomEvents {

    /**
     * The keys to search. Possible values.
     */
    public static class Key {

        protected Key() {
        }

        /**
         * Body.
         */
        public static final String CONTENT_BODY = "content.body";

        /**
         * Name.
         */
        public static final String CONTENT_NAME = "content.name";

        /**
         * Topic.
         */
        public static final String CONTENT_TOPIC = "content.topic";
    }

    /**
     * Order.
     */
    public static class Order {

        protected Order() {
        }

        /**
         * Recent.
         */
        public static final String RECENT = "recent";

        /**
         * Rank.
         */
        public static final String rank = "rank";
    }

    /**
     * Required. The string to search events for.
     */
    @SerializedName("search_term")
    public String searchTerm;

    /**
     * The keys to search. Defaults to all. One of: ["content.body", "content.name", "content.topic"]
     */
    public List<String> keys;

    /**
     * This takes a filter.
     */
    public Filter filter;

    /**
     * The order in which to search for results. One of: ["recent", "rank"]
     */
    @SerializedName("order_by")
    public String orderBy;

    /**
     * Configures whether any context for the events returned are included in the response.
     */
    @SerializedName("event_context")
    public EventContext eventContext;

    /**
     * Requests the server return the current state for each room returned.
     */
    @SerializedName("include_state")
    public Boolean includeState;

    /**
     * Requests that the server partitions the result set based on the provided list of keys.
     */
    public Groupings groupings;
}
