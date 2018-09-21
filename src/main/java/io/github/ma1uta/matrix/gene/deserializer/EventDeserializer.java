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

package io.github.ma1uta.matrix.gene.deserializer;

import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.CALL_ANSWER;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.CALL_CANDIDATES;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.CALL_HANGUP;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.CALL_INVITE;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.DIRECT;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.FULLY_READ;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.IGNORED_USER_LIST;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.PRESENCE;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.RECEIPT;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_ALIASES;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_AVATAR;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_CANONICAL_ALIAS;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_CREATE;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_GUEST_ACCESS;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_HISTORY_VISIBILITY;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_JOIN_RULES;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_MEMBER;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_MESSAGE;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_NAME;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_PINNED_EVENTS;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_POWER_LEVELS;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_REDACTION;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_THIRD_PARTY_INVITE;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.ROOM_TOPIC;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.STICKER;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.TAG;
import static io.github.ma1uta.matrix.gene.model.common.Event.EventType.TYPING;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.AUDIO;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.EMOTE;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.FILE;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.IMAGE;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.LOCATION;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.NOTICE;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.TEXT;
import static io.github.ma1uta.matrix.gene.model.common.Event.MessageType.VIDEO;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.ma1uta.matrix.gene.model.common.Event;
import io.github.ma1uta.matrix.gene.model.common.EventContent;
import io.github.ma1uta.matrix.gene.model.common.StrippedState;
import io.github.ma1uta.matrix.gene.model.common.Unsigned;
import io.github.ma1uta.matrix.gene.model.common.events.CallAnswer;
import io.github.ma1uta.matrix.gene.model.common.events.CallCandidates;
import io.github.ma1uta.matrix.gene.model.common.events.CallHangup;
import io.github.ma1uta.matrix.gene.model.common.events.CallInvite;
import io.github.ma1uta.matrix.gene.model.common.events.Direct;
import io.github.ma1uta.matrix.gene.model.common.events.FullyRead;
import io.github.ma1uta.matrix.gene.model.common.events.IgnoredUserList;
import io.github.ma1uta.matrix.gene.model.common.events.RawEvent;
import io.github.ma1uta.matrix.gene.model.common.events.RawMessage;
import io.github.ma1uta.matrix.gene.model.common.events.Receipt;
import io.github.ma1uta.matrix.gene.model.common.events.RoomAliases;
import io.github.ma1uta.matrix.gene.model.common.events.RoomAvatar;
import io.github.ma1uta.matrix.gene.model.common.events.RoomCanonicalAlias;
import io.github.ma1uta.matrix.gene.model.common.events.RoomCreate;
import io.github.ma1uta.matrix.gene.model.common.events.RoomGuestAccess;
import io.github.ma1uta.matrix.gene.model.common.events.RoomHistoryVisibility;
import io.github.ma1uta.matrix.gene.model.common.events.RoomJoinRules;
import io.github.ma1uta.matrix.gene.model.common.events.RoomMember;
import io.github.ma1uta.matrix.gene.model.common.events.RoomMessage;
import io.github.ma1uta.matrix.gene.model.common.events.RoomName;
import io.github.ma1uta.matrix.gene.model.common.events.RoomPinned;
import io.github.ma1uta.matrix.gene.model.common.events.RoomPowerLevels;
import io.github.ma1uta.matrix.gene.model.common.events.RoomRedaction;
import io.github.ma1uta.matrix.gene.model.common.events.RoomThirdPartyInvite;
import io.github.ma1uta.matrix.gene.model.common.events.RoomTopic;
import io.github.ma1uta.matrix.gene.model.common.events.Sticker;
import io.github.ma1uta.matrix.gene.model.common.events.Tag;
import io.github.ma1uta.matrix.gene.model.common.events.Typing;
import io.github.ma1uta.matrix.gene.model.common.events.messages.Audio;
import io.github.ma1uta.matrix.gene.model.common.events.messages.Emote;
import io.github.ma1uta.matrix.gene.model.common.events.messages.File;
import io.github.ma1uta.matrix.gene.model.common.events.messages.Image;
import io.github.ma1uta.matrix.gene.model.common.events.messages.Location;
import io.github.ma1uta.matrix.gene.model.common.events.messages.Notice;
import io.github.ma1uta.matrix.gene.model.common.events.messages.Text;
import io.github.ma1uta.matrix.gene.model.common.events.messages.Video;
import io.github.ma1uta.matrix.gene.model.sync.InviteState;
import io.github.ma1uta.matrix.gene.model.sync.Presence;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Event deserializer.
 */
public class EventDeserializer implements JsonDeserializer<Event> {
    @Override
    public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Event event = new Event();

        if (!(json instanceof JsonObject)) {
            throw new JsonParseException("");
        }

        JsonObject object = (JsonObject) json;
        String type = asString(object.get("type"));
        if (isBlank(type)) {
            throw new JsonParseException("Missing required field: type");
        }

        event.type = type;
        event.content = content(object.get("content"), type, context);

        String eventId = asString(object.get("event_id"));
        if (isBlank(eventId)) {
            throw new JsonParseException("Missing required field: event_id");
        }
        event.eventId = eventId;

        String roomId = asString(object.get("room_id"));
        if (isBlank(roomId)) {
            throw new JsonParseException("Missing required field: room_id");
        }
        event.roomId = roomId;


        String sender = asString(object.get("sender"));
        if (isBlank(sender)) {
            throw new JsonParseException("Missing required field: sender");
        }
        event.sender = sender;


        Long originServerTs = asLong(object.get("origin_server_ts"));
        if (originServerTs == null) {
            throw new JsonParseException("Missing required field: origin_server_ts");
        }
        event.originServerTs = originServerTs;

        event.unsigned = context.deserialize(object.get("unsigned"), Unsigned.class);

        event.prevContent = content(object.get("prev_content"), type, context);

        event.stateKey = asString(object.get("state_key"));

        JsonArray inviteRoomState = object.getAsJsonArray("invite_room_state");
        if (inviteRoomState != null && inviteRoomState.isJsonArray()) {
            ArrayList<StrippedState> strippedStates = new ArrayList<>(inviteRoomState.size());
            for (JsonElement inviteItem : inviteRoomState) {
                strippedStates.add(context.deserialize(inviteItem, InviteState.class));
            }
            event.inviteRoomState = strippedStates;
        }

        return event;
    }

    protected String asString(JsonElement jsonElement) {
        return jsonElement != null && !jsonElement.isJsonNull() ? jsonElement.getAsString() : null;
    }

    protected Long asLong(JsonElement jsonElement) {
        return jsonElement != null && !jsonElement.isJsonNull() ? jsonElement.getAsLong() : null;
    }

    protected EventContent content(JsonElement jsonElement, String type, JsonDeserializationContext context) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }

        switch (type) {
            case CALL_ANSWER:
                return context.deserialize(jsonElement, CallAnswer.class);
            case CALL_CANDIDATES:
                return context.deserialize(jsonElement, CallCandidates.class);
            case CALL_HANGUP:
                return context.deserialize(jsonElement, CallHangup.class);
            case CALL_INVITE:
                return context.deserialize(jsonElement, CallInvite.class);
            case DIRECT:
                return context.deserialize(jsonElement, Direct.class);
            case IGNORED_USER_LIST:
                return context.deserialize(jsonElement, IgnoredUserList.class);
            case PRESENCE:
                return context.deserialize(jsonElement, Presence.class);
            case RECEIPT:
                return context.deserialize(jsonElement, Receipt.class);
            case ROOM_ALIASES:
                return context.deserialize(jsonElement, RoomAliases.class);
            case ROOM_AVATAR:
                return context.deserialize(jsonElement, RoomAvatar.class);
            case ROOM_CANONICAL_ALIAS:
                return context.deserialize(jsonElement, RoomCanonicalAlias.class);
            case ROOM_CREATE:
                return context.deserialize(jsonElement, RoomCreate.class);
            case ROOM_GUEST_ACCESS:
                return context.deserialize(jsonElement, RoomGuestAccess.class);
            case ROOM_HISTORY_VISIBILITY:
                return context.deserialize(jsonElement, RoomHistoryVisibility.class);
            case ROOM_JOIN_RULES:
                return context.deserialize(jsonElement, RoomJoinRules.class);
            case ROOM_MEMBER:
                return context.deserialize(jsonElement, RoomMember.class);
            case ROOM_MESSAGE:
                return roomMessage(jsonElement, context);
            case ROOM_NAME:
                return context.deserialize(jsonElement, RoomName.class);
            case ROOM_PINNED_EVENTS:
                return context.deserialize(jsonElement, RoomPinned.class);
            case ROOM_POWER_LEVELS:
                return context.deserialize(jsonElement, RoomPowerLevels.class);
            case ROOM_REDACTION:
                return context.deserialize(jsonElement, RoomRedaction.class);
            case ROOM_THIRD_PARTY_INVITE:
                return context.deserialize(jsonElement, RoomThirdPartyInvite.class);
            case ROOM_TOPIC:
                return context.deserialize(jsonElement, RoomTopic.class);
            case STICKER:
                return context.deserialize(jsonElement, Sticker.class);
            case TAG:
                return context.deserialize(jsonElement, Tag.class);
            case TYPING:
                return context.deserialize(jsonElement, Typing.class);
            case FULLY_READ:
                return context.deserialize(jsonElement, FullyRead.class);
            default:
                return new RawEvent(jsonElement);
        }
    }

    protected RoomMessage roomMessage(JsonElement jsonElement, JsonDeserializationContext context) {
        JsonObject object = (JsonObject) jsonElement;

        String msgtype = asString(object.get("msgtype"));
        if (isBlank(msgtype)) {
            throw new JsonParseException("Missing required msgtype");
        }

        switch (msgtype) {
            case AUDIO:
                return context.deserialize(jsonElement, Audio.class);
            case EMOTE:
                return context.deserialize(jsonElement, Emote.class);
            case FILE:
                return context.deserialize(jsonElement, File.class);
            case IMAGE:
                return context.deserialize(jsonElement, Image.class);
            case LOCATION:
                return context.deserialize(jsonElement, Location.class);
            case NOTICE:
                return context.deserialize(jsonElement, Notice.class);
            case TEXT:
                return context.deserialize(jsonElement, Text.class);
            case VIDEO:
                return context.deserialize(jsonElement, Video.class);
            default:
                return new RawMessage(object);
        }
    }

    protected boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
