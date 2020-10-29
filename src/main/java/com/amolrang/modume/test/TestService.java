package com.amolrang.modume.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;
import com.amolrang.modume.repository.UserBoardRepository;
import com.google.gson.JsonObject;

@Service
public class TestService {
	
	@Autowired
	UserBoardRepository userBoardRepository;
	
	@ResponseBody
	public String imageUpload(HttpServletRequest request, HttpServletResponse response, 
			MultipartHttpServletRequest multiFile) throws Exception {      
		JsonObject json = new JsonObject();
        OutputStream out = null;
        PrintWriter printWriter = null; 
        MultipartFile file = multiFile.getFile("upload");
        
        // StringUtils.isNotBlank("str") -> whitespace나 공백문자, null이면 true 반환
        if(file != null) {
        	if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
        		if(file.getContentType().toLowerCase().startsWith("image/")) {
        			try {
        	        	 //파일 이름 가져오기
        	            String fileName = file.getName();
        	            byte[] bytes = file.getBytes();
        	            String uploadPath = request.getServletContext().getRealPath("") + "../resources/static/img";
        	            File uploadFile = new File(uploadPath);
        	            System.out.println("getContextPath() : " + request.getContextPath());
        	            System.out.println(uploadPath);
        	            if(!uploadFile.exists()) {
        	            	uploadFile.mkdirs();
        	            }
        	            
        	            fileName = UUID.randomUUID().toString();
        	         
        	            uploadPath = uploadPath + "/" + fileName;
        	            out = new FileOutputStream(new File(uploadPath));
        	            out.write(bytes);
        	            
        	            printWriter = response.getWriter();
        	            response.setContentType("text/html");
        	            String fileUrl = request.getContextPath() + "/img/" + fileName;
        	            System.out.println("fileUrl : " + fileUrl);
        	            
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);
                        
                        Thread.sleep(3000);
                        printWriter.println(json);
        	        	
        	        } catch(Exception e) {
        	        	e.printStackTrace();
        	        } finally {
        	        	if(out != null) {
        	        		out.close();
        	        	}
        	        	if(printWriter != null) {
        	        		printWriter.close();
        	        	}
        	        }
        		}
        	}
        }
        
        return null;        
        
	}
	
	public Userboard_JPA boardRegModAction(HttpSession hs, Userboard_JPA param) {
		Object resultObject = hs.getAttribute("userInfo");
		
		if(resultObject == null) {
			return null;
		}
		
		// UserInfo가 속하는 클래스의 풀네임 추출
		String classFullNm = resultObject.getClass().getName();
		String classNm = classFullNm.substring(classFullNm.lastIndexOf(".") + 1);
		
		User_JPA user_jpa = null;
		Social_JPA social_jpa = null;
		Userboard_JPA userBoard_jpa = new Userboard_JPA();
		
		if(classNm.equals("User_JPA")) {
			user_jpa = (User_JPA)resultObject;
		} else if(classNm.equals("Social_JPA")) {
			social_jpa = (Social_JPA)resultObject;
			user_jpa = social_jpa.getUser();
			
			// social 로그인만 된 경우
			if(user_jpa == null) {
				return null;
			}
			
		}
		
		userBoard_jpa.setUser(user_jpa);
		userBoard_jpa.setTitle(param.getTitle());
		userBoard_jpa.setContent(param.getContent());
		
		System.out.println("타이틀 : " + param.getTitle());
		System.out.println("내용 : " + param.getContent());
		System.out.println("이름 : " + user_jpa.getNickname());
		
		userBoardRepository.save(userBoard_jpa);
		
		return userBoard_jpa;
		
	}

}
