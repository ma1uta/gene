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

package io.github.ma1uta.matrix.android.api;

import io.github.ma1uta.matrix.android.model.common.EmptyResponse;
import io.github.ma1uta.matrix.android.model.push.NotificationResponse;
import io.github.ma1uta.matrix.android.model.push.PushActions;
import io.github.ma1uta.matrix.android.model.push.PushEnable;
import io.github.ma1uta.matrix.android.model.push.PushRule;
import io.github.ma1uta.matrix.android.model.push.PushRulesResponse;
import io.github.ma1uta.matrix.android.model.push.PushUpdateRequest;
import io.github.ma1uta.matrix.android.model.push.PushersRequest;
import io.github.ma1uta.matrix.android.model.push.PushersResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This module adds support for push notifications. Homeservers send notifications of events to user-configured HTTP endpoints.
 * Users may also configure a number of rules that determine which events generate notifications. These are all stored and managed
 * by the user's homeserver. This allows user-specific push settings to be reused between client applications.
 */
public interface PushApi {
    /**
     * Kind of the push rules.
     */
    class Kind {

        protected Kind() {
        }

        /**
         * The highest priority rules are user-configured overrides.
         */
        public static final String OVERRIDE = "override";

        /**
         * These are identical to override rules, but have a lower priority than content, room and sender rules.
         */
        public static final String UNDERRIDE = "underride";

        /**
         * These rules configure notification behaviour for messages from a specific Matrix user ID. The rule_id of Sender rules is
         * always the Matrix user ID of the user whose messages they'd apply to.
         */
        public static final String SENDER = "sender";

        /**
         * These rules change the behaviour of all messages for a given room. The rule_id of a room rule is always the ID of the room
         * that it affects.
         */
        public static final String ROOM = "room";

        /**
         * These configure behaviour for (unencrypted) messages that match certain patterns. Content rules take one parameter: pattern,
         * that gives the glob pattern to match against. This is treated in the same way as pattern for event_match.
         */
        public static final String CONTENT = "content";
    }

    /**
     * All rules have an associated list of actions. An action affects if and how a notification is delivered for a matching event.
     */
    class Actions {

        protected Actions() {
        }

        /**
         * This causes each matching event to generate a notification.
         */
        public static final String NOTIFY = "notify";

        /**
         * This prevents each matching event from generating a notification.
         */
        public static final String DONT_NOTIFY = "dont_notify";

        /**
         * This enables notifications for matching events but activates homeserver specific behaviour to intelligently coalesce
         * multiple events into a single notification. Not all homeservers may support this. Those that do not support it should
         * treat it as the notify action.
         */
        public static final String COALESCE = "coalesce";

        /**
         * Sets an entry in the tweaks dictionary key that is sent in the notification request to the Push Gateway. This takes the form
         * of a dictionary with a set_tweak key whose value is the name of the tweak to set. It may also have a value key which is the
         * value to which it should be set.
         */
        public static final String SET_TWEAK = "set_tweak";
    }

    /**
     * The set_tweak action is used to add an entry to the 'tweaks' dictionary that is sent in the notification request to the Push Gateway.
     */
    class Tweak {

        protected Tweak() {
        }

        /**
         * A string representing the sound to be played when this notification arrives. A value of default means to play a default sound.
         * A device may choose to alert the user by some other means if appropriate, eg. vibration.
         */
        public static final String SOUND = "sound";

        /**
         * A boolean representing whether or not this message should be highlighted in the UI. This will normally take the form of
         * presenting the message in a different colour and/or style. The UI might also be adjusted to draw particular attention to
         * the room in which the event occurred. If a highlight tweak is given with no value, its value is defined to be true.
         * If no highlight tweak is given at all then the value of highlight is defined to be false.
         */
        public static final String HIGHLIGHT = "highlight";
    }

    /**
     * Homeservers can specify "server-default rules" which operate at a lower priority than "user-defined rules".
     * The rule_id for all server-default rules MUST start with a dot (".") to identify them as "server-default".
     */
    class Rule {

        protected Rule() {
        }

        /**
         * Matches all events, this can be enabled to turn off all push notifications other than those generated by override rules
         * set by the user. By default this rule is disabled.
         */
        public static final String MASTER = ".m.rule.master";

        /**
         * Matches messages with a msgtype of notice. This should be an override rule so that it takes priority over
         * content/sender/room rules.
         */
        public static final String SUPPRESS_NOTICES = ".m.rule.suppress_notices";

        /**
         * Matches any invites to a new room for this user.
         */
        public static final String INVITE_FOR_ME = ".m.rule.invite_for_me";

        /**
         * Matches any m.room.member_event.
         */
        public static final String MEMBER_EVENT = ".m.rule.member_event";

        /**
         * Matches any message whose content is unencrypted and contains the user's current display name in the room in which it was sent.
         */
        public static final String CONTAINS_DISPLAY_NAME = ".m.rule.contains_display_name";

        /**
         * Matches any message whose content is unencrypted and contains the local part of the user's Matrix ID, separated by word
         * boundaries.
         */
        public static final String CONTAINS_USER_NAME = ".m.rule.contains_user_name";

        /**
         * Matches any incoming VOIP call.
         */
        public static final String CALL = ".m.rule.call";

        /**
         * Matches any message sent in a room with exactly two members.
         */
        public static final String ROOM_ONE_TO_ONE = ".m.rule.room_one_to_one";

        /**
         * Matches all chat messages.
         */
        public static final String MESSAGE = ".m.rule.message";
    }

    /**
     * Override, Underride and Default Rules MAY have a list of 'conditions'. All conditions must hold true for an event in order
     * to apply the action for the event. A rule with no conditions always matches. Room, Sender, User and Content rules do not have
     * conditions in the same way, but instead have predefined conditions. These conditions can be configured using the parameters
     * outlined below. In the cases of room and sender rules, the rule_id of the rule determines its behaviour.
     */
    class Condition {

        protected Condition() {
        }

        /**
         * This is a glob pattern match on a field of the event. Parameters:
         * <ul>
         * <li>key: The dot-separated field of the event to match, e.g. content.body</li>
         * <li>pattern: The glob-style pattern to match against. Patterns with no special glob characters should be treated as having
         * asterisks prepended and appended when testing the condition.</li>
         * </ul>
         */
        public static final String EVENT_MATCH = "event_match";

        /**
         * This matches unencrypted messages where content.body contains the owner's display name in that room. This is a separate
         * rule because display names may change and as such it would be hard to maintain a rule that matched the user's display name.
         * This condition has no parameters.
         */
        public static final String CONTAINS_DISPLAY_NAME = "contains_display_name";

        /**
         * This matches the current number of members in the room. Parameters:
         * <ul>
         * <li>is: A decimal integer optionally prefixed by one of, ==, <, >, >= or <=. A prefix of < matches rooms where the member
         * count is strictly less than the given number and so forth. If no prefix is present, this parameter defaults to ==.</li>
         * </ul>
         */
        public static final String ROOM_MEMBER_COUNT = "room_member_count";
    }

    /**
     * Gets all currently active pushers for the authenticated user.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @return Status code 200: The pushers for this user.
     */
    @GET("/_matrix/client/r0/pushers")
    PushersResponse showPushers();

    /**
     * This endpoint allows the creation, modification and deletion of pushers for this user ID. The behaviour of this endpoint
     * varies depending on the values in the JSON body.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param pushersRequest  JSON body request.
     * @return Status code 200: The pusher was set.
     *     Status code 400: One or more of the pusher values were invalid.
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/client/r0/pushers/set")
    EmptyResponse setPushers(@Body PushersRequest pushersRequest);

    /**
     * This API is used to paginate through the list of events that the user has been, or would have been notified about.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param from            Pagination token given to retrieve the next set of events.
     * @param only            Allows basic filtering of events returned. Supply highlight to return only events where the notification had
     *                        the highlight tweak set.
     * @param limit           Limit on the number of events to return in this request.
     * @return Status code 200: A batch of events is being returned.
     */
    @GET("/_matrix/client/r0/notifications")
    NotificationResponse notifications(@Query("from") String from, @Query("only") String only, @Query("limit") Long limit);

    /**
     * Retrieve all push rulesets for this user. Clients can "drill-down" on the rulesets by suffixing a scope to this path e.g.
     * /pushrules/global/. This will return a subset of this data under the specified key e.g. the global key.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @return Status code 200: All the push rulesets for this user.
     */
    @GET("/_matrix/client/r0/pushrules")
    PushRulesResponse pushRules();

    /**
     * Retrieve a single specified push rule.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param scope           Required. Global to specify global rules.
     * @param kind            Required. The kind of rule. One of: ["override", "underride", "sender", "room", "content"].
     * @param ruleId          Required. The identifier for the rule.
     * @return Status code 200: The specific push rule. This will also include keys specific to the rule itself such as the rule's
     *     actions and conditions if set.
     */
    @GET("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}")
    PushRule pushRule(@Path("scope") String scope, @Path("kind") String kind, @Path("ruleId") String ruleId);

    /**
     * This endpoint removes the push rule defined in the path.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param scope           Required. Global to specify global rules.
     * @param kind            Required. The kind of rule. One of: ["override", "underride", "sender", "room", "content"].
     * @param ruleId          Required. The identifier for the rule.
     * @return Status code 200: The push rule was deleted.
     */
    @DELETE("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}")
    EmptyResponse deleteRule(@Path("scope") String scope, @Path("kind") String kind, @Path("ruleId") String ruleId);

    /**
     * This endpoint allows the creation, modification and deletion of pushers for this user ID. The behaviour of this endpoint
     * varies depending on the values in the JSON body.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param scope             Required. Global to specify global rules.
     * @param kind              Required. The kind of rule. One of: ["override", "underride", "sender", "room", "content"].
     * @param ruleId            Required. The identifier for the rule.
     * @param before            Use 'before' with a rule_id as its value to make the new rule the next-most important rule with respect
     *                          to the given user defined rule. It is not possible to add a rule relative to a predefined server rule.
     * @param after             This makes the new rule the next-less important rule relative to the given user defined rule. It is not
     *                          possible to add a rule relative to a predefined server rule.
     * @param pushUpdateRequest JSON body request.
     * @return Status code 200: The pusher was set.
     *     Status code 400: There was a problem configuring this push rule.
     *     Status code 429: This request was rate-limited.
     */
    @PUT("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}")
    EmptyResponse updateRule(@Path("scope") String scope, @Path("kind") String kind, @Path("ruleId") String ruleId,
                             @Query("before") String before, @Query("after") String after, @Body PushUpdateRequest pushUpdateRequest);

    /**
     * This endpoint gets whether the specified push rule is enabled.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param scope           Required. Either global or device/&lt;profile_tag&gt; to specify global rules or device rules for the given
     *                        profile_tag.
     * @param kind            Required. The kind of rule. One of: ["override", "underride", "sender", "room", "content"].
     * @param ruleId          Required. The identifier for the rule.
     * @return Status code 200: Whether the push rule is enabled.
     */
    @GET("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}/enabled")
    PushEnable getEnabled(@Path("scope") String scope, @Path("kind") String kind, @Path("ruleId") String ruleId);

    /**
     * This endpoint allows clients to enable or disable the specified push rule.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param scope           Required. Global to specify global rules.
     * @param kind            Required. The kind of rule. One of: ["override", "underride", "sender", "room", "content"].
     * @param ruleId          Required. The identifier for the rule.
     * @param pushEnable      JSON body request.
     * @return Status code 200: The push rule was enabled or disabled.
     */
    @PUT("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}/enabled")
    EmptyResponse setEnabled(@Path("scope") String scope, @Path("kind") String kind, @Path("ruleId") String ruleId,
                             @Body PushEnable pushEnable);

    /**
     * This endpoint get the actions for the specified push rule.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param scope           Required. Either global or device/&lt;profile_tag&gt; to specify global rules or device rules for the given
     *                        profile_tag.
     * @param kind            Required. The kind of rule One of: ["override", "underride", "sender", "room", "content"].
     * @param ruleId          Required. The identifier for the rule.
     * @return Status code 200: The actions for this push rule.
     */
    @GET("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}/actions")
    PushActions getActions(@Path("scope") String scope, @Path("kind") String kind, @Path("ruleId") String ruleId);

    /**
     * This endpoint allows clients to change the actions of a push rule. This can be used to change the actions of builtin rules.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param scope           Required. Global to specify global rules.
     * @param kind            Required. The kind of rule. One of: ["override", "underride", "sender", "room", "content"].
     * @param ruleId          Required. The identifier for the rule.
     * @param pushActions     JSON body request.
     * @return Status code 200: The actions for the push rule were set.
     */
    @PUT("/_matrix/client/r0/pushrules/{scope}/{kind}/{ruleId}/actions")
    EmptyResponse setActions(@Path("scope") String scope, @Path("kind") String kind, @Path("ruleId") String ruleId,
                             @Body PushActions pushActions);
}
