package generatoreCasualeFakenews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Generatore {
	private final static List<String> listaVittimeMaschi = new LinkedList<String>( Arrays.asList("Renzie", "$oro$", "D'Alema", "Bersani") );
	private final static List<String>  listaVittimeFemmine = new LinkedList<>( Arrays.asList("Boldrini", "Kyenge", "Bonino") );
	
	private final static List<String> parenteleMaschi = new LinkedList<>( Arrays.asList("fidanzato","fratello","fratellastro", "cognato", "cugino", "nonno", "nipote", "figlio","trisnonno", "trisavolo") );
	private final static List<String>  parenteleFemmine = new LinkedList<>( Arrays.asList("fidanzata","sorella", "cognata", "sorellastra","cugina", "suocera", "nonna", "nipote","figlia", "trisnonna", "trisavola", "sorella") );
	
	private final static List<String> listaFinaliCondivisione = new LinkedList<>(Arrays.asList("Kondividi se 6 indinniato", "1 Like = 1€ in meno", "KKOndividi se voui mandarli a casa"));
	
	private String preposizioneMaschile = "del";
	private String prepSempliceMaschile = "di";
	private String preposizioneFemminile = "della";
	
	private String articoloMaschile ="il";
	private String articoloFemminile = "la";
	
	
	private final static List<String> elementiDiIndignazione = new LinkedList<>(Arrays.asList("guadagna %s € al giorno per fare il portaborse al Senato",
			"guadagna %s € sui sacchetti della differenziata",
			"ha regalato %s € ai migranti con il wifi",
			"ha tolto %s € alla manutenzione del ponte Morandi",
			"ha regalato alle banche %s € destinati ai terremotati",
			"ha comprato un aereo da %s € con i nostri soldi",
			"va sulla Lamborghini da %s € pagata da noi italiani",
			"fa la cena da %s € a Ibiza con i nostri soldi"));
	
	Random rand;
	
	public Generatore()
	{
		this.rand = new Random();
		
	}
	
	public String generaStringa()
	{
		String risultato = "";
		
		
		char sesso = (Math.random()>0.5 ? 'm':'f');//Scelgo il sesso del primo parente, pongo un articolo semplice davanti
		if (sesso=='m')
		{
			risultato+=articoloMaschile+" "+this.parenteleMaschi.get(this.rand.nextInt(parenteleMaschi.size()))+" ";
		}
		else
		{
			risultato+=this.articoloFemminile+" "+this.parenteleFemmine.get(this.rand.nextInt(this.parenteleFemmine.size()))+" ";
		}
		
		int numeroParenti = (int)(Math.random()*5);//Aggiungo un numero casuale di parenti
		for (int i=0; i<numeroParenti;i++)//Aggiungo un parente con una preposizione articolata concatenata
		{
			sesso = (Math.random()>0.5 ? 'm':'f');
			if (sesso=='m')
			{
				risultato+=this.preposizioneMaschile+" "+this.parenteleMaschi.get(this.rand.nextInt(parenteleMaschi.size()))+" ";
			}
			else
			{
				risultato+=this.preposizioneFemminile+" "+this.parenteleFemmine.get(this.rand.nextInt(this.parenteleFemmine.size()))+" ";
			}
		}
		
		if (sesso=='m')//Concateno preposizione semplice e il nome della vittima, in base al sesso
		{
			risultato+=this.prepSempliceMaschile+" "+this.listaVittimeMaschi.get(this.rand.nextInt(listaVittimeMaschi.size())).toUpperCase()+" ";
		}
		else
		{
			risultato+=this.preposizioneFemminile+" "+this.listaVittimeFemmine.get(this.rand.nextInt(this.listaVittimeFemmine.size())).toUpperCase()+" ";
		}
		
		//Aggiunta accusa
		int accusaScelta = rand.nextInt(this.elementiDiIndignazione.size());
		Integer ammontare = (int) ((Math.random()+1)*100000000)
				;//Creo un numero casuale di euro
		String accusaDaConcatenare = String.format(this.elementiDiIndignazione.get(accusaScelta), ammontare.toString());//Sostituisco i %s con il numero di euro
		
		risultato+= accusaDaConcatenare;
		
		
		//Aggiunta condivisione
		risultato += "\n\n"+this.listaFinaliCondivisione.get(rand.nextInt(this.listaFinaliCondivisione.size())).toUpperCase();
		return risultato;
		
	}

}
