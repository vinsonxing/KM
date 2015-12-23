/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;
import java.util.*;

// line 224 "../../../../../../../SDKDemo.ump"
public class AddBridgeRequest
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AddBridgeRequest Attributes
  private List<BridgeType> bridgeTypes;

  //------------------------
  // INTERFACE
  //------------------------

  public void setBridgeTypes(List<BridgeType> abridgeTypes){
    bridgeTypes = abridgeTypes;
  }

  public boolean addBridgeType(BridgeType aBridgeType)
  {
    boolean wasAdded = false;
    wasAdded = bridgeTypes.add(aBridgeType);
    return wasAdded;
  }

  public boolean removeBridgeType(BridgeType aBridgeType)
  {
    boolean wasRemoved = false;
    wasRemoved = bridgeTypes.remove(aBridgeType);
    return wasRemoved;
  }

  public BridgeType getBridgeType(int index)
  {
    BridgeType aBridgeType = bridgeTypes.get(index);
    return aBridgeType;
  }

  public List<BridgeType> getBridgeTypes()
  {
    return bridgeTypes;
  }

  public int numberOfBridgeTypes()
  {
    int number = bridgeTypes.size();
    return number;
  }

  public boolean hasBridgeTypes()
  {
    boolean has = bridgeTypes.size() > 0;
    return has;
  }

  public int indexOfBridgeType(BridgeType aBridgeType)
  {
    int index = bridgeTypes.indexOf(aBridgeType);
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