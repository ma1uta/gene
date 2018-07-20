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

package io.github.ma1uta.matrix.gene.model.typing;

/**
 * Typing body request.
 */
public class TypingRequest {

    /**
     * Required. Whether the user is typing or not. If false, the timeout key can be omitted.
     */
    public Boolean typing;

    /**
     * The length of time in milliseconds to mark this user as typing.
     */
    public Long timeout;
}
