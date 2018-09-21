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

import io.github.ma1uta.matrix.gene.model.common.Event;
import io.github.ma1uta.matrix.gene.model.common.Page;
import io.github.ma1uta.matrix.gene.model.event.JoinedMembersResponse;
import io.github.ma1uta.matrix.gene.model.event.MembersResponse;
import io.github.ma1uta.matrix.gene.model.event.RedactRequest;
import io.github.ma1uta.matrix.gene.model.event.SendEventResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

/**
 * There are several APIs provided to GET events for a room.
 */
public interface EventApi {

    /**
     * Get a single event based on roomId/eventId. You must have permission to retrieve this event e.g. by being a member in the
     * room for this event.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId  Required. The ID of the room the event is in.
     * @param eventId Required. The event ID to get.
     * @return <p>Status code 200: The full event.</p>
     * <p>Status code 404: The event was not found or you do not have permission to read this event.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/event/{eventId}")
    @Headers("Content-type: application/json")
    Event roomEvent(@Path("roomId") String roomId, @Path("eventId") String eventId);

    /**
     * Looks up the contents of a state event in a room. If the user is joined to the room then the state is taken from the current
     * state of the room. If the user has left the room then the state is taken from the state of the room when they left.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId    Required. The room to look up the state in.
     * @param eventType Required. The type of state to look up.
     * @param stateKey  Required. The key of the state to look up.
     * @return <p>Status code 200: The content of the state event.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     * <p>Status code 404: The room has no state with the given type or key.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/state/{eventType}/{stateKey}")
    @Headers("Content-type: application/json")
    Map<String, Object> roomEventWithTypeAndState(@Path("roomId") String roomId, @Path("eventType") String eventType,
                                                      @Path("stateKey") String stateKey);

    /**
     * Looks up the contents of a state event in a room. If the user is joined to the room then the state is taken from the
     * current state of the room. If the user has left the room then the state is taken from the state of the room when they left.
     * <br>
     * This looks up the state event with the empty state key.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId    Required. The room to look up the state in.
     * @param eventType Required. The type of state to look up.
     * @return <p>Status code 200: The content of the state event.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     * <p>Status code 404: The room has no state with the given type or key.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/state/{eventType}")
    @Headers("Content-type: application/json")
    Map<String, Object> roomEventWithType(@Path("roomId") String roomId, @Path("eventType") String eventType);

    /**
     * Get the state events for the current state of a room.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId Required. The room to look up the state for.
     * @return <p>Status code 200: The current state of the room.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/state")
    @Headers("Content-type: application/json")
    List<Event> roomEvents(@Path("roomId") String roomId);

    /**
     * Get the list of members for this room.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId Required. The room to get the member events for.
     * @return <p>Status code 200: A list of members of the room. If you are joined to the room then this will be the current
     * members of the room. If you have left the room then this will be the members of the room when you left.</p>
     * <p>Status code 403: You aren't a member of the room and weren't previously a member of the room.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/members")
    @Headers("Content-type: application/json")
    MembersResponse members(@Path("roomId") String roomId);

    /**
     * This API returns a map of MXIDs to member info objects for members of the room. The current user must be in the room for
     * it to work, unless it is an Application Service in which case any of the AS's users must be in the room. This API
     * is primarily for Application Services and should be faster to respond than /members as it can be implemented more
     * efficiently on the server.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId Required. The room to get the members of.
     * @return <p>Status code 200: A map of MXID to room member objects.</p>
     * <p>Status code 403: You aren't a member of the room.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/joined_members")
    @Headers("Content-type: application/json")
    JoinedMembersResponse joinedMembers(@Path("roomId") String roomId);

    /**
     * This API returns a list of message and state events for a room. It uses pagination query parameters to paginate history in the room.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId Required. The room to get events from.
     * @param from   Required. The token to start returning events from. This token can be obtained from a prev_batch token
     *               returned for each room by the sync API, or from a start or end token returned by a previous request to
     *               this endpoint.
     * @param to     The token to stop returning events at. This token can be obtained from a prev_batch token returned for
     *               each room by the sync endpoint, or from a start or end token returned by a previous request to this endpoint.
     * @param dir    Required. The direction to return events from. One of: ["b", "f"]
     * @param limit  The maximum number of events to return. Default: 10.
     * @param filter A JSON RoomEventFilter to filter returned events with.
     * @return <p>Status code 200: A list of messages with a new token to request more.</p>
     * <p>Status code 403: You aren't a member of the room.</p>
     */
    @GET("/_matrix/client/r0/rooms/{roomId}/messages")
    @Headers("Content-type: application/json")
    Page<Event> messages(@Path("roomId") String roomId, @Query("from") String from, @Query("to") String to, @Query("dir") String dir,
                         @Query("limit") Integer limit, @Query("filter") String filter);

    /**
     * State events can be sent using this endpoint. These events will be overwritten if (room id), (event type) and (state key) all match.
     * <br>
     * Requests to this endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated from the state_key.
     * Furthermore, POST is unsupported on state paths.
     * <br>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the
     * type of event. See Room Events for the m. event specification.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId    Required. The room to set the state in.
     * @param eventType Required. The type of event to send.
     * @param stateKey  Required. The state_key for the state to send. Defaults to the empty string.
     * @param event     event.
     * @return <p>Status code 200: An ID for the sent event.</p>
     */
    @PUT("/_matrix/client/r0/rooms/{roomId}/state/{eventType}/{stateKey}")
    @Headers("Content-type: application/json")
    SendEventResponse sendEventWithTypeAndState(@Path("roomId") String roomId, @Path("eventType") String eventType,
                                                @Path("stateKey") String stateKey, @Body Map<String, Object> event);

    /**
     * State events can be sent using this endpoint. This endpoint is equivalent to calling /rooms/{roomId}/state/{eventType}/{stateKey}
     * with an empty stateKey. Previous state events with matching (roomId) and (eventType), and empty (stateKey), will be overwritten.
     * <br>
     * Requests to this endpoint cannot use transaction IDs like other PUT paths because they cannot be differentiated from the state_key.
     * Furthermore, POST is unsupported on state paths.
     * <br>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the type
     * of event. See Room Events for the m. event specification.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId    Required. The room to set the state in.
     * @param eventType Required. The type of event to send.
     * @param event     event.
     * @return <p>Status code 200: An ID for the sent event.</p>
     */
    @PUT("/_matrix/client/r0/rooms/{roomId}/state/{eventType}")
    @Headers("Content-type: application/json")
    SendEventResponse sendEventWithType(@Path("roomId") String roomId, @Path("eventType") String eventType,
                                        @Body Map<String, Object> event);

    /**
     * This endpoint is used to send a message event to a room. Message events allow access to historical events and pagination,
     * making them suited for "once-off" activity in a room.
     * <br>
     * The body of the request should be the content object of the event; the fields in this object will vary depending on the
     * type of event. See Room Events for the m. event specification.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId    Required. The room to send the event to.
     * @param eventType Required. The type of event to send.
     * @param txnId     Required. The transaction ID for this event. Clients should generate an ID unique across requests with the
     *                  same access token; it will be used by the server to ensure idempotency of requests.
     * @param event     event.
     * @return <p>Status code 200: An ID for the sent event.</p>
     */
    @PUT("/_matrix/client/r0/rooms/{roomId}/send/{eventType}/{txnId}")
    @Headers("Content-type: application/json")
    SendEventResponse sendEvent(@Path("roomId") String roomId, @Path("eventType") String eventType, @Path("txnId") String txnId,
                                @Body Map<String, Object> event);

    /**
     * Strips all information out of an event which isn't critical to the integrity of the server-side representation of the room.
     * <br>
     * This cannot be undone.
     * <br>
     * Users may redact their own events, and any user with a power level greater than or equal to the redact power level of the
     * room may redact events there.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId  Required. The room from which to redact the event.
     * @param eventId Required. The ID of the event to redact.
     * @param txnId   Required. The transaction ID for this event. Clients should generate a unique ID; it will be used by the
     *                server to ensure idempotency of requests.
     * @param event   The reason for the event being redacted.
     * @return <p>Status code 200: An ID for the redaction event.</p>
     */
    @PUT("/_matrix/client/r0/rooms/{roomId}/redact/{eventId}/{txnId}")
    @Headers("Content-type: application/json")
    SendEventResponse redact(@Path("roomId") String roomId, @Path("eventId") String eventId, @Path("txnId") String txnId,
                             @Body RedactRequest event);
}
