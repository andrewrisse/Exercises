"""
file: DynamicArray.py
Trying to learn python. First attempt at implementing a data structure in python after looking at an example, example is taken from:
https://www.geeksforgeeks.org/implementation-of-dynamic-array-in-python/
Created By: Andrew Risse
"""
import ctypes

class DynamicArray(object):

    def __init__(self):
        self.n = 0 #number of actual elements, default is 0
        self.capacity = 1 #default capacity is 1
        self.A = self.make_array(self.capacity)
    
    def len(self):
        """
        Return the capacity of the array  
        """
        return self.capacity
    
    def __getitem__(self, k):
        """
        Return the item at index k
        """
        if 0 <= k < self.n:
            return self.A[k]
        else: print ("Invalid k")

    def append(self, ele):
        if self.n == self.capacity: #array is full, need to resize
            self._resize(2 * self.capacity) #double the array size
        self.A[self.n] = ele  #add the new element to the end of the array
        self.n += 1  #increase total number of actual elements

    def _resize(self, new_cap):
        """
        Resize the array
        """
        B = self.make_array(new_cap)
        for k in range(self.n):
            B[k] = self.A[k] #copy elements from old array into new array
        self.A = B  #point the original array to the new one
        self.capacity = new_cap  #set the new capacity 

    def make_array(self, new_cap):
        """
        Return a new array with the new capacity
        """
        return (new_cap * ctypes.py_object)()

#Instantiate
arr = DynamicArray()
arr.append(1)
print("capacity of array is: ",arr.len())

arr.append(2)
print("capacity of array is: ",arr.len())

arr.append(3)
print("capacity of array is: ",arr.len())

print("arr[0] is: ", arr[0])
print("arr[1] is: ", arr[1])
print("arr[2] is: ", arr[2])    

