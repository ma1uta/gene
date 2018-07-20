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

package io.github.ma1uta.matrix.gene.model.common.events.nested;

import com.google.gson.annotations.SerializedName;

/**
 * File info.
 */
public class FileInfo {

    /**
     * The mimetype of the image, e.g. image/jpeg.
     */
    public String mimetype;

    /**
     * Size of the image in bytes.
     */
    public Long size;

    /**
     * The URL to a thumbnail of the image.
     */
    @SerializedName("thumbnail_url")
    public String thumbnailUrl;

    /**
     * Metadata about the image referred to in thumbnail_url.
     */
    @SerializedName("thumbnail_info")
    public ThumbnailInfo thumbnailInfo;
}
