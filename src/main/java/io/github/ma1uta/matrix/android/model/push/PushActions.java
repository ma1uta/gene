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

package io.github.ma1uta.matrix.android.model.push;

import java.util.List;

/**
 * JSON body request/response for action push api.
 */
public class PushActions {

    /**
     * Required. The action(s) to perform for this rule.
     * <p/>
     * One of: ["notify", "dont_notify", "coalesce", "set_tweak"].
     */
    public List<String> actions;
}
