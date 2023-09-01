package com.kufar.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.kufar.security.UserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = jwtUtils.parseJwt(request);
		if(token != null && jwtUtils.validateJwtToken(token)) {
			String username = jwtUtils.getUsernameFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username); // объект UserDetails нужен для создания объекта аутентификации и проверки имени и пароля.
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // это объект аутентификации нужный Spring Security
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // благодаря этому классу в запрос добавляется информация, которая важна для запроса (ip адрес и прочее) 
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

        filterChain.doFilter(request, response);
		
	}

}
