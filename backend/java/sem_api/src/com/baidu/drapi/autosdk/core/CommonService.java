/**
 * VersionService.java
 *
 * Copyright @year@ @company@, Inc.
 *
 * @company@ licenses this file to you under the Apache License, version 2.0
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

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baidu.drapi.autosdk.exception.ApiException;

/**
 * Internal use only, please ignore.
 * 
 * @author @author@ (@author-email@)
 * 
 * @version @version@, $Date: 2010-7-30$
 * 
 */
public abstract class CommonService implements Config {
    protected static final Log log = LogFactory.getLog(CommonService.class);

    protected String serverUrl = "";
    protected String username = "";
    protected String password = "";
    protected String accessToken = "";
    protected String token = "";
    protected String target = null;
    protected String protocol = "JSON";
    protected AuthHeader authHeader = null;
    protected boolean disableCNCheck = false;
    protected boolean compressMessage = false;

    protected Integer connectTimeoutMills = 10000;
    protected Integer readTimeoutMills = 1800000;

    //private Version currentVersion = V2.v;

    /**
     * Get the client side serivce using the Json protocol.
     * 
     * @param <T>
     *            The service interface
     * @param cls
     *            The service interface
     * @return The client side serivce stub.
     * @throws ApiException 
     * @throws Throwable 
     */
    public final <T> T getJsonService(Class<T> cls) throws ApiException {
        return JsonProxy.createProxy(cls, this);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Integer getConnectTimeoutMills() {
        return connectTimeoutMills;
    }

    public void setConnectTimeoutMills(Integer connectTimeoutMills) {
        this.connectTimeoutMills = connectTimeoutMills;
    }

    public Integer getReadTimeoutMills() {
        return readTimeoutMills;
    }

    public void setReadTimeoutMills(Integer readTimeoutMills) {
        this.readTimeoutMills = readTimeoutMills;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Get the client side serivce stub with the default version.
     * 
     * @param <T>
     *            The service interface
     * @param cls
     *            The service interface
     * @return The client side serivce stub.
     * @throws ApiException 
     * @throws Throwable 
     */
    public final <T> T getService(Class<T> cls) throws ApiException {
        //        if ("SOAP".equalsIgnoreCase(protocol)) {
        //            return getSoapService(cls);
        //        } else {
        return JsonProxy.createProxy(cls, this);
        //        }
    }

    /**
     * Get the client side serivce stub.
     * 
     * @param <T>
     *            The service interface
     * @param cls
     *            The service interface
     * @param v
     *            The version instance
     * @return The client side serivce stub.
     * @throws ApiException 
     */
    /*public final <T> T getSoapService(Class<T> cls) throws ApiException {
        generateHeader();
        T proxy = getInternalService(cls);
        Map<String, Object> reqCtxt = ((BindingProvider) proxy).getRequestContext();
        reqCtxt.put(Header.HEADER_LIST, headers);
        reqCtxt.put("com.sun.xml.internal.ws.connect.timeout", connectTimeoutMills);
        reqCtxt.put("com.sun.xml.internal.ws.request.timeout", readTimeoutMills);

        // This will show you how to configure the http conduit dynamically
        Client client = ClientProxy.getClient(proxy);
        
        // This http is used to configure the http related properties.
        HTTPConduit http = (HTTPConduit) client.getConduit();

        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

        httpClientPolicy.setConnectionTimeout(connectTimeoutMills);
        httpClientPolicy.setAllowChunking(true);
        httpClientPolicy.setReceiveTimeout(readTimeoutMills);

        http.setClient(httpClientPolicy);

        if (disableCNCheck) {
            TLSClientParameters tlsClientParameters = new TLSClientParameters();
            tlsClientParameters.setDisableCNCheck(true);
            tlsClientParameters.setUseHttpsURLConnectionDefaultHostnameVerifier(false);

            http.setTlsClientParameters(tlsClientParameters);
        }

        return proxy;
    }*/

    /**
     * Set the current version to be used when call <code>getService</code> Please do not call this method directly, it
     * will be called automatically when reading the configuration file.
     * 
     * @param str
     *            The version marker. For example, V2
     */
    public void setVersion(String str) {
        //        currentVersion = Version.getVersion(str);
        //        if (currentVersion == null) {
        //            throw new ClientInternalException("The Version you set [" + str + "] does not exist!");
        //        }
    }

    /**
     * Set the thread pool size.
     * 
     * @param value
     */
    private void setThreadPoolSize(String value) {
        // executor = Executors.newFixedThreadPool(Integer.parseInt(value));
    }

    /**
     * Please do not call this method directly, it will be called automatically when reading the configuration file.
     * 
     * @param key
     *            The configuration key
     * @param value
     *            The configuration value
     * 
     */
    public void setConfig(String key, String value) {
        try {
            if ("version".equalsIgnoreCase(key)) {
                setVersion(value);
            } else if ("threadPoolSize".equalsIgnoreCase(key)) {
                setThreadPoolSize(value);
            } else {
                setField(CommonService.class.getDeclaredField(key), value);
            }
        } catch (Exception e) {
            log.info("Can not set [" + key + "] into config field: " + e.toString());
        }
    }

    // /////////////////////////////////////////////////////////////////////////////
    // Protected or private methods
    // /////////////////////////////////////////////////////////////////////////////

    //    protected abstract <T> T getInternalService(Class<T> cls) throws ApiException;

    public void generateHeader() throws ApiException {
        authHeader = new AuthHeader();
        if (accessToken != null && !"".equals(accessToken)) {
            authHeader.setAccessToken(accessToken);
        } else {
        }
        // 绕过json验证用户名密码为空的限制
        authHeader.setUsername(username);
        authHeader.setPassword(password);
        authHeader.setToken(token);
        authHeader.setTarget(target);

        log.info("Current user: " + username);
    }

    /**
     * Change the user in header into another one.
     * @param authHeader
     * @param service The client side service sub.
     * @throws ApiException 
     */
    /*public void updateHeader(AuthHeader authHeader, Object service) throws ApiException {
        try {
            Header header = new Header(new QName(AddressUtil.getHeaderNameSpace(), "AuthHeader"), authHeader,
                    new JAXBDataBinding(AuthHeader.class));
            List<Header> tmpHeaders = new ArrayList<Header>();
            tmpHeaders.add(header);
            if (service instanceof BindingProvider) {
                Map<String, Object> reqCtxt = ((BindingProvider) service).getRequestContext();
                reqCtxt.put(Header.HEADER_LIST, tmpHeaders);
                log.info("Current user changed to: " + authHeader.getUsername());
            } else {
                throw new IllegalArgumentException("service instance is not correct!");
            }
        } catch (JAXBException e) {
            log.fatal("Failed to genarate AuthHeader!", e);
            throw new ApiException("Failed to update AuthHeader!");
        }
    }*/

    /**
     * Get current version.
     * @return
     */
    //    protected Version getCurrentVersion() {
    //        return currentVersion;
    //    }

    private void setField(Field field, String value) throws Exception {
        field.setAccessible(true);
        Class<?> cls = field.getType();
        if (cls.equals(int.class)) {
            field.setInt(this, Integer.parseInt(value));
        } else if (cls.equals(long.class)) {
            field.setLong(this, Long.parseLong(value));
        } else if (cls.equals(boolean.class)) {
            field.setBoolean(this, Boolean.parseBoolean(value));
        } else if (cls.equals(Integer.class)) {
            field.set(this, Integer.parseInt(value));
        } else if (cls.equals(Long.class)) {
            field.set(this, Long.parseLong(value));
        } else if (cls.equals(Boolean.class)) {
            field.set(this, Boolean.parseBoolean(value));
        } else {
            field.set(this, value);
        }
    }
}
