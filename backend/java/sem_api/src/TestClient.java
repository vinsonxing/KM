import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.baidu.drapi.autosdk.core.CommonService;
import com.baidu.drapi.autosdk.core.ResHeader;
import com.baidu.drapi.autosdk.core.ResHeaderUtil;
import com.baidu.drapi.autosdk.core.ServiceFactory;
import com.baidu.drapi.autosdk.exception.ApiException;
import com.baidu.drapi.autosdk.sms.v3.GetRealTimeDataRequest;
import com.baidu.drapi.autosdk.sms.v3.GetRealTimeDataResponse;
import com.baidu.drapi.autosdk.sms.v3.RealTimeRequestType;
import com.baidu.drapi.autosdk.sms.v3.RealTimeResultType;
import com.baidu.drapi.autosdk.sms.v3.ReportService;


public class TestClient {

    public static void main(String[] args) throws ApiException, ParseException {
        CommonService factory = ServiceFactory.getInstance();
        ReportService service = factory.getService(ReportService.class);
        
        GetRealTimeDataRequest request = new GetRealTimeDataRequest();
        RealTimeRequestType type = new RealTimeRequestType();
        List<String> aperformanceData = new ArrayList<String>();
        aperformanceData.add("impression");
        aperformanceData.add("click");
        
        type.setPerformanceData(aperformanceData);
        
        type.setLevelOfDetails(11);
        
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -2);
        String dateStr = sdf.format(now.getTime());
        
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        type.setStartDate(sdf2.parse(dateStr + " 00:00:00"));
        type.setEndDate(sdf2.parse(dateStr + " 23:59:59"));
        
        type.setReportType(14);
        type.setUnitOfTime(5);
        type.setDevice(0);
        
        request.setRealTimeRequestTypes(type);
        GetRealTimeDataResponse resp = service.getRealTimeData(request);
        ResHeader resh = ResHeaderUtil.getJsonResHeader(true);
        //ResHeaderUtil.printResHeader(resh);
        
        List<RealTimeResultType> rtrtList = resp.getRealTimeResultTypes();
        for (RealTimeResultType realTimeResultType : rtrtList) {
            System.out.println("------------------------------");
            System.out.println("ID: " + realTimeResultType.getID());
            for (String kpi : realTimeResultType.getKPIs()) {
                System.out.println("KPI: " + kpi);
            }

            for (String kpi : realTimeResultType.getName()) {
                System.out.println("KPI: " + kpi);
            }
        }
  
        
    }

}
