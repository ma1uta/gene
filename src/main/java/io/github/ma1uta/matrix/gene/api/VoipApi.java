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

import io.github.ma1uta.matrix.gene.model.voip.VoipResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * The homeserver MAY provide a TURN server which clients can use to contact the remote party. The following HTTP API endpoints will
 * be used by clients in order to get information about the TURN server.
 */
public interface VoipApi {

    /**
     * This API provides credentials for the client to use when initiating calls.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @return <p>Status code 200: The TURN server credentials.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @GET("/_matrix/client/r0/turnServer")
    @Headers("Content-type: application/json")
    VoipResponse turnServer();
}
