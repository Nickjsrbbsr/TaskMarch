package nihar.controller;


import nihar.repository.GreetingRepository;
import nihar.entity.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {
    @Autowired
    GreetingRepository greetingRepository;


    @GetMapping("/fetchStatus")
//    @ResponseBody
    public String fetchStatusOfUserFromTheDatabase(@RequestParam("id") Integer id) {
        try {
            System.out.println("Oye Hoye "+id);
            Greeting greeting = greetingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Greeting not found for ID: " + id));
            System.out.println(greeting);
            System.out.println("status is " + greeting.getStatus());
            System.out.println(greeting.getStatus());
//            // Create an object to serialize
//            MyObject myObject = new MyObject("John", greeting.getStatus());

            return greeting.getStatus();



        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
//         return "10";
    }
}
