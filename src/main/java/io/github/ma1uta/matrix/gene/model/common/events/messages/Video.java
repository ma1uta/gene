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
import io.github.ma1uta.matrix.gene.model.common.events.nested.EncryptedFile;
import io.github.ma1uta.matrix.gene.model.common.events.nested.VideoInfo;

/**
 * This message represents a single video clip.
 */
public class Video extends RoomMessage {

    /**
     * Metadata about the video clip referred to in url.
     */
    public VideoInfo info;

    /**
     * Required. The URL to the video clip.
     */
    public String url;

    /**
     * Required if the file is encrypted. Information on the encrypted file, as specified in End-to-end encryption.
     */
    public EncryptedFile file;

    @Override
    public String getMsgtype() {
        return Event.MessageType.VIDEO;
    }
}
