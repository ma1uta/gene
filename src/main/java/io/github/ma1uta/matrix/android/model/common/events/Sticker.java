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

import io.github.ma1uta.matrix.android.model.common.EventContent;
import io.github.ma1uta.matrix.android.model.common.events.nested.ImageInfo;

/**
 * This message represents a single sticker image.
 */
public class Sticker implements EventContent {

    /**
     * Required. A textual representation or associated description of the sticker image. This could be the alt text of the original
     * image, or a message to accompany and further describe the sticker.
     */
    public String body;

    /**
     * Required. Metadata about the image referred to in url including a thumbnail representation.
     */
    public ImageInfo info;

    /**
     * Required. The URL to the sticker image. This must be a valid mxc:// URI.
     */
    public String url;
}
