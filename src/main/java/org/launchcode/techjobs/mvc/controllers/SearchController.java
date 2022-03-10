package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @RequestMapping(value= "results", method = RequestMethod.POST)
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType){
        ArrayList<Job> jobs;
        if(searchTerm.toLowerCase().equals("all") || searchTerm.toLowerCase().equals("")){
            jobs = JobData.findAll();
        }else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//            model.addAttribute("title", "Jobs with " + searchType + ":" + searchTerm);
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ":" + searchTerm);

        return "search";
    }
}
