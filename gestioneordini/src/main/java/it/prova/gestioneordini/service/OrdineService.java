	package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public Ordine caricaSingoloElementoEagerArticoli(Long id) throws Exception;

	public void aggiorna(Ordine ordine) throws Exception;

	public void inserisciNuovo(Ordine ordine) throws Exception;

	public void rimuovi(Long idArticolo) throws Exception;

	public void aggiungiArticolo(Articolo articolo, Ordine ordine) throws Exception;
	
	public List<Ordine> findTuttiOrdiniDiUnaCategoria(Categoria categoria) throws Exception;
		
	public List<String> getIndirizziOrdiniCheHannoArticoliConCodiceCheContiene(String string) throws Exception;
	
	public Ordine findIlPiuRecenteDellaCategoria(Categoria categoria) throws Exception;

	void setOrdineDAO(OrdineDAO ordineDAO);

}
