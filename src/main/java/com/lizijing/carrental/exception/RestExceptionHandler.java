package com.lizijing.carrental.exception;

import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p> 异常捕获 </p>
 *
 * @author LiZijing
 * @date 2022/3/4
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResult<String> exception(Exception e) {
        if (e instanceof BadSqlGrammarException) {
            log.error("db error:", e);
            return CommonResult.failed(ResultCode.DB_ERROR, e.getMessage());
        } else {
            log.error("error:", e);
            return CommonResult.failed(ResultCode.SYS_ERROR, e.getMessage());
        }
    }
}
