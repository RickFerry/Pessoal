
package com.mycompany.agenda.dao;

import com.mycompany.agenda.model.TipoContato;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class ComboBoxGenericoDao<T> {
    
    private ObservableList<T> obsList = FXCollections.observableArrayList();
    
        public ObservableList<T> comboBox(String nomeClasse){
            List<T> lista = new ArrayList<>();
            Session sessao = ConexaoBanco.getSessionFactory().openSession();
            sessao.beginTransaction();
            lista = sessao.createQuery(" from " + nomeClasse).getResultList();
            sessao.getTransaction().commit();
        
        for (T t : lista) {
            obsList.add(t);
        }
        return obsList;
    }
}
