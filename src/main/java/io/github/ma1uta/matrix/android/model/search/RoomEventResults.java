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
import io.github.ma1uta.matrix.android.model.common.Event;

import java.util.List;
import java.util.Map;

/**
 * Room events results.
 */
public class RoomEventResults {

    /**
     * An approximate count of the total number of results found.
     */
    public Long count;

    /**
     * List of words which should be highlighted, useful for stemming which may change the query terms.
     */
    public List<String> highlights;

    /**
     * List of results in the requested order.
     */
    public List<Result> results;

    /**
     * The current state for every room in the results. This is included if the request had the include_state key set with a value of true.
     * <p/>
     * The string key is the room ID for which the State Event array belongs to.
     */
    public Map<String, List<Event>> state;

    /**
     * Any groups that were requested.
     * <p/>
     * The outer string key is the group key requested (eg: room_id or sender). The inner string key is the grouped value
     * (eg: a room's ID or a user's ID).
     */
    public Map<String, Map<String, GroupValue>> groups;

    /**
     * Token that can be used to get the next batch of results, by passing as the next_batch parameter to the next call. If this field
     * is absent, there are no more results.
     */
    @SerializedName("next_batch")
    public String nextBatch;
}
