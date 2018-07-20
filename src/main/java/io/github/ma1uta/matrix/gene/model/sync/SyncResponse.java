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

package io.github.ma1uta.matrix.gene.model.sync;

import com.google.gson.annotations.SerializedName;

/**
 * JSON body response for sync api.
 */
public class SyncResponse {

    /**
     * The batch token to supply in the since param of the next /sync request.
     */
    @SerializedName("next_batch")
    public String nextBatch;

    /**
     * Updates to rooms.
     */
    public Rooms rooms;

    /**
     * The updates to the presence status of other users.
     */
    public Presence presence;

    /**
     * The global public data created by this user.
     */
    @SerializedName("account_data")
    public AccountData accountData;

    /**
     * Information on the send-to-device messages for the client device, as defined in Send-to-Device messaging.
     */
    @SerializedName("to_device")
    public ToDevice toDevice;

    /**
     * Information on end-to-end device updates, as specified in End-to-end encryption.
     */
    @SerializedName("device_lists")
    public DeviceLists deviceLists;
}
