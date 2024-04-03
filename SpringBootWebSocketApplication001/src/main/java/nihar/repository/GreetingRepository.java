package nihar.repository;


import nihar.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface GreetingRepository extends JpaRepository<Greeting, Integer> {
//    void updateStatusById(Integer id);
    @Modifying
    @Query("UPDATE Greeting e SET e.status = 'approved' WHERE e.id = :id")
    void updateStatusToApprovedById(Integer id);
//    public void save();

//    public void save(int i, String name, String password);
}

