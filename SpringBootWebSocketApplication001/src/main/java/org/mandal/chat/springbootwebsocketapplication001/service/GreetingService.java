package org.mandal.chat.springbootwebsocketapplication001.service;

import org.mandal.chat.springbootwebsocketapplication001.jdbc.JdbcDeleteExample;
import org.mandal.chat.springbootwebsocketapplication001.jdbc.JdbcExample;
import org.mandal.chat.springbootwebsocketapplication001.model.GreetingDTO;
import org.mandal.chat.springbootwebsocketapplication001.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GreetingService {
    @Autowired
    GreetingRepository greetingRepository;

//    public String updateStatusToApproved(String id) {
//
//        return "";
//    }
//    @Transactional
//    public void updateStatusToApproved(String id,String status) {
//        System.out.println("In service layer to update Data of the database .To update status ->");
//        greetingRepository.updateStatusToApprovedById(Integer.parseInt(id));
//        System.out.println("updateStatus call to repository done ");
//
//    }
public void updateStatusToApproved(int id, String name,String status) {
    System.out.println("greeting DAO1 ID is:"+id+" and status is :"+status +" and name is :"+name);
    System.out.println(greetingRepository);
    GreetingDTO dto = new GreetingDTO();
    dto.setId(id);
    dto.setName(name);
    dto.setStatus("sagar");
    greetingRepository.save(dto) ;

}

    public void feedDataToDB(String id, String name, String status) {
        var data = greetingRepository.findById(Integer.parseInt(id));
        if((data.isPresent())){
            greetingRepository.deleteById(Integer.parseInt(id));
        }

        System.out.println("Feed data to db called");
        GreetingDTO dTo = new GreetingDTO();
        dTo.setId(Integer.parseInt(id));
        dTo.setName(name);
        dTo.setStatus(status);

        greetingRepository.save(dTo);
        System.out.println("feeddataToDB Method exceution completed>>>...");
    }

    public void feedDataToDB2(String messageId, String name, String status) {

        JdbcDeleteExample.main(Integer.parseInt(messageId));

        System.out.println("Feed data 2 to db2 called");

        JdbcExample.main(Integer.parseInt(messageId),name,status);
        System.out.println("feeddataToDB2 Method exceution completed>>>...");
    }

}
