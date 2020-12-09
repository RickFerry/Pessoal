
package com.mycompany.agenda.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerta {
    
    static ButtonType btnConfirmar = new ButtonType("Confirmar");
    static ButtonType btnCancelar = new ButtonType("Cancelar");
    static boolean resposta;
    
    public static void msgInformacao(String msg){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Alertas Sobre Cadastros");
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
    
    public static boolean msgConfirmacao(String msgExclusao){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText("Eclusão de Registros");
        alerta.setContentText("Deseja realmente excluir " + msgExclusao + "?");
        alerta.getButtonTypes().setAll(btnConfirmar, btnCancelar);
        
        alerta.showAndWait().ifPresent(b -> {
            if(b == btnConfirmar){
                resposta = true;
            }else{
                resposta = false;
            }
        });
        return resposta;
    }
}
