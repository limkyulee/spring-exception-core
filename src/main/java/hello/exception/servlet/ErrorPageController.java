package hello.exception.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

// FIXME : 예외 처리용 Controller

@Slf4j
@Controller
public class ErrorPageController {

    // RequestDispatcher 상수로 정의되어 있음
    public static final String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
    public static final String ERROR_EXCEPTION = "jakarta.servlet.error.exception";
    public static final String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
    public static final String ERROR_MESSAGE = "jakarta.servlet.error.message";
    public static final String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("404 ERROR");
        printErrorInfo(request);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("500 ERROR");
        printErrorInfo(request);
        return "error-page/500";
    }

    private void printErrorInfo(HttpServletRequest request){
        log.info("ERROR_EXCEPTION_TYPE : {}", ERROR_EXCEPTION_TYPE);
        log.info("ERROR_EXCEPTION : {}", ERROR_EXCEPTION);
        log.info("ERROR_REQUEST_URI : {}", ERROR_REQUEST_URI);
        log.info("ERROR_MESSAGE : {}", ERROR_MESSAGE);
        log.info("ERROR_SERVLET_NAME : {}", ERROR_SERVLET_NAME);
        log.info("ERROR_STATUS_CODE : {}", ERROR_STATUS_CODE);
        log.info("dispatcherType : {}", request.getDispatcherType());
    }
}
