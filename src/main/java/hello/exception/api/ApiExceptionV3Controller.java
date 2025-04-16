package hello.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api3/controller-advice")
public class ApiExceptionV3Controller {

    @GetMapping("/{id}")
    public MemberDto getMemberExceptionTest(@PathVariable("id") String id) {
        if(id.equals("error")){
            throw new RuntimeException("RuntimeException Error Message");
        }

        return new MemberDto(id, "hello " + id);
    }

    @GetMapping("/test")
    public String illegalArgumentExceptionTest(){
        throw new IllegalArgumentException("IllegalArgumentException Error Message");
    }

    @Data
    @AllArgsConstructor
    public static class MemberDto {
        private String memberId;
        private String name;
    }
}
