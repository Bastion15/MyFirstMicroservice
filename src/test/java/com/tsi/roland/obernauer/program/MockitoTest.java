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
        String actual = microServiceApp.addNewActor("term","terme");
        Assertions.assertEquals("Test Actor",actual, "Updating actor was not successful");
    }

    @Test
    public void deleteActor() {
        final Actor actor = new Actor("Mock", "Actor");
        Mockito.when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        String actual = String.valueOf(microServiceApp.deleteActor(1));
        Mockito.verify(actorRepository).delete(actor);
        Assertions.assertEquals("Actor successfully removed from the database", actual, "Removing actor from the database was unsuccessful");
    }


/////////////////////////////////////////////////////////// Country ////////////////////////////////////////////////////////////////

    @Test
    public void getAllCountries() {
        microServiceApp.getAllCountries();
        verify(countryRepository).findAll();
    }

    @Test
    public void addCountry() {
        Country newCountry = new Country("Boraborabor");
        String expected = "Country successfully added to the database";
        String actual = microServiceApp.addNewCountry(newCountry.getCountry());
        ArgumentCaptor<Country> countryArgumentCaptor = ArgumentCaptor.forClass(Country.class);
        verify(countryRepository).save(countryArgumentCaptor.capture());
        countryArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual, "Adding country to the database was unsuccessful");
    }

    @Test
    public void updateCountry() {
        final Country country = new Country("Mock");
        Optional<Country> optionalCountry = Optional.of(country);
        Mockito.when(countryRepository.findById(1)).thenReturn(optionalCountry);
        Country update = new Country("Test");
        String actual = String.valueOf(microServiceApp.updateCountry("update"));
        Assertions.assertEquals("Test",actual, "Updating Country was not successful");
    }
    @Test
    public void deleteCountry() {
        final Country country = new Country("Robarobarob");
        Optional<Country> optionalCountry = Optional.of(country);
        Mockito.when(countryRepository.findById(1)).thenReturn(optionalCountry);
        String actual = microServiceApp.deleteCountry(1);
        Mockito.verify(countryRepository).delete(country);
        Assertions.assertEquals("Country successfully removed from the database", actual, "Removing country from the database was unsuccessful");
    }

    ////////////////////////////////////////////////////// City ////////////////////////////////////////////////////////

    @Test
    public void getAllCities() {
        microServiceApp.getAllCities();
        verify(cityRepository).findAll();
    }

    @Test
    public void addCity() {
        City newCity = new City("Boraborabor");
        String expected = "City successfully added to the database";
        String actual = microServiceApp.addNewCity(newCity.getCity());
        ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
        verify(cityRepository).save(cityArgumentCaptor.capture());
        cityArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual, "Adding city to the database was unsuccessful");
    }

    @Test
    public void updateCity() {
        final City city = new City("Mock");
        Optional<City> optionalCity = Optional.of(city);
        Mockito.when(cityRepository.findById(1)).thenReturn(optionalCity);
        City update = new City("Test");
        String actual = String.valueOf(microServiceApp.updateCity("Test"));
        Assertions.assertEquals("Test",actual , "Updating City was not successful");
    }
    @Test
    public void deleteCity() {
        final City city = new City("Robarobarob");
        Optional<City> optionalCity = Optional.of(city);
        Mockito.when(cityRepository.findById(1)).thenReturn(optionalCity);
        String actual = microServiceApp.deleteCity(1);
        Mockito.verify(cityRepository).delete(city);
        Assertions.assertEquals("City successfully removed from the database", actual, "Removing city from the database was unsuccessful");
    }

}






















































