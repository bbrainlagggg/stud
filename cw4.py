def sum_array_recursive(arr):
    """
    Рекурсивно вычисляет сумму элементов массива (списка).

    :param arr: Список чисел.
    :return: Сумма элементов списка.
    """
    # 1. Базовый случай: Пустой массив
    if not arr:
        return 0
    
    # 2. Рекурсивный шаг: 
    # Возвращаем первый элемент + сумму остатка массива
    else:
        # arr[0] - первый элемент
        # arr[1:] - срез, содержащий все элементы, кроме первого
        return arr[0] + sum_array_recursive(arr[1:])



my_array = [10, 2, 5, 8]
result = sum_array_recursive(my_array)

print(f"Массив: {my_array}")
print(f"Сумма элементов (рекурсивно): {result}") 
