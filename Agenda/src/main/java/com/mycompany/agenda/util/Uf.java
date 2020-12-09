
package com.mycompany.agenda.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Uf {
    
    private static ObservableList<String> obsList;
    
    public static ObservableList gerarUf(){
        obsList = FXCollections.observableArrayList(
                "SP", "RJ", "MG", "DF", "RS"
        );
        return obsList;
    }
}
