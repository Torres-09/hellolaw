package com.hellolaw.hellolaw.util;

import static com.hellolaw.hellolaw.entity.Category.*;

import java.util.HashMap;
import java.util.Map;

import com.hellolaw.hellolaw.entity.Category;

public class CategoryConverter {
	private static final Map<String, Category> categoryMap = new HashMap<>();

	static {
		categoryMap.put("스토킹", STAKING);
		categoryMap.put("성범죄", SEX_CRIME);
		categoryMap.put("교통사고 및 음주운전", TRAFFIC_ACCIDENT_AND_DRINKING_DRIVING);
		categoryMap.put("폭행 및 상해", ASSAULT_AND_INJURY);
		categoryMap.put("마약", DRUGS);
		categoryMap.put("사기", FRAUD);
		categoryMap.put("이혼", DIVORCE);
		categoryMap.put("상속 및 가사", INHERITANCE_AND_HOUSEHOLD);
		categoryMap.put("대여금 및 미수금", LOAN_AND_UNPAID_AND_BOND_COLLECTION);
		categoryMap.put("행정소송", ADMINISTRATIVE_LITIGATION);
		categoryMap.put("소비자분쟁", CONSUMER_DISPUTES);
		categoryMap.put("기타", OTHER);
		categoryMap.put("STAKING", STAKING);
		categoryMap.put("SEX_CRIME", SEX_CRIME);
		categoryMap.put("TRAFFIC_ACCIDENT_AND_DRINKING_DRIVING", TRAFFIC_ACCIDENT_AND_DRINKING_DRIVING);
		categoryMap.put("ASSAULT_AND_INJURY", ASSAULT_AND_INJURY);
		categoryMap.put("DRUGS", DRUGS);
		categoryMap.put("FRAUD", FRAUD);
		categoryMap.put("DIVORCE", DIVORCE);
		categoryMap.put("INHERITANCE_AND_HOUSEHOLD", INHERITANCE_AND_HOUSEHOLD);
		categoryMap.put("LOAN_AND_UNPAID_AND_BOND_COLLECTION", LOAN_AND_UNPAID_AND_BOND_COLLECTION);
		categoryMap.put("ADMINISTRATIVE_LITIGATION", ADMINISTRATIVE_LITIGATION);
		categoryMap.put("CONSUMER_DISPUTES", CONSUMER_DISPUTES);
		categoryMap.put("OTHER", OTHER);
	}

	public static Category getCategoryInEnum(String koreanCategory) {
		return categoryMap.getOrDefault(koreanCategory, OTHER);
	}
}
