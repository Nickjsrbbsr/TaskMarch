package org.mandal.chat.taskapplicaion001.controller;

import org.mandal.chat.taskapplicaion001.entity.Greeting;
import org.mandal.chat.taskapplicaion001.repository.GreetingRepository;
import org.mandal.chat.taskapplicaion001.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@ResponseBody
public class MyController {

    @Autowired
    private GreetingRepository greetingRepository;
    @Autowired
    private GreetingService greetingService;
//    @RequestMapping(method = RequestMethod.GET , value = "/helloNihar")
//    public void getData(){
//
//
//    }
@GetMapping("/helloNihar")
public String handleUserRequest(@RequestParam("userId") String userId,
                                @RequestParam("userName") String userName) {
    // Process the user request here
    // For example, you can save the data to a database

    System.out.println("Received user ID: " + userId);
    System.out.println("Received user name: " + userName);

    // Return the view name or redirect to another page
    return "index"; // Assuming you have a successPage.jsp or successPage.html
}
@PostMapping("/submit")
//@ResponseBody
    public String handleFormSubmission(Model model ,
                                       @RequestParam("id") String id,
                                       @RequestParam("name") String name,
                                       @RequestParam("status") String status){
    greetingService.saveData(id,name,status);
    System.out.println("id: " + id + " , name: " + name+" ,password :"+status);
    model.addAttribute("greetings",greetingRepository.findAll());
    System.out.println("Post Mapping /submit handleFormSubmission method called");
    return "save";

}
    @GetMapping("/fetchData")
//@ResponseBody
    public String hello2(Model model){
        System.out.println("/fetchData called");
        List<Greeting> all = greetingRepository.findAll();
        System.out.println(all);
        model.addAttribute("greetings",greetingRepository.findAll());
        return "index";

    }
    @GetMapping("/form")
//@ResponseBody
    public String hello3(){
//        model.addAttribute("greetings",greetingRepository.findAll());
        return "save";

    }
    @PostMapping("/updateData")
    public void updateDataOfStatusInDatabase(@RequestParam Integer id){
        System.out.println("Post Mapping /updateData is called with id:"+id+" as a data and now gonna fetch the data");
        greetingService.updateStatusToApproved(id);

    }


}
