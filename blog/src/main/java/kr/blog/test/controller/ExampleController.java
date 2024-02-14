package kr.blog.test.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleaf(Model model){
        person testPerson = new person();
        testPerson.setId(1L);
        testPerson.setName("홍길동");
        testPerson.setAge(23);
        testPerson.setHobby(List.of("운동","산책","독서","영화감상"));
        model.addAttribute("Person", testPerson);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }
}

    @Getter
    @Setter
    class person{
    private long id;
    private String name;
    private int age;
    private List<String> hobby;
    }