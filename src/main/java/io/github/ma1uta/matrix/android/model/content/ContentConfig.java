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

package io.github.ma1uta.matrix.android.model.content;

import com.google.gson.annotations.SerializedName;

/**
 * Content config.
 */
public class ContentConfig {

    /**
     * The maximum size an upload can be in bytes. Clients SHOULD use this as a guide when uploading content.
     * If not listed or null, the size limit should be treated as unknown.
     */
    @SerializedName("m.upload.size")
    public long uploadSize;
}
