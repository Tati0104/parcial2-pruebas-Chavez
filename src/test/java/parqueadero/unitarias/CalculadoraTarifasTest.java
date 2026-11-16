package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import parqueadero.CalculadoraTarifas;

/** Pruebas unitarias de calcularTarifa: particiones de equivalencia y valores límite. */
class CalculadoraTarifasTest {

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    @Test
    @DisplayName("Un carro con una hora y un minuto paga dos horas")
    void carroConUnaHoraYUnMinutoPagaDosHoras() {
        assertEquals(6000, calculadora.calcularTarifa(61, "carro"));
    }

    @Test
    @DisplayName("Una moto con media hora paga una hora")
    void motoConMediaHoraPagaUnaHora() {
        assertEquals(1500, calculadora.calcularTarifa(30, "moto"));
    }

    @Test
    @DisplayName("Rechaza un tipo de vehículo desconocido")
    void rechazaUnTipoDeVehiculoDesconocido() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularTarifa(30, "bicicleta"));
    }

    // Partición: minutos de gracia (0 a 15) no pagan. Límites: 0 y 15.
    @ParameterizedTest(name = "{1} con {0} minutos no paga")
    @CsvSource({
        "0, carro",
        "15, carro",
        "0, moto",
        "10, moto",
        "15, moto"
    })
    @DisplayName("Dentro de los minutos de gracia no se paga nada")
    void dentroDeLaGraciaNoPaga(int minutos, String tipoVehiculo) {
        assertEquals(0, calculadora.calcularTarifa(minutos, tipoVehiculo));
    }

    // Partición: después de la gracia se cobra cada hora o fracción.
    // Límites: 16 (primer minuto cobrado), 60 y 61 (cambio de hora).
    @ParameterizedTest(name = "{1} con {0} minutos paga {2}")
    @CsvSource({
        "16, carro, 3000",
        "60, carro, 3000",
        "61, carro, 6000",
        "120, carro, 6000",
        "180, carro, 9000",
        "16, moto, 1500",
        "60, moto, 1500",
        "61, moto, 3000",
        "120, moto, 3000"
    })
    @DisplayName("Después de la gracia se cobra cada hora o fracción de hora")
    void cobraCadaHoraOFraccion(int minutos, String tipoVehiculo, int esperado) {
        assertEquals(esperado, calculadora.calcularTarifa(minutos, tipoVehiculo));
    }

    // Tope del carro (25000). 480 min = 8 h = 24000, justo debajo del tope.
    // 481 min = 9 h = 27000, se corta al tope.
    @ParameterizedTest(name = "carro con {0} minutos paga {1}")
    @CsvSource({
        "480, 24000",
        "481, 25000",
        "600, 25000",
        "1440, 25000"
    })
    @DisplayName("Un carro nunca paga más del tope diario de 25000")
    void carroNoSuperaElTopeDiario(int minutos, int esperado) {
        assertEquals(esperado, calculadora.calcularTarifa(minutos, "carro"));
    }

    // Tope de la moto (12000). 420 min = 7 h = 10500, debajo del tope.
    // 480 min = 8 h = 12000, justo en el tope. 481 min = 9 h = 13500, se corta al tope.
    @ParameterizedTest(name = "moto con {0} minutos paga {1}")
    @CsvSource({
        "420, 10500",
        "480, 12000",
        "481, 12000",
        "600, 12000",
        "1440, 12000"
    })
    @DisplayName("Una moto nunca paga más del tope diario de 12000")
    void motoNoSuperaElTopeDiario(int minutos, int esperado) {
        assertEquals(esperado, calculadora.calcularTarifa(minutos, "moto"));
    }

    // Partición inválida: minutos fuera del rango 0 a 1440. Límites: -1 y 1441.
    @ParameterizedTest(name = "{1} con {0} minutos es rechazado")
    @CsvSource({
        "-1, carro",
        "1441, carro",
        "-1, moto",
        "1441, moto"
    })
    @DisplayName("Rechaza minutos fuera del rango de 0 a 1440")
    void rechazaMinutosFueraDeRango(int minutos, String tipoVehiculo) {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcularTarifa(minutos, tipoVehiculo));
        assertEquals("Los minutos deben estar entre 0 y 1440", error.getMessage());
    }

    // Partición inválida: cualquier tipo distinto de "carro" o "moto".
    @ParameterizedTest(name = "rechaza el tipo \"{0}\"")
    @ValueSource(strings = {"bicicleta", "camion", "", "Carro", "MOTO"})
    @DisplayName("Rechaza tipos de vehículo distintos de carro y moto")
    void rechazaTiposDeVehiculoNoValidos(String tipoVehiculo) {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> calculadora.calcularTarifa(30, tipoVehiculo));
        assertEquals("Tipo de vehículo no válido", error.getMessage());
    }
}
