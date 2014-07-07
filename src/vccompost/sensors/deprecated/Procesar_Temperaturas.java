package vccompost.sensors.deprecated;

import java.util.List;

import javax.swing.SwingWorker;

public class Procesar_Temperaturas extends SwingWorker<Integer, Integer> {

	String trabajo = "";

	float[] sen = {0, 0, 0, 0, 0, 0, 0, 0, 0};

	public static float ERROR = (float)999.9999;
	
	OnTempProcessListener onTPListener;

	public interface OnTempProcessListener {
		public void onTProcessStarted(int avance);
		public void onTProcessFinished();
	}

	public void setOnTempProcessListener(OnTempProcessListener otpl) {
		onTPListener = otpl;
	}

	public Procesar_Temperaturas(String tarea) {
		trabajo = tarea;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		// LT8-WL
		// sensor1:
		// 1;38.2979&
		// Funcionamiento Correcto
		// Esperando Comandos.....
		// =>A
		// Toma de 250 Muestras Iniciada!#
		// ;1;38.2979&
		// ;2;38.2979&
		// ;3;38.2979&
		// ;4;38.2979&
		// ;5;38.2979&
		// ;6;38.2979&
		// ;7;38.2979&
		// ;8;38.2979&
		// #Toma de 250 Muestras Terminada!
		// #1#/

		// Bx
		// Toma de 250*x Muestras Iniciada!#
		// ;1;38.2979&
		// ;2;38.2979&
		// ;3;38.2979&
		// ;4;38.2979&
		// ;5;38.2979&
		// ;6;38.2979&
		// ;7;38.2979&
		// ;8;38.2979&
		// #Toma de 250*x Muestras Terminada!
		// #250*x#/

		String[] p1 = trabajo.split("#");
		int f = p1.length;
		publish(f);
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
//						e.printStackTrace();
						System.out.println("Error en Sensor " + nsen);
					}
					sen[nsen] = vsen;
				}
			}
		}
		return f;
	}

	@Override
	protected void done() {
		if (onTPListener != null)
			onTPListener.onTProcessFinished();
		super.done();
	}

	@Override
	protected void process(List<Integer> chunks) {
		if (onTPListener != null)
			onTPListener.onTProcessStarted(chunks.get(0));
		super.process(chunks);
	}
}
