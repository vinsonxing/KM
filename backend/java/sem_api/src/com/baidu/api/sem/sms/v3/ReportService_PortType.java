/**
 * ReportService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.baidu.api.sem.sms.v3;

public interface ReportService_PortType extends java.rmi.Remote {
    public com.baidu.api.sem.sms.v3.RealTimeResultType[] getRealTimeData(com.baidu.api.sem.sms.v3.GetRealTimeDataRequest parameters) throws java.rmi.RemoteException;
    public com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse getProfessionalReportId(com.baidu.api.sem.sms.v3.GetProfessionalReportIdRequest parameters) throws java.rmi.RemoteException;
    public com.baidu.api.sem.sms.v3.GetReportStateResponse getReportState(com.baidu.api.sem.sms.v3.GetReportStateRequest parameters) throws java.rmi.RemoteException;
    public com.baidu.api.sem.sms.v3.GetReportFileUrlResponse getReportFileUrl(com.baidu.api.sem.sms.v3.GetReportFileUrlRequest parameters) throws java.rmi.RemoteException;
    public com.baidu.api.sem.sms.v3.RealTimeQueryResultType[] getRealTimeQueryData(com.baidu.api.sem.sms.v3.GetRealTimeQueryDataRequest parameters) throws java.rmi.RemoteException;
    public com.baidu.api.sem.sms.v3.RealTimePairResultType[] getRealTimePairData(com.baidu.api.sem.sms.v3.GetRealTimePairDataRequest parameters) throws java.rmi.RemoteException;
}
