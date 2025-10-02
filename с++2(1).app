#include <iostream>
#include <stack>
using namespace std;

int main() {
    stack<string> st;
    string x = "x", y = "y", z = "z";
    st.push(x);
    st.push(y);
    st.push(z);
    string top = st.top();
    st.pop();
    cout << top;
}
