package parqueadero;

public class Descuentos {
    public int aplicarDescuento(int valor, String tipoCliente) {
        if ("frecuente".equals(tipoCliente)) {
            return (int) (valor * 0.9);
        }
        return valor;
    }
}
