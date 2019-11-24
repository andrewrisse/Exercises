"""
oneEditAway.py
Creator: Andrew Risse
This program takes two strings and checks if they are within an edit distance of 0 or 1. O(n)
"""
# determine if the strings are one potentially one or zero edits away with a replace, insert, or remove
def oneEditAway(str1, str2):
    if(len(str1) == len(str2)):
        return checkReplace(str1, str2)
    elif (len(str1) + 1 == len(str2)):
        return checkInsert(str1, str2)
    elif (len(str1) - 1 == len(str2)):
        return checkRemove(str1, str2)
    else:
        return False

# we can only find one character different if we are going to replace
def checkReplace(str1, str2):
    foundDiff = 0
    for i in range(0,len(str1)):
        if( str1[i] != str2[i]):
            if (foundDiff):
                return False
            foundDiff = 1 
    return True

# if str1 is shorter than str2 by 1 char, the first time a different char is found, we shift over 1 char in str 2 and ensure the rest are the same
def checkInsert(str1, str2):
    index= 0
    foundDiff = 0
    for i in range(0, len(str1)):
        if (str1[i - index] != str2[i]):
            if(foundDiff):
                return False
            foundDiff = 1 
            index += 1 # found a non matching char, index is used to shift the string by continuing to look at the same str1 char on next pass, this can only be reached once
    return True

# if str1 is longer by 1 char, the firt time a different char is found, we shift over 1 char and ensure the rest are the same  
def checkRemove(str1, str2):
    index = 0
    foundDiff = 0
    for i in range(0,len(str1)-1):
        
        if(str1[i] != str2[i - index]):
            if(foundDiff):
                return False
            foundDiff = 1 # found a non matching char, index is used to shift the string by continuing to look at the same str2 char on next pass, this can only be reached once
            index += 1
    return True
    

# test cases
test_cases = ["abcd", "abc","abc","abcd","aabc","abc","abc","aabc", "abc", "abc","abc","bbc","bbc","abc","aac", "abc", "abc", "aac","abc", "ade", "abcde", "abc", "abc", "abcde"]

for i in range(1, len(test_cases), 2):
    if(oneEditAway(test_cases[i-1], test_cases[i])):
        print("Strings " + test_cases[i-1] + " and " + test_cases[i] + " are one or zero edits away")
    else:
        print("Strings " + test_cases[i-1] + " and " + test_cases[i] + " are not one edit away")
            