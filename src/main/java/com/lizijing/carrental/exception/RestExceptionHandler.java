package com.lizijing.carrental.exception;

import com.lizijing.carrental.result.CommonResult;
import com.lizijing.carrental.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
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
            // 数据库语法异常
            log.error("DB Error:", e);
            return CommonResult.failed(ResultCode.DB_ERROR, e.getMessage());
        } else if (e instanceof DuplicateKeyException) {
            // 数据库重复键异常
            log.error("DuplicateKey Error:", e);
            return CommonResult.failed(ResultCode.DB_Duplicate_ERROR, e.getCause().getMessage());
        } else {
            // 其他异常
            log.error("Error:", e);
            return CommonResult.failed(ResultCode.SYS_ERROR);
        }
    }
}
