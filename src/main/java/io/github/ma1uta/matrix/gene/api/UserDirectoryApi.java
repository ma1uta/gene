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

import io.github.ma1uta.matrix.gene.model.userdirectory.SearchRequest;
import io.github.ma1uta.matrix.gene.model.userdirectory.SearchResponse;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * User directory.
 * <br>
 * Provides search over all users.
 */
public interface UserDirectoryApi {

    /**
     * This API performs a server-side search over all users registered on the server. It searches user ID and displayname
     * case-insensitively for users that you share a room with or that are in public rooms.
     * <br>
     * <b>Rate-limited</b>: Yes.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param request json body request.
     * @return <p>Status code 200: The results of the search.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST("/_matrix/client/r0/user_directory/search")
    @Headers("Content-type: application/json")
    SearchResponse searchUsers(@Body SearchRequest request);
}
