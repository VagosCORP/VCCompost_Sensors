package vccompost.sensors.deprecated;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VCCompost_Sensors extends Application /*implements OnComunicationListener*/ {
	
//	Procesar_Temperaturas pt;
//	Comunic comunic;
//	Thread th;
//	List<Float> sen1;
//	List<Float> sen2;
//	List<Float> sen3;
//	List<Float> sen4;
//	List<Float> sen5;
//	List<Float> sen6;
//	List<Float> sen7;
//	List<Float> sen8;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("LayoutSensors.fxml"));
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
//			sas();
//			comunic = new Comunic(2000);
//			comunic.setComunicationListener(this/*new OnComunicationListener() {
//				
//				@Override
//				public void onDataReceived(String dato) {
//					LayoutSensorsController.text.appendText(dato);
//				}
//			}*/);
//			th = new Thread(comunic);
//			th.setDaemon(true);
//			th.start();
//			comunic.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
//	public void sas() {
//		pt =  new Procesar_Temperaturas(
////				"LT8-WL"
////				+ "sensor1:"
////				+ "1;38.2979&"
////				+ "Funcionamiento Correcto"
////				+ "Esperando Comandos....."+ 
//				"Toma de 13 Muestras Iniciada!#"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "1;38.2979&2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//				+ "#Toma de 13 Muestras Terminada!#13#/");
//		pt.setOnTempProcessListener(new OnTempProcessListener() {
//			
//			@Override
//			public void onTProcessStarted(int avance) {
//				System.out.println("onTProcessStarted " + avance);
//			}
//			
//			@Override
//			public void onTProcessFinished() {
//				System.out.println("onTProcessFinished");
//				sen1 = pt.sen1;
//				sen2 = pt.sen2;
//				sen3 = pt.sen3;
//				sen4 = pt.sen4;
//				sen5 = pt.sen5;
//				sen6 = pt.sen6;
//				sen7 = pt.sen7;
//				sen8 = pt.sen8;
//				
//				for(int i = 0; i<pt.nmu; i++) {
//					final String jabapar = "\n\rsen1 = "+sen1.get(i)+" sen2 = "+sen2.get(i)+" sen3 = "+
//							sen3.get(i)+" sen4 = "+sen4.get(i)+" sen5 = "+sen5.get(i)+" sen6 = "+
//							sen6.get(i)+" sen7 = "+sen7.get(i)+" sen8 = "+sen8.get(i);
//					System.out.println(jabapar);
//					LayoutSensorsController.text.appendText(jabapar);
//					LayoutSensorsController.Label1.setText(jabapar);
////					Platform.runLater(new Runnable() {
////						
////						@Override
////						public void run() {
////							text.appendText(jabapar);
////						}
////					});
//				}
//			}
//		});
//		pt.execute();
//	}

//	@Override
//	public void onDataReceived(String dato) {
//		LayoutSensorsController.text.appendText(dato);
//		LayoutSensorsController.Label1.setText(dato);
//	}
	
}
