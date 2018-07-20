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

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.EventContent;
import io.github.ma1uta.matrix.gene.model.common.events.nested.Offer;

/**
 * Message Event.
 * <p/>
 * This event is sent by the caller when they wish to establish a call.
 */
public class CallInvite implements EventContent {

    /**
     * Required. The ID of the call this event relates to.
     */
    @SerializedName("call_id")
    public String callId;

    /**
     * Required. The session description object.
     */
    public Offer offer;

    /**
     * Required. The version of the VoIP specification this message adheres to. This specification is version 0.
     */
    public Long version;

    /**
     * Required. The time in milliseconds that the invite is valid for. Once the invite age exceeds this value,
     * clients should discard it. They should also no longer show the call as awaiting an answer in the UI.
     */
    public Long lifetime;
}
