package com.allmsi.msg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author lirui
 * @Date 2018-08-23
 * 
 */
public class Utils {
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	public static String getAccess(String msg) {
		System.out.println(msg);
		return msg;
	}

	
	//数组去空值
	public static String[] arrayRemoveNull(String[] data) {

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < data.length && data.length > 0; i++) {
			if (data[i] == null || "".equals(data[i].trim().toString())) {
				continue;
			} else {
				list.add(data[i]);
			}
		}
		String[] newArray = new String[list.size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = list.get(i);
		}
		return newArray;
	}

}