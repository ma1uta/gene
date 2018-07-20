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

import io.github.ma1uta.matrix.gene.model.version.VersionsResponse;
import retrofit2.http.GET;

/**
 * Gets the versions of the specification supported by the server.
 * <p/>
 * Values will take the form rX.Y.Z.
 * <p/>
 * Only the latest Z value will be reported for each supported X.Y value.
 * i.e. if the server implements r0.0.0, r0.0.1, and r1.2.0, it will report r0.0.1 and r1.2.0.
 */
public interface VersionApi {

    /**
     * Gets the versions of the specification supported by the server.
     *
     * @return Status code 200: The versions supported by the server.
     */
    @GET("/_matrix/client/versions")
    VersionsResponse versions();
}
