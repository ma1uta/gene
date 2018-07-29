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

import io.github.ma1uta.matrix.gene.model.common.EmptyResponse;
import io.github.ma1uta.matrix.gene.model.report.ReportRequest;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Users may encounter content which they find inappropriate and should be able to report it to the server administrators or room
 * moderators for review. This module defines a way for users to report content.
 * <br>
 * Content is reported based upon a negative score, where -100 is "most offensive" and 0 is "inoffensive".
 */
public interface ReportApi {

    /**
     * Reports an event as inappropriate to the server, which may then notify the appropriate people.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId        Required. The room in which the event being reported is located.
     * @param eventId       Required. The event to report.
     * @param reportRequest JSON body request.
     * @return <p>Status code 200: The event has been reported successfully.</p>
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/report/{eventId}")
    @Headers("Content-type: application/json")
    EmptyResponse report(@Path("roomId") String roomId, @Path("eventId") String eventId, @Body ReportRequest reportRequest);
}
