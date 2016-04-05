package it.polito.tdp.permuta;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.permuta.model.PermutaModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;

public class PermutaController {

	private PermutaModel model ;
	
    public void setModel(PermutaModel model) {
		this.model = model;
	}


	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<Integer> spinLungh;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcola(ActionEvent event) {
    	
    	// legge il valore selezionato nello spinner
    	Integer lungh = spinLungh.getValue() ;
    	
    	// calcola le permutazioni
    	model.crea(lungh);
    	List<List<Integer>> allPerm = model.permuta() ;
    	
    	// mostra il risultato
    	// usa uno StringBuffer perché chiamare molte volte .appendText è troppo lento
    	StringBuffer sb = new StringBuffer() ;
    	txtResult.clear();
    	for( List<Integer> perm : allPerm ) {
    		sb.append(perm.toString());
    		sb.append('\n') ;
    	}
    	
    	txtResult.setText(sb.toString());
    }


    @FXML
    void initialize() {
        assert spinLungh != null : "fx:id=\"spinLungh\" was not injected: check your FXML file 'Permuta.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Permuta.fxml'.";
        
        // Definisce il contenuto dello spinner: numeri interi tra 1 e 10
        spinLungh.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10)) ; // define range
        // Definisce il valore iniziale
        spinLungh.getValueFactory().setValue(4); // set starting (default) value

    }
}
