/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;
import java.util.*;

// line 81 "../../../../../../../SDKDemo.ump"
public class GetAdgroupByAdgroupIdResponse
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GetAdgroupByAdgroupIdResponse Attributes
  private List<AdgroupType> adgroupTypes;

  //------------------------
  // INTERFACE
  //------------------------

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
    return super.toString() + "["+ "]"
     + outputString;
  }
}