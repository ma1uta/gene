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

package io.github.ma1uta.matrix.android.model.common.events.nested;

import com.google.gson.annotations.SerializedName;

/**
 * Video info.
 */
public class VideoInfo extends FileInfo {

    /**
     * The intended display height of the image in pixels. This may differ from the intrinsic dimensions of the image file.
     */
    @SerializedName("h")
    public Long height;

    /**
     * The intended display width of the image in pixels. This may differ from the intrinsic dimensions of the image file.
     */
    @SerializedName("w")
    public Long width;

    /**
     * The duration of the video in milliseconds.
     */
    public Long duration;
}
