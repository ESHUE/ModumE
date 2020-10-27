package com.amolrang.modume.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amolrang.modume.service.TestService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @RestController
@Controller
public class TestController {
	@Autowired
	private TestService service;

	@RequestMapping(value = "/test", produces="text/plain;charset=UTF-8")
	public String test(Principal principal,OAuth2AuthenticationToken authentication) {
		log.info("principal:{}",principal);
		return principal.toString();
	}
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Principal principal) {
		return "/boardList";
	}
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(Principal principal) {
		return "/boardDetail";
	}
	
	@RequestMapping(value = "/boardRegMod", method = RequestMethod.GET)
	public String boardRegMod(Principal principal) {
		return "/boardRegMod";
	}
	
	
	
	
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
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
        	            log.info("fileName:{}",fileName);
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
                        
                        Gson gson = new Gson();
                        String strJson = gson.toJson(json);
                        System.out.println(strJson);
                        printWriter.println(strJson);
                 
                    }catch(IOException e){
                        e.printStackTrace();
                    }finally{
                        if(out != null){
                            out.close();
                        }
                        if(printWriter != null){
                            printWriter.close();
                        }		
     				}
     			}
     		}
     	}
     	return null;        
        
	}
		
	
	
	
	
	@GetMapping("/userinfo")
	public String userInfo() {
		return "/userinfo";
	}
	
	@GetMapping("/userinfo0")
	public String userInfo0() {
		return "/userinfo0";
	}
	
	@GetMapping("/userinfo1")
	public String userInfo1() {
		return "/userinfo1";
	}
	
	@GetMapping("/userinfo2")
	public String userInfo2() {
		return "/userinfo2";
	}
	
	@GetMapping("/userinfo3")
	public String userInfo3() {
		return "/userinfo3";
	}
 }
