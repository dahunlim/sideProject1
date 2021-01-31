package exitProject.web;

import exitProject.web.dto.mainResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public mainResponseDto helloDto(@RequestParam("name") String name,
                                    @RequestParam("amount") int amount) {
        return new mainResponseDto(name, amount);
    }
}
