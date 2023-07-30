package alibek.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(path = "/view_controller")
public class ViewController {

    @GetMapping(path = "/fourth_resource")
    public String fourthResource(Model model) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Moscow", 12_400);
        map.put("Astana", 1_300);
        map.put("London", 7_700);
        map.put("Kiev", 5_400);
        model.addAttribute("map", map);
        int count = 0;
        int sum = 0;
        Set<String> key = map.keySet();
        for (String s : key) {
            count++;
            sum += map.get(s);
        }
        sum = sum / count;
        model.addAttribute("sum", sum);
        return "test/fourth_resource_page";
    }


}
