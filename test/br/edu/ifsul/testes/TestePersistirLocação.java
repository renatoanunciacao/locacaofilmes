/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Locacao;
import br.edu.ifsul.modelo.Pessoa;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexv
 */
public class TestePersistirLocação {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirLocação() {
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
    public void persistirLocacao(){
        boolean exception = false;
        try{
            Locacao l = new Locacao();
            Pessoa p = em.find(Pessoa.class, 1);
            l.setEmprestimo(new GregorianCalendar(2017, Calendar.FEBRUARY, 05));
            l.setDevolucao(new GregorianCalendar(2017, Calendar.MARCH, 05));
            l.setPessoa(p);
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        //verifico se não ocorreu exceção para passar no teste
        Assert.assertEquals(false, exception);
    }
    
}
