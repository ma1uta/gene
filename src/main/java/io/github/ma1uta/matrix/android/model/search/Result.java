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

import io.github.ma1uta.matrix.android.model.common.Event;

/**
 * Search result.
 */
public class Result {

    /**
     * A number that describes how closely this result matches the search. Higher is closer.
     */
    public Long rank;

    /**
     * The event that matched.
     */
    public Event result;

    /**
     * Context for result, if requested.
     */
    public EventContextResponse context;
}