package vccompost.sensors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import vclibs.communication.Eventos.OnComunicationListener;
import vclibs.communication.Eventos.OnConnectionListener;
import vclibs.communication.javafx.Comunic;

public class Adquirir_Temperaturas extends Task<Integer> {

	boolean estable = false;
	Thread th;
	Comunic comunic;
	Timeline timer;
	String tarea = "";
	public float[] sen		= { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	float[] sen0	= { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public static float ERROR = (float) 999.9999;

	AdListener adListener;

	public interface AdListener {
		public void OnDataReceived();
		public void OnProcTerminated();
	}

	public void setAdListener(AdListener listener) {
		adListener = listener;
	}

	public Adquirir_Temperaturas() {
		estable = false;
		for(int i = 1; i< 9; i++)
			sen0[i] = 0;
	}

	public void protocolo() {
		timer = new Timeline(new KeyFrame(Duration.seconds(10),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if(!estable)
							adquirir(1);
						else {
							adquirir(4); //4 = 1000 muestras
							timer.stop();
						}
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	@Override
	protected Integer call() throws Exception {
		protocolo();
		return null;
	}

	void procesar(String datos) {
		String[] p1 = datos.split("#");
		int f = p1.length;
		if (f <= 4) {
			System.out.println("Algo salió mal");
		} else if (f == 5) {
			String[] vals = p1[1].split("&");
			int l = vals.length;
			int nsen = 0;
			float vsen = 0;
			for (int i = 0; i < l; i++) {
				String[] ap = vals[i].split(";");
				if (ap.length == 3) {
					nsen = Integer.parseInt(ap[1]);
					try {
						vsen = Float.parseFloat(ap[2]);
					} catch (Exception e) {
						vsen = ERROR;
						System.out.println("Error en Sensor " + nsen);
					}
					sen[nsen] = vsen;
				}
			}
			if (!estable) {
				for (int i = 1; i < 9; i++) {
					float dif = (float)sen[i] - sen0[i];
//					System.out.println("sen = " + Float.toString(sen[i]));
//					System.out.println("sen0 = " + Float.toString(sen0[i]));
//					System.out.println("dif = " + Float.toString(dif));
					if (Math.abs(dif) < 0.6) {
						estable = true;
					} else {
						estable = false;
						sen0 = sen.clone();
						break;
					}
				}
				if (estable)
					System.out.println("Temperatura Estable");
				else
					System.out.println("Temperatura Inestable");
			}
		}
	}

	public void adquirir(int n250) {
		comunic = new Comunic("20.0.0.6", 2000);
		comunic.setConnectionListener(new OnConnectionListener() {

			@Override
			public void onConnectionstablished() {
				if (n250 == 1) {
					comunic.enviar('A');
				} else {
					comunic.enviar('B');
					comunic.enviar(n250);
				}
			}

			@Override
			public void onConnectionfinished() {

			}
		});
		comunic.setComunicationListener(new OnComunicationListener() {

			@Override
			public void onDataReceived(String dato) {
				tarea += dato;
				if (dato.endsWith("/")) {
					comunic.Detener_Actividad();
					procesar(tarea);
					tarea = "";
					if (n250 == 4) {
						System.out.println("Temperatura Estable");
						if (adListener != null)
							adListener.OnProcTerminated();
					}
					else {
						if (adListener != null)
							adListener.OnDataReceived();
					}
				}
			}
		});
		th = new Thread(comunic);
		th.setDaemon(true);
		th.start();

	}
}