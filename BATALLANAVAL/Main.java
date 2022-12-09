import java.util.*;

public class Main {

    public static int puntajeJ1 = 0;
    public static int puntajeJ2 = 0;
    public static int barcosJ1 = 0;
    public static int barcosJ2 = 0;
    public static Jugador[] jugadores = new Jugador[2];
    public static char[][] tablero = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean levelOne = false;
        boolean perdido = false;

        int puntosc = 0;
        int c = 11;

        // Ingreso de jugadores
        ingresarJugador(sc, 1);
        ingresarJugador(sc, 2);

        // Creación de tablero
        generarTablero(sc);

        // Presentración de Tablero
        imprimirTablero(tablero);

        // Selección de cantidad de barcos
        barcosCantidad(sc);

        // Despliegue de Barcos Jugador 1
        despliegueBarcos(sc, 1);

        // Despliegue de Barcos Jugador 2
        despliegueBarcos(sc, 2);

        // Inicio de Partida
        boolean turnoJugador1 = true;
        boolean juegoTerminado = false;
        boolean sumarPuntos = false;
        Jugador j1 = jugadores[0];
        Jugador j2 = jugadores[1];

        while (!juegoTerminado) {
            if (turnoJugador1) {
                // Fase de Definición de Ataque
                Barco ataque = defineAtaque(sc, j1);

                // Fase de Evaluación de Ataque
                for (int i = 0; i < j2.barcosCoordenadas.size(); i++) {
                    Barco barcoAtacado = j2.barcosCoordenadas.get(i);
                    if (barcoAtacado.coordenadaX == ataque.coordenadaX &&
                            barcoAtacado.coordenadaY == ataque.coordenadaY) {
                        sumarPuntos = true;
                    }
                }

                // Fase de Resultados de Ataque
                if (sumarPuntos) {
                    barcosJ2 = barcosJ2 + 1;
                    j2.setBarcosDestruidos(barcosJ2);
                    System.out.println("Ha hundido un barco enemigo!!!");

                    // Fase de Incremento de Puntaje
                    puntajeJ1 = puntajeJ1 + 1;
                    j1.setPuntaje(puntajeJ1);
                    System.out.println("Jugador " + j1.getNombreJugador() + ", su puntaje es de " + j1.getPuntaje() + " puntos");

                    // Fase de evaluación de puntos apra terminar juego
                    if (j1.getPuntaje() == j2.getBarcosTotales()) juegoTerminado = true;
                } else {
                    System.out.println("No ha Habido suerte...");
                    System.out.println("Jugador " + j1.getNombreJugador() + ", su puntaje es de " + j1.getPuntaje() + " puntos");
                }
                // Termina Turno
                System.out.println("Terminando turno...\n");
                turnoJugador1 = false;
            } else {
                sumarPuntos = false;
                // Fase de Definición de Ataque
                Barco ataque = defineAtaque(sc, j2);

                // Fase de Evaluación de Ataque
                for (int i = 0; i < j1.barcosCoordenadas.size(); i++) {
                    Barco barcoAtacado = j1.barcosCoordenadas.get(i);
                    if (barcoAtacado.coordenadaX == ataque.coordenadaX &&
                            barcoAtacado.coordenadaY == ataque.coordenadaY) {
                        sumarPuntos = true;
                    }
                }

                // Fase de Resultados de Ataque
                if (sumarPuntos) {
                    barcosJ1 = barcosJ1 + 1;
                    j1.setBarcosDestruidos(barcosJ1);
                    System.out.println("Ha hundido un barco enemigo!!!");

                    // Fase de Incremento de Puntaje
                    puntajeJ2 = puntajeJ2 + 1;
                    j2.setPuntaje(puntajeJ2);
                    System.out.println("Jugador " + j2.getNombreJugador() + ", su puntaje es de " + j2.getPuntaje() + " puntos");

                    // Fase de evaluación de puntos apra terminar juego
                    if (j2.getPuntaje() == j1.getBarcosTotales()) juegoTerminado = true;
                } else {
                    System.out.println("No ha Habido suerte...");
                    System.out.println("Jugador " + j2.getNombreJugador() + ", su puntaje es de " + j2.getPuntaje() + " puntos");
                }
                // Termina Turno
                System.out.println("Terminando turno...\n");
                turnoJugador1 = true;
            }
        }

        // Determinación de resultados finales
        if (j1.getPuntaje() == j2.getBarcosDestruidos()) {
            System.out.println("Felicidades jugador " + j1.getNombreJugador() + ", usted ha ganado!");
            System.out.println("Puntos: " + j1.getPuntaje());
            System.out.println("Barcos Propios hundidos: " + j1.getBarcosDestruidos());
            System.out.println("Barcos Enemigos hundidos: " + j2.getBarcosDestruidos());
        } else {
            System.out.println("Felicidades jugador " + j2.getNombreJugador() + ", usted ha ganado!");
            System.out.println("Puntos: " + j2.getPuntaje());
            System.out.println("Barcos Propios hundidos: " + j2.getBarcosDestruidos());
            System.out.println("Barcos Enemigos hundidos: " + j1.getBarcosDestruidos());
        }
    }

    public static void ingresarJugador(Scanner sc, int jugador) {
        Jugador j = new Jugador();
        System.out.println("Cual es tu nombre jugador " + jugador + "?");
        String nombre = sc.nextLine();
        j.setId(jugador);
        j.setNombreJugador(nombre);
        j.setPuntaje(0);
        j.setBarcosDestruidos(0);
        jugadores[jugador - 1] = j;
        System.out.println("Suerte, " + j.getNombreJugador());
        System.out.println(" ");
    }

    public static void generarTablero(Scanner sc) {
        System.out.println("Escoge el tamaño del tablero; por ejemplo, \"5\" = tablero de 5x5");
        int tamano = sc.nextInt();
        tablero = new char[tamano][tamano];

        System.out.println("Generando tablero de juego...");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = 'x';
            }
        }
        System.out.println("Tablero generado!");
    }

    public static void imprimirTablero(char[][] matriz) {
        int b = 0;
        int c = 0;
        System.out.print("   ");
        for (int i = 0; i < matriz.length; i++) {
            c++;
            if (i <= 8) {
                System.out.print(0);
            }
            System.out.print(c + " ");
        }
        System.out.println(" ");
        for (int i = 0; i < matriz.length; i++) {
            b++;
            if (i <= 8) {
                System.out.print(0);
            }
            System.out.print(b);
            for (int j = 0; j < matriz[0].length; j++) {

                if (j == 0 || j == matriz.length - 1) {
                    if (j == 0) {
                        System.out.print("| " + matriz[i][j]);

                    }
                    if (j == matriz.length - 1) {
                        System.out.print("  " + matriz[i][j] + " |");
                    }
                } else {
                    System.out.print("  " + matriz[i][j]);
                }
            }
            System.out.println("");
        }
    }

    public static void barcosCantidad(Scanner sc) {
        System.out.println("Escoge cuantos barcos tendrán los jugadores. (Máximo 4)");
        int barcos = sc.nextInt();

        //matrizBarco = new char[barcos][barcos];

        if (barcos <= 4) {
            jugadores[0].setBarcosTotales(barcos);
            jugadores[1].setBarcosTotales(barcos);
            //numeroBarcos = barcos;
        } else {
            System.out.println("ingrese una cantidad valida");
            System.out.println(" ");
        }
    }

    public static void despliegueBarcos(Scanner sc, int jugador) {
        int coordenadaX = 0;
        int coordenadaY = 0;
        Barco barco = null;
        Jugador j = jugadores[jugador - 1];

        System.out.println("Hola jugador " + j.getNombreJugador() + ", por favor...");
        for (int i = 1; i <= j.getBarcosTotales(); i++) {
            System.out.println("Ingrese las coordenadas del barco no. " + i);
            System.out.println("En que fila? (Eje-X)");
            coordenadaX = sc.nextInt();
            System.out.println("En que columna? (Eje-Y)");
            coordenadaY = sc.nextInt();

            barco = new Barco();
            barco.Id = i;
            barco.coordenadaX = coordenadaX;
            barco.coordenadaY = coordenadaY;

            j.barcosCoordenadas.add(barco);
            System.out.println("");

        }
        System.out.println("Jugador " + j.getNombreJugador() + ", sus barcos han sido desplegados!");
        System.out.println("");
    }

    public static Barco defineAtaque(Scanner sc, Jugador j) {
        int X = 0;
        int Y = 0;
        Barco coord;
        System.out.println("Jugador " + j.getNombreJugador() + ", determine un ataque...");
        System.out.println("Ingrese las coordenadas de su ataque:");
            System.out.println("En que fila? (Eje-X)");
            X = sc.nextInt();
        System.out.println("En que columna? (Eje-Y)");
        Y = sc.nextInt();
        coord = new Barco();
        coord.coordenadaX = X;
        coord.coordenadaY = Y;
        return coord;
    }

    public static class Jugador {
        private Integer Id;
        private String nombreJugador;
        private Integer barcosTotales;
        private Integer barcosDestruidos;
        public ArrayList<Barco> barcosCoordenadas = new ArrayList<Barco>();
        private Integer puntaje;

        // Id Jugador
        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        // Nombre Jugador
        public String getNombreJugador() {
            return nombreJugador;
        }

        public void setNombreJugador(String nombreJugador) {
            this.nombreJugador = nombreJugador;
        }

        //Barcos Totales
        public Integer getBarcosTotales() {
            return barcosTotales;
        }

        public void setBarcosTotales(Integer barcosTotales) {
            this.barcosTotales = barcosTotales;
        }

        // Barcos Destruidos
        public Integer getBarcosDestruidos() {
            return barcosDestruidos;
        }

        public void setBarcosDestruidos(Integer barcosDestruidos) {
            this.barcosDestruidos = barcosDestruidos;
        }

        // Puntaje
        public Integer getPuntaje() {
            return puntaje;
        }

        public void setPuntaje(Integer puntaje) {
            this.puntaje = puntaje;
        }
    }
}