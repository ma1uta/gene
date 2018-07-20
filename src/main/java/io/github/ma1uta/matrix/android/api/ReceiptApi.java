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

package io.github.ma1uta.matrix.android.api;

import io.github.ma1uta.matrix.android.model.common.EmptyResponse;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * This module adds in support for receipts. These receipts are a form of acknowledgement of an event. This module defines a single
 * acknowledgement: m.read which indicates that the user has read up to a given event.
 * <p/>
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
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room in which to send the event.
     * @param receiptType     Required. The type of receipt to send. One of: ["m.read"]
     * @param eventId         Required. The event ID to acknowledge up to.
     * @return Status code 200: The receipt was sent.
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/receipt/{receiptType}/{eventId}")
    EmptyResponse receipt(@Path("roomId") String roomId, @Path("receiptType") String receiptType, @Path("eventId") String eventId);
}
