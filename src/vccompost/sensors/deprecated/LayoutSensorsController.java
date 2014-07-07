package vccompost.sensors.deprecated;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import vccompost.sensors.deprecated.Adquirir_Temperaturas.listener;
import vclibs.communication.javafx.Comunic;

public class LayoutSensorsController implements Initializable {

	Adquirir_Temperaturas at;
	Comunic comunic;
	Thread th;
	
	@FXML Button But1;
	@FXML TextArea text;
	@FXML Label Label1;
	@FXML ListView<Float> list1;
	@FXML ListView<Float> list2;
	@FXML ListView<Float> list3;
	@FXML ListView<Float> list4;
	@FXML ListView<Float> list5;
	@FXML ListView<Float> list6;
	@FXML ListView<Float> list7;
	@FXML ListView<Float> list8;
	
	@FXML public void But1Click() {
		text.setText("boton precionado");
//		at.adquirir(10);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		at = new Adquirir_Temperaturas();
		at.setListener(new listener() {

			@Override
			public void dataRCV() {
//				text.setText("S1 = "+at.sen[0]+
//						" S2 = "+at.sen[1]+
//						" S3 = "+at.sen[2]+
//						" S4 = "+at.sen[3]+
//						" \r\nS5 = "+at.sen[4]+
//						" S6 = "+at.sen[5]+
//						" S7 = "+at.sen[6]+
//						" S8 = "+at.sen[7]);
//				final ObservableList<Float> itemss1  = FXCollections.observableArrayList(at.sen[1]);
//				final ObservableList<Float> itemss2  = FXCollections.observableArrayList(at.sen[2]);
//				final ObservableList<Float> itemss3  = FXCollections.observableArrayList(at.sen[3]);
//				final ObservableList<Float> itemss4  = FXCollections.observableArrayList(at.sen[4]);
//				final ObservableList<Float> itemss5  = FXCollections.observableArrayList(at.sen[5]);
//				final ObservableList<Float> itemss6  = FXCollections.observableArrayList(at.sen[6]);
//				final ObservableList<Float> itemss7  = FXCollections.observableArrayList(at.sen[7]);
//				final ObservableList<Float> itemss8  = FXCollections.observableArrayList(at.sen[8]);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						text.appendText(
								" S1 = "+at.sen[1]+
								" S2 = "+at.sen[2]+
								" S3 = "+at.sen[3]+
								" S4 = "+at.sen[4]+
								"\r\n"	+
								" S5 = "+at.sen[5]+
								" S6 = "+at.sen[6]+
								" S7 = "+at.sen[7]+
								" S8 = "+at.sen[8]+
								"\r\n");
//						list1.setItems(itemss1);
//						list2.setItems(itemss2);
//						list3.setItems(itemss3);
//						list4.setItems(itemss4);
//						list5.setItems(itemss5);
//						list6.setItems(itemss6);
//						list7.setItems(itemss7);
//						list8.setItems(itemss8);
					}
				});
			}

			@Override
			public void procTerminated() {
//				text.setText("S1F = "+at.resF[0]+
//						" S2F = "+at.resF[1]+
//						" S3F = "+at.resF[2]+
//						" S4F = "+at.resF[3]+
//						" \r\n"  +
//						" S5F = "+at.resF[4]+
//						" S6F = "+at.resF[5]+
//						" S7F = "+at.resF[6]+
//						" S8F = "+at.resF[7]);
//				final ObservableList<Float> itemss1  = FXCollections.observableArrayList(at.senF[1]);
//				final ObservableList<Float> itemss2  = FXCollections.observableArrayList(at.senF[2]);
//				final ObservableList<Float> itemss3  = FXCollections.observableArrayList(at.senF[3]);
//				final ObservableList<Float> itemss4  = FXCollections.observableArrayList(at.senF[4]);
//				final ObservableList<Float> itemss5  = FXCollections.observableArrayList(at.senF[5]);
//				final ObservableList<Float> itemss6  = FXCollections.observableArrayList(at.senF[6]);
//				final ObservableList<Float> itemss7  = FXCollections.observableArrayList(at.senF[7]);
//				final ObservableList<Float> itemss8  = FXCollections.observableArrayList(at.senF[8]);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						text.appendText(
								" S1F = "+at.sen[1]+
								" S2F = "+at.sen[2]+
								" S3F = "+at.sen[3]+
								" S4F = "+at.sen[4]+
								"\r\n"	 +
								" S5F = "+at.sen[5]+
								" S6F = "+at.sen[6]+
								" S7F = "+at.sen[7]+
								" S8F = "+at.sen[8]+
								"\r\n");
//						list1.setItems(itemss1);
//						list2.setItems(itemss2);
//						list3.setItems(itemss3);
//						list4.setItems(itemss4);
//						list5.setItems(itemss5);
//						list6.setItems(itemss6);
//						list7.setItems(itemss7);
//						list8.setItems(itemss8);
					}
				});
			}
		});
		th = new Thread(at);
		th.setDaemon(true);
		th.start();
	}
	
//	"LT8-WL"
//	+ "sensor1:"
//	+ "1;38.2979&"
//	+ "Funcionamiento Correcto"
//	+ "Esperando Comandos....."
//	+ "Toma de 13 Muestras Iniciada!#"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;Err&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&3;38.2979&4;38.2979&5;38.2979&6;38.2979&7;38.2979&8;38.2979&"
//	+ ";1;Err&;2;38.2979&;3;38.2979&;4;38.2979&;5;38.2979&;6;38.2979&;7;38.2979&;8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&;3;38.2979&;4;38.2979&;5;38.2979&;6;Err&;7;38.2979&;8;38.2979&"
//	+ ";1;38.2979&;2;38.2979&;3;38.2979&;4;38.2979&;5;38.2979&;6;38.2979&;7;38.2979&;8;38.2979&"
//	+ "#Toma de 13 Muestras Terminada!#13#/");

	
}
