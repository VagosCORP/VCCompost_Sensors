package vccompost.sensors;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import vccompost.sensors.Adquirir_Temperaturas.AdListener;

public class LayoutSensorsController implements Initializable {
	
	Adquirir_Temperaturas at;
	Thread th;
	
	ObservableList<Float> itemss1 = FXCollections.observableArrayList();
	ObservableList<Float> itemss2 = FXCollections.observableArrayList();
	ObservableList<Float> itemss3 = FXCollections.observableArrayList();
	ObservableList<Float> itemss4 = FXCollections.observableArrayList();
	ObservableList<Float> itemss5 = FXCollections.observableArrayList();
	ObservableList<Float> itemss6 = FXCollections.observableArrayList();
	ObservableList<Float> itemss7 = FXCollections.observableArrayList();
	ObservableList<Float> itemss8 = FXCollections.observableArrayList();
	
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
		text.appendText("Adquisición Iniciada!\r\n");
		at = new Adquirir_Temperaturas();
		at.setAdListener(new AdListener() {

			@Override
			public void OnDataReceived() {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						itemss1.add(at.sen[1]);
						itemss2.add(at.sen[2]);
						itemss3.add(at.sen[3]);
						itemss4.add(at.sen[4]);
						itemss5.add(at.sen[5]);
						itemss6.add(at.sen[6]);
						itemss7.add(at.sen[7]);
						itemss8.add(at.sen[8]);
						list1.setItems(itemss1);
						list2.setItems(itemss2);
						list3.setItems(itemss3);
						list4.setItems(itemss4);
						list5.setItems(itemss5);
						list6.setItems(itemss6);
						list7.setItems(itemss7);
						list8.setItems(itemss8);
					}
				});
			}

			@Override
			public void OnProcTerminated() {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						text.appendText("Adquisición Terminada!\r\n");
						itemss1.add(at.sen[1]);
						itemss2.add(at.sen[2]);
						itemss3.add(at.sen[3]);
						itemss4.add(at.sen[4]);
						itemss5.add(at.sen[5]);
						itemss6.add(at.sen[6]);
						itemss7.add(at.sen[7]);
						itemss8.add(at.sen[8]);
						list1.setItems(itemss1);
						list2.setItems(itemss2);
						list3.setItems(itemss3);
						list4.setItems(itemss4);
						list5.setItems(itemss5);
						list6.setItems(itemss6);
						list7.setItems(itemss7);
						list8.setItems(itemss8);
					}
				});
			}
			
		});
		th = new Thread(at);
		th.setDaemon(true);
		th.start();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
//	text.appendText(
//	" S1 = "+at.sen[1]+
//	" S2 = "+at.sen[2]+
//	" S3 = "+at.sen[3]+
//	" S4 = "+at.sen[4]+
//	"\r\n"	+
//	" S5 = "+at.sen[5]+
//	" S6 = "+at.sen[6]+
//	" S7 = "+at.sen[7]+
//	" S8 = "+at.sen[8]+
//	"\r\n");
	
}
