/**
 * ReportServiceSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public class ReportServiceSOAPStub extends org.apache.axis.client.Stub implements com.baidu.api.sem.sms.v3.ReportService_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRealTimeData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getRealTimeDataRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeDataRequest"), com.baidu.api.sem.sms.v3.GetRealTimeDataRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeDataResponse"));
        oper.setReturnClass(com.baidu.api.sem.sms.v3.RealTimeResultType[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getRealTimeDataResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "realTimeResultTypes"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProfessionalReportId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getProfessionalReportIdRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getProfessionalReportIdRequest"), com.baidu.api.sem.sms.v3.GetProfessionalReportIdRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getProfessionalReportIdResponse"));
        oper.setReturnClass(com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getProfessionalReportIdResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReportState");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getReportStateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportStateRequest"), com.baidu.api.sem.sms.v3.GetReportStateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportStateResponse"));
        oper.setReturnClass(com.baidu.api.sem.sms.v3.GetReportStateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getReportStateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReportFileUrl");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getReportFileUrlRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportFileUrlRequest"), com.baidu.api.sem.sms.v3.GetReportFileUrlRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportFileUrlResponse"));
        oper.setReturnClass(com.baidu.api.sem.sms.v3.GetReportFileUrlResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getReportFileUrlResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRealTimeQueryData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getRealTimeQueryDataRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeQueryDataRequest"), com.baidu.api.sem.sms.v3.GetRealTimeQueryDataRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeQueryDataResponse"));
        oper.setReturnClass(com.baidu.api.sem.sms.v3.RealTimeQueryResultType[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getRealTimeQueryDataResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "realTimeQueryResultTypes"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRealTimePairData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getRealTimePairDataRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimePairDataRequest"), com.baidu.api.sem.sms.v3.GetRealTimePairDataRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimePairDataResponse"));
        oper.setReturnClass(com.baidu.api.sem.sms.v3.RealTimePairResultType[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "getRealTimePairDataResponse"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "realTimePairResultTypes"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

    }

    public ReportServiceSOAPStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ReportServiceSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ReportServiceSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getProfessionalReportIdRequest");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetProfessionalReportIdRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getProfessionalReportIdResponse");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeDataRequest");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetRealTimeDataRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeDataResponse");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimeResultType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeResultType");
            qName2 = new javax.xml.namespace.QName("", "realTimeResultTypes");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimePairDataRequest");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetRealTimePairDataRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimePairDataResponse");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimePairResultType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimePairResultType");
            qName2 = new javax.xml.namespace.QName("", "realTimePairResultTypes");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeQueryDataRequest");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetRealTimeQueryDataRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getRealTimeQueryDataResponse");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimeQueryResultType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeQueryResultType");
            qName2 = new javax.xml.namespace.QName("", "realTimeQueryResultTypes");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportFileUrlRequest");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetReportFileUrlRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportFileUrlResponse");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetReportFileUrlResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportStateRequest");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetReportStateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", ">getReportStateResponse");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.GetReportStateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "AttributeType");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.AttributeType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimePairResultType");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimePairResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeQueryRequestType");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimeQueryRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeQueryResultType");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimeQueryResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeRequestType");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimeRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "RealTimeResultType");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.RealTimeResultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("https://api.baidu.com/sem/sms/v3", "ReportRequestType");
            cachedSerQNames.add(qName);
            cls = com.baidu.api.sem.sms.v3.ReportRequestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.baidu.api.sem.sms.v3.RealTimeResultType[] getRealTimeData(com.baidu.api.sem.sms.v3.GetRealTimeDataRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://api.baidu.com/sem/sms/v3/ReportService/getRealTimeData");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRealTimeData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.baidu.api.sem.sms.v3.RealTimeResultType[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.baidu.api.sem.sms.v3.RealTimeResultType[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.baidu.api.sem.sms.v3.RealTimeResultType[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse getProfessionalReportId(com.baidu.api.sem.sms.v3.GetProfessionalReportIdRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://api.baidu.com/sem/sms/v3/ReportService/getProfessionalReportId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getProfessionalReportId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.baidu.api.sem.sms.v3.GetReportStateResponse getReportState(com.baidu.api.sem.sms.v3.GetReportStateRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://api.baidu.com/sem/sms/v3/ReportService/getReportState");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getReportState"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.baidu.api.sem.sms.v3.GetReportStateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.baidu.api.sem.sms.v3.GetReportStateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.baidu.api.sem.sms.v3.GetReportStateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.baidu.api.sem.sms.v3.GetReportFileUrlResponse getReportFileUrl(com.baidu.api.sem.sms.v3.GetReportFileUrlRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://api.baidu.com/sem/sms/v3/ReportService/getReportFileUrl");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getReportFileUrl"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.baidu.api.sem.sms.v3.GetReportFileUrlResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.baidu.api.sem.sms.v3.GetReportFileUrlResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.baidu.api.sem.sms.v3.GetReportFileUrlResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.baidu.api.sem.sms.v3.RealTimeQueryResultType[] getRealTimeQueryData(com.baidu.api.sem.sms.v3.GetRealTimeQueryDataRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://api.baidu.com/sem/sms/v3/ReportService/getRealTimeQueryData");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRealTimeQueryData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.baidu.api.sem.sms.v3.RealTimeQueryResultType[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.baidu.api.sem.sms.v3.RealTimeQueryResultType[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.baidu.api.sem.sms.v3.RealTimeQueryResultType[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.baidu.api.sem.sms.v3.RealTimePairResultType[] getRealTimePairData(com.baidu.api.sem.sms.v3.GetRealTimePairDataRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("https://api.baidu.com/sem/sms/v3/ReportService/getRealTimePairData");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getRealTimePairData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.baidu.api.sem.sms.v3.RealTimePairResultType[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.baidu.api.sem.sms.v3.RealTimePairResultType[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.baidu.api.sem.sms.v3.RealTimePairResultType[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
