package com.zah.app.comm.err;

import com.zah.app.util.RTUtil;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by zah on 2017/5/22.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    public static org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelMap handleSQLException(Exception ex) {
        logger.error(ex.getMessage());
        return RTUtil.getMap(Err.ERR_SYS);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public ModelMap handleIOException() {
        return RTUtil.getMap(Err.ERR_PATH_NOT_EXIST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AppException.class)
    public ModelMap signException(AppException ex) {
        logger.warn(ex.getMessage());
        return RTUtil.getMap(ex.getErr());
    }
}
