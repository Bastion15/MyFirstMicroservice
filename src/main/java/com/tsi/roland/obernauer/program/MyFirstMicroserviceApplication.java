package com.tsi.roland.obernauer.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*")
@SpringBootApplication
@RestController
@RequestMapping("/home")

public class MyFirstMicroserviceApplication {

	@Autowired
	private ActorRepository actorRepository;



	public static void main(String[] args) {
		SpringApplication.run(MyFirstMicroserviceApplication.class, args);
	}

	public MyFirstMicroserviceApplication(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	@GetMapping("/allActors")
	public @ResponseBody
	Iterable<Actor>getAllActors(){
		return actorRepository.findAll();
	}
	@PostMapping("/addNewActor")
	public @ResponseBody String addNewActor(@RequestParam String first_name, @RequestParam String last_name, @RequestParam int actorId) {
		Actor a = new Actor(first_name, last_name, actorId);
		System.out.println(first_name + " " + last_name + " " + actorId);
		actorRepository.save(a);
		return "successfully added to the database";
	}
	@PutMapping("/actor/{id}")
	public ResponseEntity<Actor> updateActor(@PathVariable(value = "id") int actorId,
												   @Valid @RequestBody Actor actorDetails) throws ResourceNotFoundException {
		Actor actor = actorRepository.findById(actorId)
				.orElseThrow(() -> new ResourceNotFoundException("Actor not found for this id :: " + actorId));

		actor.setLast_name(actorDetails.getLast_name());
		actor.setFirst_name(actorDetails.getFirst_name());
		final Actor updatedActor = actorRepository.save(actor);
		return ResponseEntity.ok(updatedActor);
	}
	@DeleteMapping("/actor/{id}")
	public Map<String, Boolean> deleteActor(@PathVariable(value = "id") int actorId)
			throws ResourceNotFoundException {
		Actor actor = actorRepository.findById(actorId)
				.orElseThrow(() -> new ResourceNotFoundException("Actor not found for this id :: " + actorId));

		actorRepository.delete(actor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
