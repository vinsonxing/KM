/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;

// line 48 "../../../../../../../SDKDemo.ump"
public class SublinkInfo
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SublinkInfo Attributes
  private String description;
  private String destinationUrl;

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setDestinationUrl(String aDestinationUrl)
  {
    boolean wasSet = false;
    destinationUrl = aDestinationUrl;
    wasSet = true;
    return wasSet;
  }

  public String getDescription()
  {
    return description;
  }

  public String getDestinationUrl()
  {
    return destinationUrl;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "," +
            "destinationUrl" + ":" + getDestinationUrl()+ "]"
     + outputString;
  }
}