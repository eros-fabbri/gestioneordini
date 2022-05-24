package it.prova.gestioneordini.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "articolo")
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "prezzosingolo")
	private int prezzoSingolo;
	@Column(name = "dataInserimento")
	private Date dataInserimento;
	@JoinColumn(name = "ordine_id", nullable = false)
	private Ordine ordine;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "articolo_categoria", joinColumns = @JoinColumn(name = "articolo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	private List<Categoria> categorie;

	public Articolo() {
		super();
	}

	public Articolo(String descrizione, int prezzoSingolo, Date dataInserimento, Ordine ordine,
			List<Categoria> categorie) {
		super();
		this.descrizione = descrizione;
		this.prezzoSingolo = prezzoSingolo;
		this.dataInserimento = dataInserimento;
		this.ordine = ordine;
		this.categorie = categorie;
	}

	public Articolo(long id, String descrizione, int prezzoSingolo, Date dataInserimento, Ordine ordine,
			List<Categoria> categorie) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.prezzoSingolo = prezzoSingolo;
		this.dataInserimento = dataInserimento;
		this.ordine = ordine;
		this.categorie = categorie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getPrezzoSingolo() {
		return prezzoSingolo;
	}

	public void setPrezzoSingolo(int prezzoSingolo) {
		this.prezzoSingolo = prezzoSingolo;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public List<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<Categoria> categorie) {
		this.categorie = categorie;
	}

}
