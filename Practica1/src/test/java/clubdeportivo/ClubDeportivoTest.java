package clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Executable;

class ClubDeportivoTest {

    private ClubDeportivo club;
    private Grupo grupo;

    @BeforeEach
    void setUp() throws ClubException {
        club = new ClubDeportivo("Club Test");
        grupo = new Grupo("Actividad 1", "Entrenamiento", 10, 5, 10.0);
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

        // Act & Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

}