# language: es
Característica: Cobro de estadías en EstacionaYa
  Como dueño del parqueadero
  Quiero saber cuánto paga cada vehículo y cuánto descuento recibe cada cliente
  Para cobrar de forma justa y sin errores

  Esquema del escenario: Cobro según el tiempo de estadía
    Dado que un <vehiculo> estuvo <minutos> minutos en el parqueadero
    Cuando se calcula lo que debe pagar
    Entonces debe pagar <valor> pesos

    Ejemplos:
      | vehiculo | minutos | valor | caso                         |
      | carro    | 15      | 0     | dentro de la gracia          |
      | carro    | 16      | 3000  | primera hora                 |
      | carro    | 61      | 6000  | una hora y fracción          |
      | carro    | 481     | 25000 | supera el tope diario        |
      | moto     | 15      | 0     | dentro de la gracia          |
      | moto     | 16      | 1500  | primera hora                 |
      | moto     | 61      | 3000  | una hora y fracción          |
      | moto     | 481     | 12000 | supera el tope diario        |

  Escenario: Un cliente frecuente recibe 10 % de descuento
    Dado que la cuenta de un cliente es de 3000 pesos
    Cuando se aplica el descuento de cliente "frecuente"
    Entonces debe pagar 2700 pesos

  Escenario: Un cliente de convenio recibe 20 % de descuento
    Dado que la cuenta de un cliente es de 25000 pesos
    Cuando se aplica el descuento de cliente "convenio"
    Entonces debe pagar 20000 pesos
