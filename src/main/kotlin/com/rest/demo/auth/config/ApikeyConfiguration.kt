//package com.rest.demo.auth.config
//
//import com.rest.demo.auth.ApikeyInterceptor
//import org.springframework.stereotype.Component
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
//
///*
//* this class for setup / register the apikey
//* */
//@Component
//class ApikeyConfiguration(val apikeyInterceptor: ApikeyInterceptor): WebMvcConfigurer {
//    override fun addInterceptors(registry: InterceptorRegistry) {
//        super.addInterceptors(registry)
//        registry.addWebRequestInterceptor(apikeyInterceptor)
//    }
//}
