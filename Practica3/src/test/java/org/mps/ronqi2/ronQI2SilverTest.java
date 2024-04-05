package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mps.dispositivo.DispositivoSilver;

public class ronQI2SilverTest {

    
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    @DisplayName("Cuando se inicializa de forma correcta, se llama una sola vez al configurar de cada sensor")
    @Test
    public void testInicializar() {
        // STEP 1: create mock object
        DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

        // STEP 2: stubbing
        when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
        when(mockedDispositivo.conectarSensorSonido()).thenReturn(true);
        when(mockedDispositivo.configurarSensorPresion()).thenReturn(true);
        when(mockedDispositivo.configurarSensorSonido()).thenReturn(true);

        // STEP 3: using the mocked object
        RonQI2Silver ronQi2Silver = new RonQI2Silver();
        ronQi2Silver.anyadirDispositivo(mockedDispositivo);
        boolean result = ronQi2Silver.inicializar();

        // STEP 4: asserting
        assertTrue(result);

        // STEP 5: optional -> verifying
        verify(mockedDispositivo, times(1)).conectarSensorPresion();
        verify(mockedDispositivo, times(1)).conectarSensorSonido();
        verify(mockedDispositivo, times(1)).configurarSensorPresion();
        verify(mockedDispositivo, times(1)).configurarSensorSonido();
    }

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */
    
    @DisplayName("Cuando se reconecta un dispositivo desconectado, se conectan ambos sensores")
    @Test
    public void testReconectar() {
        // STEP 1: create mock object
        DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

        // STEP 2: stubbing
        when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
        when(mockedDispositivo.conectarSensorSonido()).thenReturn(true);

        // STEP 3: using the mocked object
        RonQI2Silver ronQi2Silver = new RonQI2Silver();
        ronQi2Silver.anyadirDispositivo(mockedDispositivo);
        boolean result = ronQi2Silver.reconectar();

        // STEP 4: asserting
        assertTrue(result);

        // STEP 5: optional -> verifying
        verify(mockedDispositivo, times(1)).conectarSensorPresion();
        verify(mockedDispositivo, times(1)).conectarSensorSonido();
    }

    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}
