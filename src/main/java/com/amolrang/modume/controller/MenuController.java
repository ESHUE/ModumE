package com.amolrang.modume.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amolrang.modume.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuController {
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(Model model, Principal principal) {
		model.addAttribute(StringUtils.TitleKey(), "회원정보");
		log.info("메뉴열기 : {}",principal );
		return "/menu";
	}
}
