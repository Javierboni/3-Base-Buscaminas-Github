import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author Javier Bonifacio Hernández
 **
 */
public class ActionBoton implements ActionListener{

	private VentanaPrincipal ventana;
	private int i;
	private int j;

	public ActionBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(ventana.getJuego().abrirCasilla(i, j)){
<<<<<<< HEAD
			ventana.mostrarNumMinasAlrededor(i, j);	
			if(ventana.getJuego().esFinJuego()){
				ventana.mostrarFinJuego(false);
			}
		}else{
			ventana.mostrarFinJuego(true);			
		}	
=======
			ventana.mostrarNumMinasAlrededor(i, j);		
		}else{
			ventana.mostrarFinJuego(ventana.getJuego().esFinJuego());
			for (int i = 0; i < ventana.getJuego().LADO_TABLERO; i++) {
				for (int j = 0; j < ventana.getJuego().LADO_TABLERO; j++) {
					ventana.getBotonesJuego()[i][j].setEnabled(false);
				}
			}			
		}
>>>>>>> 59b3f93035b71b4243f4a94e8389d17a34359054
	}
}
