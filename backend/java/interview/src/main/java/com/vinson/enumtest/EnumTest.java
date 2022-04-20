package com.vinson.enumtest;

public class EnumTest {

	public static interface OneInterface {
		void test();
	}
	
	public static enum SchoolType {
		HIGH,MIDDLE
	}
	/**
	 *  enum can't expends anohter class/enum, but can implement an interface
	 *
	 */
	public static enum SubSchoolType implements OneInterface {
		PRIMARY;
		
		@Override
		public void test() {
			// TODO Auto-generated method stub
			
		}
	}
}
