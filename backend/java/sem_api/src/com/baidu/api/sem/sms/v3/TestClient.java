package com.baidu.api.sem.sms.v3;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.soap.SOAPException;

import org.apache.axis.client.Stub;

public class TestClient {

    public static void main(String[] args) throws RemoteException, ParseException, SOAPException {
        ReportServiceProxy proxy = 
                new ReportServiceProxy();
        //proxy.setEndpoint("");"https://api.baidu.com/sem/sms/v3/ReportService"
        
        ReportService_PortType service = proxy.getReportService_PortType();
        
     // set soap header
        ((Stub)service).setHeader(new SoapHeader().getHeader());
        
        GetProfessionalReportIdRequest parameters = new GetProfessionalReportIdRequest();
        
        ReportRequestType rrt = new ReportRequestType();
        rrt.setPerformanceData(new String[] { "impression", "ctr" });
        
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -2);
        String dateStr = sdf.format(now.getTime());
        
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(sdf2.parse(dateStr + " 00:00:00"));
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(sdf2.parse(dateStr + " 23:59:59"));
        
        rrt.setStartDate(startDate);
        rrt.setEndDate(endDate);
        
        rrt.setReportType(14);
        
        rrt.setLevelOfDetails(11);
        
        //rrt.setStatRange(3);
        
        rrt.setUnitOfTime(5);
        
        parameters.setReportRequestType(rrt);
        
        GetProfessionalReportIdResponse response = proxy.getProfessionalReportId(parameters);
        
        System.out.print(response.getReportId());
    }

}
