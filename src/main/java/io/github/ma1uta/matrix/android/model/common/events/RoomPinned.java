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

import java.util.List;

/**
 * This event is used to "pin" particular events in a room for other participants to review later. The order of the pinned
 * events is guaranteed and based upon the order supplied in the event. Clients should be aware that the current user may
 * not be able to see some of the events pinned due to visibility settings in the room. Clients are responsible for determining
 * if a particular event in the pinned list is displayable, and have the option to not display it if it cannot be pinned in the client.
 */
public class RoomPinned implements EventContent {

    /**
     * Required. An ordered list of event IDs to pin.
     */
    public List<String> pinned;
}
