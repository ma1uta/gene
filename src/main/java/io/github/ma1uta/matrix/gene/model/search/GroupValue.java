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

package io.github.ma1uta.matrix.gene.model.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Group value.
 */
public class GroupValue {

    /**
     * Token that can be used to get the next batch of results in the group, by passing as the next_batch parameter to the next call.
     * If this field is absent, there are no more results in this group.
     */
    @SerializedName("next_batch")
    public String nextBatch;

    /**
     * Key that can be used to order different groups.
     */
    public Long order;

    /**
     * Which results are in this group.
     */
    public List<String> results;
}
