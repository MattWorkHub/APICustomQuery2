package apicustomquery.controllers;

import apicustomquery.entities.Flight;
import apicustomquery.entities.enums.StatusEnum;
import apicustomquery.repositories.FlightRepository;
import apicustomquery.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;


    @GetMapping("/provision")
    public ResponseEntity<List<Flight>>provFlights(){
        List<Flight> flights = flightService.provFlights();
        return ResponseEntity.ok().body(flights);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Flight>> selectAllFlights(){
        List<Flight> allFlights = flightService.getAllFlights();
        return ResponseEntity.ok().body(allFlights);
    }

    @GetMapping("/getflight")
    public ResponseEntity<Optional<Flight>> getFlight(@RequestParam Long id){
        Optional<Flight> flightOptional  = flightService.getFlight(id);
        return ResponseEntity.ok().body(flightOptional);
    }



    @GetMapping("/pagination")
    public Page<Flight> getAllFlights(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }


    @GetMapping("/ontime")
    public ResponseEntity <List<Flight>> getOnTime() {
        List<Flight> onTimeFlights = flightRepository.findByStatusEnum(StatusEnum.ONTIME);
        return ResponseEntity.ok(onTimeFlights);
    }

    @GetMapping("/delayed")
    public ResponseEntity <List<Flight>> getDelayed() {
        List<Flight> onTimeFlights = flightRepository.findByStatusEnum(StatusEnum.DELAYED);
        return ResponseEntity.ok(onTimeFlights);
    }

    @GetMapping("/cancelled")
    public ResponseEntity <List<Flight>> getCancelled() {
        List<Flight> onTimeFlights = flightRepository.findByStatusEnum(StatusEnum.CANCELLED);
        return ResponseEntity.ok(onTimeFlights);
    }



}