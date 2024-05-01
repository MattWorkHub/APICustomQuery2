package apicustomquery.services;

import apicustomquery.entities.Flight;
import apicustomquery.entities.enums.StatusEnum;
import apicustomquery.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;
    Random random = new Random();
    private StatusEnum randomStatus() {
        String[] statuses = {"ONTIME", "DELAYED", "CANCELLED"};
        return StatusEnum.valueOf(statuses[random.nextInt(statuses.length)]);
    }

    public List<Flight> provFlights() {
        for (int i = 0; i < 500; i++) {
            Flight flight = new Flight();
            flight.setDescription("Flight " + i);
            flight.setFromAirport("Airport " + i);
            flight.setToAirport("Airport " + (i + 1));
            flight.setStatusEnum(randomStatus());
            flightRepository.save(flight);
        }
        return flightRepository.findAll();
    }
    public List<Flight> getAllFlights(){
        List<Flight> flights = flightRepository.findAll();
        return flights;
    }

    public Optional<Flight> getFlight(Long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        return flightOptional;
    }

}