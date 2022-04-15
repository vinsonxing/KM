package com.vinson.study.adhoc;

import java.io.ByteArrayOutputStream;

public class ClosableSteamTest {
	
	public static void main(String[] args) {
		
		
		try (ByteArrayOutputStream outputStream = getByteArrayOutputStream(false)){
			
			System.out.println(outputStream);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	private static ByteArrayOutputStream getByteArrayOutputStream(boolean init) {
		return init ? new ByteArrayOutputStream() : null;
	}

}
