package com.wwh.blog.config;

import com.wwh.springcloud.pojo.ResultMessage;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMessage errorHandler(Exception ex) {
        ResultMessage res = new ResultMessage();
        if (ex instanceof MethodArgumentNotValidException) {
            Map<String, Object> errors = new HashMap<>();
            ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach(
                    error-> {
                        String filedName = ((FieldError)error).getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(filedName, errorMessage);
                    }
            );
            res.setData(errors);
            res.setSuccess(false);
            res.setCode(400);
        } else {
            res.setCode(400);
            res.setSuccess(false);
            res.setMsg(ex.getMessage());

        }

        return res;
    }

}
