import java.util.Random;

/**
 * Clase gestora del tablero de juego. Guarda una matriz de enteros representado
 * el tablero. Si hay una mina en una posición guarda el número -1 Si no hay una
 * mina, se guarda cuántas minas hay alrededor. Almacena la puntuación de la
 * partida
 * 
 * @author Javier Bonifacio Hernández
 *
 */
public class ControlJuego {
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int[][] tablero;
	private int puntuacion;

	// Variable creada para que sea mas facil de leer el metodo esFinJuego()
	private int puntuacionFinal = (LADO_TABLERO * LADO_TABLERO) - MINAS_INICIALES;

	// He añadido el setter para poder actualizar la puntuacion desde la clase
	// ventana
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public ControlJuego() {
		// Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];

		// Inicializamos una nueva partida
		inicializarPartida();
		// cada vez que lo haga puedo depurarlo
		depurarTablero();
	}

	/**
	 * Método para generar un nuevo tablero de partida:
	 * 
	 * @pre: La estructura tablero debe existir.
	 * @post: Al final el tablero se habrá inicializado con tantas minas como marque
	 *        la variable MINAS_INICIALES. El resto de posiciones que no son minas
	 *        guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida() {

		// TODO: Repartir minas e inicializar puntacion. Si hubiese un tablero anterior,
		// lo pongo todo a cero para inicializarlo.
		puntuacion = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = 0;
			}
		}
		colocarMinas(); // Lo he separado en otro metodo para que quede un poco mas facil de mirar

		// Al final del m�todo hay que guardar el n�mero de minas para las casillas que
		// no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA) {
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}
	}

	private void colocarMinas() {
		int x = 0, y = 0;
		for (int i = 0; i < MINAS_INICIALES; i++) {
			x = aleatorioLado();
			y = aleatorioLado();
			if (tablero[x][y] == MINA) {
				// Creo este while a parte del if para asegurarme de que nunca me va a poner dos
				// minas en el mismo sitio
				while (tablero[x][y] == MINA) {
					x = aleatorioLado();
					y = aleatorioLado();
				}
			}
			tablero[x][y] = MINA;
		}
	}

	/**
	 * Me devuelve un entero aleatorio dentro de los parametros del tablero
	 * 
	 * @return
	 */
	private int aleatorioLado() {
		return new Random().nextInt(LADO_TABLERO);
	}

	/**
	 * Cálculo de las minas adjuntas: Para calcular el número de minas tenemos que
	 * tener en cuenta que no nos salimos nunca del tablero. Por lo tanto, como
	 * mucho la i y la j valdrán LADO_TABLERO-1. Por lo tanto, como poco la i y la j
	 * valdrán 0.
	 * 
	 * @param i: posición vertical de la casilla a rellenar
	 * @param j: posición horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j) {
		int iInicial = Math.max(0, i - 1);
		int iFinal = Math.min(LADO_TABLERO - 1, i + 1);
		int jInicial = Math.max(0, j - 1);
		int jFinal = Math.min(LADO_TABLERO - 1, j + 1);
		int contMinas = 0;

		// Compruebo si hay minas alrededor y si las hay las sumo al contador
		for (int vertical = iInicial; vertical <= iFinal; vertical++) {
			for (int horizontal = jInicial; horizontal <= jFinal; horizontal++) {
				if (tablero[vertical][horizontal] == MINA) {
					contMinas++;
				}
			}
		}
		return contMinas;
	}

	/**
	 * Método que nos permite
	 * 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por
	 *      el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j) {
		if (tablero[i][j] == MINA) {
			return false;
		}
		puntuacion++;
		return true;
	}

	/**
	 * Método que checkea si se ha terminado el juego porque se han abierto todas
	 * las casillas.
	 * 
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son
	 *         minas.
	 **/
	public boolean esFinJuego() {
		if (puntuacion == puntuacionFinal) {
			return true;
		}
		return false;
	}

	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza
	 * para depurar
	 */
	public void depurarTablero() {
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: " + puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * 
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta
	 *      calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * Método que devuelve la puntuación actual
	 * 
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return this.puntuacion;
	}

}
