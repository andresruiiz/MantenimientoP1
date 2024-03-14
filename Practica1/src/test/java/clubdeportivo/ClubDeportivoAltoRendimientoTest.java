package clubdeportivo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClubDeportivoAltoRendimientoTest {
    ClubDeportivoAltoRendimiento club;

    @BeforeEach
    void setup() throws ClubException {
        club = new ClubDeportivoAltoRendimiento("Club", 10, 10.0);
    }

    @Test
    @DisplayName("Comprobar que el club se crea correctamente sin tamaño")
    void testCrearClubSinTam() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento clubPrueba = null;
        String nombre = "Club";
        int ngrupos = 10;
        double incremento = 10.0;
        String expectedToString = "Club --> [  ]";

        // Act
        clubPrueba = new ClubDeportivoAltoRendimiento(nombre, ngrupos, incremento);

        // Assert+
        assertEquals(expectedToString, clubPrueba.toString());

    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con maximo negativo")
    void testCrearClubConMaximoNegativo() {
        // Arrange
        String nombre = "Club";
        int tam = -10;
        double incremento = 10.0;

        // Act y Assert
        assertThrows(ClubException.class, () ->new ClubDeportivoAltoRendimiento(nombre, tam, incremento));
    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con incremento negativo")
    void testCrearClubConIncrementoNegativo() {
        // Arrange
        String nombre = "Club";
        int tam = 10;
        double incremento = -10.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento(nombre, tam, incremento));
    }

    @Test
    @DisplayName("Comprobar que el club se crea correctamente con tamaño")
    void testCrearClubConTam() throws ClubException {
        // Arrange
        ClubDeportivoAltoRendimiento clubPrueba = null;
        String nombre = "Club";
        int tam = 10;
        int ngrupos = 10;
        double incremento = 10.0;
        String expectedToString = "Club --> [  ]";

        // Act
        clubPrueba = new ClubDeportivoAltoRendimiento(nombre, tam, ngrupos, incremento);

        // Assert
        assertEquals(expectedToString, clubPrueba.toString());
        assertNotNull(clubPrueba);
    }

    @Test
    @DisplayName("Comprobar que el club lanza excepción con tamaño negativo")
    void testCrearClubConTamNegativo() {
        // Arrange
        String nombre = "Club";
        int tam = -10;
        int ngrupos = 10;
        double incremento = 10.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento(nombre, tam, ngrupos, incremento));
    }

    @Test
    @DisplayName("Comprobar que el club lanza excepción con maximo negativo")
    void testCrearClubConMaximoNegativoConTam() {
        // Arrange
        String nombre = "Club";
        int tam = 10;
        int ngrupos = -10;
        double incremento = 10.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento(nombre, tam, ngrupos, incremento));
    }

    @Test
    @DisplayName("Comprobar que el club lanza excepción con incremento negativo")
    void testConstructorConIncrementoNegativoConTam() {
        // Arrange
        String nombre = "Club";
        int tam = 10;
        int ngrupos = 10;
        double incremento = -10.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new ClubDeportivoAltoRendimiento(nombre, tam, ngrupos, incremento));
    }

    @Test
    @DisplayName("Comprobar que se añade una actividad correctamente")
    void testAnyadirActividad() throws ClubException {
        // Arrange
        String[] datos = {"codigo", "actividad", "10", "5", "10.0"};
        String expectedToString = "Club --> [ (codigo - actividad - 10.0 euros - P:10 - M:5) ]";

        // Act
        club.anyadirActividad(datos);

        // Assert
        assertEquals(expectedToString, club.toString());
    }

    @Test
    @DisplayName("Comprobar que se añade una actividad correctamente con plazas mayores que maximo")
    void testAnyadirActividadConPlazasMayoresQueMaximo() throws ClubException {
        // Arrange
        String[] datos = {"codigo", "actividad", "20", "5", "10.0"};
        String expectedToString = "Club --> [ (codigo - actividad - 10.0 euros - P:10 - M:5) ]";

        // Act
        club.anyadirActividad(datos);

        // Assert
        assertEquals(expectedToString, club.toString());
    }

    @Test
    @DisplayName("Comprobar que se lanza excepción al añadir actividad con datos insuficientes")
    void testAnyadirActividadConDatosInsuficientes() {
        // Arrange
        String[] datos = {"codigo", "actividad", "10", "5"};

        // Act y Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }

    @Test
    @DisplayName("Comprobar que se lanza excepción al añadir actividad con formato de número incorrecto")
    void testAnyadirActividadConFormatoNumeroIncorrecto() {
        // Arrange
        String[] datos = {"codigo", "actividad", "10", "5", "no es un número"};

        // Act y Assert
        assertThrows(ClubException.class, () -> club.anyadirActividad(datos));
    }
    
    @Test
    @DisplayName("Comprobar ingresos con incremento")
    void testIngresos() throws ClubException {
        // Arrange
        String[] datos = {"codigo", "actividad", "10", "5", "10.0"};
        double expectedIngresos = 55.0;

        // Act
        club.anyadirActividad(datos);

        // Assert
        assertEquals(expectedIngresos, club.ingresos());
    }
    
    

}