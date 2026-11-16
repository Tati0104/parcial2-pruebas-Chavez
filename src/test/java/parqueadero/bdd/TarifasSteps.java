package parqueadero.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import parqueadero.CalculadoraTarifas;
import parqueadero.Descuentos;

public class TarifasSteps {
    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();
    private final Descuentos descuentos = new Descuentos();

    private String tipoVehiculo;
    private int minutos;
    private int valorCuenta;
    private int valorAPagar;

    @Dado("que un {word} estuvo {int} minutos en el parqueadero")
    public void queUnVehiculoEstuvoMinutos(String vehiculo, int minutosEstadia) {
        tipoVehiculo = vehiculo;
        minutos = minutosEstadia;
    }

    @Cuando("se calcula lo que debe pagar")
    public void seCalculaLoQueDebePagar() {
        valorAPagar = calculadora.calcularTarifa(minutos, tipoVehiculo);
    }

    @Dado("que la cuenta de un cliente es de {int} pesos")
    public void queLaCuentaDeUnClienteEs(int valor) {
        valorCuenta = valor;
    }

    @Cuando("se aplica el descuento de cliente {string}")
    public void seAplicaElDescuentoDeCliente(String tipoCliente) {
        valorAPagar = descuentos.aplicarDescuento(valorCuenta, tipoCliente);
    }

    @Entonces("debe pagar {int} pesos")
    public void debePagar(int valorEsperado) {
        assertEquals(valorEsperado, valorAPagar);
    }
}
