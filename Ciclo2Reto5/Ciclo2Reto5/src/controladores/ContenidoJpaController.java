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
import modelos.Serie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelos.Contenido;

/**
 *
 * @author norma
 */
public class ContenidoJpaController implements Serializable {

    public ContenidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contenido contenido) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Serie serie = contenido.getSerie();
            if (serie != null) {
                serie = em.getReference(serie.getClass(), serie.getSerId());
                contenido.setSerie(serie);
            }
            em.persist(contenido);
            if (serie != null) {
                Contenido oldContenidoOfSerie = serie.getContenido();
                if (oldContenidoOfSerie != null) {
                    oldContenidoOfSerie.setSerie(null);
                    oldContenidoOfSerie = em.merge(oldContenidoOfSerie);
                }
                serie.setContenido(contenido);
                serie = em.merge(serie);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContenido(contenido.getConId()) != null) {
                throw new PreexistingEntityException("Contenido " + contenido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contenido contenido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contenido persistentContenido = em.find(Contenido.class, contenido.getConId());
            Serie serieOld = persistentContenido.getSerie();
            Serie serieNew = contenido.getSerie();
            List<String> illegalOrphanMessages = null;
            if (serieOld != null && !serieOld.equals(serieNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Serie " + serieOld + " since its contenido field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (serieNew != null) {
                serieNew = em.getReference(serieNew.getClass(), serieNew.getSerId());
                contenido.setSerie(serieNew);
            }
            contenido = em.merge(contenido);
            if (serieNew != null && !serieNew.equals(serieOld)) {
                Contenido oldContenidoOfSerie = serieNew.getContenido();
                if (oldContenidoOfSerie != null) {
                    oldContenidoOfSerie.setSerie(null);
                    oldContenidoOfSerie = em.merge(oldContenidoOfSerie);
                }
                serieNew.setContenido(contenido);
                serieNew = em.merge(serieNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contenido.getConId();
                if (findContenido(id) == null) {
                    throw new NonexistentEntityException("The contenido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contenido contenido;
            try {
                contenido = em.getReference(Contenido.class, id);
                contenido.getConId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contenido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Serie serieOrphanCheck = contenido.getSerie();
            if (serieOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Contenido (" + contenido + ") cannot be destroyed since the Serie " + serieOrphanCheck + " in its serie field has a non-nullable contenido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(contenido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contenido> findContenidoEntities() {
        return findContenidoEntities(true, -1, -1);
    }

    public List<Contenido> findContenidoEntities(int maxResults, int firstResult) {
        return findContenidoEntities(false, maxResults, firstResult);
    }

    private List<Contenido> findContenidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contenido.class));
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

    public Contenido findContenido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contenido.class, id);
        } finally {
            em.close();
        }
    }

    public int getContenidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contenido> rt = cq.from(Contenido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
