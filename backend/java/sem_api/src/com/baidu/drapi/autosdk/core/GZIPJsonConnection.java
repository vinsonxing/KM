/**
 * GZIPJsonConnection.java
 *
 * Copyright 2011 Baidu, Inc.
 *
 * Baidu licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.baidu.drapi.autosdk.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.baidu.drapi.autosdk.exception.ApiException;

public class GZIPJsonConnection extends JsonConnection {

    /**
     * @param url The request URL
     * @throws MalformedURLException
     * @throws IOException
     */
    public GZIPJsonConnection(String url) throws MalformedURLException, IOException {
        super(url + "/gzip");
    }

    @Override
    protected InputStream readResponse() throws ApiException {
        try {
            return new GZIPInputStream(super.readResponse());
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }
 
    @Override
    protected OutputStream sendRequest() throws ApiException {
        try {
            return new GZIPOutputStream(super.sendRequest());
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    
}
