package CuatroEnLinea;

import java.util.Scanner;

public class CuatroEnLinea {
    Scanner dato = new Scanner(System.in);
    
    String[][] matrizVista = new String[6][7];
    int[][] matrizLogica = new int[6][7];
    
    //Metodo para mostrar el tablero inicial
    public void tablero(){
        for(int i=0; i<6; i++){
            for(int x=0; x<7; x++){
                matrizVista[i][x] = "-";
            }
        }
    }
    
    //Metodo para pedir la posicion del jugador 1 y 2
    public void pedirPosicion() {
    int vertical;   //variable que guarda la posicion de la columna
    int j1 = 1, j2 = 2;  //variable que usare para reconocer al jugador 1 o 2 en el method colocarJuego/Posicion
    boolean acabar = false; //variable para q termine el ciclo una vez haya un ganador
    
    //PidoNombres
    String nombre1, nombre2;
        
        System.out.print("\nDigite un nombre para el jugador n°1: ");
        nombre1 = dato.nextLine();
        System.out.print("Digite un nombre para el jugador n°2: ");
        nombre2 = dato.nextLine();
        
        mostrar();
    
    //Bucle que controla el juego
    do {
        System.out.println("\n-----Turno de "+nombre1+"-----");
        System.out.print("Digite un valor en el eje vertical(1-7): ");
        vertical = dato.nextInt();
        
        // Validar que el valor de vertical este entre 1 y 7
        while (vertical < 1 || vertical > 7) {
            System.out.print("Valor inválido. Ingrese un número entre 1 y 7: ");
            vertical = dato.nextInt();
        }
        vertical--; //Le resto para que coincida con lo que el usuario digito
        colocarJuego(vertical, j1); // Mando la posicion y el n° de jugador al metodo colocarJuego/Posicion
        
        if (comprobarGanador() == true) {   //Llamo al metodo comprobarGanador y si retorna false es xq nadie gano aun
            acabar = true;
            System.out.println("¡El jugador 1 ha ganado. Felicidades!");
            break;  //Este break me rompe el ciclo while asi ya no le pide la posicion al jugador 2
        }
        if (verificarEmpate()) {
            acabar = true;
            System.out.println("¡El juego ha terminado en empate!");
            break;
        }
        System.out.println("\n-----Turno de "+nombre2+"-----");
        System.out.print("Digite un valor en el eje vertical: ");
        vertical = dato.nextInt();
        
        // Validar que el valor de vertical este entre 1 y 7
        while (vertical < 1 || vertical > 7) {
            System.out.print("Valor inválido. Ingrese un número entre 1 y 7: ");
            vertical = dato.nextInt();
        }

        vertical--;
        colocarJuego(vertical, j2); // Mando la posicion y el n° de jugador al metodo colocarJuego/Posicion

        if (comprobarGanador() == true) {
            acabar = true;
            System.out.println("¡El jugador 2 ha ganado. Felicidades!");
        }
        if (verificarEmpate()) {
            acabar = true;
            System.out.println("¡El juego ha terminado en empate!");
            break;
        }
        
    } while (acabar != true);
}
    
    //Metodo que va a colocar la jugada en la posicion brindada por el usuario
    public void colocarJuego(int vertical, int jugador) {
    int indiceFila = -1; // Variable que guarda el número de fila que está libre para poder guardar allí el siguiente valor

    while (indiceFila == -1) {
        for (int i = 0; i < 6; i++) {
            if (matrizLogica[i][vertical] == 0) { // Vertical tiene la posición de la columna elegida por el usuario
                indiceFila = i; // En caso de estar libre se guarda la posición de la última fila disponible
            }
        }

        if (indiceFila == -1) {
            System.out.println("La columna está llena. Elige otra columna.");
            System.out.print("Digite un valor en el eje vertical: ");
            vertical = dato.nextInt();
            
            // Validar que el valor de vertical esté entre 1 y 7
            while (vertical < 1 || vertical > 7) {
                System.out.print("Valor inválido. Ingrese un número entre 1 y 7: ");
                vertical = dato.nextInt();
            }
            
            vertical--;
        }
    }

    if (jugador == 1) {
        matrizLogica[indiceFila][vertical] = 1; // IndiceFila y vertical ya tienen la posición donde colocar el objeto
        matrizVista[indiceFila][vertical] = "X";
        mostrar(); // Muestra la tabla
    } else {
        matrizLogica[indiceFila][vertical] = 2;
        matrizVista[indiceFila][vertical] = "O";
        mostrar(); // Muestra la tabla
    }
}
    public boolean verificarEmpate() {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 7; j++) {
            if (matrizLogica[i][j] == 0) {
                return false; // Si hay al menos una casilla vacía, no es un empate
            }
        }
    }
    return true; // Todas las casillas están llenas, es un empate
}
    
    public boolean comprobarGanador() {
    // Horizontal
    for(int i = 0; i < 6; i++) {
        for (int x = 0; x < 4; x++) {
            if (matrizLogica[i][x] == 1 && matrizLogica[i][x + 1] == 1 && matrizLogica[i][x + 2] == 1 && matrizLogica[i][x + 3] == 1) {
                return true;
            }
            if (matrizLogica[i][x] == 2 && matrizLogica[i][x + 1] == 2 && matrizLogica[i][x + 2] == 2 && matrizLogica[i][x + 3] == 2) {
                return true;
            }
        }
    }
    
    // Vertical
    for(int i = 0; i<3; i++){
        for(int x = 0; x < 7; x++){
            if(matrizLogica[i][x] == 1 && matrizLogica[i+1][x] == 1 && matrizLogica[i+2][x] == 1 && matrizLogica[i+3][i] == 1){
                return true;
            }
            if(matrizLogica[i][x] == 2 && matrizLogica[i+1][x] == 2 && matrizLogica[i+2][x] == 2 && matrizLogica[i+3][i] == 2){
                return true;
            }
        }
    }
    
    // Diagonal Derecha 
    for (int i = 0; i < 3; i++) {
        for (int x = 0; x < 4; x++) {
            if (matrizLogica[i][x] == 1 && matrizLogica[i + 1][x + 1] == 1 && matrizLogica[i + 2][x + 2] == 1 && matrizLogica[i + 3][x + 3] == 1) {
                return true;
            }
            if (matrizLogica[i][x] == 2 && matrizLogica[i + 1][x + 1] == 2 && matrizLogica[i + 2][x + 2] == 2 && matrizLogica[i + 3][x + 3] == 2) {
                return true;
            }
        }
    }

    // Diagonal Izquierda
    for (int i = 0; i < 3; i++) {
        for (int x = 3; x < 7; x++) {
            if (matrizLogica[i][x] == 1 && matrizLogica[i + 1][x - 1] == 1 && matrizLogica[i + 2][x - 2] == 1 && matrizLogica[i + 3][x - 3] == 1) {
                return true;
            }
            if (matrizLogica[i][x] == 2 && matrizLogica[i + 1][x - 1] == 2 && matrizLogica[i + 2][x - 2] == 2 && matrizLogica[i + 3][x - 3] == 2) {
                return true;
            }
        }
    }
    
    //En caso que no haya un 4 en linea Diagonal, vertical, horizontal retorna false
    return false; // si no en cualquier caso retorna True en los if anteriores
}   
    public void mostrar(){
        System.out.println("\n-1-2-3-4-5-6-7-");
        for(int i=0; i<6; i++){
            for(int x=0; x<7; x++){
                System.out.print("|"+matrizVista[i][x]);
            }
             System.out.print("|");
            System.out.println("");
        }
    }
    
    public static void main(String[] args){
        CuatroEnLinea c1 = new CuatroEnLinea();
        
        c1.tablero();
        
        c1.pedirPosicion();
            
        
        
    }
}
