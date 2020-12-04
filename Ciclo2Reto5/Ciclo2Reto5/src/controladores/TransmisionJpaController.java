/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Transmision;
import modelos.TransmisionPK;

/**
 *
 * @author norma
 */
public class TransmisionJpaController implements Serializable {

    public TransmisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transmision transmision) throws PreexistingEntityException, Exception {
        if (transmision.getTransmisionPK() == null) {
            transmision.setTransmisionPK(new TransmisionPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transmision);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTransmision(transmision.getTransmisionPK()) != null) {
                throw new PreexistingEntityException("Transmision " + transmision + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transmision transmision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transmision = em.merge(transmision);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TransmisionPK id = transmision.getTransmisionPK();
                if (findTransmision(id) == null) {
                    throw new NonexistentEntityException("The transmision with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TransmisionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transmision transmision;
            try {
                transmision = em.getReference(Transmision.class, id);
                transmision.getTransmisionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transmision with id " + id + " no longer exists.", enfe);
            }
            em.remove(transmision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transmision> findTransmisionEntities() {
        return findTransmisionEntities(true, -1, -1);
    }

    public List<Transmision> findTransmisionEntities(int maxResults, int firstResult) {
        return findTransmisionEntities(false, maxResults, firstResult);
    }

    private List<Transmision> findTransmisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transmision.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Transmision findTransmision(TransmisionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transmision.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransmisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transmision> rt = cq.from(Transmision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
