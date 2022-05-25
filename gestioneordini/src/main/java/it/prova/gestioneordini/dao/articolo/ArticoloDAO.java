package it.prova.gestioneordini.dao.articolo;

import java.util.List;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Articolo;

public interface ArticoloDAO extends IBaseDAO<Articolo> {

	public Articolo findByIdFetchingCategorie(Long id) throws Exception;
	
	public Long getSommaPrezziArticoliMarioRossi() throws Exception;
	
	

}
