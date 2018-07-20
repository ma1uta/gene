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
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Central Authentication Service (CAS) is a web-based single sign-on protocol.
 * <p/>
 * An overview of the process, as used in Matrix, is as follows:
 * <ul>
 * <li>The Matrix client instructs the user's browser to navigate to the /login/cas/redirect endpoint on the user's homeserver.</li>
 * <li>The homeserver responds with an HTTP redirect to the CAS user interface, which the browser follows.</li>
 * <li>The CAS system authenticates the user.</li>
 * <li>The CAS server responds to the user's browser with a redirect back to the /login/cas/ticket endpoint on the homeserver,
 * which the browser follows. A 'ticket' identifier is passed as a query parameter in the redirect.</li>
 * <li>The homeserver receives the ticket ID from the user's browser, and makes a request to the CAS server to validate the ticket.</li>
 * <li>Having validated the ticket, the homeserver responds to the browser with a third HTTP redirect, back to the Matrix
 * client application. A login token is passed as a query parameter in the redirect.</li>
 * <li>The Matrix client receives the login token and passes it to the /login API.</li>
 * </ul>
 * <p/>
 */
public interface CasApi {

    /**
     * A web-based Matrix client should instruct the user's browser to navigate to this endpoint in order to log in via CAS.
     * <p/>
     * The server MUST respond with an HTTP redirect to the CAS interface. The URI MUST include a service parameter giving the path
     * of the /login/cas/ticket endpoint (including the redirectUrl query parameter).
     * <p/>
     * For example, if the endpoint is called with redirectUrl=https://client.example.com/?q=p, it might redirect to
     * {@code https://cas.example.com/?service=https%3A%2F%2Fserver.example.com%2F_matrix%2Fclient%2Fr0%2Flogin%2Fcas%2Fticket%3Fredirect
     * Url%3Dhttps%253A%252F%252Fclient.example.com%252F%253Fq%253Dp}.
     *
     * @param redirectUrl Required. URI to which the user will be redirected after the homeserver has authenticated the user with CAS.
     * @return A redirect to the CAS interface.
     */
    @GET("/_matrix/client/r0/login/cas/redirect")
    EmptyResponse redirect(@Query("redirectUrl") String redirectUrl);

    /**
     * Once the CAS server has authenticated the user, it will redirect the browser to this endpoint (assuming /login/cas/redirect
     * gave it the correct service parameter).
     * <p/>
     * The server MUST call /proxyValidate on the CAS server, to validate the ticket supplied by the browser.
     * <p/>
     * If validation is successful, the server must generate a Matrix login token. It must then respond with an HTTP redirect to the
     * URI given in the redirectUrl parameter, adding a loginToken query parameter giving the generated token.
     * <p/>
     * If validation is unsuccessful, the server should respond with a 401 Unauthorized error, the body of which will be displayed
     * to the user.
     *
     * @param redirectUrl Required. The redirectUrl originally provided by the client to /login/cas/redirect.
     * @param ticket      Required. CAS authentication ticket.
     * @return <ul>
     * <li>Status code 302: A redirect to the Matrix client.</li>
     * <li>Status code 401: The server was unable to validate the CAS ticket.</li>
     * </ul>
     */
    @GET("/_matrix/client/r0/login/cas/ticket")
    EmptyResponse ticket(@Query("redirectUrl") String redirectUrl, @Query("ticket") String ticket);
}
