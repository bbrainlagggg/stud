nums = [10, 20, 30]
nums.append(40)              
print("Список:", nums)       
print("Первый элемент:", nums[0])
print("Длина:", len(nums))

stack = []
stack.append("A")            
stack.append("B")
print("Стек:", stack)        
print("Верх стека:", stack[-1])  
top = stack.pop()            
print("Сняли:", top)
print("Стек после pop:", stack)
