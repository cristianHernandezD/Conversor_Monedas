<h1>Proyecto: Conversor de Monedas</h1>

Este proyecto es una aplicación Java que permite a los usuarios convertir entre diferentes monedas utilizando datos actualizados obtenidos de una API de tasas de cambio (ExchangeRate-API).
El sistema interactúa con el usuario a través de un menú, donde este puede seleccionar entre varias opciones de conversión.

<h3>Funcionamiento del Código:</h3>

- <h5>Conexión a la API:</h5>

El programa utiliza la clase HttpURLConnection para conectarse a la API de ExchangeRate, obteniendo las tasas de cambio actuales para el Dólar (USD).
La respuesta de la API se maneja en formato JSON, que es convertido a un objeto Java usando la librería Gson para facilitar el acceso a las tasas de cambio.

- <h5>Entrada y Validación del Usuario:</h5> 

El programa presenta un menú de opciones al usuario, donde este selecciona la conversión que desea realizar (por ejemplo, de Peso Colombiano a Dólar o viceversa).
Se valida que la entrada del usuario sea correcta (opciones válidas y valores numéricos).
Si el usuario ingresa un valor incorrecto, se le pide que ingrese nuevamente hasta que sea válido.

- <h5>Lógica de Conversión:</h5>

Una vez que se selecciona la opción de conversión y se ingresa la cantidad, el programa utiliza las tasas de cambio obtenidas de la API para calcular el valor en la moneda deseada.
Dependiendo de la opción seleccionada, el sistema calcula la conversión correspondiente multiplicando o dividiendo por la tasa de cambio.

- <h5>Opciones de Conversión:</h5>

Peso Colombiano a Dólar y viceversa.<br>
Peso Argentino a Dólar y viceversa.<br>
Real Brasileño a Dólar y viceversa.

- <h5>Manejo de Errores:</h5>

Si hay algún problema con la conexión a la API, el programa captura las excepciones y muestra un mensaje de error sin detener abruptamente la ejecución.
Si la tasa de cambio no está disponible para una moneda específica, se notifica al usuario.
Salida del Programa:

El programa finaliza cuando el usuario selecciona la opción "Salir" en el menú.

Este conversor de monedas es una solución simple y efectiva para realizar conversiones entre múltiples monedas, utilizando tasas de cambio en tiempo real proporcionadas por una API externa.
