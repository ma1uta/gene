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

/**
 * Joined room.
 */
public class JoinedRoom {

    /**
     * Updates to the state, between the time indicated by the since parameter, and the start of the timeline (or all state up to the
     * start of the timeline, if since is not given, or full_state is true).
     */
    public State state;

    /**
     * The timeline of messages and state changes in the room.
     */
    public Timeline timeline;

    /**
     * The ephemeral events in the room that aren't recorded in the timeline or state of the room. e.g. typing.
     */
    public Ephemeral ephemeral;

    /**
     * The public data that this user has attached to this room.
     */
    @SerializedName("account_data")
    public AccountData accountData;

    /**
     * Counts of unread notifications for this room.
     */
    @SerializedName("unread_notifications")
    public UnreadNotificationCounts unreadNotifications;
}
