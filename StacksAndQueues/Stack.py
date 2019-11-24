'''
Stack.py
creator: Andrew Risse
This program has the following classes: Node, Stack, SetOfStacks, and MyQueue.
Stack is a normal stack implementation.
SetOfStacks is a collection of stacks, new stacks are created when capacity is reached.
MyQueue uses two stacks to implement a queue.
'''

class Node:
 
    def __init__(self, val):
        self.val = val
        self.above = None
        self.below = None

# Stack data structure following a first in last out paradigm, used top and bottom pointers to
# top and bottom pointers as well as removeBottom method are necessary for SetOfStacks functionality
class Stack:
    
    def __init__(self, capacity):
        self.capacity = capacity
        self.size = 0
        self.top = None
        self.bottom = None
    
    #remove top element
    def pop(self):
        if self.size > 0:
            self.size -= 1
            item = self.top
            self.top = self.top.below
            return item
        else:
            return False
    
    #push an element onto the stack
    def push(self, val):
        if self.size < self.capacity: # not full 
            self.size += 1 
            n = Node(val)
            if self.size == 1: 
                self.bottom = n
            self.join(n, self.top) #connect the new node to old top
            self.top = n
            return True
        else:
            return False

    #join two nodes        
    def join(self, above, below):
        if below != None:
            below.above = above
        if above != None:
            above.below = below

    # return top element's val, but don't pop it        
    def peek(self):
        if self.isEmpty(): return False
        else:
            return self.top.val

    # remove the bottom element in the stack
    def removeBottom(self):
        originalBottom = self.bottom
        self.bottom = self.bottom.above
        if self.bottom != None: 
            self.bottom.below = None # make sure the new bottom doesn't point to another node
        self.size -= 1 
        return originalBottom.val
        
    def isFull(self):
        return self.size == self.capacity
        
    def isEmpty(self):
        return self.size == 0
        
    def printStack(self):
        node = self.top
        nodeList = []
        while node != None:
            nodeList.append(node.val)
            node = node.below
        print (nodeList)

    #sort the stack so min items are on top
    def sort(self):
        tempStack = Stack(self.capacity) #create a temporary stack, used to re-order elements
        while(not self.isEmpty()):
            tempItem = self.pop()
            while(not tempStack.isEmpty() and (tempStack.peek() > tempItem.val)): # compare next time with top item in temp stack
                self.push(tempStack.pop().val) #if the top temp stack item is greater, push that one back onto the original stack
            
            tempStack.push(tempItem.val) #push this lesser item onto the temp stack
        while(not tempStack.isEmpty()): #take temp stack items and pop them to reverse their order, push them onto original stack
            self.push(tempStack.pop().val)
        
        
# Stack where is it is filled up, it creates another stack     
class SetOfStacks:
    def __init__(self, capacity):
        self.stacks = []
        self.capacity = capacity   

    # returns the last stack created    
    def lastStack(self):
        if len(self.stacks) == 0: 
            return None
        else:
            return self.stacks[len(self.stacks) - 1]

    #push an item onto the stack    
    def push(self, val):
        stack = self.lastStack()
        
        if stack == None or stack.isFull(): # create a new stack if first time or full
            newStack = Stack(self.capacity)
            newStack.push(val)
            self.stacks.append(newStack)
            
        else:
            stack.push(val)
    
    #remove top element from stack (will be the last stack)
    def pop(self):
        stack = self.lastStack()
        if stack == None:
            return False # error, no stack
        else:
           node = stack.pop()
           if stack.size == 0:
               self.stacks.pop() # if the stack is emptied, get rid of it from the list of stacks
    
    # pop from a specific stack
    def popAt(self, index):
        self.shiftLeft(index, True)

    # used to implement popAt, first time called it removes the top element from the 
    # indicated stack, second time it takes bottom element from stack to the right and puts it on top
    # of the stack that we originally popped from, this is done continuosly down the line to shift 
    # all the stacks over   
    def shiftLeft(self, index, popTop):
        stack = self.stacks[index]
        if(popTop):
            removedItem = stack.pop()
        else:
            removedItem = stack.removeBottom()
        if stack.isEmpty(): self.stacks.pop(index) #if the stack is emptied, remove it from list of stacks
        else:
            if len(self.stacks) > index + 1: # there are more stacks to the right of this one
                nodeVal = self.shiftLeft(index + 1, False)
                stack.push(nodeVal) # push the bottom node from the stack to the right onto this stack
        return removedItem

# Implementation of Queue data structure following first in first out paradigm, uses two stacks to do so
class MyQueue:

    def __init__(self, capacity):
        self.oldStack = Stack(capacity) # used to store oldest items in the stack in reverse order
        self.newStack = Stack(capacity) # 1st stack where items get pushed on to
    
    # remove the oldest item
    def remove(self):
            self.swap()
            return self.oldStack.pop()

    # anytime a pop or peek is called, this function is called and all items from the newStack
    # are popped and pushed onto the old stack (only when old stack is empty). This places the items
    # in the proper order for a first in first out during removal
    def swap(self):
        if self.oldStack.isEmpty():
            while not self.newStack.isEmpty():
                self.oldStack.push(self.newStack.pop().val)
    # push node onto the newStack
    def add(self, val):
        self.newStack.push(val)
    # Returns the oldest item in the queue
    def peek(self):
        self.swap()
        return self.oldStack.peek()



''' 
stack = Stack(3)
stack.push(1)
stack.push(2)
stack.push(3)
stack.printStack()
'''

testSet = SetOfStacks(3)
testSet.push(1)
testSet.push(2)
testSet.push(3)
testSet.push(4)
testSet.push(5)
testSet.push(6)
testSet.push(7)

print ("stacks are: ")
for stack in testSet.stacks:
    stack.printStack()
    
testSet.popAt(1)

print("stacks are: ")
for stack in testSet.stacks:
    stack.printStack()

testSet.pop()

print("stacks are: ")
for stack in testSet.stacks:
    stack.printStack()

print("test MyQueue")

queue = MyQueue(4)
queue.add(1)
queue.add(2)
queue.add(3)
queue.add(4)
print("newQueue is: ")
queue.newStack.printStack()

print("Remove(): ")
print(queue.remove().val)

print("newQueue is: ")
queue.newStack.printStack()
print("oldQueue is: ")
queue.oldStack.printStack()

print("Adding two more elements")
queue.add(5)
queue.add(6)

print("newQueue is: ")
queue.newStack.printStack()
print("oldQueue is: ")
queue.oldStack.printStack()

print("Remove(): ")
print(queue.remove().val)

print("newQueue is: ")
queue.newStack.printStack()
print("oldQueue is: ")
queue.oldStack.printStack()

print("Test short stack: ")

ss = Stack(4)
ss.push(4)
ss.push(3)
ss.push(7)
ss.push(1)

print("ss is: ")
ss.printStack()
 
ss.sort()
ss.printStack()


            