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

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * JSON body resposnse with joined rooms.
 */
public class JoinedRoomsResponse {

    /**
     * Required. The ID of each room in which the user has joined membership.
     */
    @SerializedName("joined_rooms")
    public List<String> joinedRooms;
}
