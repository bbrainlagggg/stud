import random
from collections import deque

class TabuSearchGraphColoring:
    def __init__(self, num_vertices, max_iterations=500, tabu_size=10):
        self.num_vertices = num_vertices
        self.max_iterations = max_iterations
        self.tabu_size = tabu_size
        self.graph = self._generate_random_graph() # Генерация графа с 12 вершинами

    def _generate_random_graph(self):
        """Генерирует случайную матрицу смежности для 12 вершин."""
        adj_matrix = [[0] * self.num_vertices for _ in range(self.num_vertices)]
        # Плотность ребер 40%
        for i in range(self.num_vertices):
            for j in range(i + 1, self.num_vertices):
                if random.random() < 0.4:
                    adj_matrix[i][j] = 1
                    adj_matrix[j][i] = 1
        return adj_matrix

    def _count_conflicts(self, solution, graph):
        """Подсчитывает количество ребер, соединяющих вершины одного цвета."""
        conflicts = 0
        for i in range(self.num_vertices):
            for j in range(i + 1, self.num_vertices):
                if graph[i][j] == 1 and solution[i] == solution[j]:
                    conflicts += 1
        return conflicts

    def solve(self):
        # Начинаем с жадного предположения или количества цветов = кол-ву вершин
        # Будем пытаться уменьшать k, пока можем найти решение
        k = self.num_vertices
        final_solution = list(range(self.num_vertices)) # Каждая вершина имеет свой цвет
        
        print(f"--- Запуск Табу-поиска для графа с {self.num_vertices} вершинами ---")

        while k > 1:
            print(f"\nПопытка найти раскраску для {k-1} цветов...")
            # Генерируем начальное случайное решение для k-1 цветов
            current_solution = [random.randint(0, k-2) for _ in range(self.num_vertices)]
            best_solution_for_k = list(current_solution)
            min_conflicts = self._count_conflicts(current_solution, self.graph)
            
            # Табу-лист: хранит пары (вершина, цвет), которые запрещено устанавливать
            # Используем deque с фиксированной длиной для автоматического удаления старых записей
            tabu_list = deque(maxlen=self.tabu_size)
            
            iter_count = 0
            found_valid = False

            while iter_count < self.max_iterations:
                current_conflicts = self._count_conflicts(current_solution, self.graph)
                
                # Если конфликтов 0, мы нашли валидную раскраску
                if current_conflicts == 0:
                    final_solution = list(current_solution)
                    found_valid = True
                    print(f"  -> Успех! Найдено решение без конфликтов на итерации {iter_count}")
                    break

                # Поиск лучшего соседа
                best_neighbor = None
                best_neighbor_conflicts = float('inf')
                move_to_make = None # (vertex, new_color, old_color)

                # Перебираем вершины и пробуем менять их цвет
                # (В реальных больших задачах перебирают не всё, а только конфликтующие вершины)
                for v in range(self.num_vertices):
                    original_color = current_solution[v]
                    for c in range(k-1):
                        if c == original_color:
                            continue
                        
                        # Проверка Табу: запрещено ли присваивать вершине v цвет c?
                        if (v, c) in tabu_list:
                            # Здесь можно добавить "Aspiration criteria" (игнорировать табу, если решение лучше глобального)
                            # Но по условию задачи следуем строгим правилам списка
                            continue

                        # Создаем соседа
                        current_solution[v] = c
                        neighbor_conflicts = self._count_conflicts(current_solution, self.graph)
                        
                        # Оценка соседа
                        if neighbor_conflicts < best_neighbor_conflicts:
                            best_neighbor_conflicts = neighbor_conflicts
                            best_neighbor = list(current_solution)
                            move_to_make = (v, c, original_color)
                        
                        # Возвращаем цвет обратно для следующей итерации цикла
                        current_solution[v] = original_color

                # Если не нашли куда двигаться (все ходы в табу или тупик), прерываем
                if best_neighbor is None:
                    break

                # Применяем лучший ход
                current_solution = best_neighbor
                vertex, new_color, old_color = move_to_make
                
                # Добавляем обратный ход в табу-лист (запрещаем возвращать старый цвет этой вершине)
                tabu_list.append((vertex, old_color))
                
                iter_count += 1

            if found_valid:
                k -= 1 # Пробуем еще меньше цветов
            else:
                print(f"  -> Не удалось найти решение для {k-1} цветов за {self.max_iterations} итераций.")
                break
        
        return k, final_solution

# --- Запуск ---
if __name__ == "__main__":
    # Инициализация задачи (Вариант 18)
    solver = TabuSearchGraphColoring(num_vertices=12, max_iterations=500, tabu_size=10)
    
    # Решение
    final_k, colors = solver.solve()
    
    # Вывод результатов
    print("-" * 30)
    print(f"ФИНАЛЬНЫЙ РЕЗУЛЬТАТ:")
    print(f"Минимальное количество цветов: {final_k}")
    print("Распределение цветов по вершинам:")
    
    # Группировка для красивого вывода
    color_map = {}
    for vertex, color in enumerate(colors):
        if color not in color_map:
            color_map[color] = []
        color_map[color].append(vertex)
        
    for c, vertices in sorted(color_map.items()):
        print(f"  Цвет {c}: Вершины {vertices}")
