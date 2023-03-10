package com.vinson.enumtest;

import java.util.EnumSet;

public class EnumTest {

	public static interface OneInterface {
		void test();
	}
	
	public static enum SchoolType {
		HIGH,MIDDLE
	}
	/**
	 *  enum can't expends another class/enum, but can implement an interface
	 *
	 */
	public static enum SubSchoolType implements OneInterface {
		PRIMARY;
		
		@Override
		public void test() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static void main(String[] args) {
        EnumSet<SchoolType> st = EnumSet.noneOf(SchoolType.class);
        
        st.add(SchoolType.HIGH);
        System.out.println(st.size());
    }
}
