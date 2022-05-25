package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.categoria.CategoriaDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listAll() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;
	
	public Categoria caricaSingoloElementoEagerArticoli(Long id) throws Exception;

	public void aggiorna(Categoria categoria) throws Exception;

	public void inserisciNuovo(Categoria categoria) throws Exception;

	public void rimuovi(Long idCategoria) throws Exception;
	
	public void aggiungiArticolo(Articolo articolo, Categoria categoria) throws Exception;
	
	public List<String> getCodiciCategoriaDiOrdiniFebbraio2022() throws Exception;

	void setCategoriaDAO(CategoriaDAO categoriaDAO);
}


