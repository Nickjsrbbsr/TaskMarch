package org.mandal.chat.springbootwebsocketapplication001.repository;

import org.mandal.chat.springbootwebsocketapplication001.model.GreetingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MyRepository extends CrudRepository<GreetingDTO, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE GreetingDTO e SET e.status = 'APPROVED' WHERE e.id = :id")
    void updateStatusToApprovedById(Integer id);
}
