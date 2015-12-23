/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;

// line 23 "../../../../../../../SDKDemo.ump"
public class Monitor
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Monitor Attributes
  private Long monitorId;
  private Long folderId;
  private Long id;
  private Long adgroupId;
  private Long campaignId;
  private Integer type;
  private OptType opt;

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMonitorId(Long aMonitorId)
  {
    boolean wasSet = false;
    monitorId = aMonitorId;
    wasSet = true;
    return wasSet;
  }

  public boolean setFolderId(Long aFolderId)
  {
    boolean wasSet = false;
    folderId = aFolderId;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(Long aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setAdgroupId(Long aAdgroupId)
  {
    boolean wasSet = false;
    adgroupId = aAdgroupId;
    wasSet = true;
    return wasSet;
  }

  public boolean setCampaignId(Long aCampaignId)
  {
    boolean wasSet = false;
    campaignId = aCampaignId;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(Integer aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setOpt(OptType aOpt)
  {
    boolean wasSet = false;
    opt = aOpt;
    wasSet = true;
    return wasSet;
  }

  public Long getMonitorId()
  {
    return monitorId;
  }

  public Long getFolderId()
  {
    return folderId;
  }

  public Long getId()
  {
    return id;
  }

  public Long getAdgroupId()
  {
    return adgroupId;
  }

  public Long getCampaignId()
  {
    return campaignId;
  }

  public Integer getType()
  {
    return type;
  }

  public OptType getOpt()
  {
    return opt;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "monitorId" + ":" + getMonitorId()+ "," +
            "folderId" + ":" + getFolderId()+ "," +
            "id" + ":" + getId()+ "," +
            "adgroupId" + ":" + getAdgroupId()+ "," +
            "campaignId" + ":" + getCampaignId()+ "," +
            "type" + ":" + getType()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "opt" + "=" + (getOpt() != null ? !getOpt().equals(this)  ? getOpt().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}