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

package io.github.ma1uta.matrix.gene.api;

import io.github.ma1uta.matrix.gene.model.account.AvailableResponse;
import io.github.ma1uta.matrix.gene.model.account.DeactivateRequest;
import io.github.ma1uta.matrix.gene.model.account.PasswordRequest;
import io.github.ma1uta.matrix.gene.model.account.RegisterRequest;
import io.github.ma1uta.matrix.gene.model.account.RequestToken;
import io.github.ma1uta.matrix.gene.model.account.ThreePidRequest;
import io.github.ma1uta.matrix.gene.model.account.ThreePidResponse;
import io.github.ma1uta.matrix.gene.model.account.WhoamiResponse;
import io.github.ma1uta.matrix.gene.model.auth.LoginResponse;
import io.github.ma1uta.matrix.gene.model.common.EmptyResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Account registration and management.
 */
public interface AccountApi {
    /**
     * The kind of account to register.
     */
    class RegisterType {

        protected RegisterType() {
        }

        /**
         * Guest.
         */
        public static final String GUEST = "guest";

        /**
         * User. Default.
         */
        public static final String USER = "user";
    }

    /**
     * Register for an account on this homeserver.
     *
     * @param kind            The kind of account to register. Defaults to user. One of: ["guest", "user"].
     * @param registerRequest JSON body parameters.
     * @return <p>Status code 200: The account has been registered.</p>
     * <p>Status code 400: Part of the request was invalid. This may include one of the following error codes:</p>
     * <ul>
     * <li>M_USER_IN_USE : The desired user ID is already taken.</li>
     * <li>M_INVALID_USERNAME : The desired user ID is not a valid user name.</li>
     * <li>M_EXCLUSIVE : The desired user ID is in the exclusive namespace claimed by an application service.</li>
     * </ul>
     * <p>
     * These errors may be returned at any stage of the registration process, including after authentication
     * if the requested user ID was registered whilst the client was performing authentication.
     * </p>
     * <p>
     * Homeservers MUST perform the relevant checks and return these codes before performing User-Interactive Authentication,
     * although they may also return them after authentication is completed if, for example,
     * the requested user ID was registered whilst the client was performing authentication.
     * </p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST("/_matrix/client/r0/register")
    @Headers("Content-type: application/json")
    LoginResponse register(@Query("kind") String kind, @Body RegisterRequest registerRequest);

    /**
     * Proxies the identity server API validate/email/requestToken, but first checks that the given email address is not already
     * associated with an account on this Home Server. Note that, for consistency, this API takes JSON objects, though the
     * Identity Server API takes x-www-form-urlencoded parameters. See the Identity Server API for further information.
     *
     * @param requestToken request.
     * @return <p>Status code 200: An email has been sent to the specified address. Note that this may be an email containing the
     * validation token or it may be informing the user of an error.</p>
     * <p>Status code 400: Part of the request was invalid. This may include one of the following error codes:</p>
     * <ul>
     * <li>M_THREEPID_IN_USE : The email address is already registered to an account on this server.
     * However, if the home server has the ability to send email, it is recommended that the server instead send an email to
     * the user with instructions on how to reset their password. This prevents malicious parties from being able to determine
     * if a given email address has an account on the Home Server in question.</li>
     * <li>M_SERVER_NOT_TRUSTED : The id_server parameter refers to an ID server that is not trusted by this Home Server.</li>
     * </ul>
     */
    @POST("/_matrix/client/r0/register/email/requestToken")
    @Headers("Content-type: application/json")
    EmptyResponse requestToken(@Body RequestToken requestToken);

    /**
     * Changes the password for an account on this homeserver.
     * <br>
     * This API endpoint uses the User-Interactive Authentication API.
     * <br>
     * An access token should be submitted to this endpoint if the client has an active session.
     * <br>
     * The homeserver may change the flows available depending on whether a valid access token is provided.
     *
     * @param passwordRequest password.
     * @return <p>Status code 200: The password has been changed.</p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST("/_matrix/client/r0/account/password")
    @Headers("Content-type: application/json")
    EmptyResponse password(@Body PasswordRequest passwordRequest);

    /**
     * Proxies the identity server API validate/email/requestToken, but first checks that the given email address is associated
     * with an account on this Home Server. This API should be used to request validation tokens when authenticating for
     * the account/password endpoint. This API's parameters and response are identical to that of the HS API
     * /register/email/requestToken except that M_THREEPID_NOT_FOUND may be returned if no account matching the given email
     * address could be found. The server may instead send an email to the given address prompting the user to create an account.
     * M_THREEPID_IN_USE may not be returned.
     *
     * @return <p>Status code 200: An email was sent to the given address.</p>
     */
    @POST("/_matrix/client/r0/account/password/email/requestToken")
    @Headers("Content-type: application/json")
    EmptyResponse passwordRequestToken();

    /**
     * Deactivate the user's account, removing all ability for the user to login again.
     * <br>
     * This API endpoint uses the User-Interactive Authentication API.
     * <br>
     * An access token should be submitted to this endpoint if the client has an active session.
     * <br>
     * The homeserver may change the flows available depending on whether a valid access token is provided.
     *
     * @param deactivateRequest request.
     * @return <p>Status code 200: The account has been deactivated.</p>
     * <p>Status code 401: The homeserver requires additional authentication information.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @POST("/_matrix/client/r0/account/deactivate")
    @Headers("Content-type: application/json")
    EmptyResponse deactivate(@Body DeactivateRequest deactivateRequest);

    /**
     * Checks to see if a username is available, and valid, for the server.
     * <br>
     * The server should check to ensure that, at the time of the request, the username requested is available for use.
     * This includes verifying that an application service has not claimed the username and that the username fits the server's
     * desired requirements (for example, a server could dictate that it does not permit usernames with underscores).
     * <br>
     * Matrix clients may wish to use this API prior to attempting registration, however the clients must also be aware
     * that using this API does not normally reserve the username. This can mean that the username becomes unavailable
     * between checking its availability and attempting to register it.
     * <br>
     * <b>Rate-limited</b>: Yes.
     *
     * @param username Required. The username to check the availability of.
     * @return Status code 200: The username is available.
     * <p>Status code 400: Part of the request was invalid or the username is not available. This may include one of the following error
     * codes:</p>
     * <ul>
     * <li>M_USER_IN_USE : The desired username is already taken.</li>
     * <li>M_INVALID_USERNAME : The desired username is not a valid user name.</li>
     * <li>M_EXCLUSIVE : The desired username is in the exclusive namespace claimed by an application service.</li>
     * </ul>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @GET("/_matrix/client/r0/register/available")
    @Headers("Content-type: application/json")
    AvailableResponse available(@Query("username") String username);

    /**
     * Gets a list of the third party identifiers that the homeserver has associated with the user's account.
     * <br>
     * This is not the same as the list of third party identifiers bound to the user's Matrix ID in Identity Servers.
     * <br>
     * Identifiers in this list may be used by the homeserver as, for example, identifiers that it will accept to reset the user's
     * account password.
     *
     * @return <p>Status code 200: The lookup was successful.</p>
     */
    @GET("/_matrix/client/r0/account/3pid")
    @Headers("Content-type: application/json")
    ThreePidResponse showThreePid();

    /**
     * Adds contact information to the user's account.
     *
     * @param threePidRequest new contact information.
     * @return <p>Status code 200: The addition was successful.</p>
     * <p>Status code 403: The credentials could not be verified with the identity server.</p>
     */
    @POST("/_matrix/client/r0/account/3pid")
    @Headers("Content-type: application/json")
    EmptyResponse updateThreePid(@Body ThreePidRequest threePidRequest);

    /**
     * Proxies the identity server API validate/email/requestToken, but first checks that the given email address is not already
     * associated with an account on this Home Server. This API should be used to request validation tokens when adding an email
     * address to an account. This API's parameters and response is identical to that of the HS API /register/email/requestToken endpoint.
     *
     * @return <p>Status code 200: An email was sent to the given address.</p>
     */
    @POST("/_matrix/client/r0/account/3pid/email/requestToken")
    @Headers("Content-type: application/json")
    EmptyResponse threePidRequestToken();

    /**
     * Gets information about the owner of a given access token.
     * <br>
     * Note that, as with the rest of the Client-Server API, Application Services may masquerade as users within their namespace
     * by giving a user_id query parameter. In this situation, the server should verify that the given user_id is registered by
     * the appservice, and return it in the response body.
     *
     * @return <p>Status code 200: The token belongs to a known user.</p>
     * <p>Status code 401: The token is not recognised.</p>
     * <p>Status code 403: The appservice cannot masquerade as the user or has not registered them.</p>
     * <p>Status code 429: This request was rate-limited.</p>
     */
    @GET("/_matrix/client/r0/account/whoami")
    @Headers("Content-type: application/json")
    WhoamiResponse whoami();
}
