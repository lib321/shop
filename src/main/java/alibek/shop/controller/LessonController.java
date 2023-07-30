package alibek.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lesson_controller")
public class LessonController {

    @RequestMapping(path = "/first_resource")
    public String firstResource() {
        return "First Spring Application";
    }
}
