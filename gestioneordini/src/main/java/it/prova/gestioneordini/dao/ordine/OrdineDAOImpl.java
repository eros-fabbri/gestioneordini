package it.prova.gestioneordini.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {

		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {

		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Ordine findByIdFetchingArticoli(Long id) throws Exception {

		TypedQuery<Ordine> query = entityManager.createQuery(
				"select o FROM Ordine o left join fetch o.articoli a where o.id = :idOrdine", Ordine.class);
		query.setParameter("idOrdine", id);
		return query.getResultList().stream().findFirst().orElse(null);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ordine> findTuttiOrdiniDiUnaCategoria(Categoria categoria) throws Exception {
				
		Query query = entityManager.createNativeQuery(
				"select * from ordine o inner join articolo a on o.id=a.ordine_id inner join articolo_categoria ac on ac.articolo_id=a.id inner join categoria c on ac.categoria_id=c.id where c.id= :idCategoria",Ordine.class);
		query.setParameter("idCategoria", categoria.getId());
		return query.getResultList();
	}

	@Override
	public Ordine findIlPiuRecenteDellaCategoria(Categoria categoria) throws Exception {
		
		TypedQuery<Ordine> query = entityManager.createQuery(
				"select o FROM Ordine o  join  o.articoli a join a.categorie c where c.id = :idCategoria", Ordine.class);
		query.setParameter("idCategoria", categoria.getId());
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public List<String> getIndirizziOrdiniCheHannoArticoliConCodiceCheContiene(String stringa) throws Exception {

		TypedQuery<String> query = entityManager.createQuery(
				"select o.indirizzoSpedizione from Ordine o join o.articoli a where a.numeroSeriale like ?1 ", String.class);
		query.setParameter(1, "%"+stringa+"%");
		return query.getResultList();
		
	}
	

}
