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

package io.github.ma1uta.matrix.android.model.sync;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.android.model.common.Event;

import java.util.List;

/**
 * Timeline.
 */
public class Timeline {

    /**
     * List of events.
     */
    public List<Event> events;

    /**
     * True if the number of events returned was limited by the limit on the filter.
     */
    public Boolean limited;

    /**
     * A token that can be supplied to the from parameter of the rooms/{roomId}/messages endpoint.
     */
    @SerializedName("prev_batch")
    public String prevBatch;
}
