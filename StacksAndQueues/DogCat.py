'''
DogCat.py
creator: Andrew Risse
This program creates a AnimalQueue called pound that has dog and cats of the type Animal. Functions include deQueueAny which removes 
the animal that has been in the pound the longest, deQueueDog which removes the Dog that has been in the pound the longest, and 
deQueueCat which removes the Cat that has been in the pound the longest.
'''

class Animal():
    order = None
    name = None

    def __init__(self, name):
        self.name = name

    def setOrder(self, order):
        self.order = order

    def getOrder(self):
        return self.order
    
        
class Dog(Animal):
    def __init__(self, name):
        Animal.__init__(self, name)

class Cat(Animal):
    def __init__(self, name):
        Animal.__init__(self, name)

class AnimalQueue():
    dogs = []
    cats = []
    order = 0

    #add an animal to the queue
    def enqueue(self, animal): 
        animal.setOrder(self.order) #set order of arrival
        self.order += 1

        if type(animal) == Dog:
            self.dogs.append(animal)
        else:
            self.cats.append(animal)

    # deQueue the oldest animal
    def deQueueAny(self):
        if(len(self.dogs) == 0):
            return self.deQueueCat()
        elif (len(self.cats) == 0):
            return self.deQueueDog()

        if(self.dogs[0].order < self.cats[0].order):
            return self.dogs.pop(0)
        else:
            return self.cats.pop(0)
    # deQueue the oldest cat
    def deQueueCat(self):
        if(len(self.cats) == 0):
            return None
        else:
            return self.cats.pop(0)
    # dequeue the oldest dog
    def deQueueDog(self):
        if(len(self.dogs) == 0):
            return None
        else:
            return self.dogs.pop(0)

    

#Test Cases

Bobo = Dog('Bobo')
Bella = Dog('Bella')
Mike = Dog('Mike')
Steve = Dog('Steve')
Charles = Cat('Charles')
Mary = Cat('Mary')
Susie = Cat('Susie')
Tom = Cat('Tom')

pound = AnimalQueue()
pound.enqueue(Bobo)
pound.enqueue(Charles)
pound.enqueue(Bella)
pound.enqueue(Susie)
pound.enqueue(Mike)
pound.enqueue(Steve)
pound.enqueue(Mary)
pound.enqueue(Tom)

print('Dogs: ')
for a in pound.dogs:
    print (a.name)
print('\n')
print('Cats: ')
for a in pound.cats:
    print (a.name)
print('\n')
print("DequeueAny: ")
print(pound.deQueueAny().name)
print("DequeueAny: ")
print(pound.deQueueAny().name)
print("DequeueAny: ")
print(pound.deQueueAny().name)
print("DequeueDog: ")
print(pound.deQueueDog().name)
print("DequeueCat: ")
print(pound.deQueueCat().name)


