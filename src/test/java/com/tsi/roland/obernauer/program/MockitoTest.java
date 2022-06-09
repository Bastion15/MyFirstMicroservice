package com.tsi.roland.obernauer.program;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    private MyFirstMicroserviceApplication microServiceApp;

    @Mock
    private ActorRepository actorRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        microServiceApp = new MyFirstMicroserviceApplication(actorRepository, countryRepository, cityRepository);
    }

    @Test
    public void getAllActors() {
        microServiceApp.getAllActors();
        verify(actorRepository).findAll();
    }

    @Test
    public void addActor() {
        Actor newActor = new Actor("Mock", "Actor");
        String expected = "Actor successfully added to the database";
        String actual = microServiceApp.addNewActor(newActor.getFirst_name(), newActor.getLast_name());

        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);

        verify(actorRepository).save(actorArgumentCaptor.capture());

        actorArgumentCaptor.getValue();

        Assertions.assertEquals(expected, actual, "Adding actor to the database was unsuccessful");
    }

    @Test
    public void updateActor() {
        final Actor actor = new Actor("Mock", "Actor");
        Optional<Actor> optionalActor = Optional.of(actor);
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        Actor update = new Actor("Test", "Actor");
        String actual = microServiceApp.updateActor(1, update);
        String expected = "Test Actor";
        Assertions.assertEquals(actual, expected, "Updating actor was not successful");
    }

    @Test
    public void deleteActor() {
        final Actor actor = new Actor("Mock", "Actor");
        Optional<Actor> optionalActor = Optional.of(actor);
        Mockito.when(actorRepository.findById(1)).thenReturn(optionalActor);
        String actual = microServiceApp.deleteActor(1);
        Mockito.verify(actorRepository).delete(actor);
        Assertions.assertEquals("Actor successfully removed from the database", actual, "Actor was not removed from the database");
    }


/////////////////////////////////////////////////////////// Country ////////////////////////////////////////////////////////////////

    @Test
    public void getAllCities() {
        microServiceApp.getAllCities();
        verify(cityRepository).findAll();
    }


}






















































