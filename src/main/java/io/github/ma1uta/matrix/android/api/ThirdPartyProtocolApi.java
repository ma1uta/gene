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

import io.github.ma1uta.matrix.android.model.common.protocol.Protocol;
import io.github.ma1uta.matrix.android.model.common.protocol.ProtocolLocation;
import io.github.ma1uta.matrix.android.model.common.protocol.ProtocolUser;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

/**
 * Application services can provide access to third party networks via bridging. This allows Matrix users to communicate with users
 * on other communication platforms, with messages ferried back and forth by the application service. A single application service
 * may bridge multiple third party networks, and many individual locations within those networks. A single third party network
 * location may be bridged to multiple Matrix rooms.
 */
public interface ThirdPartyProtocolApi {

    /**
     * Fetches the overall metadata about protocols supported by the homeserver. Includes both the available protocols and all
     * fields required for queries against each protocol.
     *
     * @return Status code 200: The protocols supported by the homeserver.
     */
    @GET("/_matrix/client/r0/thirdparty/protocols")
    @Headers("Content-type: application/json")
    Map<String, Protocol> protocols();

    /**
     * Fetches the metadata from the homeserver about a particular third party protocol.
     *
     * @param protocol Required. The name of the protocol.
     * @return Status code 200: The protocol was found and metadata returned.
     *     Status code 404: The protocol is unknown.
     */
    @GET("/_matrix/client/r0/thirdparty/protocol/{protocol}")
    @Headers("Content-type: application/json")
    Protocol protocol(@Path("protocol") String protocol);

    /**
     * Requesting this endpoint with a valid protocol name results in a list of successful mapping results in a JSON array.
     * Each result contains objects to represent the Matrix room or rooms that represent a portal to this third party network.
     * Each has the Matrix room alias string, an identifier for the particular third party network protocol, and an object
     * containing the network-specific fields that comprise this identifier. It should attempt to canonicalise the identifier
     * as much as reasonably possible given the network type.
     *
     * @param protocol     Required. The protocol used to communicate to the third party network.
     * @param searchFields One or more custom fields to help identify the third party location.
     * @return Status code 200: At least one portal room was found.
     *     Status code 404: No portal rooms were found.
     */
    @GET("/_matrix/client/r0/thirdparty/location/{protocol}")
    @Headers("Content-type: application/json")
    List<ProtocolLocation> locationProtocol(@Path("protocol") String protocol, @Query("searchFields") String searchFields);

    /**
     * Retrieve a Matrix User ID linked to a user on the third party service, given a set of user parameters.
     *
     * @param protocol Required. The name of the protocol.
     * @param params   query params.
     * @return Status code 200: The Matrix User IDs found with the given parameters.
     *     Status code 404: The Matrix User ID was not found.
     */
    @GET("/_matrix/client/r0/thirdparty/user/{protocol}")
    @Headers("Content-type: application/json")
    List<ProtocolUser> userProtocol(@Path("protocol") String protocol, @QueryMap Map<String, String> params);

    /**
     * Retreive an array of third party network locations from a Matrix room alias.
     *
     * @param alias Required. The Matrix room alias to look up.
     * @return Status code 200: At least one portal room was found.
     *     Status code 404: No portal rooms were found.
     */
    @GET("/_matrix/client/r0/thirdparty/location")
    @Headers("Content-type: application/json")
    List<ProtocolLocation> location(@Query("alias") String alias);

    /**
     * Retreive an array of third party users from a Matrix User ID.
     *
     * @param userId Required. The Matrix User ID to look up.
     * @return Status code 200: The Matrix User IDs found with the given parameters.
     *     Status code 404: The Matrix User ID was not found.
     */
    @GET("/_matrix/client/r0/thirdparty/user")
    @Headers("Content-type: application/json")
    List<ProtocolUser> user(@Query("userid") String userId);
}
