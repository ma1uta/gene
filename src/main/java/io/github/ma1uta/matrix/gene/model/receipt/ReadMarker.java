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

package io.github.ma1uta.matrix.gene.model.receipt;

import com.google.gson.annotations.SerializedName;

/**
 * Read marker.
 */
public class ReadMarker {

    /**
     * Required. The event ID the read marker should be located at. The event MUST belong to the room.
     */
    @SerializedName("m.fully_read")
    public String fullyRead;

    /**
     * The event ID to set the read receipt location at. This is equivalent to calling /receipt/m.read/$elsewhere:domain.com and
     * is provided here to save that extra call.
     */
    @SerializedName("m.read")
    public String read;
}
