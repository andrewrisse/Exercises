"""
stringIsPermutation.py
Creator: Andrew Risse
This program checks if one string is a permutation of another. 
There are two different implementations.
Assumes extended ASCII input. Case sensitive and spaces count as unique character.
"""

# Uses a hash map "like" implementation
# O(n) time, O(1) space
def stringIsPermutation(str1, str2):
    if len(str1) != len(str2): #if strings are not same length, cannot be permutation
        print ("Not a permutation")
        return False
    
    array = [0] * 256 #assuming extended ASCII
    for c in str1: # increase a counter at the array index for each character
        array[ord(c)] += 1
    for c in str2: # subrtract one at the array index for each character each time it is encountered
        array[ord(c)] -= 1
        if array[ord(c)] < 0: # if the value in the array goes negative, then it is not a permutation
            print ("Not a permutation")
            return False
    print ("Is a permutation")
    return True

# Uses sorting
# O(nlogn) time, O(n) space
def stringIsPermutation2(str1, str2):
    if len(str1) != len(str2): #if strings are not same length, cannot be permutation
        print ("Not a permutation")
        return False
    # turn the strings into lists split on a space delimeter
    str1 = list(str1.split(" ")) 
    str2 = list(str2.split(" "))   
    # for each element in the list, sort each character
    str1 = [''.join(sorted(i)) for i in str1]
    str2 = [''.join(sorted(i)) for i in str2]
    # sort the whole list 
    str1 = sorted(str1)
    str2 = sorted(str2)

    if str1 == str2:
        print ("Is a permutation")
        return True
    else: 
        print ("Not a permutation")
        return False 

    

#test cases
print ("stringIsPermutation:")
stringIsPermutation("dog", "god")       #yes
stringIsPermutation("dog", "gad")       #no
stringIsPermutation("dog", "godd")      #no
stringIsPermutation("dog", "God")       #no
stringIsPermutation("do g", "God")      #no
stringIsPermutation("dog is", "is god") #yes

print ("stringIsPermutation2:")
stringIsPermutation2("dog", "god")        #yes
stringIsPermutation2("dog", "gad")       #no
stringIsPermutation2("dog", "godd")      #no
stringIsPermutation2("dog", "God")       #no
stringIsPermutation2("do g", "God")      #no
stringIsPermutation2("dog is", "is god") #yes
