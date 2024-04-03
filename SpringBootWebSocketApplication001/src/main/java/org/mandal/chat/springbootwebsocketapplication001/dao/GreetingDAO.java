package org.mandal.chat.springbootwebsocketapplication001.dao;

import org.mandal.chat.springbootwebsocketapplication001.model.GreetingDTO;
import org.mandal.chat.springbootwebsocketapplication001.repository.GreetingRepository;
import org.mandal.chat.springbootwebsocketapplication001.util.DatabaseUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Service
public class GreetingDAO {
    @Autowired
    GreetingRepository greetingRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void updateStatusToApprovedById(int id, String name,String status) {
        System.out.println("greeting DAO1 ID is:"+id+" and status is :"+status +" and name is :"+name);
        System.out.println(greetingRepository);
        GreetingDTO dto = new GreetingDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setStatus("sagar");
        greetingRepository.save(dto) ;

    }
}

