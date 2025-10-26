#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// ВСПОМОГАТЕЛЬНОЕ
void show(const vector<int>& v) {
    for (int x : v) cout << x << " ";
    cout << endl;
}

// СОРТИРОВКИ
void selectMinSort(vector<int>& v) {
    int n = v.size();
    for (int left = 0; left < n - 1; ++left) {
        int minPos = left;
        for (int k = left + 1; k < n; ++k)
            if (v[k] < v[minPos]) minPos = k;
        swap(v[left], v[minPos]);
    }
}

void bubbleSortFast(vector<int>& v) {
    int n = v.size();
    for (int pass = 0; pass < n - 1; ++pass) {
        bool swapped = false;
        for (int i = 0; i < n - pass - 1; ++i) {
            if (v[i] > v[i + 1]) {
                swap(v[i], v[i + 1]);
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}

void insertSort(vector<int>& v) {
    int n = v.size();
    for (int i = 1; i < n; ++i) {
        int cur = v[i];
        int j = i - 1;
        while (j >= 0 && v[j] > cur) {
            v[j + 1] = v[j];
            j--;
        }
        v[j + 1] = cur;
    }
}

vector<int> mergeParts(const vector<int>& a, const vector<int>& b) {
    vector<int> merged;
    int i = 0, j = 0;
    while (i < a.size() && j < b.size()) {
        if (a[i] <= b[j]) merged.push_back(a[i++]);
        else merged.push_back(b[j++]);
    }
    while (i < a.size()) merged.push_back(a[i++]);
    while (j < b.size()) merged.push_back(b[j++]);
    return merged;
}

vector<int> mergeSort(const vector<int>& v) {
    if (v.size() <= 1) return v;
    int mid = v.size() / 2;
    vector<int> left(v.begin(), v.begin() + mid);
    vector<int> right(v.begin() + mid, v.end());
    left = mergeSort(left);
    right = mergeSort(right);
    return mergeParts(left, right);
}

void shellSort(vector<int>& v) {
    int n = v.size();
    for (int gap = n / 2; gap > 0; gap /= 2) {
        for (int i = gap; i < n; ++i) {
            int temp = v[i];
            int j = i;
            while (j >= gap && v[j - gap] > temp) {
                v[j] = v[j - gap];
                j -= gap;
            }
            v[j] = temp;
        }
    }
}

int partition(vector<int>& v, int lo, int hi) {
    int pivot = v[hi];
    int i = lo - 1;
    for (int j = lo; j < hi; ++j) {
        if (v[j] <= pivot) {
            ++i;
            swap(v[i], v[j]);
        }
    }
    swap(v[i + 1], v[hi]);
    return i + 1;
}

void quickSort(vector<int>& v, int lo, int hi) {
    if (lo < hi) {
        int p = partition(v, lo, hi);
        quickSort(v, lo, p - 1);
        quickSort(v, p + 1, hi);
    }
}

void siftDown(vector<int>& v, int size, int root) {
    int largest = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;
    if (left < size && v[left] > v[largest]) largest = left;
    if (right < size && v[right] > v[largest]) largest = right;
    if (largest != root) {
        swap(v[root], v[largest]);
        siftDown(v, size, largest);
    }
}

void heapSort(vector<int>& v) {
    int n = v.size();
    for (int i = n / 2 - 1; i >= 0; --i) siftDown(v, n, i);
    for (int end = n - 1; end > 0; --end) {
        swap(v[0], v[end]);
        siftDown(v, end, 0);
    }
}

// ПОИСК
int findLinear(const vector<int>& v, int key) {
    for (int i = 0; i < v.size(); ++i)
        if (v[i] == key) return i;
    return -1;
}

int findBinary(const vector<int>& v, int key) {
    int lo = 0, hi = v.size() - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (v[mid] == key) return mid;
        if (v[mid] < key) lo = mid + 1;
        else hi = mid - 1;
    }
    return -1;
}

int searchInterpolation(const vector<int>& v, int key) {
    int low = 0, high = v.size() - 1;
    while (low <= high && key >= v[low] && key <= v[high]) {
        if (low == high) return (v[low] == key) ? low : -1;
        int pos = low + (key - v[low]) * (high - low) / (v[high] - v[low]);
        if (v[pos] == key) return pos;
        if (v[pos] < key) low = pos + 1;
        else high = pos - 1;
    }
    return -1;
}

int searchFibonacci(const vector<int>& v, int key) {
    int n = v.size();
    int fm2 = 0, fm1 = 1, fm = fm2 + fm1;
    while (fm < n) {
        fm2 = fm1;
        fm1 = fm;
        fm = fm1 + fm2;
    }
    int offset = -1;
    while (fm > 1) {
        int i = min(offset + fm2, n - 1);
        if (v[i] < key) {
            fm = fm1;
            fm1 = fm2;
            fm2 = fm - fm1;
            offset = i;
        } else if (v[i] > key) {
            fm = fm2;
            fm1 = fm1 - fm2;
            fm2 = fm - fm1;
        } else return i;
    }
    if (fm1 && offset + 1 < n && v[offset + 1] == key) return offset + 1;
    return -1;
}

// ДЕМОНСТРАЦИИ
void demoSelectMinSort() { vector<int> data = {64,25,12,22,11}; cout<<"Сортировка выбором:\n"; show(data); selectMinSort(data); cout<<"После сортировки:\n"; show(data);}
void demoBubbleSortFast() { vector<int> data={64,34,25,12,22,11}; cout<<"\nСортировка пузырьком:\n"; show(data); bubbleSortFast(data); cout<<"После сортировки:\n"; show(data);}
void demoInsertSort() { vector<int> data={12,11,13,5,6}; cout<<"\nСортировка вставками:\n"; show(data); insertSort(data); cout<<"После сортировки:\n"; show(data);}
void demoMergeSort() { vector<int> data={38,27,43,3,9,82,10}; cout<<"\nСортировка слиянием:\n"; show(data); vector<int> sorted=mergeSort(data); cout<<"После сортировки:\n"; show(sorted);}
void demoShellSort() { vector<int> data={23,12,1,8,34,56,7}; cout<<"\nСортировка Шелла:\n"; show(data); shellSort(data); cout<<"После сортировки:\n"; show(data);}
void demoQuickSort() { vector<int> data={10,7,8,9,1,5}; cout<<"\nБыстрая сортировка:\n"; show(data); quickSort(data,0,data.size()-1); cout<<"После сортировки:\n"; show(data);}
void demoHeapSort() { vector<int> data={12,11,13,5,6,7}; cout<<"\nПирамидальная сортировка:\n"; show(data); heapSort(data); cout<<"После сортировки:\n"; show(data);}
void demoFindLinear() { vector<int> data={3,8,1,10,5}; int key=10; int pos=findLinear(data,key); cout<<"\nЛинейный поиск "<<key<<": "; if(pos!=-1) cout<<"найден на позиции "<<pos<<"\n"; else cout<<"не найден\n";}
void demoFindBinary() { vector<int> data={1,3,5,7,9,11}; int key=7; int pos=findBinary(data,key); cout<<"Бинарный поиск "<<key<<": "; if(pos!=-1) cout<<"найден на позиции "<<pos<<"\n"; else cout<<"не найден\n";}
void demoSearchInterpolation() { vector<int> data={10,15,20,25,30,35,40,45,50}; int key=35; int pos=searchInterpolation(data,key); cout<<"Интерполяционный поиск "<<key<<": "; if(pos!=-1) cout<<"найден на позиции "<<pos<<"\n"; else cout<<"не найден\n";}
void demoSearchFibonacci() { vector<int> data={10,22,35,40,45,50,80,82,85,90,100}; int key=85; int pos=searchFibonacci(data,key); cout<<"Поиск Фибоначчи "<<key<<": "; if(pos!=-1) cout<<"найден на позиции "<<pos<<"\n"; else cout<<"не найден\n";}

// MAIN
int main() {
    demoSelectMinSort();
    demoBubbleSortFast();
    demoInsertSort();
    demoMergeSort();
    demoShellSort();
    demoQuickSort();
    demoHeapSort();
    demoFindLinear();
    demoFindBinary();
    demoSearchInterpolation();
    demoSearchFibonacci();
    return 0;
}
