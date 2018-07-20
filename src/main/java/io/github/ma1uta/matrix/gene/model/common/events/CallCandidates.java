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
import io.github.ma1uta.matrix.gene.model.common.events.nested.Candidate;

import java.util.List;

/**
 * Message Event.
 * <p/>
 * This event is sent by callers after sending an invite and by the callee after answering. Its purpose is to give the other
 * party additional ICE candidates to try using to communicate.
 */
public class CallCandidates implements EventContent {

    /**
     * Required. The ID of the call this event relates to.
     */
    @SerializedName("call_id")
    public String callId;

    /**
     * Required. Array of objects describing the candidates.
     */
    public List<Candidate> candidates;

    /**
     * Required. The version of the VoIP specification this messages adheres to. This specification is version 0.
     */
    public Long version;
}
