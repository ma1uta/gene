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

package io.github.ma1uta.matrix.gene.model.common.events.messages;

import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.model.common.events.RoomMessage;

/**
 * This message is the most basic message and is used to represent text.
 */
public abstract class FormattedBody extends RoomMessage {

    /**
     * Format.
     */
    public static class Format {

        protected Format() {

        }

        /**
         * "org.matrix.custom.html".
         */
        public static final String ORG_MATRIX_CUSTOM_HTML = "org.matrix.custom.html";
    }

    /**
     * The format used in the ``formatted_body``. Currently only ``org.matrix.custom.html`` is supported.
     */
    public String format;

    /**
     * The formatted version of the ``body``. This is required if ``format`` is specified.
     */
    @SerializedName("formatted_body")
    public String formattedBody = Format.ORG_MATRIX_CUSTOM_HTML;
}
