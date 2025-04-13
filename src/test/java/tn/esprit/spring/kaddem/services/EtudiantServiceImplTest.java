package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;
    private Contrat contrat;
    private Equipe equipe;
    private Departement departement;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Test");
        etudiant.setPrenomE("User");

        contrat = new Contrat();
        contrat.setIdContrat(1);

        equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setEtudiants(new HashSet<>()); // Initialisation explicite

        departement = new Departement();
        departement.setIdDepart(1);
    }
    @Test
    void retrieveAllEtudiants_ShouldReturnListOfEtudiants() {
        // Arrange
        List<Etudiant> expectedEtudiants = Arrays.asList(etudiant);
        when(etudiantRepository.findAll()).thenReturn(expectedEtudiants);

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(etudiant, result.get(0));
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void addEtudiant_ShouldSaveAndReturnEtudiant() {
        // Arrange
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("Test", result.getNomE());
        assertEquals("User", result.getPrenomE());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void updateEtudiant_ShouldUpdateAndReturnEtudiant() {
        // Arrange
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.updateEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("Test", result.getNomE());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void retrieveEtudiant_WhenExists_ShouldReturnEtudiant() {
        // Arrange
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(1);
    }

    @Test
    void retrieveEtudiant_WhenNotExists_ShouldThrowException() {
        // Arrange
        when(etudiantRepository.findById(2)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            etudiantService.retrieveEtudiant(2);
        });
        verify(etudiantRepository, times(1)).findById(2);
    }

    @Test
    void removeEtudiant_ShouldDeleteEtudiant() {
        // Arrange
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        doNothing().when(etudiantRepository).delete(etudiant);

        // Act
        etudiantService.removeEtudiant(1);

        // Assert
        verify(etudiantRepository, times(1)).findById(1);
        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    void assignEtudiantToDepartement_ShouldSetDepartement() {
        // Arrange
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        etudiantService.assignEtudiantToDepartement(1, 1);

        // Assert
        assertNotNull(etudiant.getDepartement());
        assertEquals(1, etudiant.getDepartement().getIdDepart());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void addAndAssignEtudiantToEquipeAndContract_ShouldCreateRelations() {
        // Arrange
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);

        // Assert
        assertNotNull(result);
        verify(contratRepository, times(1)).findById(1);
        verify(equipeRepository, times(1)).findById(1);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void getEtudiantsByDepartement_ShouldReturnFilteredList() {
        // Arrange
        List<Etudiant> expectedEtudiants = Arrays.asList(etudiant);
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(1)).thenReturn(expectedEtudiants);

        // Act
        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(1);
    }
}
