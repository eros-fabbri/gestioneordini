package it.prova.gestioneordini.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;
import it.prova.gestioneordini.service.ArticoloService;
import it.prova.gestioneordini.service.CategoriaService;
import it.prova.gestioneordini.service.MyServiceFactory;
import it.prova.gestioneordini.service.OrdineService;

public class TestGestioneordini {
	public static void main(String[] args) {
		CategoriaService categoriaService = MyServiceFactory.getCategoriaServiceInstance();
		ArticoloService articoloService = MyServiceFactory.getArticoloServiceInstance();
		OrdineService ordineService = MyServiceFactory.getOrdineServiceInstance();
		try {

			testInserisciNuovoOrdineEArticolo(articoloService,ordineService);
			testRimuoviArticoloDaOrdine(ordineService, articoloService);
			testFindTuttiOrdiniCategoria(articoloService, ordineService, categoriaService);
			testRimuoviArticolo(articoloService);
			testAggiungiCategoriaAdArticolo(articoloService, categoriaService, ordineService);
			testRimuoviCategoria(categoriaService);
			testGetCodiciCategoriaDiOrdiniFebbraio2022(articoloService, ordineService, categoriaService);

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}

	public static void testInserisciNuovoOrdineEArticolo(ArticoloService articoloService,
			OrdineService ordineService) throws Exception {
		System.out.println(".......testInserisciNuovoArticolo inizio.............");
		Ordine ordineInstance = new Ordine("mario rossi", "via roma 33",
				new SimpleDateFormat("dd/MM/yyyy").parse("24/09/2019"));
		ordineService.inserisciNuovo(ordineInstance);
		Articolo articoloInstance = new Articolo("articolo test", 100, "jfaui78",
				new SimpleDateFormat("dd/MM/yyyy").parse("24/09/2019"));
		articoloInstance.setOrdine(ordineInstance);
		articoloService.inserisciNuovo(articoloInstance);
		if (articoloInstance.getId() == 0)
			throw new RuntimeException("testInserisciNuovoArticolo fallito ");

		System.out.println(".......testInserisciNuovoArticolo fine: PASSED.............");

	}

	public static void testRimuoviArticoloDaOrdine(OrdineService ordineService, ArticoloService articoloService)
			throws Exception {
		System.out.println(".......testRimuoviArticoloDaOrdine inizio.............");
		Articolo articoloTest = new Articolo("tv", 6000, "ehehe",
				new SimpleDateFormat("dd/MM/yyyy").parse("09/02/2020"));
		Ordine ordineTest = new Ordine("Paolo", "Via Milano 42C",
				new SimpleDateFormat("dd/MM/yyyy").parse("17/08/2021"));
		articoloTest.setOrdine(ordineTest);
		ordineService.inserisciNuovo(ordineTest);
		articoloService.inserisciNuovo(articoloTest);
		articoloService.rimuovi(articoloTest.getId());
		System.out.println(".......testRimuoviArticoloDaOrdine fine: PASSED.............");
	}

	public static void testRimuoviArticolo(ArticoloService articoloService) throws Exception {
		System.out.println(".......testRimuoviArticolo inizio.............");
		List<Articolo> elencoArticoliPresenti = articoloService.listAll();
		Articolo articoloTest = elencoArticoliPresenti.get(0);
		articoloService.rimuovi(articoloTest.getId());
		System.out.println(".......testRimuoviArticolo fine: PASSED.............");
	}
	
	public static void testRimuoviCategoria(CategoriaService categoriaService) throws Exception {
		System.out.println(".......testRimuoviCategoria inizio.............");
		List<Categoria> elencoCategorie = categoriaService.listAll();
		Categoria categoriaTest= elencoCategorie.get(0);
		categoriaService.rimuovi(categoriaTest.getId());
		System.out.println(".......testRimuoviCategoria PASSED.............");
	}

	public static void testAggiungiCategoriaAdArticolo(ArticoloService articoloService,
			CategoriaService categoriaService, OrdineService ordineService) throws Exception {
		System.out.println(".......testAggiungiCategoriaAdArticolo inizio.............");
		
		Ordine ordineTest = new Ordine("Pippo", "Via pluto 80",
				new SimpleDateFormat("dd/MM/yyyy").parse("25/07/2020"));
		Articolo articoloTest = new Articolo("tv", 6000, "ehehe",
				new SimpleDateFormat("dd/MM/yyyy").parse("09/02/2020"));
		
		articoloTest.setOrdine(ordineTest);
		ordineService.inserisciNuovo(ordineTest);
		articoloService.inserisciNuovo(articoloTest);
		Categoria cartegoriaTest = new Categoria("Dolci", "091032R");
		categoriaService.inserisciNuovo(cartegoriaTest);
		articoloService.aggiungiCategoria(articoloTest, cartegoriaTest);
		
		System.out.println(".......testAggiungiCategoriaAdArticolo fine: PASSED.............");
	}

	public static void testFindTuttiOrdiniCategoria(ArticoloService articoloService, OrdineService ordineService,
			CategoriaService categoriaService) throws Exception {
		System.out.println(".......testFindTuttiOrdiniCategoria inizio.............");

		Ordine ordineTest = new Ordine("Mario", "Via mario 7", new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2020"));
		Articolo articoloTest = new Articolo("tv piatto", 1000, "pttv",
				new SimpleDateFormat("dd/MM/yyyy").parse("09/02/2020"));
		Categoria categoriaTest = new Categoria("televisori", "TV2000");

		ordineService.inserisciNuovo(ordineTest);
		articoloTest.setOrdine(ordineTest);
		articoloService.inserisciNuovo(articoloTest);
		categoriaService.inserisciNuovo(categoriaTest);

		categoriaService.aggiungiArticolo(articoloTest, categoriaTest);

		if (categoriaTest.getId() <1)
			throw new RuntimeException("FAILED:  categoria id non valido");
		System.out.println(ordineService.findTuttiOrdiniDiUnaCategoria(categoriaTest));
		System.out.println(".......testFindTuttiOrdiniCategoria fine: PASSED.............");

	}
	
	public static void testGetCodiciCategoriaDiOrdiniFebbraio2022(ArticoloService articoloService, OrdineService ordineService,
			CategoriaService categoriaService) throws Exception {
		
		System.out.println(".......testGetCodiciCategoriaDiOrdiniFebbraio2022 inizio.............");

		Ordine ordineTest = new Ordine("Mario", "Via mario 7", new SimpleDateFormat("dd/MM/yyyy").parse("22/02/2022"));
		Articolo articoloTest = new Articolo("smartphone", 300, "smpn300",
				new SimpleDateFormat("dd/MM/yyyy").parse("09/02/2020"));
		Categoria categoriaTest = new Categoria("smartphones", "SMP00N3S");

		ordineService.inserisciNuovo(ordineTest);
		articoloTest.setOrdine(ordineTest);
		articoloService.inserisciNuovo(articoloTest);
		categoriaService.inserisciNuovo(categoriaTest);

		categoriaService.aggiungiArticolo(articoloTest, categoriaTest);

		if (categoriaTest.getId() <1)
			throw new RuntimeException("FAILED:  categoria id non valido");
		System.out.println(categoriaService.getCodiciCategoriaDiOrdiniFebbraio2022());
		System.out.println(".......testGetCodiciCategoriaDiOrdiniFebbraio2022 fine: PASSED.............");
		
	}
	
	
	
	

}
