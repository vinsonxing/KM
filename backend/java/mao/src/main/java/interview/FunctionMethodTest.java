package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionMethodTest {
	static class Employee{
		String name;
		int age;
		
		public Employee(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
	}
	public static void main(String[] args) {
		Function<Employee, String> funcEmployeeToString = (e)->{return e.getName();};
		List<Employee> employeeList=Arrays.asList(
			      new Employee("Harry Major", 25),
			      new Employee("Ethan Hardy", 65),
			      new Employee("Nancy Smith", 15),
			      new Employee("Deborah Sprightly", 29));
		List<String> empNameList = convertEmployeeToNameList(employeeList, funcEmployeeToString);
		empNameList.forEach(System.out::println);
	}
	
	public static List<String> convertEmployeeToNameList(List<Employee> employeeList, Function<Employee, String> funEmployeeToString){
		List<String> nameList = new ArrayList<>(); 
		for(Employee emp:employeeList) {
			nameList.add(funEmployeeToString.apply(emp));
		}
		return nameList;
	}
}
