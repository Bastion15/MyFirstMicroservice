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
    public void getAllActors()
    {
        microServiceApp.getAllActors();
        verify(actorRepository).findAll();
    }


}