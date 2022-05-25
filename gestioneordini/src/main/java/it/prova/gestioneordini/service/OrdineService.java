	package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public Ordine caricaSingoloElementoEagerArticoli(Long id) throws Exception;

	public void aggiorna(Ordine articolo) throws Exception;

	public void inserisciNuovo(Ordine articolo) throws Exception;

	public void rimuovi(Long idArticolo) throws Exception;

	public void aggiungiArticolo(Articolo articolo, Ordine ordine) throws Exception;

	void setOrdineDAO(OrdineDAO ordineDAO);

}
