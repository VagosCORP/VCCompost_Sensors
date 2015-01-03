package vccompost.sensors.deprecated;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import vccompost.sensors.deprecated.Procesar_Temperaturas.OnTempProcessListener;
import vclibs.communication.Eventos.OnComunicationListener;
import vclibs.communication.Eventos.OnConnectionListener;
import vclibs.communication.Eventos.OnTimeOutListener;
import vclibs.communication.javafx.Comunic;
import vclibs.communication.javafx.TimeOut;

public class Adquirir_Temperaturas extends Task<Integer> {

	Procesar_Temperaturas proctemp;
	Thread th, thto;
	Comunic comunic;
	TimeOut timeout;
	// boolean timeout_enabled = false;
	boolean estable = false;
	int cont = 0;
	int contx = 0;
	Timeline timer;

	String tarea = "";
	public float[] sen = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	float[] senF = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	float[] resS0 = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	listener listenerx;

	public interface listener {
		public void dataRCV();

		public void procTerminated();
	}

	public void setListener(listener l) {
		listenerx = l;
	}

	public Adquirir_Temperaturas() {
		estable = false;
		cont = 0;
		contx = 0;
	}

	public void protocolo() {
		timer = new Timeline(new KeyFrame(Duration.seconds(5),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if(!estable)
							adquirir(1);
						else
							adquirir(4); //4 = 1000 muestras
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

	@Override
	protected Integer call() throws Exception {
		// adquirir(250);
		protocolo();
		return null;
	}

	public void adquirir(int cant) {
		tarea = "";
		comunic = new Comunic("20.0.0.6", 2000);
		comunic.setConnectionListener(new OnConnectionListener() {

			@Override
			public void onConnectionstablished() {
				timeout = new TimeOut(2000);// ~160
				timeout.setTimeOutListener(new OnTimeOutListener() {

					@Override
					public void onTimeOutEnabled() {
						// timeout_enabled = true;
					}

					@Override
					public void onTimeOutCancelled() {
						// timeout_enabled = false;
					}

					@Override
					public void onTimeOut() {
						// timeout_enabled = false;
						comunic.Detener_Actividad();
					}
				});
				thto = new Thread(timeout);
				thto.setDaemon(true);
				thto.start();
				if(cant == 1)
					comunic.enviar("A");
				else {
					comunic.enviar("B");
					comunic.enviar(cant);
				}
			}

			@Override
			public void onConnectionfinished() {

			}
		});
		comunic.setComunicationListener(new OnComunicationListener() {

			@Override
			public void onDataReceived(String dato, int[] ndato) {
				tarea += dato;
				if (dato.endsWith("/")) {
					timeout.cancel();
					comunic.Detener_Actividad();
					procesar(tarea);
					tarea = "";
				}
			}
		});
		th = new Thread(comunic);
		th.setDaemon(true);
		th.start();
	}

	public void procesar(String data) {
		proctemp = new Procesar_Temperaturas(data);
		proctemp.setOnTempProcessListener(new OnTempProcessListener() {

			@Override
			public void onTProcessStarted(int avance) {
				// System.out.println("Procesamiento iniciado");
			}

			@Override
			public void onTProcessFinished() {
				sen = proctemp.sen;
				if (listenerx != null)
					listenerx.dataRCV();
				if (!estable) {
					for (int i = 1; i < 9; i++) {
						float dif = sen[i] - resS0[i];
						if (dif < 1) {
							estable = true;
						} else {
							estable = false;
							resS0 = sen;
							break;
						}
					}
					if(estable)
						System.out.println("Temperatura Estable");
					else
						System.out.println("Temperatura Inestable");
				} else {
					System.out.println("Temperatura Estable");
					timer.stop();
					if (listenerx != null)
						listenerx.procTerminated();
				}
			}
		});
		proctemp.execute();
	}

	public void temporizar(int sec) {
		Timeline timer = new Timeline(new KeyFrame(Duration.seconds(sec),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}

}
