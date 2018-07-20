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

/**
 * Events can be redacted by either room or server admins. Redacting an event means that all keys not required by the protocol
 * are stripped off, allowing admins to remove offensive or illegal content that may have been attached to any event. This cannot
 * be undone, allowing server owners to physically delete the offending data. There is also a concept of a moderator hiding a message
 * event, which can be undone, but cannot be applied to state events. The event that has been redacted is specified in the redacts
 * event level key.
 */
public class RoomRedaction implements EventContent {

    /**
     * The reason for the redaction, if any.
     */
    public String reason;
}
