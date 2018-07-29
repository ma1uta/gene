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
import io.github.ma1uta.matrix.gene.model.common.Event;
import io.github.ma1uta.matrix.gene.model.presence.PresenceList;
import io.github.ma1uta.matrix.gene.model.presence.PresenceStatus;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

/**
 * Each user has the concept of presence information. This encodes:
 * <ul>
 * <li>Whether the user is currently online</li>
 * <li>How recently the user was last active (as seen by the server)</li>
 * <li>Whether a given client considers the user to be currently idle</li>
 * <li>Arbitrary information about the user's current status (e.g. "in a meeting").</li>
 * </ul>
 * <br>
 * This information is collated from both per-device (online, idle, last_active) and per-user (status) data, aggregated
 * by the user's homeserver and transmitted as an m.presence event. This is one of the few events which are sent outside
 * the context of a room. Presence events are sent to all users who subscribe to this user's presence through a presence
 * list or by sharing membership of a room.
 * <br>
 * A presence list is a list of user IDs whose presence the user wants to follow. To be added to this list, the user being
 * added must be invited by the list owner who must accept the invitation.
 * <br>
 * User's presence state is represented by the presence key, which is an enum of one of the following:
 * <ul>
 * <li>online : The default state when the user is connected to an event stream.</li>
 * <li>unavailable : The user is not reachable at this time e.g. they are idle.</li>
 * <li>offline : The user is not connected to an event stream or is explicitly suppressing their profile information from being sent.</li>
 * </ul>
 */
public interface PresenceApi {

    /**
     * This API sets the given user's presence state. When setting the status, the activity time is updated to reflect
     * that activity; the client does not need to specify the last_active_ago field. You cannot set the presence state of
     * another user.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId         Required. The user whose presence state to update.
     * @param presenceStatus JSON body request.
     * @return <p>Status code 200: The new presence state was set.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @PUT("/_matrix/client/r0/presence/{userId}/status")
    @Headers("Content-type: application/json")
    EmptyResponse setPresenceStatus(@Path("userId") String userId, @Body PresenceStatus presenceStatus);

    /**
     * Get the given user's presence state.
     *
     * @param userId Required. The user whose presence state to get.
     * @return <p>Status code 200: The presence state for this user.</p>
     * <p>Status code 403: You are not allowed to see this user's presence status.</p>
     * <p>Status code 404: There is no presence state for this user. This user may not exist or isn't exposing presence
     * information to you.</p>
     */
    @GET("/_matrix/client/r0/presence/{userId}/status")
    @Headers("Content-type: application/json")
    PresenceStatus getPresenceStatus(@Path("userId") String userId);

    /**
     * Adds or removes users from this presence list.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId       Required. The user whose presence list is being modified.
     * @param presenceList JSON body request.
     * @return <p>Status code 200: The list was updated.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST("/_matrix/client/r0/presence/list/{userId}")
    @Headers("Content-type: application/json")
    EmptyResponse setPresenceList(@Path("userId") String userId, @Body PresenceList presenceList);

    /**
     * Retrieve a list of presence events for every user on this list.
     *
     * @param userId Required. The user whose presence list should be retrieved.
     * @return <p>Status code 200: A list of presence events for this list.</p>
     */
    @GET("/_matrix/client/r0/presence/list/{userId}")
    @Headers("Content-type: application/json")
    List<Event> getPresenceList(@Path("userId") String userId);
}
