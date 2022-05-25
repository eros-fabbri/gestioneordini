package it.prova.gestioneordini.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDao;

	@Override
	public List<Ordine> listAll() throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDao.setEntityManager(entityManager);

			return ordineDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDao.setEntityManager(entityManager);

			return ordineDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElementoEagerArticoli(Long id) throws Exception {

		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDao.setEntityManager(entityManager);

			return ordineDao.findByIdFetchingArticoli(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ordine articolo) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDao.setEntityManager(entityManager);

			ordineDao.insert(articolo);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Ordine articolo) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDao.setEntityManager(entityManager);

			ordineDao.insert(articolo);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idArticolo) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDao.setEntityManager(entityManager);

			ordineDao.delete(ordineDao.get(idArticolo));

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void aggiungiArticolo(Articolo articolo, Ordine ordine) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDao.setEntityManager(entityManager);

			ordine = entityManager.merge(ordine);
			articolo = entityManager.merge(articolo);

			ordine.getArticoli().add(articolo);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {
		this.ordineDao = ordineDAO;
	}

	@Override
	public List<Ordine> findTuttiOrdiniDiUnaCategoria(Categoria categoria) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			ordineDao.setEntityManager(entityManager);

			return ordineDao.findTuttiOrdiniDiUnaCategoria(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<String> getIndirizziOrdiniCheHannoArticoliConCodiceCheContiene(String string) throws Exception {
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			ordineDao.setEntityManager(entityManager);

			return ordineDao.getIndirizziOrdiniCheHannoArticoliConCodiceCheContiene(string);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine findIlPiuRecenteDellaCategoria(Categoria categoria) throws Exception {
		
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			ordineDao.setEntityManager(entityManager);

			return ordineDao.findIlPiuRecenteDellaCategoria(categoria);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}	}

//	@Override
//	public List<Ordine> findTuttiOrdiniDiUnaCategoria(Categoria categoria) throws Exception {
//		EntityManager entityManager = EntityManagerUtil.getEntityManager();
//
//		try {
//			ordineDao.setEntityManager(entityManager);
//
//			return ordineDao.findIlPiuRecenteDellaCategoria(categoria);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			EntityManagerUtil.closeEntityManager(entityManager);
//		}
	}
	

