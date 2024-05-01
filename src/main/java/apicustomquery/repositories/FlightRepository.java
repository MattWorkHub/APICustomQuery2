package apicustomquery.repositories;

import apicustomquery.entities.Flight;
import apicustomquery.entities.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {


    List<Flight> findByStatusEnum(StatusEnum statusEnum);



}