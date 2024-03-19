/*
 * @author 1: Nicoló Melley
 * @author 2: Andrés Ruiz Sánchez
 */

package clubdeportivo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class GrupoTest {
    Grupo grupo;

    @BeforeEach
    void setup() throws ClubException {
        grupo = new Grupo("Grupo1", "Deporte", 10, 5, 25.0);
    }
    
    @Test
    @DisplayName("Comprobar que el grupo se crea correctamente")
    void creacionCorrecta() throws ClubException {
        // Arrange
        Grupo grupoPrueba = null;
        String codigo = "Grupo1";
        String actividad = "Deporte";
        int nplazas = 10;
        int matriculados = 10;
        double tarifa = 25.0;
        String expectedToString = "(Grupo1 - Deporte - 25.0 euros - P:10 - M:10)";

        // Act
        grupoPrueba = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        // Assert
        assertEquals(expectedToString, grupoPrueba.toString());
    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con plazas negativas")
    void lanzaExcepcionCuandoCreaGrupoConPlazasNegativas() {
        // Arrange
        String codigo = "Grupo1";
        String actividad = "Deporte";
        int nplazas = -10;
        int matriculados = 10;
        double tarifa = 25.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con matriculados negativos")
    void lanzaExcepcionCuandoCreaGrupoConMatriculadosNegativos() {
        // Arrange
        String codigo = "Grupo1";
        String actividad = "Deporte";
        int nplazas = 10;
        int matriculados = -10;
        double tarifa = 25.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con tarifa negativa")
    void lanzaExcepcionCuandoCreaGrupoConTarifaNegativa() {
        // Arrange
        String codigo = "Grupo1";
        String actividad = "Deporte";
        int nplazas = 10;
        int matriculados = 10;
        double tarifa = -25.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con matriculados mayores que plazas")
    void lanzaExcepcionCuandoCreaGrupoConMatriculadosMayoresQuePlazas() {
        // Arrange
        String codigo = "Grupo1";
        String actividad = "Deporte";
        int nplazas = 10;
        int matriculados = 20;
        double tarifa = 25.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con código nulo")
    void lanzaExcepcionCuandoCreaGrupoConCodigoNulo() {
        // Arrange
        String codigo = null;
        String actividad = "Deporte";
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 25.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprobar que el constructor lanza excepción con actividad nula")
    void lanzaExcepcionCuandoCreaGrupoConActividadNula() {
        // Arrange
        String codigo = "Grupo1";
        String actividad = null;
        int nplazas = 10;
        int matriculados = 5;
        double tarifa = 25.0;

        // Act y Assert
        assertThrows(ClubException.class, () -> new Grupo(codigo, actividad, nplazas, matriculados, tarifa));
    }

    @Test
    @DisplayName("Comprobar que se actualizan las plazas correctamente")
    void actualizaPlazasCorrectamente() throws ClubException {
        // Arrange
        int nplazas = 20;
        String expectedToString = "(Grupo1 - Deporte - 25.0 euros - P:20 - M:5)";

        // Act
        grupo.actualizarPlazas(nplazas);

        // Assert
        assertEquals(expectedToString, grupo.toString());
    }

    @Test
    @DisplayName("Comprobar que el método actualizarPlazas lanza excepción con plazas negativas")
    void lanzaExcepcionActualizandoConPlazasNegativas() {
        // Arrange
        int nplazas = -20;

        // Act y Assert
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(nplazas));
    }
    
    @Test
    @DisplayName("Comprobar que el método actualizarPlazas lanza excepción con plazas menores que matriculados")
    void lanzaExcepcionActualizandoPlazasConPlazasMenoresQueMatriculados() {
        // Arrange
        int nplazas = 4;

        // Act y Assert
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(nplazas));
    }

    @Test
    @DisplayName("Comprobar que se matriculan correctamente")   
    void matricularCorrectamente() throws ClubException {
        // Arrange
        int n = 5;
        String expectedToString = "(Grupo1 - Deporte - 25.0 euros - P:10 - M:10)";

        // Act
        grupo.matricular(n);

        // Assert
        assertEquals(expectedToString, grupo.toString());
    }

    @Test
    @DisplayName("Comprobar que el método matricular lanza excepción con matriculados negativos")
    void lanzaExcepcionMatriculandoConMatriculadosNegativos() {
        // Arrange
        int n = -5;

        // Act y Assert
        assertThrows(ClubException.class, () -> grupo.matricular(n));
    }

    @Test
    @DisplayName("Comprobar que el método matricular lanza excepción con matriculados mayores que plazas")
    void lanzaExcepcionMatriculandoConMatriculadosMayoresQuePlazas() {
        // Arrange
        int n = 20;

        // Act y Assert
        assertThrows(ClubException.class, () -> grupo.matricular(n));
    }

    @Test
    @DisplayName("Comprobar que se obtiene el número de plazas libres correctamente")
    void plazasLibresCorrectas() {
        // Arrange
        int expected = 5;

        // Act
        int result = grupo.plazasLibres();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que se obtiene el número de plazas libres correctamente con matriculados")
    void plazasLibresCorrectasConMatriculados() throws ClubException {
        // Arrange
        grupo.matricular(5);
        int expected = 0;

        // Act
        int result = grupo.plazasLibres();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que se obtiene el código correctamente")
    void codigoCorrecto() {
        // Arrange
        String expected = "Grupo1";

        // Act
        String result = grupo.getCodigo();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que se obtiene la actividad correctamente")
    void actividadCorrecta() {
        // Arrange
        String expected = "Deporte";

        // Act
        String result = grupo.getActividad();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que se obtienen las plazas correctamente")
    void numeroDePlazasCorrectas() {
        // Arrange
        int expected = 10;

        // Act
        int result = grupo.getPlazas();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que se obtienen los matriculados correctamente")
    void numeroDeMatriculadosCorrecto() {
        // Arrange
        int expected = 5;

        // Act
        int result = grupo.getMatriculados();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que se obtiene la tarifa correctamente")
    void tarifaCorrecta() {
        // Arrange
        double expected = 25.0;

        // Act
        double result = grupo.getTarifa();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que se obtiene el toString correctamente")
    void toStringSeDevuelveConFormatoCorrecto() {
        // Arrange
        String expected = "(Grupo1 - Deporte - 25.0 euros - P:10 - M:5)";
        
        // Act
        String result = grupo.toString();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que el equals funciona correctamente")
    void equalsFuncionaCorrecto() throws ClubException {
        // Arrange
        Grupo grupo2 = new Grupo("Grupo1", "Deporte", 10, 5, 25.0);
        boolean expected = true;

        // Act
        boolean result = grupo.equals(grupo2);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que el equals funciona correctamente con distinto grupo")
    void equalsDevuelveFalsoConDistintoGrupo() throws ClubException {
        // Arrange
        Grupo grupo2 = new Grupo("Grupo2", "Deporte", 10, 5, 25.0);
        boolean expected = false;

        // Act
        boolean result = grupo.equals(grupo2);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que el equals funciona correctamente con objeto distinto a Grupo")
    void equalsFunDevuelveFalsoConObjetoDistinto() {
        // Arrange
        Object grupo2 = new Object();
        boolean expected = false;

        // Act
        boolean result = grupo.equals(grupo2);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que el equals funciona correctamente con null")
    void equalsDevuelveFalsoConNull() {
        // Arrange
        Object grupo2 = null;
        boolean expected = false;

        // Act
        boolean result = grupo.equals(grupo2);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que el equals funciona correctamente con mismo código y distinta actividad")
    void equalsDevuelveFalsoConMismoCodigoYDistintaActividad() throws ClubException {
        // Arrange
        Grupo grupo2 = new Grupo("Grupo1", "OtraActividad", 10, 5, 25.0);
        boolean expected = false;

        // Act
        boolean result = grupo.equals(grupo2);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Comprobar que el equals funciona correctamente con distinto código y misma actividad")
    void equalsDevuelveFalsoConDistintoCodigoYMismaActividad() throws ClubException {
        // Arrange
        Grupo grupo2 = new Grupo("Grupo2", "Deporte", 10, 5, 25.0);
        boolean expected = false;

        // Act
        boolean result = grupo.equals(grupo2);

        // Assert
        assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("Comprobar que el hashCode funciona correctamente")
    void hashCodeCorrecto() {
        // Arrange
        int expected = grupo.getCodigo().toUpperCase().hashCode() + grupo.getActividad().toUpperCase().hashCode();

        // Act
        int result = grupo.hashCode();

        // Assert
        assertEquals(expected, result);
    }
}
