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

package io.github.ma1uta.matrix.android.model.room;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * JSON body response.
 */
public class PublicRoomsResponse {

    /**
     * Required. A paginated chunk of public rooms.
     */
    public List<PublicRoomsChunk> chunk;

    /**
     * A pagination token for the response. The absence of this token means there are no more results to fetch and the client
     * should stop paginating.
     */
    @SerializedName("next_batch")
    public String nextBatch;

    /**
     * A pagination token that allows fetching previous results. The absence of this token means there are no results before
     * this batch, i.e. this is the first batch.
     */
    @SerializedName("prev_batch")
    public String prevBatch;

    /**
     * An estimate on the total number of public rooms, if the server has an estimate.
     */
    @SerializedName("total_room_count_estimate")
    public Long totalRoomCountEstimate;
}
