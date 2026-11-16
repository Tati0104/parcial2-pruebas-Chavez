package parqueadero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportes {

    private static final String CARRO = "carro";
    private static final String MOTO = "moto";
    private static final String TOTAL_RECAUDADO = "total_recaudado";

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    public Map<String, Integer> resumen(List<Registro> registros) {
        Map<String, Integer> resumenDelDia = new HashMap<>();
        resumenDelDia.put(CARRO, 0);
        resumenDelDia.put(MOTO, 0);
        resumenDelDia.put(TOTAL_RECAUDADO, 0);

        for (Registro registro : registros) {
            resumenDelDia.merge(registro.tipo(), 1, Integer::sum);
            int valorEstadia = calculadora.calcularTarifa(registro.minutos(), registro.tipo());
            resumenDelDia.merge(TOTAL_RECAUDADO, valorEstadia, Integer::sum);
        }
        return resumenDelDia;
    }
}