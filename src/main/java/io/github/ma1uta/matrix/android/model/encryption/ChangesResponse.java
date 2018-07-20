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

package io.github.ma1uta.matrix.android.model.encryption;

import java.util.List;

/**
 * The list of users who updated their devices.
 */
public class ChangesResponse {

    /**
     * The Matrix User IDs of all users who updated their device identity keys.
     */
    public List<String> changed;

    /**
     * The Matrix User IDs of all users who may have left all the end-to-end encrypted rooms they previously shared with the user.
     */
    public List<String> left;
}
