package com.hao.notificationcenter.utils;

import java.util.Random;

/**
 * @author Muggle Lee
 * @Date: 2019/7/31 15:14
 */
public class VerificationCodeUtil {

	private static String[] NUMBERS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static Random RANDOM = new Random();

	/**
	 * 生成n位随机数值字符串
	 *
	 * @param n
	 * @return
	 */
	public static String randomCode(int n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			builder.append(NUMBERS[RANDOM.nextInt(NUMBERS.length)]);
		}
		return builder.toString();
	}
}
