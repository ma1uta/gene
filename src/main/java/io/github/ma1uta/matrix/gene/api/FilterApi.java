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

import io.github.ma1uta.matrix.gene.model.filter.FilterData;
import io.github.ma1uta.matrix.gene.model.filter.FilterResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Filters can be created on the server and can be passed as as a parameter to APIs which return events. These filters alter the
 * data returned from those APIs. Not all APIs accept filters.
 */
public interface FilterApi {
    /**
     * Uploads a new filter definition to the homeserver. Returns a filter ID that may be used in future requests to restrict which
     * events are returned to the client.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId     Required. The id of the user uploading the filter. The access token must be authorized to make requests for
     *                   this user id.
     * @param filterData JSON body parameters.
     * @return <p>Status code 200: The filter was created.</p>
     */
    @POST("/_matrix/client/r0/user/{userId}/filter")
    @Headers("Content-type: application/json")
    FilterResponse uploadFilter(@Path("userId") String userId, @Body FilterData filterData);

    /**
     * Download a filter.
     * <br>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId   Required. The user ID to download a filter for.
     * @param filterId Required. The filter ID to download.
     * @return <p>Status code 200: "The filter defintion".</p>
     * <p>Status code 404: Unknown filter.</p>
     */
    @GET("/_matrix/client/r0/user/{userId}/filter/{filterId}")
    @Headers("Content-type: application/json")
    FilterData getFilter(@Path("userId") String userId, @Path("filterId") String filterId);
}
