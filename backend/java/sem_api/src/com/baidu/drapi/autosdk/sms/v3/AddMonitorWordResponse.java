/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;
import java.util.*;

// line 121 "../../../../../../../SDKDemo.ump"
public class AddMonitorWordResponse
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AddMonitorWordResponse Attributes
  private List<Monitor> monitors;

  //------------------------
  // INTERFACE
  //------------------------

  public void setMonitors(List<Monitor> amonitors){
    monitors = amonitors;
  }

  public boolean addMonitor(Monitor aMonitor)
  {
    boolean wasAdded = false;
    wasAdded = monitors.add(aMonitor);
    return wasAdded;
  }

  public boolean removeMonitor(Monitor aMonitor)
  {
    boolean wasRemoved = false;
    wasRemoved = monitors.remove(aMonitor);
    return wasRemoved;
  }

  public Monitor getMonitor(int index)
  {
    Monitor aMonitor = monitors.get(index);
    return aMonitor;
  }

  public List<Monitor> getMonitors()
  {
    return monitors;
  }

  public int numberOfMonitors()
  {
    int number = monitors.size();
    return number;
  }

  public boolean hasMonitors()
  {
    boolean has = monitors.size() > 0;
    return has;
  }

  public int indexOfMonitor(Monitor aMonitor)
  {
    int index = monitors.indexOf(aMonitor);
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