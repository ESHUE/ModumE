package com.amolrang.modume.test;

import javax.servlet.http.HttpSession;

import com.amolrang.modume.model.Social_JPA;


public class CommonUtils {
	
	// 0: 로그인 안 된 상태
	// 1: 모둠이 + 소셜
	// 2: 모듐이만
	// 3: 소셜만
	public static int getLoginType(HttpSession hs) {
		Object resultObject = hs.getAttribute("userInfo");
		
		if(resultObject == null) {
			return 0;
		}
		
		// UserInfo가 속하는 클래스의 풀네임 추출
		String classFullNm = resultObject.getClass().getName();
		String classNm = classFullNm.substring(classFullNm.lastIndexOf(".") + 1);
		
		if(classNm.equals("User_JPA")) {
			return 2;
		} else if(classNm.equals("Social_JPA")) {
			// social 로그인만 된 경우
			if(((Social_JPA)resultObject).getUserSeq() == null) {
				return 3;
			}
			return 1;
			
		}
		
		return 0;
		
	}

}
