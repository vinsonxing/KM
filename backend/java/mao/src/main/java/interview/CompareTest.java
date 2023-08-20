package interview;

import static java.util.Comparator.*;

import java.util.Comparator;
public class CompareTest {

	private static final Comparator<PhoneNumber> COMPARATOR =
			comparingInt(PhoneNumber::getAreaCode)
			.thenComparingInt(PhoneNumber::getPrefix)
			.thenComparingInt(PhoneNumber::getLineNum);
	
}
class PhoneNumber{
	int areaCode;
	int prefix;
	int lineNum;
	public int getAreaCode() {
		return areaCode; 
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public int getPrefix() {
		return prefix;
	}
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}	
}
class Employee1{
	String id;
	String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Employee1(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}

