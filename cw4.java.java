public class Main {
    
    /**
     * @brief Основная публичная функция для вычисления суммы элементов массива.
     * Запускает вспомогательную рекурсивную функцию, начиная с индекса 0.
     * * @param arr Исходный массив целых чисел.
     * @return Сумма элементов массива.
     */
    public static int sumArrayRecursive(int[] arr) {
        // Вызываем вспомогательную функцию, передавая массив и начальный индекс (0).
        return sumArrayRecursiveHelper(arr, 0); 
    }

    /**
     * @brief Вспомогательная рекурсивная функция, работающая с индексами.
     * * @param arr Массив целых чисел.
     * @param index Текущий индекс, с которого начинается суммирование.
     * @return Сумма элементов массива, начиная с текущего индекса.
     */
    private static int sumArrayRecursiveHelper(int[] arr, int index) {
        
        // 1. Базовый случай (Условие завершения): 
        // Если индекс достиг конца массива (его длины), возвращаем 0.
        if (index == arr.length) {
            return 0;
        }

        // 2. Рекурсивный шаг: 
        // Возвращаем текущий элемент (arr[index]) 
        // плюс рекурсивный вызов для следующего элемента (index + 1).
        return arr[index] + sumArrayRecursiveHelper(arr, index + 1);
    }

    public static void main(String[] args) {
        // Пример использования
        int[] myArray = {10, 2, 5, 8};
        int result = sumArrayRecursive(myArray);

        System.out.println("Массив: [10, 2, 5, 8]");
        System.out.println("Сумма элементов (рекурсивно): " + result); 
        // Ожидаемый вывод: 25
    }
}