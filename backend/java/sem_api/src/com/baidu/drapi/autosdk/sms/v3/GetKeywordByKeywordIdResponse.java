/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

package com.baidu.drapi.autosdk.sms.v3;
import com.baidu.drapi.autosdk.common.*;
import java.util.*;

// line 138 "../../../../../../../SDKDemo.ump"
public class GetKeywordByKeywordIdResponse
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GetKeywordByKeywordIdResponse Attributes
  private List<KeywordType> keywordTypes;

  //------------------------
  // INTERFACE
  //------------------------

  public void setKeywordTypes(List<KeywordType> akeywordTypes){
    keywordTypes = akeywordTypes;
  }

  public boolean addKeywordType(KeywordType aKeywordType)
  {
    boolean wasAdded = false;
    wasAdded = keywordTypes.add(aKeywordType);
    return wasAdded;
  }

  public boolean removeKeywordType(KeywordType aKeywordType)
  {
    boolean wasRemoved = false;
    wasRemoved = keywordTypes.remove(aKeywordType);
    return wasRemoved;
  }

  public KeywordType getKeywordType(int index)
  {
    KeywordType aKeywordType = keywordTypes.get(index);
    return aKeywordType;
  }

  public List<KeywordType> getKeywordTypes()
  {
    return keywordTypes;
  }

  public int numberOfKeywordTypes()
  {
    int number = keywordTypes.size();
    return number;
  }

  public boolean hasKeywordTypes()
  {
    boolean has = keywordTypes.size() > 0;
    return has;
  }

  public int indexOfKeywordType(KeywordType aKeywordType)
  {
    int index = keywordTypes.indexOf(aKeywordType);
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