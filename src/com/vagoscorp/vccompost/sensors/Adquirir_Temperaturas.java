package com.vagoscorp.vccompost.sensors;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import vclibs.communication.Eventos.OnComunicationListener;
import vclibs.communication.Eventos.OnConnectionListener;
import vclibs.communication.Eventos.OnTimeOutListener;
import vclibs.communication.javafx.Comunic;
import vclibs.communication.javafx.TimeOut;

import com.vagoscorp.vccompost.sensors.Procesar_Temperaturas.OnTempProcessListener;

public class Adquirir_Temperaturas extends Task<Integer>{
	
	Procesar_Temperaturas proctemp;
	Thread th, thto;
	Comunic comunic;
	TimeOut timeout;
//	boolean timeout_enabled = false;
	boolean estable = false;
	int cont = 0;
	int contx = 0;
	Timeline timer;
	
	String tarea = "";
	List<Float> sen1;
	List<Float> sen2;
	List<Float> sen3;
	List<Float> sen4;
	List<Float> sen5;
	List<Float> sen6;
	List<Float> sen7;
	List<Float> sen8;
	List<Float> sen1F;
	List<Float> sen2F;
	List<Float> sen3F;
	List<Float> sen4F;
	List<Float> sen5F;
	List<Float> sen6F;
	List<Float> sen7F;
	List<Float> sen8F;
	float[] resS0 = {0,0,0,0,0,0,0,0};
	float[] resS = {0,0,0,0,0,0,0,0};
	int[] resE = {0,0,0,0,0,0,0,0};
	public float[] resF = {0,0,0,0,0,0,0,0};
	
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
		sen1F = new ArrayList<Float>();
		sen2F = new ArrayList<Float>();
		sen3F = new ArrayList<Float>();
		sen4F = new ArrayList<Float>();
		sen5F = new ArrayList<Float>();
		sen6F = new ArrayList<Float>();
		sen7F = new ArrayList<Float>();
		sen8F = new ArrayList<Float>();
	}
	
	public void protocolo() {
		timer = new Timeline(new KeyFrame(Duration.seconds(70),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if(!estable)
							adquirir(100);
						else
							adquirir(250);
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
	
	@Override
	protected Integer call() throws Exception {
//		adquirir(250);
		protocolo();
		return null;
	}
	
	public void adquirir(final int num) {
		tarea = "";
		comunic = new Comunic("20.0.0.6", 2000);
		comunic.setConnectionListener(new OnConnectionListener() {
			
			@Override
			public void onConnectionstablished() {
				timeout = new TimeOut((long)200*num);//~160
				timeout.setTimeOutListener(new OnTimeOutListener() {
					
					@Override
					public void onTimeOutEnabled() {
//						timeout_enabled = true;
					}
					
					@Override
					public void onTimeOutCancelled() {
//						timeout_enabled = false;
					}
					
					@Override
					public void onTimeOut() {
//						timeout_enabled = false;
						comunic.Detener_Actividad();
					}
				});
				thto = new Thread(timeout);
				thto.setDaemon(true);
				thto.start();
				comunic.enviar("A");
				comunic.enviar(num);
			}
			
			@Override
			public void onConnectionfinished() {
				
			}
		});
		comunic.setComunicationListener(new OnComunicationListener() {
			
			@Override
			public void onDataReceived(String dato) {
				tarea += dato;
				if(dato.endsWith("/")) {
					timeout.cancel();
					comunic.Detener_Actividad();
					procesar(tarea);
					tarea = "";
				}
			}
		});
		th =  new Thread(comunic);
		th.setDaemon(true);
		th.start();
	}
	
	public void procesar(String data) {
		proctemp = new Procesar_Temperaturas(data);
		proctemp.setOnTempProcessListener(new OnTempProcessListener() {
			
			@Override
			public void onTProcessStarted(int avance) {
				
			}
			
			@Override
			public void onTProcessFinished() {
				sen1 = proctemp.sen1;
				sen2 = proctemp.sen2;
				sen3 = proctemp.sen3;
				sen4 = proctemp.sen4;
				sen5 = proctemp.sen5;
				sen6 = proctemp.sen6;
				sen7 = proctemp.sen7;
				sen8 = proctemp.sen8;
				resS = proctemp.resS;
				resE = proctemp.resE;
				if(listenerx != null)
					listenerx.dataRCV();
				if(!estable) {
					int l = resE.length;
					for(int i = 0; i< l;i++) {
						float dif = resS[i] - resS0[i];
						if(dif < 1) {
							estable = true;
						}else {
							estable = false;
							resS0 = resS;
							break;
						}
					}
					if(estable)
						System.out.println("Temperatura Estable");
					else
						System.out.println("Temperatura Inestable");
				}else {
					System.out.println("Temperatura Estable");
					if(cont < 4) {
						sen1F.addAll(sen1);
						sen2F.addAll(sen2);
						sen3F.addAll(sen3);
						sen4F.addAll(sen4);
						sen5F.addAll(sen5);
						sen6F.addAll(sen6);
						sen7F.addAll(sen7);
						sen8F.addAll(sen8);
						cont++;
						System.out.println("Muestra Parcial " + cont + "/4 Terminada!");
						if(cont == 4) {
							timer.stop();
						}
						for(int i = 0; i<8; i++) {
							resF[i] += resS[i]; 
						}
						if(cont == 4) {
							for(int i = 0; i<8; i++)
								resF[i] = resF[i]/4;
							cont = 0;
							if(listenerx != null)
								listenerx.procTerminated();
						}
					}
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
