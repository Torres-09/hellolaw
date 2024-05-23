package com.hellolaw.hellolaw.util;

import java.util.HashMap;
import java.util.Map;

public class CategoryConstant {

	private static final Map<String, String> categoryMap = new HashMap<>();

	static {
		categoryMap.put("stalking", "스토킹");
		categoryMap.put("sex crimes", "성범죄");
		categoryMap.put("traffic accidents/drinking driving", "교통사고/음주운전");
		categoryMap.put("assault/injury", "폭행/상해");
		categoryMap.put("drugs", "마약");
		categoryMap.put("fraud", "사기");
		categoryMap.put("divorce", "이혼");
		categoryMap.put("inheritance/households", "상속/가사");
		categoryMap.put("loans/unpaid/bond collection", "대여금/미수금/채권추심");
		categoryMap.put("administrative litigation", "행정소송");
		categoryMap.put("consumer disputes", "소비자분쟁");
		categoryMap.put("other", "기타");
	}

	public static String getCategoryInKorean(String englishCategory) {
		return categoryMap.getOrDefault(englishCategory, "기타");
	}
}
