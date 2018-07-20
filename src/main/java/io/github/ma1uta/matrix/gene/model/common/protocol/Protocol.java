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

package io.github.ma1uta.matrix.gene.model.common.protocol;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Protocol metadata.
 */
public class Protocol {

    /**
     * Fields used to identify a third party user.
     */
    @SerializedName("user_fields")
    public List<String> userFields;

    /**
     * Fields used to identify a third party location.
     */
    @SerializedName("location_fields")
    public List<String> locationFields;

    /**
     * An icon representing the third party protocol.
     */
    public String icon;

    /**
     * All location or user fields should have an entry here.
     */
    @SerializedName("field_types")
    public Map<String, FieldMetadata> fieldTypes;

    /**
     * A list of objects representing independent instances of configuration.
     * For instance multiple networks on IRC if multiple are bridged by the same bridge.
     */
    public List<Instance> instances;
}
