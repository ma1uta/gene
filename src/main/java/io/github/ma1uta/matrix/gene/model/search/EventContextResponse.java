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

package io.github.ma1uta.matrix.gene.model.search;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.Event;
import io.github.ma1uta.matrix.gene.model.profile.Profile;

import java.util.List;
import java.util.Map;

/**
 * Event context.
 */
public class EventContextResponse {

    /**
     * Pagination token for the start of the chunk.
     */
    public String start;

    /**
     * Pagination token for the end of the chunk.
     */
    public String end;

    /**
     * The historic profile information of the users that sent the events returned.
     * <br>
     * The string key is the user ID for which the profile belongs to.
     */
    @SerializedName("profile_info")
    public Map<String, Profile> profileInfo;

    /**
     * Events just before the result.
     */
    @SerializedName("events_before")
    public List<Event> eventsBefore;

    /**
     * Events just after the result.
     */
    @SerializedName("events_after")
    public List<Event> eventsAfter;
}
