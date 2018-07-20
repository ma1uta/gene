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

package io.github.ma1uta.matrix.android.model.push;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.android.model.common.Event;

import java.util.List;

/**
 * Notification.
 */
public class Notification {

    /**
     * Required. The action(s) to perform when the conditions for this rule are met. See Push Rules: API.
     */
    public List<Object> actions;

    /**
     * Required. The Event object for the event that triggered the notification.
     */
    public Event event;

    /**
     * The profile tag of the rule that matched this event.
     */
    @SerializedName("avatar_url")
    public String profileTag;

    /**
     * Required. Indicates whether the user has sent a read receipt indicating that they have read this message.
     */
    public Boolean read;

    /**
     * Required. The ID of the room in which the event was posted.
     */
    @SerializedName("room_id")
    public String roomId;

    /**
     * Required. The unix timestamp at which the event notification was sent, in milliseconds.
     */
    public Long ts;
}
