package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if(id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }

        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력값");
        }

        if(id.equals("user-ex")){
            // PLUS : UserHandlerExceptionResolver 로 해당 예외 처리 진행.
            //      | 컨트롤러에서 예외가 발생하여도 ExceptionResolver 에서 예외 처리.
            //      | 서블릿 컨테이너까지 예외가 전달되지않고, 스프링 MVC 예외 처리가 끝남.
            //      | WAS 는 로직이 정상 처리 되었다고 판단함.
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }


    // PLUS : ResponseStatusException | 상태코드와 에러 메세지를 한번에 처리할 수 있는 Exception.
    @GetMapping("/api/response-status-ex2")
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @Data
    @AllArgsConstructor
    public static class MemberDto {
        private String memberId;
        private String name;
    }

}
