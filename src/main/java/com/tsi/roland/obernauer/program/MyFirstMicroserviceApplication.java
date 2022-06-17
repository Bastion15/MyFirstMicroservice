package com.tsi.roland.obernauer.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@CrossOrigin(origins = "*")
@SpringBootApplication
@RestController
@RequestMapping("/home")

public class MyFirstMicroserviceApplication {

	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CityRepository cityRepository;



	public static void main(String[] args) {
		SpringApplication.run(MyFirstMicroserviceApplication.class, args);
	}

	public MyFirstMicroserviceApplication(ActorRepository actorRepository, CountryRepository countryRepository, CityRepository cityRepository) {
		this.actorRepository = actorRepository;
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
	}

	@GetMapping("/allActors")
	public @ResponseBody
	Iterable<Actor>getAllActors(){
		return actorRepository.findAll();
	}

	@GetMapping("/actor/{actor_id}")
	public @ResponseBody
	Optional<Actor> getActor_id(@PathVariable("actor_id")int actor_id)
	{
		return actorRepository.findById(actor_id);
	}


	@PostMapping("/addNewActor")
	String addNewActor(@RequestParam String first_name, @RequestParam String last_name) {
		Actor a = new Actor (first_name, last_name);
		actorRepository.save(a);
		return "Actor successfully added to the database";
	}


	@PutMapping("/actor/{id}")
	@ResponseBody
	public ResponseEntity<Actor> updateActor(@RequestParam Integer id, @RequestParam String first_name, @RequestParam String last_name){
		Actor updateActor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor does not exit with id: " + id));
		updateActor.setFirst_name(first_name);
		updateActor.setLast_name(last_name);
		actorRepository.save(updateActor);
		return ResponseEntity.ok(updateActor);
	}

	@DeleteMapping("/actor/{id}")
	public ResponseEntity<Actor> deleteActor(@RequestParam Integer id){
		Actor deleteActor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor does not exit with id: " + id));
		actorRepository.deleteById(id);
		return ResponseEntity.ok(deleteActor);

	}

	///////////////////////////////////////////////// Country /////////////////////////////////////////////////////////

	@GetMapping("/allCountries")
	public @ResponseBody
	Iterable<Country>getAllCountries(){
		return countryRepository.findAll();
	}

	@GetMapping("/country/{country_id}")
	public @ResponseBody
	Optional<Country> getCountry_id(@PathVariable("country_id")int country_id)
	{
		return countryRepository.findById(country_id);
	}

	@PostMapping("/addNewCountry")
	String addNewCountry(@RequestParam String country) {
		Country a = new Country (country);
		countryRepository.save(a);
		return "Country successfully added to the database";
	}

	@PutMapping("/country/{id}")
	@ResponseBody
	public String updateCountry(@RequestParam String country){
		return "Country was successfully added to the database";
	}

	@DeleteMapping("/country/{id}")
	public String deleteCountry(@PathVariable("id") int country_id){;
		countryRepository.deleteById(country_id);
		return "Country successfully removed from the database";
	}


	/////////////////////////////////////////////////// CITY /////////////////////////////////////////////////////////
	@GetMapping("/allCities")
	public @ResponseBody
	Iterable<City>getAllCities(){
		return cityRepository.findAll();
	}

	@GetMapping("/city/{city_id}")
	public @ResponseBody
	Optional<City> getCity_id(@PathVariable("city_id")int city_id)
	{
		return cityRepository.findById(city_id);
	}

	@PostMapping("/addNewCity")
	String addNewCity(@RequestParam String city) {
		City a = new City (city);
		cityRepository.save(a);
		return "City successfully added to the database";
	}

	@PutMapping("/city/{id}")
	@ResponseBody
	public String updateCity(@RequestParam String city){
		return "City was successfully added to the database";
	}

	@DeleteMapping("/city/{id}")
	public String deleteCity(@PathVariable("id") int city_id){
        cityRepository.deleteById(city_id);
        return "City successfully deleted from the database";
	}

}



















