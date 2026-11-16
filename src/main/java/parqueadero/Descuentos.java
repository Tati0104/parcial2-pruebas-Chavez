package parqueadero;

public class Descuentos {
    public int aplicarDescuento(int valor, String tipoCliente) {
                if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }

        if ("frecuente".equals(tipoCliente)) {
            return (int) (valor * 0.9);
        }
        if ("convenio".equals(tipoCliente)) {
            return (int) (valor * 0.8);
        }
        return valor;
    }
}
