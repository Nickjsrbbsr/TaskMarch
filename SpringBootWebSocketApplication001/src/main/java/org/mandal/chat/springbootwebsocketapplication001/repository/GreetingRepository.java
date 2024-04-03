package org.mandal.chat.springbootwebsocketapplication001.repository;

import org.mandal.chat.springbootwebsocketapplication001.model.GreetingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GreetingRepository extends CrudRepository<GreetingDTO, Integer> {



    @Modifying
    @Transactional
    @Query("UPDATE GreetingDTO g SET g.status = 'APPROVED' WHERE g.id = :id")
    void updateStatusToApprovedById(@Param("id") Integer id);

}
