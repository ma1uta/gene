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
import io.github.ma1uta.matrix.gene.model.common.events.nested.Receipts;

import java.util.HashMap;

/**
 * A map of which rooms are considered 'direct' rooms for specific users is kept in account_data in an event of type m.direct.
 * The content of this event is an object where the keys are the user IDs and values are lists of room ID strings of the 'direct'
 * rooms for that user ID.
 * <br>
 * Map EVENT_ID -&gt; 'm.read' -&gt; USER_ID -&gt; ts: long
 */
public class Receipt extends HashMap<String, Receipts> implements EventContent {
}
