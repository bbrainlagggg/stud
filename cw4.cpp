#include <iostream>
#include <vector>

/**
 * @brief Рекурсивно вычисляет сумму элементов массива (вектора).
 * * @param arr Ссылка на вектор целых чисел (передача по константной ссылке для эффективности).
 * @param index Текущий индекс, с которого начинается суммирование.
 * @return Сумма элементов вектора, начиная с текущего индекса.
 */
int sumArrayRecursive(const std::vector<int>& arr, int index) {
    
    // 1. Базовый случай (Условие завершения): 
    // Если индекс достиг конца вектора (его размера), возвращаем 0.
    if (index == arr.size()) {
        return 0; 
    }

    // 2. Рекурсивный шаг: 
    // Возвращаем текущий элемент (arr[index]) 
    // плюс рекурсивный вызов для следующего элемента (index + 1).
    return arr[index] + sumArrayRecursive(arr, index + 1);
}

int main() {
    // Пример использования
    std::vector<int> myArray = {10, 2, 5, 8};
    
    // Вызываем функцию, начиная с индекса 0
    int result = sumArrayRecursive(myArray, 0);

    std::cout << "Массив: [10, 2, 5, 8]" << std::endl;
    std::cout << "Сумма элементов (рекурсивно): " << result << std::endl;
    // Ожидаемый вывод: 25
    
    return 0;
}