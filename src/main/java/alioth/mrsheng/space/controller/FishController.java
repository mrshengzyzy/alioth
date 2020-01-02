package alioth.mrsheng.space.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import alioth.mrsheng.space.business.fish.FeedJob;
import alioth.mrsheng.space.business.fish.FishBusiness;
import alioth.mrsheng.space.business.fish.FishFoodStatistics;

import javax.annotation.Resource;

@Controller
public class FishController {

    @Resource
    private FishBusiness fishBusiness;

    @Resource
    private FeedJob feedJob;

    @RequestMapping(value = {"/fish"})
    public String fish(Model model) {
        FishFoodStatistics fish = fishBusiness.queryAll();
        model.addAttribute("fish", fish);
        return "fish";
    }

    @RequestMapping(value = {"/job"})
    @ResponseBody
    public String job() {
        feedJob.doJob();
        return "done";
    }
}
