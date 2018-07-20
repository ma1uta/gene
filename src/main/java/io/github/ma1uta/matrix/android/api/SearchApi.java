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

import io.github.ma1uta.matrix.android.model.search.SearchRequest;
import io.github.ma1uta.matrix.android.model.search.SearchResponse;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * The search API allows clients to perform full text search across events in all rooms that the user has been in, including those
 * that they have left. Only events that the user is allowed to see will be searched, e.g. it won't include events in rooms that
 * happened after you left.
 * <p/>
 * <a href="https://matrix.org/docs/spec/client_server/r0.3.0.html#id395">Specification.</a>
 */
public interface SearchApi {
    /**
     * Performs a full text search across different categories.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param nextBatch       The point to return events from. If given, this should be a next_batch result from a previous call
     *                        to this endpoint.
     * @param searchRequest   JSON body request.
     * @return Status code 200: Results of the search.
     *     Status code 400: Part of the request was invalid.
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/client/r0/search")
    @Headers("Content-type: application/json")
    SearchResponse search(@Query("next_batch") String nextBatch, @Body SearchRequest searchRequest);
}
