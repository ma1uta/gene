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
import io.github.ma1uta.matrix.gene.model.receipt.ReadMarker;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * This module adds in support for receipts. These receipts are a form of acknowledgement of an event. This module defines a single
 * acknowledgement: m.read which indicates that the user has read up to a given event.
 * <br>
 * Sending a receipt for each event can result in sending large amounts of traffic to a homeserver. To prevent this from becoming
 * a problem, receipts are implemented using "up to" markers. This marker indicates that the acknowledgement applies to all events
 * "up to and including" the event specified. For example, marking an event as "read" would indicate that the user had read all
 * events up to the referenced event.
 */
public interface ReceiptApi {

    /**
     * Receipt types.
     */
    class Receipt {

        protected Receipt() {
        }

        /**
         * Read receipt type.
         */
        public static final String READ = "m.read";
    }

    /**
     * This API updates the marker for the given receipt type to the event ID specified.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId      Required. The room in which to send the event.
     * @param receiptType Required. The type of receipt to send. One of: ["m.read"]
     * @param eventId     Required. The event ID to acknowledge up to.
     * @return <p>Status code 200: The receipt was sent.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/receipt/{receiptType}/{eventId}")
    @Headers("Content-type: application/json")
    EmptyResponse receipt(@Path("roomId") String roomId, @Path("receiptType") String receiptType, @Path("eventId") String eventId);

    /**
     * Sets the position of the read marker for a given room, and optionally the read receipt's location.
     * <br>
     * <b>Rate-limited:</b> Yes.
     * <br>
     * <b>Requires auth:</b> Yes.
     *
     * @param roomId Required. The room ID to set the read marker in for the user.
     * @param marker JSON body request.
     * @return <p>Status code 200: The read marker, and read receipt if provided, have been updated.</p>
     * <p>This request was rate-limited.</p>
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/read_markers")
    @Headers("Content-type: application/json")
    EmptyResponse readMarker(@Path("roomId") String roomId, @Body ReadMarker marker);
}
