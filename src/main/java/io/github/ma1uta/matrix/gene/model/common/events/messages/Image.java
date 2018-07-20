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

package io.github.ma1uta.matrix.gene.model.common.events.messages;

import io.github.ma1uta.matrix.gene.model.common.Event;
import io.github.ma1uta.matrix.gene.model.common.events.RoomMessage;
import io.github.ma1uta.matrix.gene.model.common.events.nested.ImageInfo;

/**
 * This message represents a single image and an optional thumbnail.
 */
public class Image extends RoomMessage {

    /**
     * Information about the file referred to in url.
     */
    public ImageInfo info;

    /**
     * Required. The URL to the file.
     */
    public String url;

    @Override
    public String getMsgtype() {
        return Event.MessageType.IMAGE;
    }
}
