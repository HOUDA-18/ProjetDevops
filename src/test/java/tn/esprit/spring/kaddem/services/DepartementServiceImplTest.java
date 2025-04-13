package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DepartementServiceImplTest {

    @Mock
    DepartementRepository departementRepository;

    @InjectMocks
    DepartementServiceImpl departementService;

    Departement departement1;
    Departement departement2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        departement1 = new Departement();
        departement1.setIdDepart(1);
        departement1.setNomDepart("Informatique");

        departement2 = new Departement();
        departement2.setIdDepart(2);
        departement2.setNomDepart("Génie Civil");
    }

    @Test
    void testRetrieveAllDepartements() {
        List<Departement> departements = Arrays.asList(departement1, departement2);
        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();
        assertEquals(2, result.size());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void testAddDepartement() {
        when(departementRepository.save(departement1)).thenReturn(departement1);

        Departement result = departementService.addDepartement(departement1);
        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement1);
    }

    @Test
    void testUpdateDepartement() {
        departement1.setNomDepart("Informatique Avancé");
        when(departementRepository.save(departement1)).thenReturn(departement1);

        Departement result = departementService.updateDepartement(departement1);
        assertEquals("Informatique Avancé", result.getNomDepart());
        verify(departementRepository).save(departement1);
    }

    @Test
    void testRetrieveDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));

        Departement result = departementService.retrieveDepartement(1);
        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository).findById(1);
    }

    @Test
    void testDeleteDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));

        departementService.deleteDepartement(1);
        verify(departementRepository).delete(departement1);
    }
}
