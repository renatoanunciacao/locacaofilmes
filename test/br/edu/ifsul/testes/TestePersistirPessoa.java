/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Autorizacao;
import br.edu.ifsul.modelo.Pessoa;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Renato
 */
public class TestePersistirPessoa {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirPessoa() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("LOCACAOFILMESPU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void persistirPessoa(){
        boolean exception = false;
        try{
            em.getTransaction().begin();
            Pessoa p = new Pessoa();
            p.setNome("Aluno");
            p.setEndereco("Rua Teste");
            p.setData_nascimento(new GregorianCalendar(1995, Calendar.MAY, 20));
            p.setNickname("aluno1");
            p.setChaveAcesso("aluno1");
            Autorizacao a = em.find(Autorizacao.class, "CLIENTE");
           
            p.getAutorizacoes().add(a);
            
            em.persist(p);
            em.getTransaction().commit();
            
        }catch(Exception e){
             e.printStackTrace();
            exception = true;
        }
        Assert.assertEquals(false, exception);
    }
    
}
