package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	public static int max(Integer[] array, int leftIndex, int rightIndex) {
		int max = Integer.MIN_VALUE;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public static boolean check(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length <= 1) {
			return false;
		}
		if (leftIndex < 0 || leftIndex > array.length - 1 || rightIndex < 0 || rightIndex > array.length - 1 || leftIndex > rightIndex) {
			return false;
		}
		return true;
	}

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (check(array, leftIndex, rightIndex)) {
			Integer[] cumulativa = new Integer[max(array, leftIndex, rightIndex) + 1];
			Arrays.fill(cumulativa, 0);

			for (int i = leftIndex; i <= rightIndex; i++) {
				cumulativa[array[i]] += 1;
			}
			for (int i = 1; i < cumulativa.length; i++) {
				cumulativa[i] += cumulativa[i - 1];
			}

			Integer[] arrayOrdenado = new Integer[array.length];
			for (int i = rightIndex; i >= leftIndex; i--) {
				arrayOrdenado[cumulativa[array[i]] - 1 + leftIndex] = array[i];
				cumulativa[array[i]] -= 1;
			}
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = arrayOrdenado[i];
			}
		}
	}
}
