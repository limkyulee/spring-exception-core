package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 실무에서 예외를 공통으로 처리하고자 할 때 사용.

* @ControllerAdvice
* 대상으로 지정한 여러 컨트롤러에 @ExceptionHandler, @InitBinder 기능을 부여함.
* 대상을 지정하지 않을 경우 모든 컨트롤레에 적용됨

* @RestControllerAdvice
* @ControllerAdvice 와 같고, @ResponseBody 가 추가되어있음.
* @Controller 와 @RestController 의 차이와 같음.

* [대상 컨트롤러 지정 방법]
* 특정 애노테이션이 있는 컨트롤러를 진행할 있고 특정 클래스를 지정할 수 있음.
* @ControllerAdvice("org.example.controllers") | 클래스 지정 예시
*/

/*
 * @ExceptionHandler
 * 해당 컨트롤러에서 처리하고싶은 예외를 지정할 수 있음.
 * 해당 컨트롤러에서 예외가 발생하면 해당 메서드가 호출됨.
 * 지정한 예외 또는 그 예외의 자식 클래스 모두 잡을 수 있음.

 * [우선순위]
 * 부모 예외와 자식 예외가 동시에 발생할 경우 자식예외(더 자세한 예외) 가 우선권을 가짐.

 * [다양한 예외]
 * 한번에 다양한 에외를 파라미터로 받아 처리할 수 있음.
 */

@Slf4j
@RestControllerAdvice(basePackages = "hello.exception.api")
public class ExControllerAdvice {
    // PLUS : API 상태코드 미 설정 시, 200 으로 반환, 400으로 반환되도록 에러 코드 지정.
    // FIXME : IllegalArgumentException - ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        log.error("[exceptionHandler] ex", e);
        // PLUS : 정상 흐름으로 JSON 만들어서 반환.
        return new ErrorResult("BAD", e.getMessage());
    }

    // FIXME : UserException - ExceptionHandler
    // PLUS : ResponseEntity | 응답 코드 동적으로 변경 가능.
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    // FIXME : ErrorResult = ExceptionHandler
    //       | 따로 처리하지 못한 오류들을 공통으로 처리.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("EX", e.getMessage());
        return errorResult;
    }
}
