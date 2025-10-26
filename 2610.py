def sort_select_min(lst):
    # Сортировка выбором
    n = len(lst)
    for i in range(n - 1):
        min_pos = i
        for j in range(i + 1, n):
            if lst[j] < lst[min_pos]:
                min_pos = j
        lst[i], lst[min_pos] = lst[min_pos], lst[i]


def show_list(arr):
    print(" ".join(map(str, arr)))


def demo_select_sort():
    nums = [64, 25, 12, 22, 11]
    print("Исходный список:")
    show_list(nums)
    sort_select_min(nums)
    print("После сортировки выбором:")
    show_list(nums)


def bubble_sort_opt(data):
    # Пузырьковая сортировка с проверкой
    size = len(data)
    for step in range(size - 1):
        changed = False
        for i in range(size - step - 1):
            if data[i] > data[i + 1]:
                data[i], data[i + 1] = data[i + 1], data[i]
                changed = True
        if not changed:
            break


def demo_bubble():
    nums = [64, 34, 25, 12, 22, 11]
    print("Исходный список:")
    show_list(nums)
    bubble_sort_opt(nums)
    print("После пузырьковой сортировки:")
    show_list(nums)


def sort_insert(lst):
    # Сортировка вставками
    for i in range(1, len(lst)):
        key = lst[i]
        k = i - 1
        while k >= 0 and lst[k] > key:
            lst[k + 1] = lst[k]
            k -= 1
        lst[k + 1] = key


def demo_insert():
    data = [12, 11, 13, 5, 6]
    print("Исходный список:")
    show_list(data)
    sort_insert(data)
    print("После сортировки вставками:")
    show_list(data)


def merge_sort(nums):
    # Сортировка слиянием
    if len(nums) <= 1:
        return nums
    middle = len(nums) // 2
    left_part = merge_sort(nums[:middle])
    right_part = merge_sort(nums[middle:])
    return merge_two(left_part, right_part)


def merge_two(a, b):
    # Объединение двух частей
    res = []
    x = y = 0
    while x < len(a) and y < len(b):
        if a[x] <= b[y]:
            res.append(a[x])
            x += 1
        else:
            res.append(b[y])
            y += 1
    res.extend(a[x:])
    res.extend(b[y:])
    return res


def demo_merge():
    arr = [38, 27, 43, 3, 9, 82, 10]
    print("Исходный список:")
    show_list(arr)
    result = merge_sort(arr)
    print("После сортировки слиянием:")
    show_list(result)


def shell_sort(arr):
    # Сортировка Шелла
    step = len(arr) // 2
    while step > 0:
        for i in range(step, len(arr)):
            temp = arr[i]
            j = i
            while j >= step and arr[j - step] > temp:
                arr[j] = arr[j - step]
                j -= step
            arr[j] = temp
        step //= 2


def demo_shell():
    data = [23, 12, 1, 8, 34, 56, 7]
    print("Исходный список:")
    show_list(data)
    shell_sort(data)
    print("После сортировки Шелла:")
    show_list(data)


def quick_sort(a, left=0, right=None):
    # Быстрая сортировка
    if right is None:
        right = len(a) - 1
    if left < right:
        p = partition(a, left, right)
        quick_sort(a, left, p - 1)
        quick_sort(a, p + 1, right)


def partition(a, left, right):
    pivot = a[right]
    idx = left - 1
    for j in range(left, right):
        if a[j] <= pivot:
            idx += 1
            a[idx], a[j] = a[j], a[idx]
    a[idx + 1], a[right] = a[right], a[idx + 1]
    return idx + 1


def demo_quick():
    nums = [10, 7, 8, 9, 1, 5]
    print("Исходный список:")
    show_list(nums)
    quick_sort(nums)
    print("После быстрой сортировки:")
    show_list(nums)


def heap_sort(arr):
    # Пирамидальная сортировка
    n = len(arr)
    for i in range(n // 2 - 1, -1, -1):
        sift_down(arr, n, i)
    for end in range(n - 1, 0, -1):
        arr[0], arr[end] = arr[end], arr[0]
        sift_down(arr, end, 0)


def sift_down(heap, size, i):
    largest = i
    l = 2 * i + 1
    r = 2 * i + 2
    if l < size and heap[l] > heap[largest]:
        largest = l
    if r < size and heap[r] > heap[largest]:
        largest = r
    if largest != i:
        heap[i], heap[largest] = heap[largest], heap[i]
        sift_down(heap, size, largest)


def demo_heap():
    data = [12, 11, 13, 5, 6, 7]
    print("Исходный список:")
    show_list(data)
    heap_sort(data)
    print("После пирамидальной сортировки:")
    show_list(data)


def search_linear(arr, val):
    # Линейный поиск
    for i, item in enumerate(arr):
        if item == val:
            return i
    return -1


def demo_search_linear():
    nums = [3, 8, 1, 10, 5]
    key = 10
    pos = search_linear(nums, key)
    if pos != -1:
        print(f"Элемент {key} найден на позиции {pos}")
    else:
        print(f"Элемент {key} не найден")


def search_binary(arr, val):
    # Бинарный поиск
    l, r = 0, len(arr) - 1
    while l <= r:
        m = (l + r) // 2
        if arr[m] == val:
            return m
        elif arr[m] < val:
            l = m + 1
        else:
            r = m - 1
    return -1


def demo_search_binary():
    data = [1, 3, 5, 7, 9, 11]
    key = 7
    res = search_binary(data, key)
    if res != -1:
        print(f"Элемент {key} найден по индексу {res}")
    else:
        print("Не найдено")


def search_interp(arr, val):
    # Интерполяционный поиск
    low, high = 0, len(arr) - 1
    while low <= high and arr[low] <= val <= arr[high]:
        if low == high:
            return low if arr[low] == val else -1
        diff = arr[high] - arr[low]
        if diff == 0:
            mid = low
        else:
            mid = low + (val - arr[low]) * (high - low) // diff
        mid = max(low, min(mid, high))
        if arr[mid] == val:
            return mid
        if arr[mid] < val:
            low = mid + 1
        else:
            high = mid - 1
    return -1


def demo_interp():
    arr = [10, 15, 20, 25, 30, 35, 40, 45, 50]
    val = 35
    pos = search_interp(arr, val)
    if pos != -1:
        print(f"Элемент {val} найден по индексу {pos}")
    else:
        print("Элемент не найден")


def search_fibo(arr, val):
    # Поиск Фибоначчи
    n = len(arr)
    f2, f1 = 0, 1
    f = f1 + f2
    while f < n:
        f2, f1 = f1, f
        f = f1 + f2
    offset = -1
    while f > 1:
        i = min(offset + f2, n - 1)
        if arr[i] < val:
            f = f1
            f1 = f2
            f2 = f - f1
            offset = i
        elif arr[i] > val:
            f = f2
            f1 = f1 - f2
            f2 = f - f1
        else:
            return i
    if f1 and offset + 1 < n and arr[offset + 1] == val:
        return offset + 1
    return -1


def demo_fibo():
    arr = [10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100]
    key = 85
    res = search_fibo(arr, key)
    if res != -1:
        print(f"Элемент {key} найден по индексу {res}")
    else:
        print("Элемент не найден")



if True:
    print("\n СОРТИРОВКА ВЫБОРОМ ")
    demo_select_sort()

    print("\n СОРТИРОВКА ПУЗЫРЬКОМ ")
    demo_bubble()

    print("\n СОРТИРОВКА ВСТАВКАМИ ")
    demo_insert()

    print("\n СОРТИРОВКА СЛИЯНИЕМ ")
    demo_merge()

    print("\n СОРТИРОВКА ШЕЛЛА ")
    demo_shell()

    print("\n БЫСТРАЯ СОРТИРОВКА ")
    demo_quick()

    print("\n ПИРАМИДАЛЬНАЯ СОРТИРОВКА ")
    demo_heap()

    print("\n ЛИНЕЙНЫЙ ПОИСК ")
    demo_search_linear()

    print("\n БИНАРНЫЙ ПОИСК ")
    demo_search_binary()

    print("\n ИНТЕРПОЛЯЦИОННЫЙ ПОИСК ")
    demo_interp()

    print("\n ПОИСК ФИБОНАЧЧИ ")
    demo_fibo()
