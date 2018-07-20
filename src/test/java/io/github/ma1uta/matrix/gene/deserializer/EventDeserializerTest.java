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

package io.github.ma1uta.matrix.gene.deserializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import io.github.ma1uta.matrix.gene.model.common.Event;
import io.github.ma1uta.matrix.gene.model.common.events.RoomName;
import org.junit.jupiter.api.Test;

public class EventDeserializerTest {

    @Test
    public void roomNameTest() {
        String event = "{"
            + "\"type\":\"m.room.name\","
            + "\"content\":{"
            + "    \"name\":\"test\""
            + "},"
            + "\"sender\":\"@admin:server.tld\","
            + "\"room_id\":\"!qwe:server.tld\","
            + "\"event_id\":\"$ev1:server.tld\","
            + "\"origin_server_ts\":123"
            + "}";

        Event result = new Gson().fromJson(event, Event.class);
        assertNotNull(result);
        assertEquals(Event.EventType.ROOM_NAME, result.type);
        assertEquals("@admin:server.tld", result.sender);
        assertEquals("!qwe:server.tld", result.roomId);
        assertEquals("$ev1:server.tld", result.eventId);

        assertTrue(result.content instanceof RoomName);

        RoomName roomName = (RoomName) result.content;
        assertEquals("test", roomName.name);
    }
}
