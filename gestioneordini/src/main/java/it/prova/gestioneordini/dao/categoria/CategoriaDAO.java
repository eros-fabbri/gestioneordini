package it.prova.gestioneordini.dao.categoria;


import java.util.List;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria>{
	
	public Categoria findByIdFetchingArticoli(Long id) throws Exception;
	
	public List<String> getCodiciCategoriaDiOrdiniFebbraio2022() throws Exception;
	
	

}
