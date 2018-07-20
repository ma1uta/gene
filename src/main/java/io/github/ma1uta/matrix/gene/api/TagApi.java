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

import io.github.ma1uta.matrix.gene.model.common.EmptyResponse;
import io.github.ma1uta.matrix.gene.model.tag.Tags;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.Map;

/**
 * Users can add tags to rooms. Tags are short strings used to label rooms, e.g. "work", "family". A room may have multiple tags.
 * Tags are only visible to the user that set them but are shared across all their devices.
 */
public interface TagApi {

    /**
     * TagInfo special names.
     */
    class Tag {

        protected Tag() {
        }

        /**
         * Favourite.
         */
        public static final String FAVOURITE = "m.favourite";

        /**
         * Low priority.
         */
        public static final String LOWPRIOORITY = "m.lowpriority";
    }

    /**
     * List the tags set by a user on a room.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId Required. The id of the user to get tags for. The access token must be authorized to make requests for this
     *               user id.
     * @param roomId Required. The id of the room to get tags for.
     * @return Status code 200: The list of tags for the user for the room.
     */
    @GET("/_matrix/client/r0/user/{userId}/rooms/{roomId}/tags")
    @Headers("Content-type: application/json")
    Tags showTags(@Path("userId") String userId, @Path("roomId") String roomId);

    /**
     * Add a tag to the room.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId  Required. The id of the user to add a tag for. The access token must be authorized to make requests for this
     *                user id.
     * @param roomId  Required. The id of the room to add a tag to.
     * @param tag     Required. The tag to add.
     * @param tagData tag data.
     * @return Status code 200: The tag was successfully added.
     */
    @PUT("/_matrix/client/r0/user/{userId}/rooms/{roomId}/tags/{tag}")
    @Headers("Content-type: application/json")
    EmptyResponse addTag(@Path("userId") String userId, @Path("roomId") String roomId, @Path("tag") String tag,
                         @Body Map<String, String> tagData);

    /**
     * Remove a tag from the room.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param userId Required. The id of the user to remove a tag for. The access token must be authorized to make requests
     *               for this user id.
     * @param roomId Required. The id of the room to remove a tag from.
     * @param tag    Required. The tag to remove.
     * @return Status code 200: The tag was successfully removed.
     */
    @DELETE("/_matrix/client/r0/user/{userId}/rooms/{roomId}/tags/{tag}")
    @Headers("Content-type: application/json")
    EmptyResponse deleteTag(@Path("userId") String userId, @Path("roomId") String roomId, @Path("tag") String tag);
}
