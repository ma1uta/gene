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

package io.github.ma1uta.matrix.gene.model.room;

/**
 * JSON body request.
 */
public class PublicRoomsRequest {

    /**
     * Limit the number of results returned.
     */
    public Long limit;

    /**
     * A pagination token from a previous request, allowing clients to get the next (or previous) batch of rooms.
     * The direction of pagination is specified solely by which token is supplied, rather than via an explicit flag.
     */
    public String since;

    /**
     * Filter to apply to the results.
     */
    public PublicRoomsFilter filter;
}
