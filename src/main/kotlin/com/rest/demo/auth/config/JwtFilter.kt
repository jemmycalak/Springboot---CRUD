package com.rest.demo.auth.config

import com.rest.demo.auth.service.CustomUserDetailService
import com.rest.demo.auth.service.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter: OncePerRequestFilter() {

    @Autowired lateinit var jwtUtility: JwtUtils
    @Autowired lateinit var customUserDetailService: CustomUserDetailService

    override fun doFilterInternal(
        httpServletRequest: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = httpServletRequest.getHeader("Authorization")
        var token: String? = null
        var userName: String? = null

        if (null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7)
            userName = jwtUtility.getUsernameFromToken(token)
        }

        if (null != userName && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = customUserDetailService.loadUserByUsername(userName)
            if (jwtUtility.validateToken(token, userDetails)!!) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, userDetails.authorities
                )
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(httpServletRequest, response)
    }

}
