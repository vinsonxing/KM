/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;

// line 140 "../../../../../../../SDKDemo.ump"
public class GetReportFileUrlResponse
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GetReportFileUrlResponse Attributes
  private String reportFilePath;

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReportFilePath(String aReportFilePath)
  {
    boolean wasSet = false;
    reportFilePath = aReportFilePath;
    wasSet = true;
    return wasSet;
  }

  public String getReportFilePath()
  {
    return reportFilePath;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "reportFilePath" + ":" + getReportFilePath()+ "]"
     + outputString;
  }
}