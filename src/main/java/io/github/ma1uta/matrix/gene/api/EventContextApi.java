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

package io.github.ma1uta.matrix.gene.api;

import io.github.ma1uta.matrix.gene.model.eventcontext.EventContextResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This API returns a number of events that happened just before and after the specified event. This allows clients to get the context
 * surrounding an event.
 */
public interface EventContextApi {

    /**
     * This API returns a number of events that happened just before and after the specified event. This allows clients to get the
     * context surrounding an event.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId  Required. The room to get events from.
     * @param eventId Required. The event to get context around.
     * @param limit   The maximum number of events to return. Default: 10.
     * @return <p>Status code 200: The events and state surrounding the requested event.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/context/{eventId}")
    EventContextResponse context(@Path("roomId") String roomId, @Path("eventId") String eventId, @Query("limit") Integer limit);
}
