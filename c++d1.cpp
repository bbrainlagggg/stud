#include <iostream>
#include <vector>   
#include <stack>    
#include <string>

int main() {
    std::vector<int> nums{10, 20, 30};
    nums.push_back(40);
    std::cout << "Список: ";
    for (int x : nums) std::cout << x << ' ';
    std::cout << "\nПервый элемент: " << nums.front()
              << "\nДлина: " << nums.size() << "\n\n";

    std::stack<std::string> st;
    st.push("A");                
    st.push("B");
    std::cout << "Верх стека: " << st.top() << "\n"; 
    st.pop();                    
    std::cout << "После pop верх: " << st.top() << "\n";
    std::cout << "Пустой ли стек? " << (st.empty() ? "да" : "нет") << "\n";
    return 0;
}
