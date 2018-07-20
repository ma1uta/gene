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

import io.github.ma1uta.matrix.gene.model.common.EventContent;
import io.github.ma1uta.matrix.gene.model.common.events.nested.ImageInfo;

/**
 * A picture that is associated with the room. This can be displayed alongside the room information.
 */
public class RoomAvatar implements EventContent {

    /**
     * Metadata about the image referred to in url.
     */
    public ImageInfo info;

    /**
     * Required. The URL to the image.
     */
    public String url;
}
