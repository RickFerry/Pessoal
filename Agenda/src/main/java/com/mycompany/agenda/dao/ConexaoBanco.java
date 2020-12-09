 
package com.mycompany.agenda.dao;

import com.mycompany.agenda.model.Cidade;
import com.mycompany.agenda.model.Contato;
import com.mycompany.agenda.model.TipoContato;
import com.mycompany.agenda.model.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexaoBanco {
    
    private static SessionFactory conexao = null;
    private static Configuration configuracao;
    
    private static SessionFactory buildSessionFactory(){
        
        configuracao = new Configuration().configure();
        
        configuracao.setProperty("hibernate.connection.username", "root");
        configuracao.setProperty("hibernate.connection.password", "");
        
        configuracao.addPackage("com.mycompany.agenda.model").addAnnotatedClass(TipoContato.class);
        configuracao.addPackage("com.mycompany.agenda.model").addAnnotatedClass(Cidade.class);
        configuracao.addPackage("com.mycompany.agenda.model").addAnnotatedClass(Contato.class);
        configuracao.addPackage("com.mycompany.agenda.model").addAnnotatedClass(Usuario.class);
        
        conexao = configuracao.buildSessionFactory();
        return conexao;
    }
    
    public static SessionFactory getSessionFactory(){
    
        if(conexao == null){
            conexao = buildSessionFactory();
        }
        return conexao;
    }
}