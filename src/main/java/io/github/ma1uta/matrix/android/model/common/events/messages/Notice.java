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

package io.github.ma1uta.matrix.android.model.common.events.messages;

import io.github.ma1uta.matrix.android.model.common.Event;

/**
 * The ``m.notice`` type is primarily intended for responses from automated clients. An ``m.notice`` message must be treated
 * the same way as a regular ``m.text`` message with two exceptions. Firstly, clients should present ``m.notice`` messages to
 * users in a distinct manner, and secondly, ``m.notice`` messages must never be automatically responded to. This helps to prevent
 * infinite-loop situations where two automated clients continuously exchange messages.
 */
public class Notice extends FormattedBody {

    @Override
    public String getMsgtype() {
        return Event.MessageType.NOTICE;
    }
}
