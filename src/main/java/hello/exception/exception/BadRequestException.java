package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// PLUS : @ResponseStatus | 해당 애노테이션을 적용하면 HTTP 상태 코드를 변경해줌.
//      | reason : message.properties 에 작성한 메세지를 사용 가능.
@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "error.bad")
public class BadRequestException extends RuntimeException {
}
