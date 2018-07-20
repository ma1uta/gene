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

package io.github.ma1uta.matrix.gene.model.admin;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Response for gets information about a particular user.
 */
public class AdminResponse {

    /**
     * The Matrix user ID of the user.
     */
    @SerializedName("user_id")
    public String userId;

    /**
     * Each key is an identitfier for one of the user's devices.
     */
    public Map<String, DeviceInfo> devices;
}
