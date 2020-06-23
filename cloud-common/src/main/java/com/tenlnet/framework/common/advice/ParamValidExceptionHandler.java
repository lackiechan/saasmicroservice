package com.tenlnet.framework.common.advice;

import com.tenlnet.framework.common.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 请求参数异常处理类
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-28 17:27
 */
@RestControllerAdvice
public class ParamValidExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response){

        logger.error("请求：{}发生异常：{}", request.getRequestURI(), e.getMessage(),e);

        if(e instanceof MissingServletRequestParameterException){
            String paramName=((MissingServletRequestParameterException) e).getParameterName();
            String paramType= ((MissingServletRequestParameterException) e).getParameterType();
            // 错误信息map
            Map<String, String> error = new HashMap<>();
            error.put(paramName,"不能为空");

            return Result.error("请求参数错误").put("errors",error);
        }else if(e instanceof MethodArgumentTypeMismatchException){ String name= ((MethodArgumentTypeMismatchException) e).getName();
          MethodParameter parameter=((MethodArgumentTypeMismatchException) e).getParameter();
          String message=  e.getMessage();
          String value= (String) ((MethodArgumentTypeMismatchException) e).getValue();

          String errorTip="Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'";

            // 错误信息map
            Map<String, String> error = new HashMap<>();
            if(message.contains(errorTip)){
                error.put(name,value+"转为数字异常");
            }

            return Result.error("请求参数错误").put("errors",error);
        }else if(e instanceof HttpMediaTypeNotSupportedException){
            MediaType mediaType=((HttpMediaTypeNotSupportedException) e).getContentType();
            String message=  e.getMessage();

           String type= mediaType.getType();

            return Result.error("不支持请求格式："+mediaType.toString());
        }else if(e instanceof MultipartException){
            String message=  e.getMessage();

             String errorTip="Failed to parse multipart servlet request";
            // 错误信息map
            Map<String, String> error = new HashMap<>();
            if(message.contains(errorTip)){
                logger.error("不支持form-data方式提交，请用raw格式提交");
                return Result.error("不支持form-data方式提交");
            }

            return Result.error("Failed to parse multipart servlet request");
        }else if(e instanceof HttpRequestMethodNotSupportedException){
           String method= ((HttpRequestMethodNotSupportedException) e).getMethod();
          String[] supportMethods= ((HttpRequestMethodNotSupportedException) e).getSupportedMethods();

            StringBuilder sb = new StringBuilder("可以支持的方法：");
          for(String str:supportMethods){
              sb.append(str+" ");
          }
            return Result.error("不支持"+method+"方法,"+sb.toString());
        }else{
//            ServletRequestBindingException
            return Result.error("参数错误");
        }
//        Result result = new Result();
//        result.setSuccess(false);
//        if (e instanceof MyException) {
//            result.setErrorCode(((MyException) e).getErrorCode());
//            result.setErrorMsg(((MyException) e).getErrorMsg());
//        } else {
//            result.setErrorCode(MyExceptionEnum.SYSTEM_ERROR.getCode());
//            result.setErrorMsg(MyExceptionEnum.SYSTEM_ERROR.getMsg());
//        }

    }

    /**
     * 自定义注解异常拦截
     */
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public Object handleMethodArgumentNotValidException(Exception e, HttpServletRequest request) {

        logger.error("请求：{}发生异常：{}", request.getRequestURI(), e.getMessage(),e);

        // 错误信息
        StringBuilder sb = new StringBuilder("参数校验失败：");
        // 错误信息map
        Map<String, String> error = new HashMap<>();

        String msg = "";
        if (!(e instanceof BindException) && !(e instanceof MethodArgumentNotValidException)) {
            for (ConstraintViolation cv : ((ConstraintViolationException) e).getConstraintViolations()) {
                msg = cv.getMessage();
                sb.append(msg).append("；");

                Iterator<Path.Node> it = cv.getPropertyPath().iterator();
                Path.Node last = null;
                while (it.hasNext()) {
                    last = (Path.Node) it.next();
                }
                /*for(last = null; it.hasNext(); last = (Path.Node)it.next()) {
                }*/
                error.put(last != null ? last.getName() : "", msg);
            }
        } else {
            List<ObjectError> allErrors = null;
            if (e instanceof BindException) {
                allErrors = ((BindException) e).getAllErrors();
            } else {
                allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            }
            // 拼接错误信息
            for (ObjectError oe : allErrors) {
                msg = oe.getDefaultMessage();
                sb.append(msg).append("；");
                if (oe instanceof FieldError) {
                    error.put(((FieldError) oe).getField(), msg);
                } else {
                    error.put(oe.getObjectName(), msg);
                }
            }
        }

//        Result<Map> result = new Result<>(error);
//        result.setSuccess(false);
//        result.setErrorCode(MyExceptionEnum.REQUESTPARAM_ERROR.getCode());
//        result.setErrorMsg(MyExceptionEnum.REQUESTPARAM_ERROR.getMsg() + sb.toString());
//        return result;

        return Result.error("请求参数错误").put("errors",error);

    }

}
