package interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;


public class StreamTest3 {
	public static void main(String[] args) {
		Random random = new Random(10);
		List<Member> memberList = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Member member = new Member();
			member.setId(i);
			member.setMemberName("bubu" + i);
			member.setTotalMoney(new BigDecimal(random.nextInt(40)+10));
			member.setAmount(random.nextInt(10)+10);
			member.setSex(member.getId() % 2 == 0 ? "male" : "femail");
			member.setFamilyId(random.nextInt(5) + 1);
			memberList.add(member);
		} 
		
		  // System.out.println(queryMemberNamesPerFamily(memberList)); //
		  System.out.println(queryMemberNamesPerFamily_stream(memberList)); //
		  System.out.println(queryMemberMoneyPerFamily(memberList)); //
		  System.out.println(queryMemberMoneyPerFamily_stream(memberList));
		  
		  // System.out.println(queryMemberMoneyPerFamily_stream1(memberList));
		 		memberList.stream().forEach(m->{
			if(m.getMemberName().equals("bubu1")) {
				m.setMemberName("bubu1-changed");
				//System.out.println("bubu1-changed");
			}
		});
		memberList.stream().map(Member::getMemberName).forEach(System.out::println);
	}
	
	static Map<Integer, List<String>> queryMemberNamesPerFamily(List<Member> memberList){
		Map<Integer, List<String>> familyMap = new HashMap();
		for (Member member : memberList) {
			List<String> members = familyMap.get(member.getFamilyId());
			if(members == null) {
				members = new ArrayList<String>();
			}
			members.add(member.getMemberName());
			familyMap.put(member.getFamilyId(), members);
		}
		return familyMap;
	}
	static Map<Integer, List<String>> queryMemberNamesPerFamily_stream(List<Member> memberList){
		return memberList.stream().collect(Collectors.groupingBy(Member::getFamilyId,
				Collectors.mapping(Member::getMemberName, Collectors.toList())));
	}
	
	static Map<Integer, BigDecimal> queryMemberMoneyPerFamily(List<Member> memberList){
		Map<Integer, BigDecimal> familyMap = new HashMap();
		for (Member member : memberList) {
			BigDecimal totalMoney = familyMap.get(member.getFamilyId());
			if(Objects.isNull(totalMoney)) {
				totalMoney = new BigDecimal(0);
			}
			totalMoney = totalMoney.add(member.getTotalMoney());
			familyMap.put(member.getFamilyId(), totalMoney);
		}
		return familyMap;
	}
	static Map<Integer, BigDecimal> queryMemberMoneyPerFamily_stream(List<Member> memberList){
		return memberList.stream().collect(Collectors.groupingBy(Member::getFamilyId,
				Collectors.reducing(BigDecimal.ZERO, Member::getTotalMoney, BigDecimal::add)));
	}
	
	static Map<Integer, Integer> queryMemberMoneyPerFamily_stream1(List<Member> memberList){
		return memberList.stream().collect(Collectors.groupingBy(Member::getFamilyId,
				Collectors.summingInt(Member::getAmount)));
	}
	
}
class Member{
	Integer id;
	String memberName;
	String sex;
	BigDecimal totalMoney;
	Integer familyId;
	Integer amount;
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

}
