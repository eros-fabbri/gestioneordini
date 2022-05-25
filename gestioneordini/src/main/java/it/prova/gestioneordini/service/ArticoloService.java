package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.articolo.ArticoloDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public interface ArticoloService {

	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public Articolo caricaSingoloElementoEagerCategorie(Long id) throws Exception;

	public void aggiorna(Articolo articolo) throws Exception;

	public void inserisciNuovo(Articolo articolo) throws Exception;

	public void rimuovi(Long idArticolo) throws Exception;

	public void aggiungiCategoria(Articolo articolo, Categoria categoria) throws Exception;
	
	public Long getSommaPrezziArticoliMarioRossi() throws Exception;

	void setArticoloDAO(ArticoloDAO articoloDAO);
}
