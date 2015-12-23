/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;
import java.util.*;

// line 39 "../../../../../../../SDKDemo.ump"
public class CampaignAdgroup
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CampaignAdgroup Attributes
  private Long campaignId;
  private List<AdgroupType> adgroupTypes;

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCampaignId(Long aCampaignId)
  {
    boolean wasSet = false;
    campaignId = aCampaignId;
    wasSet = true;
    return wasSet;
  }

  public void setAdgroupTypes(List<AdgroupType> aadgroupTypes){
    adgroupTypes = aadgroupTypes;
  }

  public boolean addAdgroupType(AdgroupType aAdgroupType)
  {
    boolean wasAdded = false;
    wasAdded = adgroupTypes.add(aAdgroupType);
    return wasAdded;
  }

  public boolean removeAdgroupType(AdgroupType aAdgroupType)
  {
    boolean wasRemoved = false;
    wasRemoved = adgroupTypes.remove(aAdgroupType);
    return wasRemoved;
  }

  public Long getCampaignId()
  {
    return campaignId;
  }

  public AdgroupType getAdgroupType(int index)
  {
    AdgroupType aAdgroupType = adgroupTypes.get(index);
    return aAdgroupType;
  }

  public List<AdgroupType> getAdgroupTypes()
  {
    return adgroupTypes;
  }

  public int numberOfAdgroupTypes()
  {
    int number = adgroupTypes.size();
    return number;
  }

  public boolean hasAdgroupTypes()
  {
    boolean has = adgroupTypes.size() > 0;
    return has;
  }

  public int indexOfAdgroupType(AdgroupType aAdgroupType)
  {
    int index = adgroupTypes.indexOf(aAdgroupType);
    return index;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "campaignId" + ":" + getCampaignId()+ "]"
     + outputString;
  }
}