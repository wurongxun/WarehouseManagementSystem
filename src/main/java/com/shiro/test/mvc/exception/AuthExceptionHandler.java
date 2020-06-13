package com.shiro.test.mvc.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 20191105
 * h没效果
 */
@ControllerAdvice
public class AuthExceptionHandler {

   @ExceptionHandler({UnauthorizedException.class})
   @ResponseStatus(HttpStatus.UNAUTHORIZED)//对应401
    public ModelAndView processUnautthenticatedException(NativeWebRequest request,UnauthorizedException e){

   return new ModelAndView("error","exception",e);
   }
}
