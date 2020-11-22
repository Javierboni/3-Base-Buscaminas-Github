import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

/**
 * Ventana principal del Buscaminas, esta clase será la encargada de crear y
 * modificar la parte gráfica del buscaminas.
 * 
 * @see ControlJuego
 * @author Javier Bonifacio Hernández
 * @since 1.0
 * @version 1.0
 */
public class VentanaPrincipal {

	// La ventana principal, en este caso, guarda todos los componentes:
	private JFrame ventana;
	private JPanel panelImagen;
	private JPanel panelEmpezar;
	private JPanel panelPuntuacion;
	private JPanel panelJuego;

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar después los componentes por otros
	private JPanel[][] panelesJuego;
	private JButton[][] botonesJuego;

	// Correspondencia de colores para las minas:
	private Color correspondenciaColores[] = { Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	private JButton botonEmpezar;
	private JTextField pantallaPuntuacion;

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	private ControlJuego juego;

	// Color de fondo que aplicare a todo el juego
	Color colorFondo = Color.decode("#FAFFFF");

	// Fuente que aplicare al botonEmpezar y a la pantalla de la puntuacion
	private Font fuenteC=new FontUIResource("", Font.BOLD, 20);

	// Imagen que insertare en las casillas que sean minas cuando el juego acabe
	private Icon iExplosion = new ImageIcon(getClass().getResource("img/Explosion.png"));

	/**
	 * Getter creado para desactivar botones
	 * 
	 * @return
	 */
	public JButton[][] getBotonesJuego() {
		return this.botonesJuego;
	}

	/**
	 * Setter creado para desactivar botones
	 * 
	 * @param botonesJuego
	 */
	public void setBotonesJuego(JButton[][] botonesJuego) {
		this.botonesJuego = botonesJuego;
	}

	// Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setLocationRelativeTo(null); // Para que aparezca en el centro de la pantalla
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
	}

	// Inicializa todos los componentes del frame
	public void inicializarComponentes() {

		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10, 10));

		botonEmpezar = new JButton("Go!");
		botonEmpezar.setFont(fuenteC);
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setFont(fuenteC);
		pantallaPuntuacion.setBackground(colorFondo);
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores y fuentes.
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelImagen.setBackground(colorFondo);
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelEmpezar.setBackground(colorFondo);
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelPuntuacion.setBackground(colorFondo);
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));
		panelJuego.setBackground(colorFondo);

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
				panelesJuego[i][j].setBackground(colorFondo);
			}
		}

		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		// BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);

	}

	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el
	 * programa
	 */
	public void inicializarListeners() {
		
		// Añado un Listerner a cada boton
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j].addActionListener(new ActionBoton(this, i, j));
			}
		}

		// Añado un Listener al Boton "GO" para que reinicie el juego
		botonEmpezar.addActionListener(new ActionListener() {
			@Override
			// Quito los paneles del juego
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < juego.LADO_TABLERO; i++) {
					for (int j = 0; j < juego.LADO_TABLERO; j++) {
						panelesJuego[i][j].removeAll();
					}
				}
				reset(); // Reinicia los botones del juego
				juego.inicializarPartida();
				juego.depurarTablero();
				refrescarPantalla();
			}
		});
	}

	/**
	 * Metodo que reinicia los botones
	 */
	private void reset() {
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego.length; j++) {
				botonesJuego[i][j].addActionListener(new ActionBoton(this, i, j));
			}
		}
	}

	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de la celda Saca el
	 * botón que haya en la celda determinada y añade un JLabel centrado y no
	 * editable con el número de minas alrededor. Se pinta el color del texto según
	 * la siguiente correspondecia (consultar la variable correspondeciaColor): - 0
	 * : negro - 1 : cyan - 2 : verde - 3 : naranja - 4 ó más : rojo
	 * 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {
		// Selecionar el panel[][] correspondiente
		// Eliminar todos sus componentes
		JLabel jLabel = new JLabel();
		panelesJuego[i][j].removeAll();

		// Añadir un JLabel
		// El numero de minas se saca de ControlJuego() con getMinasAlrededor()
		if (getJuego().getMinasAlrededor(i, j) != 0) {
			jLabel.setText(String.valueOf(getJuego().getMinasAlrededor(i, j)));
			jLabel.setForeground(correspondenciaColores[Integer.parseInt(jLabel.getText())]);
		}
		
		//jLabel.setText(Integer.toString(juego.getMinasAlrededor(i, j)));
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setForeground(correspondenciaColores[juego.getMinasAlrededor(i, j)]);

		panelesJuego[i][j].add(jLabel);
		actualizarPuntuacion();
		refrescarPantalla();
	}

	/**
	 * Muestra una ventana que indica el fin del juego
	 * 
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha
	 *                     explotado una mina (true) o bien porque hemos desactivado
	 *                     todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {

		// DESACTIVAR BOTONES
		for (int i = 0; i < getJuego().LADO_TABLERO; i++) {
			for (int j = 0; j < getJuego().LADO_TABLERO; j++) {
				getBotonesJuego()[i][j].setEnabled(false);

				// Si son minas insertamos la imagen
				if (juego.getMinasAlrededor(i, j) == -1) {
					botonesJuego[i][j].setText("");
					botonesJuego[i][j].setIcon(iExplosion);
					getBotonesJuego()[i][j].setEnabled(true);
					botonesJuego[i][j].setBackground(colorFondo);
				}
			}
		}

		// Si recibe true hemos explotado una mina y false si no quedan minas
		if (porExplosion) {
			JOptionPane.showMessageDialog(null, "Has explotado una mina\nPuntuacion: " + getJuego().getPuntuacion());
		} else {
			JOptionPane.showMessageDialog(null,
					"¡¡¡Has desactivado todas las casillas!!!\nPuntuacion: " + getJuego().getPuntuacion());
		}
	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(Integer.toString(getJuego().getPuntuacion()));
	}

	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS
		// COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}
}
