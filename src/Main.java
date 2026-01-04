import java.io.PrintStream; //Import necesarios
import java.nio.charset.StandardCharsets; 

public class Main { //Declaración de la clase principal
    public static void main(String[] args) throws Exception { //Método principal (lanza excepciones)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8)); //Salida en UTF8
        int[][] mapa = LeerMatrizCSV.leerMapa("mapa.csv");  //Ubicación del archivo que contiene el mapa
        
        if (mapa != null){  //Si la matriz existe, mostrar mapa
            MostrarMapa.mostrarMapa(mapa);
       } else {             //Si no existe, dar mensaje de error
            System.out.println("No se ha podido leer el mapa. ");
       }
       
       int n = mapa.length; //Definimos n como la longitud de la matriz mapa (es matriz cuadrada)
       int[][] energia = Energia.energia;   //Definimos el vector energía (el que encontramos en la clase Energia)
       
        int rec = NumeroDeCaminosPosibles_recursiva.contarCaminosRec(mapa, 0, 0); //Llamada a los métodos necesarios
        int iter = NumeroDeCaminosPosibles_iter.contarCaminosIter(mapa);
        int energ = CaminoDeMenorCoste.caminoMinimoRec(energia, 0, 0);  
        
        NumeroDeCaminosPosibles_recursiva tarea1 = new NumeroDeCaminosPosibles_recursiva(mapa, 0, 0); //Tareas (para el Runnable)
        NumeroDeCaminosPosibles_iter tarea2 = new NumeroDeCaminosPosibles_iter(mapa);
        
        //Al ser long, pasamos a double, y en milisegundos dividiendo por 1millón (Runnable ejecuta en nanosegundos)
        long tiempoRec = MedicionTiempos.medirTiempo(tarea1);  
        double tiempoRecMS = tiempoRec / 1000000.0;
        long tiempoIter = MedicionTiempos.medirTiempo(tarea2);
        double tiempoIterMS = tiempoIter / 1000000.0;
        
        //Salida correcta
        System.out.println("--EL MAPA DEL MERODEADOR-- \nTamaño del mapa:" + n + 'x' + n + 
                "\n Caminos posibles (recursivo):" + rec + "\nTiempo:" + tiempoRecMS +
                "(ms)\nCaminos posibles (iterativo):" + iter + "\nTiempo:" + tiempoIterMS +
                "(ms)\nCoste mínimo del recorrido mágico:" + energ);
    }
}