package com.vagoscorp.vccompost.sensors;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

public class Procesar_Temperaturas extends SwingWorker<Integer, Integer> {

	String trabajo = "";

	List<Float> sen1;
	List<Float> sen2;
	List<Float> sen3;
	List<Float> sen4;
	List<Float> sen5;
	List<Float> sen6;
	List<Float> sen7;
	List<Float> sen8;
	float[] resS = {0,0,0,0,0,0,0,0};
	int[] resE = {0,0,0,0,0,0,0,0};

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
		sen1 = new ArrayList<Float>();
		sen2 = new ArrayList<Float>();
		sen3 = new ArrayList<Float>();
		sen4 = new ArrayList<Float>();
		sen5 = new ArrayList<Float>();
		sen6 = new ArrayList<Float>();
		sen7 = new ArrayList<Float>();
		sen8 = new ArrayList<Float>();
	}

	@Override
	protected Integer doInBackground() throws Exception {
		// LT8-WL
		// sensor1:
		// 1;38.2979&
		// Funcionamiento Correcto
		// Esperando Comandos.....
		// =>A + 1
		// Toma de 1 Muestras Iniciada!#
		// ;1;38.2979&
		// ;2;38.2979&
		// ;3;38.2979&
		// ;4;38.2979&
		// ;5;38.2979&
		// ;6;38.2979&
		// ;7;38.2979&
		// ;8;38.2979&
		// #Toma de 1 Muestras Terminada!
		// #1#/

		// B1
		// 1;38.2979&

		// C
		// LT8-WL
		// Sensor 2
		// T=38.2979

		String[] p1 = trabajo.split("#");
		int f = p1.length;
		publish(f);
		if (f <= 4) {
			System.out.println("cancelé o algo salió mal");
			// cancelé o algo salió mal
		} else if (f == 5) {
			String[] vals = p1[1].split("&\r\n");
			int l = vals.length;
			System.out.println("l = " + l);
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
					}
					if(nsen == 1) {
						sen1.add(vsen);
					}else if(nsen == 2)
						sen2.add(vsen);
					else if(nsen == 3)
						sen3.add(vsen);
					else if(nsen == 4)
						sen4.add(vsen);
					else if(nsen == 5)
						sen5.add(vsen);
					else if(nsen == 6)
						sen6.add(vsen);
					else if(nsen == 7)
						sen7.add(vsen);
					else if(nsen == 8)
						sen8.add(vsen);
				}
			}
			float[][] ss = {promedio(sen1),promedio(sen2),promedio(sen3),promedio(sen4),
					promedio(sen5),promedio(sen6),promedio(sen7),promedio(sen8)};
			for(int i = 0;i <= 7; i++) {
				resS[i] = ss[i][0];
				resE[i] = (int)ss[i][1];
			}
		}
		return f;
	}
	
	public float[] promedio(List<Float> list) {
		int len = list.size();
		float[] res = new float[2];
		float rsum = 0;
		int err = 0;
		for(int i = 0; i<len; i++) {
			float temp = list.get(i);
			if(temp > 900) {
				err++;
			}else {
				rsum += temp;
			}
		}
		if(len - err < 40) {
			res[0] = ERROR;
		}else {
			res[0] = (float) rsum/(float)(len-err);
		}
		res[1] = (float) err;
		return res;
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
