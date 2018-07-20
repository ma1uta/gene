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

package io.github.ma1uta.matrix.gene.model.push;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Push rule.
 */
public class PushRule {

    /**
     * Required. The actions to perform when this rule is matched.
     */
    public List<Object> actions;

    /**
     * Required. Whether this is a default rule, or has been set explicitly.
     */
    @SerializedName("default")
    public Boolean defaultRule;

    /**
     * Required. Whether the push rule is enabled or not.
     */
    public Boolean enabled;

    /**
     * Required. The ID of this rule.
     */
    @SerializedName("rule_id")
    public String ruleId;

    /**
     * The conditions that must hold true for an event in order for a rule to be applied to an event. A rule with no
     * conditions always matches. Only applicable to underride and override rules.
     */
    public List<PushCondition> conditions;

    /**
     * The glob-style pattern to match against. Only applicable to content rules.
     */
    public String pattern;
}
