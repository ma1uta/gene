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

import io.github.ma1uta.matrix.gene.model.admin.AdminResponse;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Gets information about a particular user.
 */
public interface AdminApi {

    /**
     * This API may be restricted to only be called by the user being looked up, or by a server admin. Server-local administrator
     * privileges are not specified in this document.
     *
     * @param userId Required. The user to look up.
     * @return <p>Status code 200: The lookup was successful.</p>
     */
    @GET("/_matrix/client/r0/admin/whois/{userId}")
    @Headers("Content-type: application/json")
    AdminResponse whois(@Path("userId") String userId);
}
