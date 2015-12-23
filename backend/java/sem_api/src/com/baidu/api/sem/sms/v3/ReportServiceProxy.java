package com.baidu.api.sem.sms.v3;

public class ReportServiceProxy implements com.baidu.api.sem.sms.v3.ReportService_PortType {
  private String _endpoint = null;
  private com.baidu.api.sem.sms.v3.ReportService_PortType reportService_PortType = null;
  
  public ReportServiceProxy() {
    _initReportServiceProxy();
  }
  
  public ReportServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initReportServiceProxy();
  }
  
  private void _initReportServiceProxy() {
    try {
      reportService_PortType = (new com.baidu.api.sem.sms.v3.ReportService_ServiceLocator()).getReportService();
      if (reportService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)reportService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)reportService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (reportService_PortType != null)
      ((javax.xml.rpc.Stub)reportService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.baidu.api.sem.sms.v3.ReportService_PortType getReportService_PortType() {
    if (reportService_PortType == null)
      _initReportServiceProxy();
    return reportService_PortType;
  }
  
  public com.baidu.api.sem.sms.v3.RealTimeResultType[] getRealTimeData(com.baidu.api.sem.sms.v3.GetRealTimeDataRequest parameters) throws java.rmi.RemoteException{
    if (reportService_PortType == null)
      _initReportServiceProxy();
    return reportService_PortType.getRealTimeData(parameters);
  }
  
  public com.baidu.api.sem.sms.v3.GetProfessionalReportIdResponse getProfessionalReportId(com.baidu.api.sem.sms.v3.GetProfessionalReportIdRequest parameters) throws java.rmi.RemoteException{
    if (reportService_PortType == null)
      _initReportServiceProxy();
    return reportService_PortType.getProfessionalReportId(parameters);
  }
  
  public com.baidu.api.sem.sms.v3.GetReportStateResponse getReportState(com.baidu.api.sem.sms.v3.GetReportStateRequest parameters) throws java.rmi.RemoteException{
    if (reportService_PortType == null)
      _initReportServiceProxy();
    return reportService_PortType.getReportState(parameters);
  }
  
  public com.baidu.api.sem.sms.v3.GetReportFileUrlResponse getReportFileUrl(com.baidu.api.sem.sms.v3.GetReportFileUrlRequest parameters) throws java.rmi.RemoteException{
    if (reportService_PortType == null)
      _initReportServiceProxy();
    return reportService_PortType.getReportFileUrl(parameters);
  }
  
  public com.baidu.api.sem.sms.v3.RealTimeQueryResultType[] getRealTimeQueryData(com.baidu.api.sem.sms.v3.GetRealTimeQueryDataRequest parameters) throws java.rmi.RemoteException{
    if (reportService_PortType == null)
      _initReportServiceProxy();
    return reportService_PortType.getRealTimeQueryData(parameters);
  }
  
  public com.baidu.api.sem.sms.v3.RealTimePairResultType[] getRealTimePairData(com.baidu.api.sem.sms.v3.GetRealTimePairDataRequest parameters) throws java.rmi.RemoteException{
    if (reportService_PortType == null)
      _initReportServiceProxy();
    return reportService_PortType.getRealTimePairData(parameters);
  }
  
  
}