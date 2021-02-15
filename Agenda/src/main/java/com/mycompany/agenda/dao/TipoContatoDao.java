
package com.mycompany.agenda.dao;

import com.mycompany.agenda.model.Contato;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class TipoContatoDao {
    
    public static boolean liberaExclusao(Long id){
        List<Contato> lista = new ArrayList<>();
        Session sessao = ConexaoBanco.getSessionFactory().openSession();
        sessao.beginTransaction();
        lista = sessao.createQuery(" from Contato where tipoContato_id = " + id).getResultList();
        sessao.getTransaction().commit();
        sessao.close();
        
        if(lista.isEmpty()){
            return true;
        }
        return false;
    }
}