package com.zoostarinc.poker.web.controller;

import java.time.OffsetDateTime;
import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.Utils;

@Slf4j
@Controller
public class SwaggerController implements ApplicationContextAware {

	protected ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String getSwaggerUI(Model model, HttpSession session) {
		String page = "redirect:swagger-ui/index.html";
		log.debug("Loading greeting in env: {}",
				Arrays.toString(applicationContext.getEnvironment().getActiveProfiles()));
		log.debug("Session ID: {}", session.getId());

		model.addAttribute("currentTime", OffsetDateTime.now().format(Utils.ISO_DATE_TIME_FORMAT_UPTO_SECONDS));

		return page;
	}

}
