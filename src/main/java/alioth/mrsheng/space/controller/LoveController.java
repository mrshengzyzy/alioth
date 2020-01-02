package alioth.mrsheng.space.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoveController {

    @RequestMapping(value = {"/love"})
    public String love() {
        return "love";
    }
}
