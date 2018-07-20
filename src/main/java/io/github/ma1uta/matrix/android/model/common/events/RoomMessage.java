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

package io.github.ma1uta.matrix.android.model.common.events;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.android.model.common.EventContent;

/**
 * This event is used when sending messages in a room. Messages are not limited to be text. The ``msgtype`` key outlines the type
 * of message, e.g. text, audio, image, video, etc. The ``body`` key is text and MUST be used with every kind of ``msgtype`` as a
 * fallback mechanism for when a client cannot render a message. This allows clients to display *something* even if it is just plain text.
 */
public abstract class RoomMessage implements EventContent {

    /**
     * Message type.
     * <p/>
     * Read-only.
     *
     * @return message type.
     */
    @SerializedName("msgtype")
    public abstract String getMsgtype();

    /**
     * The textual representation of this message.
     */
    public String body;
}
