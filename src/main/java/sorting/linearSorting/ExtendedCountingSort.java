package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	public static int max(Integer[] array, int leftIndex, int rightIndex) {
		int max = Integer.MIN_VALUE;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public static int min(Integer[] array, int leftIndex, int rightIndex) {
		int min = Integer.MAX_VALUE;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
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
			int max = max(array, leftIndex, rightIndex);
			int min = min(array, leftIndex, rightIndex);
			Integer[] cumulativa = new Integer[(max - min) + 1];
			Arrays.fill(cumulativa, 0);

			for (int i = leftIndex; i <= rightIndex; i++) {
				cumulativa[array[i] - min] += 1;
			}
			for (int i = 1; i < cumulativa.length; i++) {
				cumulativa[i] += cumulativa[i - 1];
			}

			Integer[] arrayOrdenado = new Integer[array.length];
			for (int i = rightIndex; i >= leftIndex; i--) {
				arrayOrdenado[cumulativa[array[i] - min] - 1 + leftIndex] = array[i];
				cumulativa[array[i] - min] -= 1;
			}
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = arrayOrdenado[i];
			}
		}
	}

	public static void main(String[] args) {
		Integer[] l = {4,6,7,-4,56,-32,-6,10,70,68};
		ExtendedCountingSort sort = new ExtendedCountingSort();
		sort.sort(l, 3, 7);
		System.out.println(Arrays.toString(l));
	}

}
