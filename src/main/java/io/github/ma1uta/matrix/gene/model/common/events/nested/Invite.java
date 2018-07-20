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

package io.github.ma1uta.matrix.gene.model.common.events.nested;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.Signed;

/**
 * Third-party invites.
 */
public class Invite {

    /**
     * Required. A name which can be displayed to represent the user instead of their third party identifier.
     */
    @SerializedName("display_name")
    public String displayName;

    /**
     * Required. A block of content which has been signed, which servers can use to verify the event. Clients should ignore this.
     */
    public Signed signed;
}
