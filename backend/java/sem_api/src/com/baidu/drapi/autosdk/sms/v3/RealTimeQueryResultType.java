/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;
import java.util.*;

// line 60 "../../../../../../../SDKDemo.ump"
public class RealTimeQueryResultType
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RealTimeQueryResultType Attributes
  private String query;
  private Long keywordId;
  private Long creativeId;
  private List<String> queryInfo;
  private String date;
  private List<String> KPIs;

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuery(String aQuery)
  {
    boolean wasSet = false;
    query = aQuery;
    wasSet = true;
    return wasSet;
  }

  public boolean setKeywordId(Long aKeywordId)
  {
    boolean wasSet = false;
    keywordId = aKeywordId;
    wasSet = true;
    return wasSet;
  }

  public boolean setCreativeId(Long aCreativeId)
  {
    boolean wasSet = false;
    creativeId = aCreativeId;
    wasSet = true;
    return wasSet;
  }

  public void setQueryInfo(List<String> aqueryInfo){
    queryInfo = aqueryInfo;
  }

  public boolean addQueryInfo(String aQueryInfo)
  {
    boolean wasAdded = false;
    wasAdded = queryInfo.add(aQueryInfo);
    return wasAdded;
  }

  public boolean removeQueryInfo(String aQueryInfo)
  {
    boolean wasRemoved = false;
    wasRemoved = queryInfo.remove(aQueryInfo);
    return wasRemoved;
  }

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public void setKPIs(List<String> aKPIs){
    KPIs = aKPIs;
  }

  public boolean addKPI(String aKPI)
  {
    boolean wasAdded = false;
    wasAdded = KPIs.add(aKPI);
    return wasAdded;
  }

  public boolean removeKPI(String aKPI)
  {
    boolean wasRemoved = false;
    wasRemoved = KPIs.remove(aKPI);
    return wasRemoved;
  }

  public String getQuery()
  {
    return query;
  }

  public Long getKeywordId()
  {
    return keywordId;
  }

  public Long getCreativeId()
  {
    return creativeId;
  }

  public String getQueryInfo(int index)
  {
    String aQueryInfo = queryInfo.get(index);
    return aQueryInfo;
  }

  public List<String> getQueryInfo()
  {
    return queryInfo;
  }

  public int numberOfQueryInfo()
  {
    int number = queryInfo.size();
    return number;
  }

  public boolean hasQueryInfo()
  {
    boolean has = queryInfo.size() > 0;
    return has;
  }

  public int indexOfQueryInfo(String aQueryInfo)
  {
    int index = queryInfo.indexOf(aQueryInfo);
    return index;
  }

  public String getDate()
  {
    return date;
  }

  public String getKPI(int index)
  {
    String aKPI = KPIs.get(index);
    return aKPI;
  }

  public List<String> getKPIs()
  {
    return KPIs;
  }

  public int numberOfKPIs()
  {
    int number = KPIs.size();
    return number;
  }

  public boolean hasKPIs()
  {
    boolean has = KPIs.size() > 0;
    return has;
  }

  public int indexOfKPI(String aKPI)
  {
    int index = KPIs.indexOf(aKPI);
    return index;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "query" + ":" + getQuery()+ "," +
            "keywordId" + ":" + getKeywordId()+ "," +
            "creativeId" + ":" + getCreativeId()+ "," +
            "date" + ":" + getDate()+ "]"
     + outputString;
  }
}