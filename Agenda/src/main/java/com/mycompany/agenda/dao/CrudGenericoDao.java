
package com.mycompany.agenda.dao;

import com.mycompany.agenda.model.TipoContato;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CrudGenericoDao<T> {
   
    public boolean salvar(T tipo) throws HibernateException{
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(tipo);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    public boolean excluir(T t)throws HibernateException{
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
            session.close();
            return true;
    }
    
    public List<T> consultar(String descricao, String nomeClasse){
        List<T> lista = new ArrayList<>();
        
        Session sessao = ConexaoBanco.getSessionFactory().openSession();
        sessao.beginTransaction();
        
        if(descricao.length() == 0){
            lista = sessao.createQuery(" from " + nomeClasse).getResultList();
        }else{
            lista = sessao.createQuery(" from " + nomeClasse + " m where m.descricao"
                    + " like " + "'" + descricao + "%'").getResultList();
        } 
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }
    
}
