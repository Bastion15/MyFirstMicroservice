package com.tsi.roland.obernauer.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



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


	@PostMapping("/addNewActor")
	String addNewActor(@RequestParam String first_name, @RequestParam String last_name) {
		Actor a = new Actor (first_name, last_name);
		actorRepository.save(a);
		return "Actor successfully added to the database";
	}


	@PutMapping("/actor/{id}")
	public String updateActor(@PathVariable(value = "id") int actor_id,
												   @Valid @RequestBody Actor actorDetails) throws ResourceNotFoundException {
		Actor actor = actorRepository.findById(actor_id)
				.orElseThrow(() -> new ResourceNotFoundException("Actor not found for this id :: " + actor_id));

		actor.setLast_name(actorDetails.getLast_name());
		actor.setFirst_name(actorDetails.getFirst_name());
		final Actor updatedActor = actorRepository.save(actor);
		return updatedActor.toString();
	}

	@DeleteMapping("/actor/{id}")
	public String deleteActor(@PathVariable("id") int actor_id)
			throws ResourceNotFoundException {
		Actor actor = actorRepository.findById(actor_id)
				.orElseThrow(() -> new ResourceNotFoundException("Actor not found for this id :: " + actor_id));
		actorRepository.delete(actor);
		return "Actor successfully removed from the database";
	}

	///////////////////////////////////////////////// Country /////////////////////////////////////////////////////////

	@GetMapping("/allCountries")
	public @ResponseBody
	Iterable<Country>getAllCountries(){
		return countryRepository.findAll();
	}

	@PostMapping("/addNewCountry")
	String addNewCountry(@RequestParam String country) {
		Country a = new Country (country);
		countryRepository.save(a);
		return "Country successfully added to the database";
	}

	@PutMapping("/country/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int country_id, @Valid @RequestBody Country countryDetails) throws ResourceNotFoundException {
		Country country = countryRepository.findById(country_id)
				.orElseThrow(() -> new ResourceNotFoundException("Country not found for this id :: " + country_id));

		country.setCountry(countryDetails.getCountry());
		final Country updatedCountry = countryRepository.save(country);
		return ResponseEntity.ok(updatedCountry);
	}

	@DeleteMapping("/country/{id}")
	public String deleteCountry(@PathVariable("id") int country_id)
			throws ResourceNotFoundException {
		Country country = countryRepository.findById(country_id)
				.orElseThrow(() -> new ResourceNotFoundException("Country not found for this id :: " + country_id));
		countryRepository.delete(country);
		return "Removed from database";
	}


	/////////////////////////////////////////////////// CITY /////////////////////////////////////////////////////////
	@GetMapping("/allCities")
	public @ResponseBody
	Iterable<City>getAllCities(){
		return cityRepository.findAll();
	}

	@PostMapping("/addNewCity")
	String addNewCity(@RequestParam String city) {
		City a = new City (city);
		cityRepository.save(a);
		return "City successfully added to the database";
	}

	@PutMapping("/city/{id}")
	public ResponseEntity<City> updateCity(@PathVariable(value = "id") int city_id, @Valid @RequestBody City cityDetails) throws ResourceNotFoundException {
		City city = cityRepository.findById(city_id)
				.orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + city_id));

		city.setCity(cityDetails.getCity());
		final City updatedCity = cityRepository.save(city);
		return ResponseEntity.ok(updatedCity);
	}

	@DeleteMapping("/city/{id}")
	public String deleteCity(@PathVariable("id") int city_id)
			throws ResourceNotFoundException {
		City city = cityRepository.findById(city_id)
				.orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + city_id));
		cityRepository.delete(city);
		return "Removed from database";
	}

}



















