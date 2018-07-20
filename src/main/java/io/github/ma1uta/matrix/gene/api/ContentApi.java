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

import io.github.ma1uta.matrix.gene.model.content.ContentConfig;
import io.github.ma1uta.matrix.gene.model.content.ContentUri;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * * This module allows users to upload content to their homeserver which is retrievable from other homeservers.
 * Its' purpose is to allow users to share attachments in a room. Key locations are represented as Matrix Key (MXC) URIs.
 * They look like:
 * <pre>
 * mxc://(server-name)/(media-id)
 *
 * (server-name) : The name of the homeserver where this content originated, e.g. matrix.org
 * (media-id) : An opaque ID which identifies the content.
 * </pre>
 * Uploads are POSTed to a resource on the user's local homeserver which returns a token which is used to GET the download.
 * Key is downloaded from the recipient's local homeserver, which must first transfer the content from the origin homeserver
 * using the same API (unless the origin and destination homeservers are the same).
 */
public interface ContentApi {

    /**
     * The desired resizing method.
     */
    class Method {

        protected Method() {
        }

        /**
         * Crop.
         */
        public static final String CROP = "crop";

        /**
         * Scale.
         */
        public static final String SCALE = "scale";
    }

    /**
     * Upload some content to the content repository.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <b>Requires auth</b>: Yes.
     *
     * @param inputStream     The file content.
     * @param filename        The name of the file being uploaded.
     * @param contentType     The content type of the file being uploaded.
     * @return <b>Required</b>. The MXC URI to the uploaded content.
     *     Status code 200: The MXC URI for the uploaded content.
     *     Status code 429: This request was rate-limited.
     */
    @POST("/_matrix/media/r0/upload")
    @Headers("Content-type: multipart/form-data")
    ContentUri upload(@Body InputStream inputStream, @Query("filename") String filename, @Header("Content-Type") String contentType);

    /**
     * Download content from the content repository.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     *
     * @param serverName      Required. The server name from the mxc:// URI (the authoritory component).
     * @param mediaId         Required. The media ID from the mxc:// URI (the path component).
     * @param allowRemote     Indicates to the server that it should not attempt to fetch the media if it is deemed remote.
     *                        This is to prevent routing loops where the server contacts itself. Defaults to true if not provided.
     * @return Response headers:
     * <table border="1">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>Key-Type</td>
     * <td>string</td>
     * <td>The content type of the file that was previously uploaded.</td>
     * </tr>
     * <tr>
     * <td>Key-Disposition</td>
     * <td>string</td>
     * <td>The name of the file that was previously uploaded, if set.</td>
     * </tr>
     * </table>
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET("/_matrix/media/r0/download/{serverName}/{mediaId}")
    OutputStream download(@Path("serverName") String serverName, @Path("mediaId") String mediaId,
                          @Query("allow_remote") Boolean allowRemote);

    /**
     * Download content from the content repository as a given filename.
     *
     * @param serverName      Required. The server name from the mxc:// URI (the authoritory component).
     * @param mediaId         Required. The media ID from the mxc:// URI (the path component).
     * @param filename        Required. The filename to give in the Content-Disposition.
     * @param allowRemote     Indicates to the server that it should not attempt to fetch the media if it is deemed remote.
     *                        This is to prevent routing loops where the server contacts itself. Defaults to true if not provided.
     * @return Response headers:
     * <table border="1">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>Key-Type</td>
     * <td>string</td>
     * <td>The content type of the file that was previously uploaded.</td>
     * </tr>
     * <tr>
     * <td>Key-Disposition</td>
     * <td>string</td>
     * <td>The name of the file that was previously uploaded, if set.</td>
     * </tr>
     * </table>
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET("/_matrix/media/r0/download/{serverName}/{mediaId}/{fileName}")
    OutputStream downloadFile(@Path("serverName") String serverName, @Path("mediaId") String mediaId, @Path("fileName") String filename,
                              @Query("allow_remote") Boolean allowRemote);

    /**
     * Download a thumbnail of the content from the content repository.
     *
     * @param serverName      Required. The server name from the mxc:// URI (the authoritory component).
     * @param mediaId         Required. The media ID from the mxc:// URI (the path component)
     * @param width           The desired width of the thumbnail. The actual thumbnail may not match the size specified.
     * @param height          The desired height of the thumbnail. The actual thumbnail may not match the size specified.
     * @param method          The desired resizing method. One of: ["crop", "scale"].
     * @param allowRemote     Indicates to the server that it should not attempt to fetch the media if it is deemed remote.
     *                        This is to prevent routing loops where the server contacts itself. Defaults to true if not provided.
     * @return Response headers:
     * <table border="1">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>Key-Type</td>
     * <td>string</td>
     * <td>The content type of the file that was previously uploaded.</td>
     * </tr>
     * </table>
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET("/_matrix/media/r0/thumbnail/{serverName}/{mediaId}")
    OutputStream thumbnail(@Path("serverName") String serverName, @Path("mediaId") String mediaId, @Query("width") Long width,
                           @Query("height") Long height, @Query("method") String method, @Query("allow_remote") Boolean allowRemote);

    /**
     * Get information about a PATH for a client.
     *
     * @param url             Required. The PATH to get a preview of.
     * @param ts              The preferred point in time to return a preview for. The server may return a newer version if it does not
     *                        have the requested version available.
     * @return Response headers:
     * <table border="1">
     * <tr>
     * <th>Parameter</th>
     * <th>Type</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>matrix:image:size</td>
     * <td>number</td>
     * <td>The byte-size of the image. Omitted if there is no image attached.</td>
     * </tr>
     * <tr>
     * <td>og:image</td>
     * <td>string</td>
     * <td>An MXC URI to the image. Omitted if there is no image.</td>
     * </tr>
     * </table>
     *     Status code 200: The content that was previously uploaded.
     *     Status code 429: This request was rate-limited.
     */
    @GET("/_matrix/media/r0/preview_url")
    Map<String, String> previewUrl(@Query("url") String url, @Query("ts") String ts);

    /**
     * This endpoint allows clients to retrieve the configuration of the content repository, such as upload limitations.
     * Clients SHOULD use this as a guide when using content repository endpoints. All values are intentionally left optional.
     * Clients SHOULD follow the advice given in the field description when the field is not available.
     * <p/>
     * NOTE: Both clients and server administrators should be aware that proxies between the client and the server may affect
     * the apparent behaviour of content repository APIs, for example, proxies may enforce a lower upload size limit than is
     * advertised by the server on this endpoint.
     * <p/>
     * <b>Rate-limited</b>: Yes.
     * <b>Requires auth</b>: Yes.
     *
     * @return Status code 200: The public content repository configuration for the matrix server.
     *     Status code 429: This request was rate-limited.
     */
    @GET("/_matrix/media/r0")
    ContentConfig config();
}
