import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConversorMonedas {
    public static void main(String[] args) {

        // Inicializar variables
        int opcionCambio = 0;
        double valorInicial = 0;
        double valorCambiado = 0;
        Scanner teclado = new Scanner(System.in);
        RespuestaApi tasasCambio = null;
        String apiKey = "a3dd75251934977d73c22628";


       try {
           // Establecer conexión con la API
           URL url = new URL("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD");
           HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
           conexion.setRequestMethod("GET");
           conexion.connect();

           // Verificar y capturar respuesta de la API
           int codigoRespuesta = conexion.getResponseCode();
           if (codigoRespuesta != 200) {
               throw new RuntimeException("Ha ocurrido un error - Código: " + codigoRespuesta);
           } else {
               StringBuilder capturaApi = new StringBuilder();
               Scanner lecturaApi = new Scanner(url.openStream());
               while (lecturaApi.hasNext()) {
                   capturaApi.append(lecturaApi.nextLine());
               }
               lecturaApi.close();

               // Convertir JSON a un objeto RespuestaApi
               Gson gson = new Gson();
               try {
                   tasasCambio = gson.fromJson(capturaApi.toString(), RespuestaApi.class);
                   if (tasasCambio.getTasasDeConversion() == null) {
                       System.out.println("No se han obtenido las tasas de conversión");
                       return;
                   }
               } catch (JsonSyntaxException e) {
                   System.err.println("Error se sintaxis en el JSON: " + e.getMessage());
                   return;
               }
           }
       // Captura de cualquier otra excepcion
       } catch (Exception e) {
           e.printStackTrace();
           return;
       }

       // Menu del usuario
       String menu = """
               **************************************
               Bienvenido al Conversor de Monedas \n
               1 - Peso Colombiano =>> Dólar
               2 - Dólar =>> Peso Colombiano
               3 - Peso Argentino =>> Dólar
               4 - Dólar =>> peso Argentino
               5 - Real Brasileño =>> Dólar
               6 - Dólar =>> Real Brasileño
               7 - Salir
               **************************************
               Seleccione una opcion:
               """;

       while (opcionCambio != 7){
           System.out.println(menu);
           // Validacion de la entrada
           while (!teclado.hasNextInt()) {
               System.out.println("Por favor, ingrese una opción valida.");
               teclado.next(); // Limpiar linea de entrada
           }
           opcionCambio = teclado.nextInt();

           if (opcionCambio >= 1 && opcionCambio <= 6) {
               System.out.println("Ingrese el valor a cambiar: ");
               while (!teclado.hasNextDouble()) {
                   System.out.println("Por favor, ingrese un valor valido.");
                   teclado.next(); // Limpiar linea de entrada
               }
               valorInicial = teclado.nextDouble();
           }

           // Validar la obtención de las tasas de cambio
           if (tasasCambio != null && tasasCambio.getTasasDeConversion() != null){
               switch (opcionCambio){
                   case 1: // Peso Colombiano a Dólar
                       if (tasasCambio.getTasasDeConversion().containsKey("COP")){
                           valorCambiado = valorInicial / tasasCambio.getTasasDeConversion().get("COP");
                           System.out.println("El valor en Dolares es: $" + valorCambiado);
                       } else{
                           System.out.println("No se encontró la tasa de cambio para COP");
                       } break;

                   case 2: // Dólar a Peso Colombiano
                       if (tasasCambio.getTasasDeConversion().containsKey("COP")){
                           valorCambiado = valorInicial * tasasCambio.getTasasDeConversion().get("COP");
                           System.out.println("El valor en Pesos Colombianos es: $" + valorCambiado);
                       } else{
                           System.out.println("No se encontró la tasa de cambio para COP");
                       } break;

                   case 3: // Peso Argentino a Dólar
                       if (tasasCambio.getTasasDeConversion().containsKey("ARS")){
                           valorCambiado = valorInicial / tasasCambio.getTasasDeConversion().get("ARS");
                           System.out.println("El valor en Dolares es: $" + valorCambiado);
                       } else{
                           System.out.println("No se encontró la tasa de cambio para ARS");
                       } break;

                   case 4: // Dólar a Peso Argentino
                       if (tasasCambio.getTasasDeConversion().containsKey("ARS")){
                           valorCambiado = valorInicial * tasasCambio.getTasasDeConversion().get("ARS");
                           System.out.println("El valor en Pesos Argentinos es: $" + valorCambiado);
                       } else{
                           System.out.println("No se encontró la tasa de cambio para ARS");
                       } break;

                   case 5: // Real Brasileño a Dólar
                       if (tasasCambio.getTasasDeConversion().containsKey("BRL")){
                           valorCambiado = valorInicial / tasasCambio.getTasasDeConversion().get("BRL");
                           System.out.println("EL valor en Dolares es: $" + valorCambiado);
                       } else{
                           System.out.println("No se encontró la tasa de cambio para BRL");
                       } break;

                   case 6: // Dólar a Real Brasileño
                       if (tasasCambio.getTasasDeConversion().containsKey("BRL")){
                           valorCambiado = valorInicial * tasasCambio.getTasasDeConversion().get("BRL");
                           System.out.println("El valor en Reales Braseleños es: $" + valorCambiado);
                       } else{
                           System.out.println("No se encontró la tasa de cambio para BRL");
                       } break;
                   case 7: // Salir del conversor de monedas
                       System.out.println("Saliendo del programa, gracias por utilizar nuestros servicios.");
                       break;
                   default:
                       System.out.println("Opción no válida. Por favor, intente de nuevo.");
                       break;
               }
           } else {
               System.out.println("No se pudo obtener las tasas de cambio de la API.");
           }
       }
    }
}
