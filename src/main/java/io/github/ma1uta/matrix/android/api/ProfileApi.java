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

import io.github.ma1uta.matrix.android.model.common.EmptyResponse;
import io.github.ma1uta.matrix.android.model.profile.AvatarUrl;
import io.github.ma1uta.matrix.android.model.profile.DisplayName;
import io.github.ma1uta.matrix.android.model.profile.Profile;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Profiles.
 */
public interface ProfileApi {

    /**
     * This API sets the given user's display name. You must have permission to set this user's display name, e.g. you need to
     * have their access_token.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The user whose display name to set.
     * @param displayName     JSON body request.
     * @return Status code 200: The display name was set.
     *     Status code 429: This request was rate-limited.
     */
    @PUT("/_matrix/client/r0/profile/{userId}/displayname")
    @Headers("Content-type: application/json")
    EmptyResponse setDisplayName(@Path("userId") String userId, @Body DisplayName displayName);

    /**
     * Get the user's display name. This API may be used to fetch the user's own displayname or to query the name of other users; either
     * locally or on remote homeservers.
     *
     * @param userId          Required. The user whose display name to get.
     * @return Status code 200: The display name for this user.
     *     Status code 404: There is no display name for this user or this user does not exist.
     */
    @GET("/_matrix/client/r0/profile/{userId}/displayname")
    @Headers("Content-type: application/json")
    DisplayName showDisplayName(@Path("userId") String userId);

    /**
     * This API sets the given user's avatar URL. You must have permission to set this user's avatar URL, e.g. you need to have
     * their access_token.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId          Required. The user whose avatar URL to set.
     * @param avatarUrl       JSON body request.
     * @return Status code 200: The avatar URL was set.
     *     Status code 429: This request was rate-limited.
     */
    @PUT("/_matrix/client/r0/profile/{userId}/avatar_url")
    @Headers("Content-type: application/json")
    EmptyResponse setAvatarUrl(@Path("userId") String userId, @Body AvatarUrl avatarUrl);

    /**
     * Get the user's avatar URL. This API may be used to fetch the user's own avatar URL or to query the URL of other users;
     * either locally or on remote homeservers.
     *
     * @param userId          Required. The user whose avatar URL to get.
     * @return Status code 200: The avatar URL for this user.
     *     Status code 404: There is no avatar URL for this user or this user does not exist.
     */
    @GET("/_matrix/client/r0/profile/{userId}/avatar_url")
    @Headers("Content-type: application/json")
    AvatarUrl showAvatarUrl(@Path("userId") String userId);

    /**
     * Get the combined profile information for this user. This API may be used to fetch the user's own profile information or
     * other users; either locally or on remote homeservers. This API may return keys which are not limited to displayname or avatar_url.
     *
     * @param userId          Required. The user whose profile information to get.
     * @return Status code 200: The avatar URL for this user.
     *     Status code 404: There is no profile information for this user or this user does not exist.
     */
    @GET("/_matrix/client/r0/profile/{userId}")
    @Headers("Content-type: application/json")
    Profile profile(@Path("userId") String userId);
}
