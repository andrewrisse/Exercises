"""
file: HashMap.py
Contains a HashMap class and test cases to demonstrate implementation
Created by: Andrew Risse
"""

import ctypes

class HashMap():
    
    #create an array 
    def __init__(self, capacity):
        self.arr = [None] * capacity

    #add an element into the HashMap
    def append(self, ele):
        myNode = Node(ele) #turn the element being added into a Node object (linked list)
        h = hash(myNode.val) #hash on the new element
        if h < 0: #ensure the hash is not negative
            h = h * -1
        
        index = h % len(self.arr) #determine index to place Node at based on hash
        print(myNode.val, " hashed to: ", h, " placing at index: ", index)
        
        if self.arr[index]: #collision
         print("Collision!")
         # find the last Node in the list at this index, then place the new Node at the end
         curr = self.arr[index] 
         while(curr.next != None):
             curr = curr.next
         curr.next = myNode
         print(str(myNode.val) + " placed at index: " + str(index) + " on linked list" )

        else: #no collision, add Node at index
            self.arr[index] = myNode
            print(myNode.val, " placed at index: ", index)

    # print the HashMap for a visual representation, only print indexes with nodes
    def print_map(self):
        print("HashMap:")
        for i in range(len(self.arr)): #loop through each index 
            if(self.arr[i]): #check to see if multiple nodes at this
                print(str(i) + ": ", end="")
                self.arr[i].print_list() #print the linked list


# Node class is a linked list implementation with a method to print the list
class Node():
    def __init__(self,val):
        self.val = val
        self.next = None # the next pointer initially points to nothing 
    
    #print all the Nodes in the list
    def print_list(self):
        node = self
        while node:
            print (node.val, end="")
            node = node.next
            if(node):
                print("-> ", end ="")
        print("\n")
        

    
#example
myHash = HashMap(10) #create empty HashMap with a defined size
myHash.append("abc")
myHash.append("abc")
myHash.append("bcd")
myHash.append("cde")
myHash.append("def")
myHash.append("def")
myHash.append("def")
myHash.append(1)
myHash.append(200)
myHash.append(199)
myHash.print_map()


   