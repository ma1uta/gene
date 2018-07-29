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
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.Map;

/**
 * Client Behaviour.
 */
public interface ClientConfigApi {

    /**
     * Set some account_data for the client. This config is only visible to the user that set the account_data. The config will be
     * synced to clients in the top-level account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId      Required. The id of the user to set account_data for. The access token must be authorized to make
     *                    requests for this user id.
     * @param type        Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData account data.
     * @return <p>Status code 200: The account_data was successfully added.</p>
     */
    @PUT("/_matrix/client/r0/user/{userId}/account_data/{type}")
    @Headers("Content-type: application/json")
    EmptyResponse addConfig(@Path("userId") String userId, @Path("type") String type, @Body Map<String, String> accountData);

    /**
     * Set some account_data for the client on a given room. This config is only visible to the user that set the account_data.
     * The config will be synced to clients in the per-room account_data.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId      Required. The id of the user to set account_data for. The access token must be authorized to make requests for
     *                    this user id.
     * @param roomId      Required. The id of the room to set account_data on.
     * @param type        Required. The event type of the account_data to set. Custom types should be namespaced to avoid clashes.
     * @param accountData account data.
     * @return <p>Status code 200: The account_data was successfully added.</p>
     */
    @PUT("/_matrix/client/r0/user/{userId}/rooms/{roomId}/account_data/{type}")
    @Headers("Content-type: application/json")
    EmptyResponse addRoomConfig(@Path("userId") String userId, @Path("roomId") String roomId, @Path("type") String type,
                                @Body Map<String, String> accountData);
}
