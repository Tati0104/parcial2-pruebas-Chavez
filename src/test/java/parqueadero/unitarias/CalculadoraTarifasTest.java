package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import parqueadero.CalculadoraTarifas;

/** Pruebas unitarias iniciales de calcularTarifa. Están incompletas a propósito. */
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
    @Test 
    @DisplayName ("Una moto con 10 horas paga el tope de 20.000")
    void motoCon10HorasPagaTope() {
        assertEquals(20000, calculadora.calcularTarifa(600, "moto"));
    }
    @Test 
    @displayName ("Un carro con 10 horas paga el tope de 25.000")
    void carroCon10HorasPagaTope() {    
        assertEquals(25000, calculadora.calcularTarifa(600, "carro"));
    }
    @Test 
    @DisplayName ("Una moto con 0 horas paga 0")
    void motoCon0HorasPaga0() {
        assertEquals(0, calculadora.calcularTarifa(0, "moto"));
    }
    @Test 
    @DisplayName ("Un carro con 0 horas paga 0")
    void carroCon0HorasPaga0() {
        assertEquals(0, calculadora.calcularTarifa(0, "carro"));
    }
    @Test 
    @DisplayName ("Una moto con 1 hora paga 2.000")
    void motoCon1HoraPaga2000() {
        assertEquals(2000, calculadora.calcularTarifa(60, "moto"));
    }
    @Test 
    @DisplayName ("Un carro con 1 hora paga 5.000")
    void carroCon1HoraPaga5000() {
        assertEquals(5000, calculadora.calcularTarifa(60, "carro"));
    }
    @Test 
    @DisplayName ("Una moto con 2 horas paga 4.000")
    void motoCon2HorasPaga4000() {
        assertEquals(4000, calculadora.calcularTarifa(120, "moto"));
    }
    @Test 
    @DisplayName ("Un carro con 2 horas paga 10.000")
    void carroCon2HorasPaga10000() {
        assertEquals(10000, calculadora.calcularTarifa(120, "carro"));
    }
    @Test 
    @DisplayName ("Una moto con 10 minutos paga 0")
    void motoCon10minutosPaga0() {  
        assertEquals(0, calculadora.calcularTarifa(10, "moto"));
    }
    @Test 
    @DisplayName ("Un carro con 3 horas paga 15.000")
    void carroCon3HorasPaga15000() {
        assertEquals(15000, calculadora.calcularTarifa(180, "carro"));
    }
}