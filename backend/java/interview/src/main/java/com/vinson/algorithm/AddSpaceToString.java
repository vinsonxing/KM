package com.vinson.algorithm;

import Algorithm.BST.main;

public class AddSpaceToString {

	public static void main(String[] args) {
		printBinaryString("tt", 234542);
	}

	private static void printBinaryString(String prefix, int flag) {
		String p = Integer.toBinaryString(flag);
		StringBuilder sb = new StringBuilder();
		if (p.length() < 32) {
			for (int i = 0; i < 32 - p.length(); i++) {
				sb.append("0");
			}
			sb.append(p);
		}
		if (sb.length() != 0) {
			p = sb.toString();
		}
		char[] cs = p.toCharArray();
		sb = new StringBuilder();
		for (int i = 0; i < cs.length; i++) {
			sb.append(cs[i]);
			if ((i + 1) % 4 == 0) {
				sb.append(" ");
			}
		}

		System.out.println(prefix + "===" + sb);
	}
}
