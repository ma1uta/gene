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

import io.github.ma1uta.matrix.android.model.voip.VoipResponse;
import retrofit2.http.GET;

/**
 * The homeserver MAY provide a TURN server which clients can use to contact the remote party. The following HTTP API endpoints will
 * be used by clients in order to get information about the TURN server.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#get-matrix-client-r0-voip-turnserver">Specification.</a>
 */
public interface VoipApi {

    /**
     * This API provides credentials for the client to use when initiating calls.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @return Status code 200: The TURN server credentials.
     *     Status code 429: This request was rate-limited.
     */
    @GET("/_matrix/client/r0/voip/turnServer")
    VoipResponse turnServer();
}
