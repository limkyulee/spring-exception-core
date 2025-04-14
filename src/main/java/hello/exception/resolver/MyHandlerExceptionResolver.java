package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

        try{
            if(exception instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());

                // PLUS : 빈 ModelAndView 반환하면 뷰를 렌더링 하지 않고, 정상 흐름으로 서블릿이 리턴됨.
                return new ModelAndView();
            }
        }catch (IOException e){
            log.error("RESOLVER EX", e);
        }

        // PLUS : null 을 반환하면, 다음 ExceptionResolver 를 찾아서 실행함.
        //      | 기존 발생한 예외를 밖으로 던짐.
        return null;
    }
}
