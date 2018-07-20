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

import java.util.Map;

/**
 * Third party signed.
 */
public class ThirdPartySigned {

    /**
     * Required. The Matrix ID of the user who issued the invite.
     */
    public String sender;

    /**
     * Required. The Matrix ID of the invitee.
     */
    public String mxid;

    /**
     * Required. The state key of the m.third_party_invite event.
     */
    public String token;

    /**
     * Required. A signatures object containing a signature of the entire signed object.
     */
    public Map<String, Map<String, String>> signatures;
}
