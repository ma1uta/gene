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

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.Event;
import io.github.ma1uta.matrix.gene.model.common.events.RoomMessage;
import io.github.ma1uta.matrix.gene.model.common.events.nested.LocationInfo;

/**
 * This message represents a real-world location.
 */
public class Location extends RoomMessage {

    /**
     * Information about the file referred to in url.
     */
    public LocationInfo info;

    /**
     * Required. A geo URI representing this location.
     */
    @SerializedName("geo_uri")
    public String geoUri;

    @Override
    public String getMsgtype() {
        return Event.MessageType.LOCATION;
    }
}
