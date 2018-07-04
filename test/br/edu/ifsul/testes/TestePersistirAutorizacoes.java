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
public class TestePersistirAutorizacoes {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirAutorizacoes() {
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
    public void persistirAutorizacao() {
        boolean exception = false;
        try {           
            Pessoa p = new Pessoa();
            p.setNome("Ciclano de tal");
            p.setEndereco("Padre Valentim");
            p.setData_nascimento(new GregorianCalendar(1991, Calendar.JULY, 02));
            p.setNickname("alex");
            p.setChaveAcesso("alex");
            Autorizacao a = em.find(Autorizacao.class, "BIGBOSS");

            p.getAutorizacoes().add(a);

            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        }
        Assert.assertEquals(false, exception);
    }

}
