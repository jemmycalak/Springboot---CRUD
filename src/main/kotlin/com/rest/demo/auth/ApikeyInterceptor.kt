//package com.rest.demo.auth
//
//import com.rest.demo.repository.ApikeyRepository
//import com.rest.demo.utils.exception.UnAuthorizedException
//import org.springframework.stereotype.Component
//import org.springframework.ui.ModelMap
//import org.springframework.web.context.request.WebRequest
//import org.springframework.web.context.request.WebRequestInterceptor
//import java.lang.Exception
//
///*
//* when user hit this application, this class will be the first class that called
//* and will be check the api-key if is exist on header
//* */
//@Component
//class ApikeyInterceptor(val apikeyRepository: ApikeyRepository): WebRequestInterceptor {
//    override fun preHandle(request: WebRequest) {
//        //before request hit
//        //check if any api key required
//        val apikey = request.getHeader("api-key")
//        if (apikey.isNullOrEmpty()) {
//            throw UnAuthorizedException()
//        }
//
//        if (!apikeyRepository.existsById(apikey)){
//            throw UnAuthorizedException()
//        }
//
//        //on valid apikey
//    }
//
//    override fun postHandle(request: WebRequest, model: ModelMap?) {
//        //on progress request hited
//    }
//
//    override fun afterCompletion(request: WebRequest, ex: Exception?) {
//        // after request success hited
//    }
//}
