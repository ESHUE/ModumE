package com.amolrang.modume.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

import com.amolrang.modume.model.Boardimg_JPA;
import com.amolrang.modume.model.Social_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;
import com.amolrang.modume.repository.UserBoardRepository;
import com.google.gson.JsonObject;

import lombok.Data;

@Service
@Data
public class TestService {
	
	private List<Boardimg_JPA> imgList = new ArrayList<Boardimg_JPA>();
	
	@Autowired
	UserBoardRepository userBoardRepository;
	
	public List<Userboard_JPA> boardList(List<Userboard_JPA> list) {
		List<Userboard_JPA> editedList = new ArrayList<Userboard_JPA>();
		int limitedLength = 120;
		
		String content = "";
		String cuttedContent = "";
		String edittedContent = "";
		
		int startP = 0;
		int finishP = 0;
		
		
		// 모든 <p>태그에서 <p></p> 제외한 내용 120글자 이하 추출
		for(Userboard_JPA board : list) {
			Userboard_JPA param = new Userboard_JPA();
			
			param.setBoardseq(board.getBoardseq());
			param.setHits(board.getHits());
			param.setMdate(board.getMdate());
			param.setRdate(board.getRdate());
			param.setTitle(board.getTitle());
			param.setUserseq(board.getUserseq());
			
			content = board.getContent();
			
			while(content.contains("<p>")) {
				content = content.replace("&nbsp;", "");
				content = content.replace("<br />;", "");
				
				startP = content.indexOf("<p>") + 3;
				finishP = content.indexOf("</p>");
				
				cuttedContent = content.substring(startP, finishP);
				edittedContent += cuttedContent;
				
				content = content.replaceFirst(String.format("<p>%s</p>", cuttedContent), cuttedContent + " ");
			}
			
			if(edittedContent.length() > limitedLength) {
				edittedContent = edittedContent.substring(0, 120);
			}
			
			param.setContent(String.format("<p>%s</p>", edittedContent));
			
			System.out.println(param.getContent());
			
			editedList.add(param);
		}
		
		return editedList;
	}
	
	public List<Boardimg_JPA> imageUpload(HttpServletRequest request, HttpServletResponse response, 
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
                        
                        // DB에 저장할 이미지 파일 정보
                        Boardimg_JPA img = new Boardimg_JPA();
        	        	
                        img.setImgname(fileName);
                        img.setImgseq(imgList.size() + 1);
                        
                        imgList.add(img);
                        
                        for(Boardimg_JPA i : imgList) {
                        	System.out.println("이미지네임나가신다~");
                        	System.out.println(i.getImgname());
                        	System.out.println(i.getImgseq());
                        }
                        	
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
        
        return imgList;        
        
	}
	
	public Userboard_JPA boardRegModAction(HttpSession hs, Userboard_JPA param) {	
		User_JPA user_jpa = (User_JPA)hs.getAttribute("userInfo");

		if(user_jpa == null) {
			return null;
		}

		Userboard_JPA userBoard_jpa = new Userboard_JPA();
		
		userBoard_jpa.setUserseq(user_jpa);
		userBoard_jpa.setTitle(param.getTitle());
		userBoard_jpa.setContent(param.getContent());
		
		System.out.println("타이틀 : " + param.getTitle());
		System.out.println("내용 : " + param.getContent());
		System.out.println("이름 : " + user_jpa.getNickname());
		
		userBoardRepository.save(userBoard_jpa);
		int boardseq = userBoardRepository.findMaxBoardseqByUserSeq(user_jpa);
		System.out.println("찍어주세요 제발요.. ㅠㅠ : " + boardseq );
		
		return userBoard_jpa;
		
	}

}
