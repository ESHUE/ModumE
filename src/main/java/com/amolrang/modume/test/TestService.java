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
import com.amolrang.modume.model.UserboardModel;
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
	
	@Autowired
	UserBoardRepository userBoardRepository;
	
	@Autowired
	BoardImgRepository boardImgRepository;
	
	public UserboardModel addImgListToParam(Userboard_JPA board) {
			UserboardModel param = new UserboardModel();
			
			List<Boardimg_JPA>dbImgList = boardImgRepository.findByBoardseq(board);
			List<Boardimg_JPA> imgList = new ArrayList<Boardimg_JPA>();
			
			for(Boardimg_JPA img : dbImgList) {
				imgList.add(img);
			}
			
			param.setBoardseq(board.getBoardseq());
			param.setHits(board.getHits());
			param.setMdate(board.getMdate());
			param.setRdate(board.getRdate());
			param.setTitle(board.getTitle());
			param.setUserseq(board.getUserseq());
			param.setContent(board.getContent());
			param.setImgList(imgList);
			
		return param;
	}
	
	public List<UserboardModel> boardList(List<Userboard_JPA> list) {
		List<UserboardModel> edittedList = new ArrayList<UserboardModel>();
		
		int limitedLength = 120;
		String edittedContent = "";
		
		for(Userboard_JPA board : list) {
			// 이미지 리스트 저장
			UserboardModel param = addImgListToParam(board);
			
			// 미리보기 - limitedLength만큼 자르기
			edittedContent = board.getConvertcontent();
			if(edittedContent.length() > 120) {
				edittedContent = (edittedContent.substring(0, (limitedLength - 1))) + "   ··· ";
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
		int boardseq = 0;
		Userboard_JPA result = null;
		
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
		
		
		 // 글 수정
		   if(param.getBoardseq() != 0) {
			   boardseq = param.getBoardseq();
			   userBoard_jpa.setBoardseq(boardseq);
			   result = userBoardRepository.save(userBoard_jpa);
		   } else {
			   result = userBoardRepository.save(userBoard_jpa);
			   boardseq = result.getBoardseq();
		   }
		   
//		Integer boardseq = userBoardRepository.findMaxBoardseqByUserSeq(user_jpa);
		//Userboard_JPA temp = userBoardRepository.findByBoardseq(boardseq);
		//log.info("temp:{}",temp);
		
		// content에서 이미지 이름 찾기... ㅠㅡㅠ
		List<String> imgList = new ArrayList<String>();
		String text = userBoard_jpa.getContent();
		
		Pattern nonValidPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		Matcher matcher = nonValidPattern.matcher(text);
			   
		
		// 글 수정인 경우에 해당 boardseq의 모든 img를 db에서 삭제
		if(param.getBoardseq() != 0 ) {
			boardImgRepository.deleteByBoardseq(result); // 이거 문제 생기면 삭제!!
		}
		
		while (matcher.find()) {
			System.out.println("이거 나와야 되는데 ㅜㅜㅜㅜㅜㅜ");
			String imgPath = matcher.group(1);
			System.out.println(imgPath);
			   
			Boardimg_JPA boardImg_jpa = new Boardimg_JPA();
			   
			boardImg_jpa.setImgpath(imgPath);
			boardImg_jpa.setBoardseq(result);
			   
			imgList.add(imgPath);
			   
			   
			log.info("boardImg_jpa:{}",boardImg_jpa);
			   

			boardImgRepository.save(boardImg_jpa);
			   
		}
	   
		
		// 여기까지
		return boardseq;
	}
	

}
