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

package io.github.ma1uta.matrix.gene.model.push;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.PusherData;

/**
 * Pusher.
 * <br>
 * A pusher is a worker on the homeserver that manages the sending of HTTP notifications for a user.
 * A user can have multiple pushers: one per device.
 */
public class Pusher {

    /**
     * This is a unique identifier for this pusher. See /set for more detail. Max length, 512 bytes.
     */
    public String pushkey;

    /**
     * The kind of pusher. "http" is a pusher that sends HTTP pokes.
     */
    public String kind;

    /**
     * This is a reverse-DNS style identifier for the application. Max length, 64 chars.
     */
    @SerializedName("app_id")
    public String appId;

    /**
     * A string that will allow the user to identify what application owns this pusher.
     */
    @SerializedName("app_display_name")
    public String appDisplayName;

    /**
     * A string that will allow the user to identify what device owns this pusher.
     */
    @SerializedName("device_display_name")
    public String deviceDisplayName;

    /**
     * This string determines which set of device specific rules this pusher executes.
     */
    @SerializedName("profile_tag")
    public String profileTag;

    /**
     * The preferred language for receiving notifications (e.g. 'en' or 'en-US')
     */
    public String lang;

    /**
     * A dictionary of information for the pusher implementation itself.
     */
    public PusherData data;
}
