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
import io.github.ma1uta.matrix.android.model.room.CreateRoomRequest;
import io.github.ma1uta.matrix.android.model.room.InviteRequest;
import io.github.ma1uta.matrix.android.model.room.JoinRequest;
import io.github.ma1uta.matrix.android.model.room.JoinedRoomsResponse;
import io.github.ma1uta.matrix.android.model.room.KickRequest;
import io.github.ma1uta.matrix.android.model.room.PublicRoomsRequest;
import io.github.ma1uta.matrix.android.model.room.PublicRoomsResponse;
import io.github.ma1uta.matrix.android.model.room.RoomId;
import io.github.ma1uta.matrix.android.model.room.RoomVisibility;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

/**
 * Rooms apis.
 */
public interface RoomApi {
    /**
     * Visibility.
     */
    class Visibility {

        protected Visibility() {
        }

        /**
         * Public.
         */
        public static final String PUBLIC = "public";

        /**
         * Private.
         */
        public static final String PRIVATE = "private";
    }

    /**
     * Presets.
     */
    class Preset {

        protected Preset() {
        }

        /**
         * Private.
         */
        public static final String PRIVATE_CHAT = "private_chat";

        /**
         * Public.
         */
        public static final String PUBLIC_CHAT = "public_chat";

        /**
         * Trusted.
         */
        public static final String TRUSTED_PRIVATE_CHAT = "trusted_private_chat";
    }

    /**
     * Create a new room with various configuration options.
     * <p/>
     * The server MUST apply the normal state resolution rules when creating the new room, including checking power levels for each event.
     * It MUST apply the events implied by the request in the following order:
     * <ol>
     * <li>A default m.room.power_levels event, giving the room creator (and not other members) permission to send state events.</li>
     * <li>Events set by the presets.</li>
     * <li>Events listed in initial_state, in the order that they are listed.</li>
     * <li>Events implied by name and topic.</li>
     * <li>Invite events implied by invite and invite_3pid.</li>
     * </ol>
     * <p/>
     * The available presets do the following with respect to room state:
     * <table>
     * <tr>
     * <th>Preset</th>
     * <th>join_rules</th>
     * <th>history_visibility</th>
     * <th>guest_access</th>
     * <th>Other</th>
     * </tr>
     * <tr>
     * <td>private_chat</td>
     * <td>invite</td>
     * <td>shared</td>
     * <td>can_join</td>
     * </tr>
     * <tr>
     * <td>trusted_private_chat</td>
     * <td>invite</td>
     * <td>shared</td>
     * <td>can_join</td>
     * <td>All invitees are given the same power level as the room creator.</td>
     * </tr>
     * <tr>
     * <td>public_chat</td>
     * <td>public</td>
     * <td>shared</td>
     * <td>forbidden</td>
     * </tr>
     * </table>
     * <b>Requires auth</b>: Yes.
     *
     * @param createRoomRequest JSON body parameters.
     * @return Status code 200: Information about the newly created room.
     *     Status code 400: The request is invalid. A meaningful errcode and description error text will be returned.
     *     Example reasons for rejection include:
     *     <ul>
     *     <li>The request body is malformed (errcode set to M_BAD_JSON or M_NOT_JSON).</li>
     *     <li>The room alias specified is already taken (errcode set to M_ROOM_IN_USE).</li>
     *     <li>The initial state implied by the parameters to the request is invalid: for example, the user's power_level is set
     *     below that necessary to set the room name (errcode set to M_INVALID_ROOM_STATE).</li>
     *     </ul>
     */
    @POST("/_matrix/client/r0/createRoom")
    @Headers("Content-type: application/json")
    RoomId create(@Body CreateRoomRequest createRoomRequest);

    /**
     * Create a new mapping from room alias to room ID.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomAlias       Required. The room alias to set.
     * @param roomId          json body request.
     * @return Status code 200: The mapping was created.
     *     Status code 409: A room alias with that name already exists.
     */
    @PUT("/_matrix/client/r0/directory/room/{roomAlias}")
    @Headers("Content-type: application/json")
    EmptyResponse newAlias(@Path("roomAlias") String roomAlias, @Body RoomId roomId);

    /**
     * Requests that the server resolve a room alias to a room ID.
     * <p/>
     * The server will use the federation API to resolve the alias if the domain part of the alias does not correspond to the server's
     * own domain.
     *
     * @param roomAlias       Required. The room alias.
     * @return Status code 200: The room ID and other information for this alias.
     *     Status code 404: There is no mapped room ID for this room alias.
     */
    @GET("/_matrix/client/r0/directory/room/{roomAlias}")
    @Headers("Content-type: application/json")
    RoomId resolve(@Path("roomAlias") String roomAlias);

    /**
     * Remove a mapping of room alias to room ID.
     * <p/>
     * Servers may choose to implement additional access control checks here, for instance that room aliases can only be deleted
     * by their creator or a server administrator.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomAlias       Required. The room alias to remove.
     * @return Status code 200: The mapping was deleted.
     */
    @DELETE("/_matrix/client/r0/directory/room/{roomAlias}")
    @Headers("Content-type: application/json")
    EmptyResponse delete(@Path("roomAlias") String roomAlias);

    /**
     * This API returns a list of the user's current rooms.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @return Status code 200: A list of the rooms the user is in.
     */
    @GET("/_matrix/client/r0/joined_rooms")
    @Headers("Content-type: application/json")
    JoinedRoomsResponse joinedRooms();

    /**
     * Note that there are two forms of this API, which are documented separately. This version of the API requires that the inviter
     * knows the Matrix identifier of the invitee. The other is documented in the third party invites section.
     * <p/>
     * This API invites a user to participate in a particular room. They do not start participating in the room until they actually
     * join the room.
     * <p/>
     * Only users currently in a particular room can invite other users to join that room.
     * <p/>
     * If the user was invited to the room, the homeserver will append a m.room.member event to the room.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room identifier (not alias) to which to invite the user.
     * @param inviteRequest   JSON body request.
     * @return Status code 200: The user has been invited to join the room.
     *     Status code 403: You do not have permission to invite the user to the room. A meaningful errcode and description error text
     *     will be returned. Example reasons for rejections are:
     *     <ul>
     *     <li>The invitee has been banned from the room.</li>
     *     <li>The invitee is already a member of the room.</li>
     *     <li>The inviter is not currently in the room.</li>
     *     <li>The inviter's power level is insufficient to invite users to the room.</li>
     *     </ul>
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/invite")
    @Headers("Content-type: application/json")
    EmptyResponse invite(@Path("roomId") String roomId, @Body InviteRequest inviteRequest);

    /**
     * Note that this API requires a room ID, not alias. /join/{roomIdOrAlias} exists if you have a room alias.
     * <p/>
     * This API starts a user participating in a particular room, if that user is allowed to participate in that room.
     * After this call, the client is allowed to see all current state events in the room, and all subsequent events associated
     * with the room until the user leaves the room.
     * <p/>
     * After a user has joined a room, the room will appear as an entry in the response of the /initialSync and /sync APIs.
     * <p/>
     * If a third_party_signed was supplied, the homeserver must verify that it matches a pending m.room.third_party_invite
     * event in the room, and perform key validity checking if required by the event.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room identifier (not alias) to join.
     * @param joinRequest     JSON body request.
     * @return Status code 200: The room has been joined. The joined room ID must be returned in the room_id field.
     *     Status code 403: You do not have permission to join the room. A meaningful errcode and description error text will be returned.
     *     Example reasons for rejection are:
     *     <ul>
     *     <li>The room is invite-only and the user was not invited.</li>
     *     <li>The user has been banned from the room.</li>
     *     </ul>
     *     Status code 429:This request was rate-limited.
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/join")
    @Headers("Content-type: application/json")
    RoomId join(@Path("roomId") String roomId, @Body JoinRequest joinRequest);

    /**
     * Note that this API takes either a room ID or alias, unlike /room/{roomId}/join.
     * <p/>
     * This API starts a user participating in a particular room, if that user is allowed to participate in that room.
     * After this call, the client is allowed to see all current state events in the room, and all subsequent events associated
     * with the room until the user leaves the room.
     * <p/>
     * After a user has joined a room, the room will appear as an entry in the response of the /initialSync and /sync APIs.
     * <p/>
     * If a third_party_signed was supplied, the homeserver must verify that it matches a pending m.room.third_party_invite
     * event in the room, and perform key validity checking if required by the event.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomIdOrAlias   Required. The room identifier or alias to join.
     * @param joinRequest     JSON body request.
     * @param serverName      The servers to attempt to join the room through. One of the servers must be participating in the room.
     * @return Status code 200: The room has been joined. The joined room ID must be returned in the room_id field.
     *     Status code 403:
     *     You do not have permission to join the room. A meaningful errcode and description error text will be returned.
     *     Example reasons for rejection are:
     *     <ul>
     *     <li>The room is invite-only and the user was not invited.</li>
     *     <li>The user has been banned from the room.</li>
     *     </ul>
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/client/r0/join/{roomIdOrAlias}")
    @Headers("Content-type: application/json")
    RoomId joinByIdOrAlias(@Path("roomIdOrAlias") String roomIdOrAlias, @Query("server_name") List<String> serverName,
                           @Body JoinRequest joinRequest);

    /**
     * This API stops a user participating in a particular room.
     * <p/>
     * If the user was already in the room, they will no longer be able to see new events in the room.
     * If the room requires an invite to join, they will need to be re-invited before they can re-join.
     * <p/>
     * If the user was invited to the room, but had not joined, this call serves to reject the invite.
     * <p/>
     * The user will still be allowed to retrieve history from the room which they were previously allowed to see.
     * <p/>
     * Rate-limited: Yes.
     * <p/>
     * Requires auth: Yes.
     *
     * @param roomId          Required. The room identifier to leave.
     * @return Status code 200: The room has been left.
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/leave")
    @Headers("Content-type: application/json")
    EmptyResponse leave(@Path("roomId") String roomId);

    /**
     * This API stops a user remembering about a particular room.
     * <p/>
     * In general, history is a first class citizen in Matrix. After this API is called, however, a user will no longer be
     * able to retrieve history for this room. If all users on a homeserver forget a room, the room is eligible for deletion
     * from that homeserver.
     * <p/>
     * If the user is currently joined to the room, they must leave the room before calling this API.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room identifier to forget.
     * @return Status code 200: The room has been forgotten.
     *     Status code 400: The user has not left the room.
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/forget")
    @Headers("Content-type: application/json")
    EmptyResponse forget(@Path("roomId") String roomId);

    /**
     * Kick a user from the room.
     * <p/>
     * The caller must have the required power level in order to perform this operation.
     * <p/>
     * Kicking a user adjusts the target member's membership state to be ``leave`` with an
     * optional ``reason``. Like with other membership changes, a user can directly adjust
     * the target member's state by making a request to ``/rooms/&lt;room id&gt;/state/m.room.member/&lt;user id&gt;``.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room identifier (not alias) from which the user should be kicked.
     * @param kickRequest     JSON body request.
     * @return Status code 200: The user has been kicked from the room.
     *     Status code 403: You do not have permission to kick the user from the room. A meaningful errcode and description error
     *     text will be returned. Example reasons for rejections are:
     *     <ul>
     *     <li>The kicker is not currently in the room.</li>
     *     <li>The kickee is not currently in the room.</li>
     *     <li>The kicker's power level is insufficient to kick users from the room.</li>
     *     </ul>
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/kick")
    @Headers("Content-type: application/json")
    EmptyResponse kick(@Path("roomId") String roomId, @Body KickRequest kickRequest);

    /**
     * Ban a user in the room. If the user is currently in the room, also kick them.
     * <p/>
     * When a user is banned from a room, they may not join it or be invited to it until they are unbanned.
     * <p/>
     * The caller must have the required power level in order to perform this operation.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room identifier (not alias) from which the user should be banned.
     * @param banRequest      JSON body request.
     * @return Status code 200: The user has been kicked and banned from the room.
     *     Status code 403: You do not have permission to ban the user from the room. A meaningful errcode and description error
     *     text will be returned. Example reasons for rejections are:
     *     <ul>
     *     <li>The banner is not currently in the room.</li>
     *     <li>The banner's power level is insufficient to ban users from the room.</li>
     *     </ul>
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/ban")
    @Headers("Content-type: application/json")
    EmptyResponse ban(@Path("roomId") String roomId, @Body KickRequest banRequest);

    /**
     * Unban a user from the room. This allows them to be invited to the room, and join if they would otherwise be allowed to join
     * according to its join rules.
     * <p/>
     * The caller must have the required power level in order to perform this operation.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room identifier (not alias) from which the user should be unbanned.
     * @param unbanRequest    JSON body request.
     * @return Status code 200: The user has been unbanned from the room.
     *     Status code 403: You do not have permission to unban the user from the room. A meaningful errcode and description error
     *     text will be returned. Example reasons for rejections are:
     *     <ul>
     *     <li>The unbanner's power level is insufficient to unban users from the room.</li>
     *     </ul>
     */
    @POST("/_matrix/client/r0/rooms/{roomId}/unban")
    @Headers("Content-type: application/json")
    EmptyResponse unban(@Path("roomId") String roomId, @Body KickRequest unbanRequest);

    /**
     * Gets the visibility of a given room on the server's public room directory.
     *
     * @param roomId          Required. The room ID.
     * @return Status code 200: The visibility of the room in the directory
     *     Status code 404: The room is not known to the server
     */
    @GET("/_matrix/client/r0/directory/list/room/{roomId}")
    @Headers("Content-type: application/json")
    RoomVisibility getVisibility(@Path("roomId") String roomId);

    /**
     * Sets the visibility of a given room in the server's public room directory.
     * <p/>
     * Servers may choose to implement additional access control checks here, for instance that room visibility can only be
     * changed by the room creator or a server administrator.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param roomId          Required. The room ID.
     * @param visibility      json body request.
     * @return Status code 200: The visibility was updated, or no change was needed.
     *     Status code 404: The room is not known to the server.
     */
    @PUT("/_matrix/client/r0/directory/list/room/{roomId}")
    @Headers("Content-type: application/json")
    EmptyResponse setVisibility(@Path("roomId") String roomId, @Body RoomVisibility visibility);

    /**
     * Lists the public rooms on the server.
     * <p/>
     * This API returns paginated responses. The rooms are ordered by the number of joined members, with the largest rooms first.
     *
     * @param limit           Limit the number of results returned.
     * @param since           A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms.
     *                        The direction of pagination is specified solely by which token is supplied, rather than via an explicit flag.
     * @param server          The server to fetch the public room lists from. Defaults to the local server.
     * @return Status code 200: A list of the rooms on the server.
     */
    @GET("/_matrix/client/r0/publicRooms")
    @Headers("Content-type: application/json")
    PublicRoomsResponse showPublicRooms(@Query("limit") Long limit, @Query("since") String since, @Query("server") String server);

    /**
     * Lists the public rooms on the server, with optional filter.
     * <p/>
     * This API returns paginated responses. The rooms are ordered by the number of joined members, with the largest rooms first.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param server             The server to fetch the public room lists from. Defaults to the local server.
     * @param publicRoomsRequest JSON body request.
     * @return Status code 200: A list of the rooms on the server.
     */
    @POST("/_matrix/client/r0/publicRooms")
    @Headers("Content-type: application/json")
    PublicRoomsResponse searchPublicRooms(@Query("server") String server, @Body PublicRoomsRequest publicRoomsRequest);
}
