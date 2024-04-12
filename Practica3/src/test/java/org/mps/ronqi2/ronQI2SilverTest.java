/*
 * @author 1: Nicoló Melley
 * @author 2: Andrés Ruiz Sánchez
 */

package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mps.dispositivo.DispositivoSilver;
import org.junit.jupiter.api.Nested;

public class ronQI2SilverTest {

    @Nested
    @DisplayName("Tests para el método inicializar")
    class testInicializar{

        @DisplayName("Cuando se inicializa de forma correcta, se llama una sola vez al configurar de cada sensor")
        @Test
        public void cuandoSeInicializaCorrectamente_entoncesSeLlamaUnaVezAConfigurar() {
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

        @DisplayName("Cuando se inicializa de forma incorrecta y no se conecta el Sensor, entonces result es False")
        @Test
        public void cuandoSeInicializaIncorrectamente_entoncesNoSeConectaElSensor() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
            when(mockedDispositivo.conectarSensorSonido()).thenReturn(false);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            boolean result = ronQi2Silver.inicializar();

            // STEP 4: asserting
            assertFalse(result);

            // STEP 5: optional -> verifying
            verify(mockedDispositivo, times(1)).conectarSensorPresion();
            verify(mockedDispositivo, times(1)).conectarSensorSonido();
        }

        @DisplayName("Cuando se inicializa de forma incorrecta y no se conecta el Sensor de presión, entonces result es False")
        @Test
        public void cuandoSeInicializaIncorrectamente_entoncesNoSeConectaElSensorDePresion() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.conectarSensorPresion()).thenReturn(false);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            boolean result = ronQi2Silver.inicializar();

            // STEP 4: asserting
            assertFalse(result);

            // STEP 5: optional -> verifying
            verify(mockedDispositivo, times(1)).conectarSensorPresion();
            verify(mockedDispositivo, times(0)).conectarSensorSonido();
            verify(mockedDispositivo, times(0)).configurarSensorPresion();
            verify(mockedDispositivo, times(0)).configurarSensorSonido();
        }

        @DisplayName("Cuando se inicializa de forma correcta y se conectan ambos sensores, entonces result es True")
        @Test
        public void cuandoSeInicializaCorrectamente_entoncesSeConectanAmbosSensores() {
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

        @DisplayName("Cuando se inicializa de forma correcta y no se configura el Sensor de sonido, entonces result es False")
        @Test
        public void cuandoSeInicializaCorrectamenteSinSensorDeSonido_entoncesFalla() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
            when(mockedDispositivo.conectarSensorSonido()).thenReturn(true);
            when(mockedDispositivo.configurarSensorPresion()).thenReturn(true);
            when(mockedDispositivo.configurarSensorSonido()).thenReturn(false);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            boolean result = ronQi2Silver.inicializar();

            // STEP 4: asserting
            assertFalse(result);

            // STEP 5: optional -> verifying
            verify(mockedDispositivo, times(1)).conectarSensorPresion();
            verify(mockedDispositivo, times(1)).conectarSensorSonido();
            verify(mockedDispositivo, times(1)).configurarSensorPresion();
            verify(mockedDispositivo, times(1)).configurarSensorSonido();
        }

        @DisplayName("Cuando se inicializa de forma correcta y no se configura el Sensor de presión, entonces result es False")
        @Test
        public void cuandoSeInicializaCorrectamenteSinSensorDePresion_entoncesFalla() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
            when(mockedDispositivo.conectarSensorSonido()).thenReturn(true);
            when(mockedDispositivo.configurarSensorPresion()).thenReturn(false);
            when(mockedDispositivo.configurarSensorSonido()).thenReturn(true);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            boolean result = ronQi2Silver.inicializar();

            // STEP 4: asserting
            assertFalse(result);

            // STEP 5: optional -> verifying
            verify(mockedDispositivo, times(1)).conectarSensorPresion();
            verify(mockedDispositivo, times(1)).conectarSensorSonido();
            verify(mockedDispositivo, times(1)).configurarSensorPresion();
            verify(mockedDispositivo, times(1)).configurarSensorSonido();
        }

    }
    
    @Nested
    @DisplayName("Tests para el método reconectar")
    class testReconectar{

        @DisplayName("Cuando se reconecta un dispositivo desconectado, se conectan ambos sensores")
        @Test
        public void cuandoSeReconectaUnDispositivoDesconectado_entoncesAmbosSensoresSeConectan() {
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

        @DisplayName("Cuando se reconecta un dispositivo conectado, no se conectan los sensores")
        @Test
        public void cuandoSeRectonectaUnDispositivoConectado_entoncesAmbosSensoresNoSeConectan() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
            when(mockedDispositivo.conectarSensorSonido()).thenReturn(true);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            when(ronQi2Silver.disp.estaConectado()).thenReturn(true);
            boolean result = ronQi2Silver.reconectar();

            // STEP 4: asserting
            assertFalse(result);

            // STEP 5: optional -> verifying
            verify(mockedDispositivo, times(0)).conectarSensorPresion();
            verify(mockedDispositivo, times(0)).conectarSensorSonido();
        }
        
        @DisplayName("Cuando se reconecta un dispositivo y falla un conectarSensor, no se reconectan ambos sensores")
        @Test
        public void cuandoSeReconectaUnDispositivoYFallaConectarSensor_entoncesNoSeReconectan() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
            when(mockedDispositivo.conectarSensorSonido()).thenReturn(false);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            boolean result = ronQi2Silver.reconectar();

            // STEP 4: asserting
            assertFalse(result);

            // STEP 5: optional -> verifying
            verify(mockedDispositivo, times(1)).conectarSensorPresion();
            verify(mockedDispositivo, times(1)).conectarSensorSonido();
        }

    }

    @Nested
    @DisplayName("Tests para el método evaluarApneaSuenyo")
    class testEvaluarApneaSuenyo{

        @DisplayName("Evaluar apnea con diferentes números de lecturas previas")
        @ParameterizedTest
        @ValueSource(ints = {4, 5, 10})
        public void cuandoSeEvaluanApenasConDiferentesLecturasPreviasPorDebajoDeUmbral_entoncesNoHayApnea(int numLecturas) {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.leerSensorPresion()).thenReturn(10.0f);
            when(mockedDispositivo.leerSensorSonido()).thenReturn(20.0f);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);

            // Perform the specified number of readings
            for (int i = 0; i < numLecturas; i++) {
                ronQi2Silver.obtenerNuevaLectura();
            }

            boolean result = ronQi2Silver.evaluarApneaSuenyo();

            // STEP 4: asserting
            assertFalse(result);
        }
        
        @DisplayName("Si se realizan 5 lecturas con valores por debajo de los umbrales, no se considera que hay una apnea en proceso")
        @Test
        public void cuandoSeRealizan5LecturasDebajoDeUmbral_entoncesNoHayApnea() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.leerSensorPresion()).thenReturn(10.0f);
            when(mockedDispositivo.leerSensorSonido()).thenReturn(20.0f);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            boolean result = ronQi2Silver.evaluarApneaSuenyo();

            // STEP 4: asserting
            assertFalse(result);
        }

        @DisplayName("Si se realizan 5 lecturas con valores por encima de los umbrales, se considera que hay una apnea en proceso")
        @Test
        public void cuandoSeRealizan5LecturasEncimaDeUmbral_entoncesHayApnea() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.leerSensorPresion()).thenReturn(30.0f);
            when(mockedDispositivo.leerSensorSonido()).thenReturn(40.0f);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            ronQi2Silver.obtenerNuevaLectura();
            boolean result = ronQi2Silver.evaluarApneaSuenyo();

            // STEP 4: asserting
            assertTrue(result);
        }

        @DisplayName("Si se realizan 10 lecturas con valores por debajo de los umbrales, no se considera que hay una apnea en proceso")
        @Test
        public void cuandoSeRealizan10LecturasDebajoDeUmbral_entoncesNoHayApnea() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.leerSensorPresion()).thenReturn(10.0f);
            when(mockedDispositivo.leerSensorSonido()).thenReturn(20.0f);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            for (int i = 0; i < 10; i++) {
                ronQi2Silver.obtenerNuevaLectura();
            }
            boolean result = ronQi2Silver.evaluarApneaSuenyo();

            // STEP 4: asserting
            assertFalse(result);
        }

        @DisplayName("Si se realizan 10 lecturas con valores por encima de los umbrales, se considera que hay una apnea en proceso")
        @Test
        public void cuandoSeRealizan10LecturasEncimaDeUmbral_entoncesHayApnea() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.leerSensorPresion()).thenReturn(30.0f);
            when(mockedDispositivo.leerSensorSonido()).thenReturn(40.0f);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            for (int i = 0; i < 10; i++) {
                ronQi2Silver.obtenerNuevaLectura();
            }
            boolean result = ronQi2Silver.evaluarApneaSuenyo();

            // STEP 4: asserting
            assertTrue(result);
        }

        @DisplayName("Si se realizan 4 lecturas con valores de Presion por encima del umbral y de Sonido por debajo, no se considera que hay una apnea en proceso")
        @Test
        public void cuandoSeRealizan4LecturasConPresionEncimaYSonidoDebajoDeUmbral_entoncesNoHayApnea() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.leerSensorPresion()).thenReturn(30.0f);
            when(mockedDispositivo.leerSensorSonido()).thenReturn(20.0f);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            for (int i = 0; i < 4; i++) {
                ronQi2Silver.obtenerNuevaLectura();
            }
            boolean result = ronQi2Silver.evaluarApneaSuenyo();

            // STEP 4: asserting
            assertFalse(result);
        }

        @DisplayName("Si se realizan 4 lecturas con valores de Presion por debajo del umbral y de Sonido por encima, no se considera que hay una apnea en proceso")
        @Test
        public void cuandoSeRealizan4LecturasConPresionDebajoYSonidoEncimaDeUmbral_entoncesNoHayApnea() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);

            // STEP 2: stubbing
            when(mockedDispositivo.leerSensorPresion()).thenReturn(10.0f);
            when(mockedDispositivo.leerSensorSonido()).thenReturn(40.0f);

            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            for (int i = 0; i < 4; i++) {
                ronQi2Silver.obtenerNuevaLectura();
            }
            boolean result = ronQi2Silver.evaluarApneaSuenyo();

            // STEP 4: asserting
            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Tests para el método estaConectado")
    class testEstaConectado {
        @DisplayName("Revisa si un dispositivo está conectado correctamente")
        @Test
        public void cuandoSeCompruebaSiEstaConectado_entoncesDevuelveTrue() {
            // STEP 1: create mock object
            DispositivoSilver mockedDispositivo = mock(DispositivoSilver.class);
    
            // STEP 2: stubbing
            when(mockedDispositivo.conectarSensorPresion()).thenReturn(true);
            when(mockedDispositivo.conectarSensorSonido()).thenReturn(true);
    
            // STEP 3: using the mocked object
            RonQI2Silver ronQi2Silver = new RonQI2Silver();
            ronQi2Silver.anyadirDispositivo(mockedDispositivo);
            when(ronQi2Silver.disp.estaConectado()).thenReturn(true);
            boolean result = ronQi2Silver.estaConectado();
    
            // STEP 4: asserting
            assertTrue(result);
        }
    }
}
