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

package io.github.ma1uta.matrix.gene.model.presence;

import com.google.gson.annotations.SerializedName;

/**
 * JSON body request for presence api.
 */
public class PresenceStatus {

    /**
     * Presence types.
     */
    public static class PresenceType {

        protected PresenceType() {
        }

        /**
         * Online status.
         */
        public static final String ONLINE = "online";

        /**
         * Offline status.
         */
        public static final String OFFLINE = "offline";

        /**
         * Unavailable status.
         */
        public static final String UNAVAILABLE = "unavailable";
    }

    /**
     * Required. The new presence state. One of: ["online", "offline", "unavailable"].
     */
    public String presence;

    /**
     * The status message to attach to this state.
     */
    @SerializedName("status_msg")
    public String statusMsg;

    /**
     * The length of time in milliseconds since an action was performed by this user.
     */
    @SerializedName("last_active_ago")
    public Long lastActiveAgo;

    /**
     * Whether the user is currently active.
     */
    @SerializedName("currently_active")
    public Boolean currentlyActive;
}
