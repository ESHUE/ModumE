package com.amolrang.modume.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.model.Boardimg_JPA;
import com.amolrang.modume.model.User_JPA;
import com.amolrang.modume.model.Userboard_JPA;
import com.amolrang.modume.repository.BoardImgRepository;
import com.amolrang.modume.repository.UserBoardRepository;
import com.google.gson.JsonObject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class TestService {
	
	// private List<Boardimg_JPA> imgList = new ArrayList<Boardimg_JPA>();
	
	@Autowired
	UserBoardRepository userBoardRepository;
	
	@Autowired
	BoardImgRepository boardImgRepository;
	
	public List<Userboard_JPA> boardList(List<Userboard_JPA> list) {
		List<Userboard_JPA> edittedList = new ArrayList<Userboard_JPA>();
		int limitedLength = 120;
		
		String edittedContent = "";
		
		// 미리보기 - limitedLength만큼 자르기
		for(Userboard_JPA board : list) {
			Userboard_JPA param = new Userboard_JPA();
			
			param.setBoardseq(board.getBoardseq());
			param.setHits(board.getHits());
			param.setMdate(board.getMdate());
			param.setRdate(board.getRdate());
			param.setTitle(board.getTitle());
			param.setUserseq(board.getUserseq());
			param.setContent(board.getContent());
			
			edittedContent = board.getConvertcontent();
			
			if(edittedContent.length() > 120) {
				edittedContent = edittedContent.substring(0, (limitedLength - 1));
			}
			
			param.setConvertcontent(edittedContent);
			
			edittedList.add(param);
		}
		
		return edittedList;
	}
	
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, 
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
                        
                        // Thread.sleep(3000);
                        printWriter.println(json);
                        
                        /*
                        // DB에 저장할 이미지 파일 정보
                        Boardimg_JPA img = new Boardimg_JPA();
        	        	
                        img.setImgname(fileName);
                        img.setImgseq(imgList.size() + 1);
                        
                        imgList.add(img);
                        
                        
                        System.out.println("이미지 포문 시작");
                        for(Boardimg_JPA i : imgList) {
                        	System.out.println("이미지네임나가신다~");
                        	System.out.println(i.getImgname());
                        	System.out.println(i.getImgseq());
                        }
                        */
                        	
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
        
	}
	
	public int boardRegModAction(HttpSession hs, Userboard_JPA param) {	
		User_JPA user_jpa = (User_JPA)hs.getAttribute("userInfo");

		Userboard_JPA userBoard_jpa = new Userboard_JPA();
	
		userBoard_jpa.setUserseq(user_jpa);
		userBoard_jpa.setTitle(param.getTitle());
		userBoard_jpa.setContent(param.getContent());
		userBoard_jpa.setConvertcontent(param.getConvertcontent());
		
		System.out.println("타이틀 : " + param.getTitle());
		System.out.println("내용 : " + param.getContent());
		System.out.println("태그 뺀 내용 : " + param.getConvertcontent());
		System.out.println("이름 : " + user_jpa.getNickname());
		
		userBoardRepository.save(userBoard_jpa);
		Integer boardseq = userBoardRepository.findMaxBoardseqByUserSeq(user_jpa);
		Userboard_JPA temp = userBoardRepository.findByBoardseq(boardseq);
		log.info("temp:{}",temp);
		// content에서 이미지 이름 찾기... ㅠㅡㅠ
		List<String> imgList = new ArrayList<String>();
		String text = userBoard_jpa.getContent();
		
		Pattern nonValidPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		Matcher matcher = nonValidPattern.matcher(text);
			   
	   while (matcher.find()) {
		   System.out.println("이거 나와야 되는데 ㅜㅜㅜㅜㅜㅜ");
		   String imgPath = matcher.group(1);
		   System.out.println(imgPath);
		   
		   Boardimg_JPA boardImg_jpa = new Boardimg_JPA();
		   
		   //boardImg_jpa.setImgseq(imgList.size() + 1);
		   boardImg_jpa.setImgpath(imgPath);
		   boardImg_jpa.setBoardseq(temp);
		   
		   imgList.add(imgPath);
		   
		   
		   log.info("boardImg_jpa:{}",boardImg_jpa);
		   
		   boardImgRepository.save(boardImg_jpa);
		   
	   }
		
		// 여기까지
		
		
		return boardseq;
		
	}

}
