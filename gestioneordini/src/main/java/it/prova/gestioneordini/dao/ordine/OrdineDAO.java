package it.prova.gestioneordini.dao.ordine;

import java.util.List;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public Ordine findByIdFetchingArticoli(Long id) throws Exception;

	public List<Ordine> findTuttiOrdiniDiUnaCategoria(Categoria categoria) throws Exception;

	public Ordine findIlPiuRecenteDellaCategoria(Categoria categoria) throws Exception;
	
	public List<String> getIndirizziOrdiniCheHannoArticoliConCodiceCheContiene(String stringa) throws Exception;

}
