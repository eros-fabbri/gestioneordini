package it.prova.gestioneordini.test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Ordine;
import it.prova.gestioneordini.service.ArticoloService;
import it.prova.gestioneordini.service.CategoriaService;
import it.prova.gestioneordini.service.MyServiceFactory;
import it.prova.gestioneordini.service.OrdineService;


public class TestGestioneordini {
	public static void main(String[] args) {
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		try {
			
			testInserisciNuovoArticolo(articoloServiceInstance, ordineServiceInstance);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

	public static void testInserisciNuovoArticolo(ArticoloService articoloServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testInserisciNuovoArticolo inizio.............");
		Ordine ordineInstance = new Ordine("mario rossi", "via roma 33", new SimpleDateFormat("dd/MM/yyyy").parse("24/09/2019"));
		ordineServiceInstance.inserisciNuovo(ordineInstance);
		Articolo articoloInstance = new Articolo("articolo test", 100 ,new SimpleDateFormat("dd/MM/yyyy").parse("24/09/2019"));
		articoloInstance.setOrdine(ordineInstance);
		articoloServiceInstance.inserisciNuovo(articoloInstance);
		if (articoloInstance.getId() == 0)
			throw new RuntimeException("testInserisciNuovoArticolo fallito ");

		System.out.println(".......testInserisciNuovoArticolo fine: PASSED.............");

	}

}
