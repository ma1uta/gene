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

package io.github.ma1uta.matrix.gene.model.common;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.github.ma1uta.matrix.gene.deserializer.EventDeserializer;

import java.util.List;

/**
 * Event.
 */
@JsonAdapter(EventDeserializer.class)
public class Event {
    /**
     * Event types.
     */
    public static class EventType {

        protected EventType() {
        }

        /**
         * State Event: state_key - The homeserver domain which owns these room aliases.
         * <br>
         * This event is sent by a homeserver directly to inform of changes to the list of aliases it knows about for that room.
         * The state_key for this event is set to the homeserver which owns the room alias. The entire set of known aliases for
         * the room is the union of all the m.room.aliases events, one for each homeserver. Clients should check the validity of
         * any room alias given in this list before presenting it to the user as trusted fact. The lists given by this event should
         * be considered simply as advice on which aliases might exist, for which the client can perform the lookup to confirm whether
         * it receives the correct room ID.
         */
        public static final String ROOM_ALIASES = "m.room.aliases";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * This event is used to inform the room about which alias should be considered the canonical one. This could be for
         * display purposes or as suggestion to users which alias to use to advertise the room.
         * <br>
         * A room with an m.room.canonical_alias event with an absent, null, or empty alias field should be treated the same as
         * a room with no m.room.canonical_alias event.
         */
        public static final String ROOM_CANONICAL_ALIAS = "m.room.canonical_alias";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * This is the first event in a room and cannot be changed. It acts as the root of all other events.
         */
        public static final String ROOM_CREATE = "m.room.create";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * A room may be public meaning anyone can join the room without any prior action. Alternatively, it can be invite
         * meaning that a user who wishes to join the room must first receive an invite to the room from someone already inside
         * of the room. Currently, knock and private are reserved keywords which are not implemented.
         */
        public static final String ROOM_JOIN_RULES = "m.room.join_rules";

        /**
         * State Event: state_key - The user_id this membership event relates to.
         * <br>
         * Adjusts the membership state for a user in a room. It is preferable to use the membership APIs
         * (/rooms/&lt;room id&gt;/invite etc)
         * when performing membership actions rather than adjusting the state directly as there are a restricted set of valid
         * transformations. For example, user A cannot force user B to join a room, and trying to force this state change directly
         * will fail.
         * <br>
         * The following membership states are specified:
         * <ul>
         * <li>invite - The user has been invited to join a room, but has not yet joined it. They may not participate in the room
         * until they join.</li>
         * <li>join - The user has joined the room (possibly after accepting an invite), and may participate in it.</li>
         * <li>leave - The user was once joined to the room, but has since left (possibly by choice, or possibly by being kicked).</li>
         * <li>ban - The user has been banned from the room, and is no longer allowed to join it until they are un-banned from
         * the room (by having their membership state set to a value other than ban).</li>
         * <li>knock - This is a reserved word, which currently has no meaning.</li>
         * </ul>
         * The third_party_invite property will be set if this invite is an invite event and is the successor
         * of an m.room.third_party_invite event, and absent otherwise.
         * <br>
         * This event may also include an invite_room_state key outside the content key. If present, this contains an array of
         * StrippedState Events. These events provide information on a subset of state events such as the room name.
         */
        public static final String ROOM_MEMBER = "m.room.member";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * This event specifies the minimum level a user must have in order to perform a certain action. It also specifies the
         * levels of each user in the room.
         * <br>
         * If a user_id is in the users list, then that user_id has the associated power level. Otherwise they have the default
         * level users_default. If users_default is not supplied, it is assumed to be 0. If the room contains no m.room.power_levels
         * event, the room's creator has a power level of 100, and all other users have a power level of 0.
         * <br>
         * The level required to send a certain event is governed by events, state_default and events_default. If an event type is
         * specified in events, then the user must have at least the level specified in order to send that event. If the event type
         * is not supplied, it defaults to events_default for Message Events and state_default for State Events.
         * <br>
         * If there is no state_default in the m.room.power_levels event, the state_default is 50. If there is no events_default
         * in the m.room.power_levels event, the events_default is 0. If the room contains no m.room.power_levels event, both the
         * state_default and events_default are 0.
         * <br>
         * The power level required to invite a user to the room, kick a user from the room, ban a user from the room, or redact
         * an event, is defined by invite, kick, ban, and redact, respectively. Each of these levels defaults to 50 if they are
         * not specified in the m.room.power_levels event, or if the room contains no m.room.power_levels event.
         */
        public static final String ROOM_POWER_LEVELS = "m.room.power_levels";

        /**
         * Message Event
         * <br>
         * Events can be redacted by either room or server admins. Redacting an event means that all keys not required by the
         * protocol are stripped off, allowing admins to remove offensive or illegal content that may have been attached to any
         * event. This cannot be undone, allowing server owners to physically delete the offending data. There is also a concept
         * of a moderator hiding a message event, which can be undone, but cannot be applied to state events. The event that has
         * been redacted is specified in the redacts event level key.
         */
        public static final String ROOM_REDACTION = "m.room.redaction";

        /**
         * This event is used when sending messages in a room. Messages are not limited to be text. The msgtype key outlines
         * the type of message, e.g. text, audio, image, video, etc. The body key is text and MUST be used with every kind of
         * msgtype as a fallback mechanism for when a client cannot render a message. This allows clients to display something
         * even if it is just plain text. For more information on msgtypes, see m.room.message msgtypes.
         */
        public static final String ROOM_MESSAGE = "m.room.message";

        /**
         * NB: Usage of this event is discouraged in favour of the receipts module. Most clients will not recognise this event.
         * Feedback events are events sent to acknowledge a message in some way. There are two supported acknowledgements: delivered
         * (sent when the event has been received) and read (sent when the event has been observed by the end-user).
         * The target_event_id should reference the m.room.message event being acknowledged.
         */
        @Deprecated
        public static final String ROOM_MESSAGE_FEEDBACK = "m.room.message.feedback";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * A room has an opaque room ID which is not human-friendly to read. A room alias is human-friendly, but not all
         * rooms have room aliases. The room name is a human-friendly string designed to be displayed to the end-user.
         * The room name is not unique, as multiple rooms can have the same room name set.
         * <br>
         * A room with an m.room.name event with an absent, null, or empty name field should be treated the same as a room
         * with no m.room.name event.
         * <br>
         * An event of this type is automatically created when creating a room using /createRoom with the name key.
         */
        public static final String ROOM_NAME = "m.room.name";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * A topic is a short message detailing what is currently being discussed in the room. It can also be used as a way to
         * display extra information about the room, which may not be suitable for the room name. The room topic can also be set
         * when creating a room using /createRoom with the topic key.
         */
        public static final String ROOM_TOPIC = "m.room.topic";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * A picture that is associated with the room. This can be displayed alongside the room information.
         */
        public static final String ROOM_AVATAR = "m.room.avatar";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * This event is used to "pin" particular events in a room for other participants to review later. The order of the
         * pinned events is guaranteed and based upon the order supplied in the event. Clients should be aware that the current
         * user may not be able to see some of the events pinned due to visibility settings in the room. Clients are responsible
         * for determining if a particular event in the pinned list is displayable, and have the option to not display it if it
         * cannot be pinned in the client.
         */
        public static final String ROOM_PINNED_EVENTS = "m.room.pinned_events";

        /**
         * Message Event.
         * <br>
         * This event is sent by the caller when they wish to establish a call.
         */
        public static final String CALL_INVITE = "m.call.invite";

        /**
         * Message Event.
         * <br>
         * This event is sent by callers after sending an invite and by the callee after answering. Its purpose is to give the other
         * party additional ICE candidates to try using to communicate.
         */
        public static final String CALL_CANDIDATES = "m.call.candidates";

        /**
         * Message Event.
         * <br>
         * This event is sent by the callee when they wish to answer the call.
         */
        public static final String CALL_ANSWER = "m.call.answer";

        /**
         * Message Event.
         * <br>
         * Sent by either party to signal their termination of the call. This can be sent either once the call has has been
         * established or before to abort the call.
         */
        public static final String CALL_HANGUP = "m.call.hangup";

        /**
         * Informs the client of the list of users currently typing.
         */
        public static final String TYPING = "m.typing";

        /**
         * Informs the client of new receipts.
         */
        public static final String RECEIPT = "m.receipt";

        /**
         * Informs the client of a user's presence state change.
         */
        public static final String PRESENCE = "m.presence";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * This event controls whether a user can see the events that happened in a room from before they joined.
         */
        public static final String ROOM_HISTORY_VISIBILITY = "m.room.history_visibility";

        /**
         * State Event: state_key - The token, of which a signature must be produced in order to join the room.
         * <br>
         * Acts as an m.room.member invite event, where there isn't a target user_id to invite. This event contains a token and
         * a public key whose private key must be used to sign the token. Any user who can present that signature may use this
         * invitation to join the target room.
         */
        public static final String ROOM_THIRD_PARTY_INVITE = "m.room.third_party_invite";

        /**
         * State Event: state_key - A zero-length string.
         * <br>
         * This event controls whether guest users are allowed to join rooms. If this event is absent, servers should act as if
         * it is present and has the guest_access value "forbidden".
         */
        public static final String ROOM_GUEST_ACCESS = "m.room.guest_access";

        /**
         * Informs the client of tags on a room.
         */
        public static final String TAG = "m.tag";

        /**
         * A map of which rooms are considered 'direct' rooms for specific users is kept in account_data in an event of type m.direct.
         * The content of this event is an object where the keys are the user IDs and values are lists of room ID strings of the
         * 'direct' rooms for that user ID.
         */
        public static final String DIRECT = "m.direct";

        /**
         * A map of users which are considered ignored is kept in account_data in an event type of m.ignored_user_list.
         */
        public static final String IGNORED_USER_LIST = "m.ignored_user_list";

        /**
         * Message Event
         * <br>
         * This message represents a single sticker image.
         */
        public static final String STICKER = "m.sticker";

        /**
         * The current location of the user's read marker in a room. This event appears in the user's room account data for the room
         * the marker is applicable for.
         */
        public static final String FULLY_READ = "m.fully_read";
    }

    /**
     * Message types.
     */
    public static class MessageType {

        protected MessageType() {
        }

        /**
         * This message is the most basic message and is used to represent text.
         */
        public static final String TEXT = "m.text";

        /**
         * This message is similar to m.text except that the sender is 'performing' the action contained in the body key,
         * similar to /me in IRC. This message should be prefixed by the name of the sender. This message could also be
         * represented in a different colour to distinguish it from regular m.text messages.
         */
        public static final String EMOTE = "m.emote";

        /**
         * The m.notice type is primarily intended for responses from automated clients. An m.notice message must be treated the
         * same way as a regular m.text message with two exceptions. Firstly, clients should present m.notice messages to users
         * in a distinct manner, and secondly, m.notice messages must never be automatically responded to. This helps to prevent
         * infinite-loop situations where two automated clients continuously exchange messages.
         */
        public static final String NOTICE = "m.notice";

        /**
         * This message represents a single image and an optional thumbnail.
         */
        public static final String IMAGE = "m.image";

        /**
         * This message represents a generic file.
         */
        public static final String FILE = "m.file";

        /**
         * This message represents a real-world location.
         */
        public static final String LOCATION = "m.location";

        /**
         * This message represents a single video clip.
         */
        public static final String VIDEO = "m.video";

        /**
         * This message represents a single audio clip.
         */
        public static final String AUDIO = "m.audio";
    }

    /**
     * Membership states.
     */
    public static class MembershipState {

        protected MembershipState() {
        }

        /**
         * The user has been invited to join a room, but has not yet joined it. They may not participate in the room
         * until they join.
         */
        public static final String INVITE = "invite";

        /**
         * The user has joined the room (possibly after accepting an invite), and may participate in it.
         */
        public static final String JOIN = "join";

        /**
         * The user was once joined to the room, but has since left (possibly by choice, or possibly by being kicked).
         */
        public static final String LEAVE = "leave";

        /**
         * The user has been banned from the room, and is no longer allowed to join it until they are un-banned from
         * the room (by having their membership state set to a value other than ban).
         */
        public static final String BAN = "ban";

        /**
         * This is a reserved word, which currently has no meaning.
         */
        public static final String KNOCK = "knock";
    }

    /**
     * History visibility.
     */
    public static class Visibility {

        protected Visibility() {
        }

        /**
         * World readable.
         */
        public static final String WORLD_READABLE = "world_readable";

        /**
         * Shared.
         */
        public static final String SHARED = "shared";

        /**
         * Invited.
         */
        public static final String INVITED = "invited";

        /**
         * Joined.
         */
        public static final String JOINED = "joined";
    }

    /**
     * Whether guests can join the room.
     */
    public static class GuestAccess {

        protected GuestAccess() {
        }

        /**
         * Can join.
         */
        public static final String CAN_JOIN = "can_join";

        /**
         * Forbidden.
         */
        public static final String FORBIDDEN = "forbidden";
    }

    // ---- Common properties ----
    /**
     * The fields in this object will vary depending on the type of event. When interacting with the REST API, this is the HTTP body.
     */
    public EventContent content;

    /**
     * Required. The type of event. This SHOULD be namespaced similar to Java package naming conventions e.g.
     * 'com.example.subdomain.event.type'
     */
    public String type;

    // ---- Room events ----
    /**
     * Required. The globally unique event identifier.
     */
    @SerializedName("event_id")
    public String eventId;

    /**
     * Required. The ID of the room associated with this event.
     */
    @SerializedName("room_id")
    public String roomId;

    /**
     * Required. Contains the fully-qualified ID of the user who sent this event.
     */
    public String sender;

    /**
     * Required. Timestamp in milliseconds on originating homeserver when this event was sent.
     */
    @SerializedName("origin_server_ts")
    public Long originServerTs;

    /**
     * Contains optional extra information about the event.
     */
    public Unsigned unsigned;

    // ---- State events ----
    /**
     * Optional. The previous content for this event. If there is no previous content, this key will be missing.
     */
    @SerializedName("prev_content")
    public EventContent prevContent;

    /**
     * Required. A unique key which defines the overwriting semantics for this piece of room state. This value is often a
     * zero-length string. The presence of this key makes this event a State Event. The key MUST NOT start with '_'.
     */
    @SerializedName("state_key")
    public String stateKey;

    /**
     * This contains an array of StrippedState Events. These events provide information on a subset of state events such as the room name.
     */
    @SerializedName("invite_room_state")
    public List<StrippedState> inviteRoomState;
}
