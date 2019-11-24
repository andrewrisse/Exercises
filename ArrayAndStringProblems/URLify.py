"""
URLify.py
Creator: Andrew Risse
Drew heavily from example 1.3 in "Cracking the Coding Interview" in attempt to understand and 
write their Java version in Python.
This program replaces spaces in a string with '%20'.
Assumptions: the string has sufficient space at the end to hold the additional characters and we are given
the "true" string length.
"""

def URLify(str, trueLength):
    numSpaces = countChar(str, 0, trueLength, ' ') # count number of spaces in string
    newLength = trueLength - 1 + (numSpaces * 2) # requires 2 spaces to insert %20 since we already have a ' '
    lst = list(str) # convert string into a list because Python strings are immutable
    if newLength + 1 < len(str): #if there are extra spaces at the end of the string, don't replace those with %20, replace with null
        lst[newLength + 1] = '\0'
    oldLength = trueLength - 1 # adjust length for next for loop and the fact tha the first index is 0
    # work from end of list to front
    for oldLength in range(oldLength, -1, -1):
        #replace spaces
        if lst[oldLength] == ' ':
            lst[newLength] = '0'
            lst[newLength - 1] = '2'
            lst[newLength - 2] = '%'
            newLength -= 3
        else: #copy character
            lst[newLength] = lst[oldLength] 
            newLength -= 1
    str =''.join(lst) #turn list back into a string
    return str

# count how many target characters are in the string
def countChar(str, start, end, target):
    count = 0
    for i in range(start, end):
        if (str[i] == target):
            count += 1
    return count

#test case
print(URLify ("Mr John Smith    ", 13))
print(URLify (" This is a test        ", 15))
