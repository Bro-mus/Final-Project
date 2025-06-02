package nursery.api.controller.error;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    private enum LogStatus { STACK_TRACE, MESSAGE_ONLY }

    @Data
    private static class ExceptionMessage {
        private String message;
        private String statusReason;
        private int statusCode;
        private String timestamp;
        private String uri;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionMessage handleNoSuchElementException(NoSuchElementException ex, WebRequest req) {
        return buildExceptionMessage(ex, HttpStatus.NOT_FOUND, req, LogStatus.MESSAGE_ONLY);
    }

    private ExceptionMessage buildExceptionMessage(
            NoSuchElementException ex,
            HttpStatus status,
            WebRequest req,
            LogStatus logStatus) {

        String message      = ex.toString();
        String statusReason = status.getReasonPhrase();
        int    statusCode   = status.value();
        String timestamp    = ZonedDateTime.now()
                                  .format(DateTimeFormatter.RFC_1123_DATE_TIME);

        String uri = null;
        if (req instanceof ServletWebRequest swr) {
            uri = swr.getRequest().getRequestURI();
        }

        if (logStatus == LogStatus.MESSAGE_ONLY) {
            log.error("Exception: {}", message);
        } else {
            log.error("Exception: ", ex);
        }

        ExceptionMessage em = new ExceptionMessage();
        em.setMessage(message);
        em.setStatusReason(statusReason);
        em.setStatusCode(statusCode);
        em.setTimestamp(timestamp);
        em.setUri(uri);
        return em;
    }
}