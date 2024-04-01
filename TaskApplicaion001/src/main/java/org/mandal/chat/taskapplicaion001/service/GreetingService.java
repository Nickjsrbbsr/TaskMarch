package org.mandal.chat.taskapplicaion001.service;

import org.mandal.chat.taskapplicaion001.entity.Greeting;
import org.mandal.chat.taskapplicaion001.model.RequestStatus;
import org.mandal.chat.taskapplicaion001.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GreetingService {
    @Autowired
    GreetingRepository greetingRepository;
    public void saveData(String id, String name, String status) {
        Greeting greeting = new Greeting();
        greeting.setId(Integer.parseInt(id));
        greeting.setName(name);
        greeting.setStatus(status);
        greetingRepository.save(greeting);
        System.out.println("Greeting obj :"+greeting);
        System.out.println("Data saved successfully in db");
    }
    @Transactional
    public void updateStatusToApproved(Integer id) {
        System.out.println("In service layer to fetch Data from the database .To fetch greetings ->");
        greetingRepository.updateStatusToApprovedById(id);
    }

    public void updateStatusToApprovedById(Integer id) {
    }
}
