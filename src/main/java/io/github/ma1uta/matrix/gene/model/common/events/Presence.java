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
 * Informs the client of a user's presence state change.
 */
public class Presence implements EventContent {

    /**
     * The current avatar URL for this user, if any.
     */
    @SerializedName("avatar_url")
    public String avatarUrl;

    /**
     * The current display name for this user, if any.
     */
    public String displayname;

    /**
     * The last time since this used performed some action, in milliseconds.
     */
    @SerializedName("last_active_ago")
    public Long lastActiveAgo;

    /**
     * Required. The presence state for this user. One of: ["online", "offline", "unavailable"].
     */
    public String presence;

    /**
     * Whether the user is currently active.
     */
    @SerializedName("currently_active")
    public Boolean currentlyActive;
}
