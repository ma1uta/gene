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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Raw message for all unknown messages.
 */
public class RawMessage extends RoomMessage {

    public RawMessage() {
    }

    public RawMessage(JsonObject content) {
        this.content = content;
        this.body = content.toString();
    }

    /**
     * Content.
     */
    public JsonObject content;

    @Override
    public String getMsgtype() {
        JsonElement msgtype = content.get("msgtype");
        return msgtype != null ? msgtype.getAsString() : null;
    }
}
