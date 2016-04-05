package it.polito.tdp.permuta.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Calcola tutte le permutazioni di una lista di numeri interi (in particolare,
 * dei numeri tra 1 ed N)
 * 
 * @author Fulvio
 *
 */
public class PermutaModel {

	private List<Integer> numeri;
	private int lunghezza;

	/**
	 * Inizializza la lista di numeri (che dovrà essere permutata), generando
	 * una lista della {@code lunghezza} specificata, contentete i numeri interi
	 * tra 1 e {@code lunghezza}
	 * 
	 * @param lunghezza
	 *            lunghezza della lista (e valore massimo presente)
	 */
	public void crea(int lunghezza) {
		this.lunghezza = lunghezza;
		numeri = new ArrayList<Integer>(lunghezza);
		for (int i = 1; i <= lunghezza; i++) {
			numeri.add(i);
		}
	}

	/**
	 * Calcola e restituisce tutte le permutazioni della lista di numeri
	 * precedentemente creata.
	 * 
	 * @return Una lista di liste. La lista esterna è relativa alle diverse
	 *         permutazioni trovate. Ciascuna lista interna contiene gli interi
	 *         della permutazione specificata.
	 *
	 */
	public List<List<Integer>> permuta() {

		// Inizializza la lista di liste, vuota
		List<List<Integer>> tutte = new ArrayList<List<Integer>>();

		// lancia la ricorsione
		permuta_ric(0, lunghezza, numeri, new ArrayList<Integer>(), tutte);

		return tutte;

	}

	/**
	 * Procedura ricorsiva per il calcolo effettivo delle permutazioni
	 * 
	 * @param step
	 *            A che passo si trova la procedura. Significa che sono già
	 *            stati posizionati {@code step} numeri (da {@code 0} a
	 *            {@code step-1}, ed il prossimo da determinare è quello di
	 *            indice {@step}
	 * @param lunghezza
	 *            Lunghezza totale della serie di numeri da permutare.
	 * @param partenza
	 *            Lista iniziale dei numeri da permutare (non viene modificata)
	 * @param result
	 *            Lista costruita progressivamente dalla ricorsione (viene
	 *            modificata)
	 * @param tutte
	 *            Lista di liste che raccoglie tutte le soluzioni trovate
	 */
	private void permuta_ric(int step, int lunghezza, List<Integer> partenza, List<Integer> result,
			List<List<Integer>> tutte) {
		if (step == lunghezza) {
			// Sono alla fine: ho una permutazione completa
			// System.out.println(result);
			tutte.add(new ArrayList<>(result));
			// attenzione: tutte.add(result) sarebbe SBAGLIATO:
			// occorre CLONARE la lista, non semplicemente accodare il
			// riferimento
		} else {

			for (int pos = 0; pos < lunghezza; pos++) {
				// se il numero non è ancora stato inserito
				if (!result.contains(partenza.get(pos))) {
					// add
					result.add(partenza.get(pos));
					// try
					permuta_ric(step + 1, lunghezza, partenza, result, tutte);
					// restore
					result.remove(result.size() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {

		PermutaModel m = new PermutaModel();

		List<List<Integer>> r;

		m.crea(3);
		r = m.permuta();
		System.out.println(r);

		m.crea(4);
		r = m.permuta();
		System.out.println(r);
	}

}
