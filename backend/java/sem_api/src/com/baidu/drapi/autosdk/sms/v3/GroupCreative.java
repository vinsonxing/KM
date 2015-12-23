/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;
import java.util.*;

// line 33 "../../../../../../../SDKDemo.ump"
public class GroupCreative
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GroupCreative Attributes
  private Long adgroupId;
  private List<CreativeType> creativeTypes;

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAdgroupId(Long aAdgroupId)
  {
    boolean wasSet = false;
    adgroupId = aAdgroupId;
    wasSet = true;
    return wasSet;
  }

  public void setCreativeTypes(List<CreativeType> acreativeTypes){
    creativeTypes = acreativeTypes;
  }

  public boolean addCreativeType(CreativeType aCreativeType)
  {
    boolean wasAdded = false;
    wasAdded = creativeTypes.add(aCreativeType);
    return wasAdded;
  }

  public boolean removeCreativeType(CreativeType aCreativeType)
  {
    boolean wasRemoved = false;
    wasRemoved = creativeTypes.remove(aCreativeType);
    return wasRemoved;
  }

  public Long getAdgroupId()
  {
    return adgroupId;
  }

  public CreativeType getCreativeType(int index)
  {
    CreativeType aCreativeType = creativeTypes.get(index);
    return aCreativeType;
  }

  public List<CreativeType> getCreativeTypes()
  {
    return creativeTypes;
  }

  public int numberOfCreativeTypes()
  {
    int number = creativeTypes.size();
    return number;
  }

  public boolean hasCreativeTypes()
  {
    boolean has = creativeTypes.size() > 0;
    return has;
  }

  public int indexOfCreativeType(CreativeType aCreativeType)
  {
    int index = creativeTypes.indexOf(aCreativeType);
    return index;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "adgroupId" + ":" + getAdgroupId()+ "]"
     + outputString;
  }
}