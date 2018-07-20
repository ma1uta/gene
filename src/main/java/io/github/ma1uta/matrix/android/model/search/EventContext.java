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

package io.github.ma1uta.matrix.android.model.search;

import com.google.gson.annotations.SerializedName;

/**
 * Event context.
 */
public class EventContext {

    /**
     * How many events before the result are returned.
     */
    @SerializedName("before_limit")
    public Long beforeLimit;

    /**
     * How many events after the result are returned.
     */
    @SerializedName("after_limit")
    public Long afterLimit;

    /**
     * Requests that the server returns the historic profile information for the users that sent the events that were returned.
     */
    @SerializedName("include_profile")
    public Boolean includeProfile;
}
