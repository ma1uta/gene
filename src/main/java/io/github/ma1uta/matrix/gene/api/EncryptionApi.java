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

import io.github.ma1uta.matrix.gene.model.encryption.ChangesResponse;
import io.github.ma1uta.matrix.gene.model.encryption.ClaimRequest;
import io.github.ma1uta.matrix.gene.model.encryption.ClaimResponse;
import io.github.ma1uta.matrix.gene.model.encryption.QueryRequest;
import io.github.ma1uta.matrix.gene.model.encryption.QueryResponse;
import io.github.ma1uta.matrix.gene.model.encryption.UploadRequest;
import io.github.ma1uta.matrix.gene.model.encryption.UploadResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Matrix optionally supports end-to-end encryption, allowing rooms to be created whose conversation contents is not decryptable or
 * interceptable on any of the participating homeservers.
 */
public interface EncryptionApi {

    /**
     * Publishes end-to-end encryption keys for the device.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param uploadRequest   JSON body parameters.
     * @return Status code 200: The provided keys were sucessfully uploaded.
     */
    @POST("/_matrix/client/r0/keys/upload")
    @Headers("Content-type: application/json")
    UploadResponse uploadKey(@Body UploadRequest uploadRequest);

    /**
     * Returns the current devices and identity keys for the given users.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param queryRequest    JSON body parameters.
     * @return Status code 200: The device information.
     */
    @POST("/_matrix/client/r0/keys/query")
    @Headers("Content-type: application/json")
    QueryResponse query(@Body QueryRequest queryRequest);

    /**
     * Claims one-time keys for use in pre-key messages.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param claimRequest    JSON body parameters.
     * @return Status code 200: The claimed keys.
     */
    @POST("/_matrix/client/r0/keys/claim")
    @Headers("Content-type: application/json")
    ClaimResponse claim(@Body ClaimRequest claimRequest);

    /**
     * Gets a list of users who have updated their device identity keys since a previous sync token.
     * <p/>
     * The server should include in the results any users who:
     * <ul>
     * <li>currently share a room with the calling user (ie, both users have membership state join); and</li>
     * <li>added new device identity keys or removed an existing device with identity keys, between from and to.</li>
     * </ul>
     * Requires auth: Yes.
     *
     * @param from            Required. The desired start point of the list. Should be the next_batch field from a response to an earlier
     *                        call to /sync. Users who have not uploaded new device identity keys since this point, nor deleted existing
     *                        devices with identity keys since then, will be excluded from the results.
     * @param to              Required. The desired end point of the list. Should be the next_batch field from a recent call to /sync -
     *                        typically the most recent such call. This may be used by the server as a hint to check its caches are up to
     *                        date.
     * @return Status code 200: The list of users who updated their devices.
     */
    @GET("/_matrix/client/r0/keys/changes")
    @Headers("Content-type: application/json")
    ChangesResponse changes(@Query("from") String from, @Query("to") String to);
}
