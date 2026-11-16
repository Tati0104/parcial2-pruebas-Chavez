package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import parqueadero.Descuentos;



/** Pruebas unitarias de aplicarDescuento, construidas con TDD. */
class DescuentosTest {

    private final Descuentos descuentos = new Descuentos();

    @Test
    @DisplayName("Un cliente frecuente recibe 10 % de descuento")
    void frecuenteRecibeDiezPorCiento() {
        assertEquals(2700, descuentos.aplicarDescuento(3000, "frecuente"));
    }

    @Test
    @DisplayName("Un cliente de convenio recibe 20 % de descuento")
    void convenioRecibeVeintePorCiento() {
        assertEquals(20000, descuentos.aplicarDescuento(25000, "convenio"));
    }

    @Test
    @DisplayName("Rechaza un valor negativo")
    void rechazaValorNegativo() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> descuentos.aplicarDescuento(-1, "frecuente"));
        assertEquals("El valor no puede ser negativo", error.getMessage());
    }
}
