package com.vagoscorp.vccompost.sensors;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.vagoscorp.vccompost.sensors.Adquirir_Temperaturas.listener;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import libraries.vagoscorp.comunication.fx.Comunic;

public class LayoutSensorsController implements Initializable {

	Adquirir_Temperaturas at;
	Comunic comunic;
	Thread th;
	String jabapar = "";
	int[][] aa = new int[2][];
	int[] a = {1,2,3,4,5};
	
	
	List<Float> sen1;
	List<Float> sen2;
	List<Float> sen3;
	List<Float> sen4;
	List<Float> sen5;
	List<Float> sen6;
	List<Float> sen7;
	List<Float> sen8;
	
	@FXML Button But1;
	@FXML
	static TextArea text;
	@FXML
	static Label Label1;
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
				text.setText("S1 = "+at.resS[0]+
						" S2 = "+at.resS[1]+
						" S3 = "+at.resS[2]+
						" S4 = "+at.resS[3]+
						" \r\nS5 = "+at.resS[4]+
						" S6 = "+at.resS[5]+
						" S7 = "+at.resS[6]+
						" S8 = "+at.resS[7]);
				final ObservableList<Float> itemss1  = FXCollections.observableArrayList(at.sen1);
				final ObservableList<Float> itemss2  = FXCollections.observableArrayList(at.sen2);
				final ObservableList<Float> itemss3  = FXCollections.observableArrayList(at.sen3);
				final ObservableList<Float> itemss4  = FXCollections.observableArrayList(at.sen4);
				final ObservableList<Float> itemss5  = FXCollections.observableArrayList(at.sen5);
				final ObservableList<Float> itemss6  = FXCollections.observableArrayList(at.sen6);
				final ObservableList<Float> itemss7  = FXCollections.observableArrayList(at.sen7);
				final ObservableList<Float> itemss8  = FXCollections.observableArrayList(at.sen8);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
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
			public void procTerminated() {
				text.setText("S1F = "+at.resF[0]+
						" S2F = "+at.resF[1]+
						" S3F = "+at.resF[2]+
						" S4F = "+at.resF[3]+
						" \r\nS5F = "+at.resF[4]+
						" S6F = "+at.resF[5]+
						" S7F = "+at.resF[6]+
						" S8F = "+at.resF[7]);
				final ObservableList<Float> itemss1  = FXCollections.observableArrayList(at.sen1F);
				final ObservableList<Float> itemss2  = FXCollections.observableArrayList(at.sen2F);
				final ObservableList<Float> itemss3  = FXCollections.observableArrayList(at.sen3F);
				final ObservableList<Float> itemss4  = FXCollections.observableArrayList(at.sen4F);
				final ObservableList<Float> itemss5  = FXCollections.observableArrayList(at.sen5F);
				final ObservableList<Float> itemss6  = FXCollections.observableArrayList(at.sen6F);
				final ObservableList<Float> itemss7  = FXCollections.observableArrayList(at.sen7F);
				final ObservableList<Float> itemss8  = FXCollections.observableArrayList(at.sen8F);
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
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
