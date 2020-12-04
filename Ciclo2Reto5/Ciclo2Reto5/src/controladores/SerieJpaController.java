/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Contenido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelos.Serie;

/**
 *
 * @author norma
 */
public class SerieJpaController implements Serializable {

    public SerieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Serie serie) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Contenido contenidoOrphanCheck = serie.getContenido();
        if (contenidoOrphanCheck != null) {
            Serie oldSerieOfContenido = contenidoOrphanCheck.getSerie();
            if (oldSerieOfContenido != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Contenido " + contenidoOrphanCheck + " already has an item of type Serie whose contenido column cannot be null. Please make another selection for the contenido field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contenido contenido = serie.getContenido();
            if (contenido != null) {
                contenido = em.getReference(contenido.getClass(), contenido.getConId());
                serie.setContenido(contenido);
            }
            em.persist(serie);
            if (contenido != null) {
                contenido.setSerie(serie);
                contenido = em.merge(contenido);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSerie(serie.getSerId()) != null) {
                throw new PreexistingEntityException("Serie " + serie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Serie serie) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serie persistentSerie = em.find(Serie.class, serie.getSerId());
            Contenido contenidoOld = persistentSerie.getContenido();
            Contenido contenidoNew = serie.getContenido();
            List<String> illegalOrphanMessages = null;
            if (contenidoNew != null && !contenidoNew.equals(contenidoOld)) {
                Serie oldSerieOfContenido = contenidoNew.getSerie();
                if (oldSerieOfContenido != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Contenido " + contenidoNew + " already has an item of type Serie whose contenido column cannot be null. Please make another selection for the contenido field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contenidoNew != null) {
                contenidoNew = em.getReference(contenidoNew.getClass(), contenidoNew.getConId());
                serie.setContenido(contenidoNew);
            }
            serie = em.merge(serie);
            if (contenidoOld != null && !contenidoOld.equals(contenidoNew)) {
                contenidoOld.setSerie(null);
                contenidoOld = em.merge(contenidoOld);
            }
            if (contenidoNew != null && !contenidoNew.equals(contenidoOld)) {
                contenidoNew.setSerie(serie);
                contenidoNew = em.merge(contenidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serie.getSerId();
                if (findSerie(id) == null) {
                    throw new NonexistentEntityException("The serie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serie serie;
            try {
                serie = em.getReference(Serie.class, id);
                serie.getSerId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serie with id " + id + " no longer exists.", enfe);
            }
            Contenido contenido = serie.getContenido();
            if (contenido != null) {
                contenido.setSerie(null);
                contenido = em.merge(contenido);
            }
            em.remove(serie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Serie> findSerieEntities() {
        return findSerieEntities(true, -1, -1);
    }

    public List<Serie> findSerieEntities(int maxResults, int firstResult) {
        return findSerieEntities(false, maxResults, firstResult);
    }

    private List<Serie> findSerieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Serie.class));
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

    public Serie findSerie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Serie.class, id);
        } finally {
            em.close();
        }
    }

    public int getSerieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Serie> rt = cq.from(Serie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
