package com.zoostarinc.poker.web.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zoostarinc.poker.service.PokerService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.zoostar.common.Utils;
import net.zoostar.common.audit.Timeable;

@Slf4j
@Controller
@Timeable(threshold = 500)
@RequiredArgsConstructor
public class SwaggerController implements ApplicationContextAware, DisposableBean {
	
	public static final String SWAGGER_PAGE = "redirect:swagger-ui/index.html";

	protected ApplicationContext applicationContext;
	
	protected final PokerService pokerManager;
	
	private final DataSource dataSource;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String getSwaggerUI(@AuthenticationPrincipal OidcUser user, Model model, HttpSession session) {
		log.debug("Loading greeting in env: {}",
				Arrays.toString(applicationContext.getEnvironment().getActiveProfiles()));
		log.debug("Session ID: {}", session.getId());

		var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("Logged in Principal: {}", principal.toString());
		var name = user == null ? "Guest" : user.getGivenName();
		log.info("Hello {}!", name);

		model.addAttribute("name", name);
		model.addAttribute("currentTime", OffsetDateTime.now().format(Utils.ISO_DATE_TIME_FORMAT_UPTO_SECONDS));
		
		var player = pokerManager.retrieveByEmail(user, true);
		log.info("Welcome Player: {}", player);
		
		pokerManager.updateLoginTime(player.getEmail());
		
		return SWAGGER_PAGE;
	}

	@Override
	public void destroy() throws Exception {
		closeDataSource(dataSource);
	}

	public static void closeDataSource(DataSource dataSource) {
		if(dataSource != null) {
			Connection conn;
			try {
				conn = dataSource.getConnection();
				log.info("Performing a clean shutdown of connection: {}...", conn);
				conn.close();
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
}
