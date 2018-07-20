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
import io.github.ma1uta.matrix.android.model.device.Device;
import io.github.ma1uta.matrix.android.model.device.DeviceDeleteRequest;
import io.github.ma1uta.matrix.android.model.device.DeviceUpdateRequest;
import io.github.ma1uta.matrix.android.model.device.DevicesDeleteRequest;
import io.github.ma1uta.matrix.android.model.device.DevicesResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Clients that implement this module should offer the user a list of registered devices, as well as the means to update their
 * display names. Clients should also allow users to delete disused devices.
 */
public interface DeviceApi {

    /**
     * Gets information about all devices for the current user.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @return A list of all registered devices for this user.
     */
    @GET("/_matrix/client/r0/devices")
    @Headers("Content-type: application/json")
    DevicesResponse devices();

    /**
     * Gets information on a single device, by device id.
     *
     * @param deviceId        Required. The device to retrieve.
     * @return Status code 200: Device information.
     *     Status code 404: The current user has no device with the given ID.
     */
    @GET("/_matrix/client/r0/devices/{deviceId}")
    @Headers("Content-type: application/json")
    Device device(@Path("deviceId") String deviceId);

    /**
     * Updates the metadata on the given device.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param deviceId            Required. The device to update.
     * @param deviceUpdateRequest The new display name for this device. If not given, the display name is unchanged.
     * @return Status code 200: The device was successfully updated.
     *     Status code 404: The current user has no device with the given ID.
     */
    @PUT("/_matrix/client/r0/devices/{deviceId}")
    @Headers("Content-type: application/json")
    EmptyResponse updateDevice(@Path("deviceId") String deviceId, @Body DeviceUpdateRequest deviceUpdateRequest);

    /**
     * This API endpoint uses the User-Interactive Authentication API.
     * <p/>
     * Deletes the given device, and invalidates any access token associated with it.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param deviceId            Required. The device to delete.
     * @param deviceDeleteRequest Additional authentication information for the user-interactive authentication API.
     * @return Status code 200: The device was successfully removed, or had been removed previously.
     *     Status code 401: The homeserver requires additional authentication information.
     */
    @DELETE("/_matrix/client/r0/devices/{deviceId}")
    @Headers("Content-type: application/json")
    EmptyResponse deleteDevice(@Path("deviceId") String deviceId, @Body DeviceDeleteRequest deviceDeleteRequest);

    /**
     * This API endpoint uses the User-Interactive Authentication API.
     * <p/>
     * Deletes the given devices, and invalidates any access token associated with them.
     * <p/>
     * <b>Requires auth</b>: Yes.
     *
     * @param devicesDeleteRequest JSON body request.
     * @return Status code 200: The devices were successfully removed, or had been removed previously.
     *     Status code 401: The homeserver requires additional authentication information.
     */
    @POST("/_matrix/client/r0/delete_devices")
    @Headers("Content-type: application/json")
    EmptyResponse deleteDevices(@Body DevicesDeleteRequest devicesDeleteRequest);
}
