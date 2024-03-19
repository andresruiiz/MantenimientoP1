/*
 * @author 1: Nicoló Melley
 * @author 2: Andrés Ruiz Sánchez
 */

package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

class ClubDeportivoTest {

    private ClubDeportivo club;

    @BeforeEach
    void setUp() throws ClubException {
        club = new ClubDeportivo("Club Test");
    }


    @Test
    @DisplayName("Comprobar que el club se crea correctamente")
    void testCrearClub() throws ClubException {
        // Arrange
        ClubDeportivo clubTest = null;
        String nombre = "Club Test";
        String ExpectedToString = "Club Test --> [  ]";

        // Act
        clubTest = new ClubDeportivo(nombre);

        // Assert
        assertEquals(ExpectedToString, clubTest.toString());
    }

    @Test
    @DisplayName("Comprobar que al introducir un numero incorrecto de grupos se lanza una excepcion")
    void testCrearClubConNumeroGruposIncorrecto() {
        // Arrange
        String nombre = "Club Test";
        int n = 0;

        // Act & Assert
        assertThrows(ClubException.class, () -> new ClubDeportivo(nombre, n));
    }
    
    @Test
    @DisplayName("Añadir una actividad al club")
    void testAnyadirActividad() throws ClubException {
        // Arrange
        String ExpectedToString = "Club Test --> [ (Actividad 1 - Entrenamiento - 10.0 euros - P:10 - M:5) ]";

        // Act
        club.anyadirActividad(new String[]{"Actividad 1", "Entrenamiento", "10", "5", "10.0"});

        // Assert
        assertEquals(ExpectedToString, club.toString());
    }

    @Test
    @DisplayName("Añadir una actividad al club con datos insuficientes")
    void testAnyadirActividadConDatosInsuficientes() {
        // Arrange
        String[] datos = new String[]{"Actividad 1", "Entrenamiento", "10", "5"};

        // Act & Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Añadir una actividad al club con grupo nulo")
    void testAnyadirActividadConGrupoNulo() {
        // Arrange
        Grupo g = null;

        // Act & Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(g));
    }

    @Test
    @DisplayName("Añadir una actividad al club con formato de numero incorrecto")
    void testAnyadirActividadConFormatoNumeroIncorrecto() {
        // Arrange
        
        String[] datos = new String[]{"Actividad 1", "Entrenamiento", "10.3", "5.2", "10.0"};
        Class <ClubException> excpected = ClubException.class;
        // Act 
        
        Executable executable = () -> club.anyadirActividad(datos);
        // Assert
        assertThrows(excpected, executable);
    }

    @Test
    @DisplayName("Añadir un grupo ya presente a un club")
    void testAnyadirGrupoYaPresente() throws ClubException {
        // Arrange
        String[] datos = new String[]{"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String[] datos2 = new String[]{"Actividad 2", "Entrenamiento", "15", "10", "10.0"};
        String ExpectedToString = "Club Test --> [ (Actividad 1 - Entrenamiento - 10.0 euros - P:10 - M:5), (Actividad 2 - Entrenamiento - 10.0 euros - P:15 - M:10) ]";
        
        // Act
        club.anyadirActividad(datos);
        club.anyadirActividad(datos2);
        club.anyadirActividad(datos);

        // Assert
        assertEquals(ExpectedToString, club.toString());
    }

    @Test
    @DisplayName("Las plazas libres de club sin actividades es 0")
    void testPlazasLibresSinActividades() {
        // Arrange
        String actividad = "Actividad 1";
        int ExpectedPlazasLibres = 0;

        // Act
        int plazasLibres = club.plazasLibres(actividad);

        // Assert
        assertEquals(ExpectedPlazasLibres, plazasLibres);
    }

    @Test
    @DisplayName("Las plazas libres de una actividad en un club")
    void testPlazasLibresDeActividad() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String actividad = "Entrenamiento";
        int ExpectedPlazasLibres = 5;

        // Act
        club.anyadirActividad(datos);
        int plazasLibres = club.plazasLibres(actividad);

        // Assert
        assertEquals(ExpectedPlazasLibres, plazasLibres);
    }

    @Test
    @DisplayName("Los ingresos obtenidos se calculan correctamente")
    void testIngresos() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        double ExpectedIngresos = 50.0;

        // Act
        club.anyadirActividad(datos);
        double ingresos = club.ingresos();

        // Assert
        assertEquals(ExpectedIngresos, ingresos);
    }

    @Test
    @DisplayName("Los ingresos obtenidos se calculan correctamente cuando hay mas de una actividad")
    void testIngresosConMasDeUnaActividad() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String[] datos2 = {"Actividad 2", "Entrenamiento", "15", "10", "10.0"};
        double ExpectedIngresos = 150.0;

        // Act
        club.anyadirActividad(datos);
        club.anyadirActividad(datos2);
        double ingresos = club.ingresos();

        // Assert
        assertEquals(ExpectedIngresos, ingresos);
    }

    @Test
    @DisplayName("Matricular personas en una actividad")
    void testMatricular() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String actividad = "Entrenamiento";
        int npersonas = 3;
        int ExpectedPlazasLibres = 2;

        // Act
        club.anyadirActividad(datos);
        club.matricular(actividad, npersonas);
        int plazasLibres = club.plazasLibres(actividad);

        // Assert
        assertEquals(ExpectedPlazasLibres, plazasLibres);
    }

    @Test
    @DisplayName("Matricular personas en una actividad en la que no cabe")
    void testMatricularSinPlazas() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String actividad = "Entrenamiento";
        int npersonas = 10;

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
            club.matricular(actividad, npersonas);
        });
    }

    @Test
    @DisplayName("Matricular personas en la ultima plaza de una actividad")
    void testMatricularUltimaPlaza() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String actividad = "Entrenamiento";
        int npersonas = 5;
        int ExpectedPlazasLibres = 0;

        // Act
        club.anyadirActividad(datos);
        club.matricular(actividad, npersonas);
        int plazasLibres = club.plazasLibres(actividad);

        // Assert
        assertEquals(ExpectedPlazasLibres, plazasLibres);
    }

    @Test
    @DisplayName("Matricular un numero negativo de personas en una actividad")
    void testMatricularNumeroNegativo() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String actividad = "Entrenamiento";
        int npersonas = -5;

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
            club.matricular(actividad, npersonas);
        });
    }

    @Test
    @DisplayName("Matricular personas en una actividad que no existe")
    void testMatricularActividadInexistente() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String actividad = "Bailar";
        int npersonas = 5;

        // Act & Assert
        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
            club.matricular(actividad, npersonas);
        });
    }
    
    @Test
    @DisplayName("Matricular personas en una actividad con grupo nulo")
    void testMatricularActividadConGrupoNulo() {
        // Arrange
        String actividad = "Bailar";
        int npersonas = 5;

        // Act & Assert
        assertThrows(ClubException.class, () -> club.matricular(actividad, npersonas));
    }

    @Test
    @DisplayName("Matricular personas en una actividad sin afectar a otra actividad")
    void testMatricularSinAfectarOtraActividad() throws ClubException {
        // Arrange
        String[] datos1 = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String[] datos2 = {"Actividad 2", "Yoga", "15", "10", "10.0"};
        String actividadMatriculada = "Entrenamiento";
        int npersonas = 3;
        int ExpectedPlazasLibresOtraActividad = 5;

        // Act
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);
        club.matricular(actividadMatriculada, npersonas);
        int plazasLibresOtraActividad = club.plazasLibres("Yoga");

        // Assert
        assertEquals(ExpectedPlazasLibresOtraActividad, plazasLibresOtraActividad);
    }

    @Test
    @DisplayName("Matricular personas hasta llenar todas las plazas disponibles")
    void testMatricularHastaLlenarPlazas() throws ClubException {
        // Arrange
        String[] datos = {"Actividad 1", "Entrenamiento", "10", "5", "10.0"};
        String actividad = "Entrenamiento";
        int npersonas = 5;
        int ExpectedPlazasLibres = 0;

        // Act
        club.anyadirActividad(datos);
        club.matricular(actividad, npersonas);
        int plazasLibres = club.plazasLibres(actividad);

        // Assert
        assertEquals(ExpectedPlazasLibres, plazasLibres);
    }

    @Test
    @DisplayName("Matricular personas en múltiples grupos de una actividad")
    void testMatricularMultiplesGrupos() throws ClubException {
        // Arrange
        String[] datos1 = {"Actividad 1", "Entrenamiento", "5", "2", "10.0"};
        String[] datos2 = {"Actividad 2", "Entrenamiento", "5", "2", "10.0"};
        String actividad = "Entrenamiento";
        int npersonas = 3;
        int ExpectedPlazasLibres = 3;

        // Act
        club.anyadirActividad(datos1);
        club.anyadirActividad(datos2);
        club.matricular(actividad, npersonas);
        int plazasLibres = club.plazasLibres(actividad);

        // Assert
        assertEquals(ExpectedPlazasLibres, plazasLibres);
    }

}