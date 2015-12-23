/**
 * JsonEnvelop.java
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

public class JsonEnvelop<T, K> {
    
    private T header;
    private K body;
    
    public T getHeader() {
        return header;
    }
    public void setHeader(T header) {
        this.header = header;
    }
    public K getBody() {
        return body;
    }
    public void setBody(K body) {
        this.body = body;
    }
    
}
