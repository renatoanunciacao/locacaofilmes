/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Autorizacao;
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
public class TestePersistirAutorizacao {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirAutorizacao() {
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
            Autorizacao a = new Autorizacao();
            a.setApelido("BIGBOSS");
            a.setDetalhes("Grande Chefe que comanda o sistema");
            Autorizacao a1 = new Autorizacao();
            a1.setApelido(("CLIENTE"));
            a1.setDetalhes("Area do cliente");
            em.getTransaction().begin();
            em.persist(a);
            em.persist(a1);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        }
        Assert.assertEquals(false, exception);
    }

}
