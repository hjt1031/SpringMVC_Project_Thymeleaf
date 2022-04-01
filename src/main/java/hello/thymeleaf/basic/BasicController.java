package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String testBasic(Model model){
        model.addAttribute("data","Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String testUnescaped(Model model){
        model.addAttribute("data","Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    //변수 SpringEL
    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userA", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    // 기본객체들
    @GetMapping("basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    // 스프링 빈 등록
    @Component("helloBean")
    static class HelloBean {
        public String hello (String data) {
            return "Hello" + data;
        }
    }
    
    // 유틸리티 객체와 날짜
    @GetMapping("/date")
    public String data(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }


    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}

