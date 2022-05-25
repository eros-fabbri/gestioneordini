package it.prova.gestioneordini.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {

		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {

		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Articolo findByIdFetchingCategorie(Long id) throws Exception {

		TypedQuery<Articolo> query = entityManager.createQuery(
				"select a FROM Articolo a left join fetch a.categorie c where a.id = :idArticolo", Articolo.class);
		query.setParameter("idArticolo", id);
		return query.getResultList().stream().findFirst().orElse(null);

	}

	@Override
	public Long getSommaPrezziArticoliMarioRossi() throws Exception {
		TypedQuery<Long> query = entityManager.createQuery(
				"select sum(a.prezzoSingolo)FROM Articolo a join a.ordine o where o.nomeDestinatario = 'mario rossi'", Long.class);
		return query.getResultList().stream().findFirst().orElse(null);
	}

}
