package parqueadero;

import java.util.Map;

public class Descuentos {
    private static final int CIEN_POR_CIENTO = 100;
    private static final Map<String, Integer> PORCENTAJE_DESCUENTO = 
    Map.of("ninguno", 0, "frecuente", 10, "convenio",20);

    public int aplicarDescuento(int valor, String tipoCliente) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        if (!PORCENTAJE_DESCUENTO.containsKey(tipoCliente)) {
            throw new IllegalArgumentException("Tipo de cliente no válido");
        }
        int porcentajeAPagar = CIEN_POR_CIENTO - PORCENTAJE_DESCUENTO.get(tipoCliente);
        return valor * porcentajeAPagar / CIEN_POR_CIENTO;
    }
}
