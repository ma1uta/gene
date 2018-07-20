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
import io.github.ma1uta.matrix.gene.model.sendtodevice.SendToDeviceRequest;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * This module provides a means by which clients can exchange signalling messages without them being stored permanently as part of
 * a shared communication history. A message is delivered exactly once to each client device.
 * <p/>
 * The primary motivation for this API is exchanging data that is meaningless or undesirable to persist in the room DAG - for example,
 * one-time authentication tokens or key data. It is not intended for conversational data, which should be sent using the normal
 * /rooms/&lt;room_id&gt;/send API for consistency throughout Matrix.
 */
public interface SendToDeviceApi {

    /**
     * This endpoint is used to send send-to-device events to a set of client devices.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param eventType           Required. The type of event to send.
     * @param txnId               Required. The transaction ID for this event. Clients should generate an ID unique across requests with the
     *                            same access token; it will be used by the server to ensure idempotency of requests.
     * @param sendToDeviceRequest request body.
     * @return Status code 200: The message was successfully sent.
     */
    @PUT("/_matrix/client/r0/sendToDevice/{eventType}/{txnId}")
    @Headers("Content-type: application/json")
    EmptyResponse send(@Path("eventType") String eventType, @Path("txnId") String txnId, @Body SendToDeviceRequest sendToDeviceRequest);
}
