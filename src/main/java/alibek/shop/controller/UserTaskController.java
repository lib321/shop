package alibek.shop.controller;

import alibek.shop.pojo.Human;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/user_task_controller")
public class UserTaskController {

    private static final Human[] HUMANS = new Human[]{
            new Human("Bill", 66),
            new Human("Max", 17),
            new Human("Jeff", 35),
            new Human("Mark", 45),
    };



//    @GetMapping(path = "/get_user_list")
//    public List<Human> getUserList(@RequestParam(name = "from", required = false) Integer from,
//                                   @RequestParam(name = "to", required = false) Integer to
//    ) {
//        List<Human> humanList = new ArrayList<>();
//        if (from == null) {
//            from = Integer.MIN_VALUE;
//        }
//        if (to == null) {
//            to = Integer.MAX_VALUE;
//        }
//
//        for (Human human : HUMANS) {
//                if (human.getAge() >= from && human.getAge() <= to) {
//                    humanList.add(human);
//                }
//        }
//        return humanList;
//    }

    @GetMapping(path = "/get_user_list")
    public String getUserList(Model model, @RequestParam(name = "from", required = false) Integer from,
                                   @RequestParam(name = "to", required = false) Integer to
    ) {
        List<Human> humanList = new ArrayList<>();
        int count = 0;
        if (from == null) {
            from = Integer.MIN_VALUE;
        }
        if (to == null) {
            to = Integer.MAX_VALUE;
        }

        for (Human human : HUMANS) {
            if (human.getAge() >= from && human.getAge() <= to) {
                humanList.add(human);
            }
        }
        for (Human human : humanList) {
            if (human.getAge() >= 18) {
                count++;
            }
        }
        model.addAttribute("count", count);
        model.addAttribute("humanList", humanList);
        return "test/get_user_list_page";
    }
}
