"""
checkPermPalindrome.py
Creator: Andrew Risse
This program checks a string to see if it has a permutation that is a palindrome. It is not case sensitive, 
non letter characters are ignored.
"""

# return decimal value of character with a=0,z=25 non-letter chars return -1. Handles capital letters.
def getCharNum(c):
    if '@' < c < '[': # character is a capital letter
        return ord(c) - 65  # return lowercase decimal value 
    if ('a' <= c <= 'z'): 
        return ord(c) - 97
    else:
        return -1 # character is non-letter
    
def checkPermPalindrome(str):
    array = [0] * 26
    for c in str:
        index = getCharNum(c) 
        if(index >= 0): # skip over characters that are not letters
            array[index] += 1 # add a count for each character
    hasOdd = False
    for i in range(0,25):
        if((array[i]%2) == 1): # found an odd number of characters, can only happen once if the string has an odd number of characters
            if(hasOdd):
                return False
            hasOdd = True
    return True
    
def main():
    
    testCases = ["Tact Coa", "abc", "should return false", "bbaa"," 12bab", "12abc"]
    for t in testCases:
        if checkPermPalindrome(t):
            print("String: \"",t, "\" has a permutation that is a palindrome")
        else:
            print("String: \"",t, "\" does not have a permutation that is a palindrome")
            
if __name__ == "__main__":
    main()
