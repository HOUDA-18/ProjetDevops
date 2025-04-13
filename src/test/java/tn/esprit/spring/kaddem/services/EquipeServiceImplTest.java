package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class EquipeServiceImplTest {


    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    Equipe equipe1;
    Equipe equipe2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        equipe1 = new Equipe();
        equipe1.setIdEquipe(1);
        equipe1.setNomEquipe("Equipe 1");
        equipe1.setNiveau(Niveau.JUNIOR);

        equipe2 = new Equipe();
        equipe2.setIdEquipe(2);
        equipe2.setNomEquipe("Equipe 2");
        equipe2.setNiveau(Niveau.SENIOR);
    }

    @Test
     void testRetrieveAllEquipes() {
        List<Equipe> equipes = Arrays.asList(equipe1, equipe2);
        when(equipeRepository.findAll()).thenReturn(equipes);

        List<Equipe> result = equipeService.retrieveAllEquipes();
        assertEquals(2, result.size());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
     void testAddEquipe() {
        when(equipeRepository.save(equipe1)).thenReturn(equipe1);

        Equipe result = equipeService.addEquipe(equipe1);
        assertNotNull(result);
        assertEquals("Equipe 1", result.getNomEquipe());
        verify(equipeRepository, times(1)).save(equipe1);
    }

    @Test
     void testUpdateEquipe() {
        equipe1.setNomEquipe("Equipe 1 Modifiée");
        when(equipeRepository.save(equipe1)).thenReturn(equipe1);

        Equipe result = equipeService.updateEquipe(equipe1);
        assertEquals("Equipe 1 Modifiée", result.getNomEquipe());
        verify(equipeRepository).save(equipe1);
    }

    @Test
     void testRetrieveEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe1));

        Equipe result = equipeService.retrieveEquipe(1);
        assertNotNull(result);
        assertEquals("Equipe 1", result.getNomEquipe());
        verify(equipeRepository).findById(1);
    }

    @Test
     void testDeleteEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe1));

        equipeService.deleteEquipe(1);
        verify(equipeRepository).delete(equipe1);
    }
}
