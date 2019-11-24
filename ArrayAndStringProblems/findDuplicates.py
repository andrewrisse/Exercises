'''
findDuplicates.py
Creator: Andrew Risse
This program assumes an ASCII string input and outputs whether or not there is a duplicate character. 
The first function accomplishes this using a list/array, the second uses a bit vector
O(n) time, O(1) space
 
'''

# finds duplicates using a list/array
def findDuplicates(str):
    if len(str) > 256: #if string is longer than 256 characters, it must have a duplicate
        return False
    array = [None] * 256
    #iterate through each character, check if the character has been seen and placed in the array, if not, add to the array
    for c in str:
        i = ord(c)
        if array[i] == i:
            print ("Has duplicate")
            return False
        else:
            array[i] = i
    print("No duplicates")
    return True
    
# finds duplicates using a bit vector    
def findDuplicatesBitArray(str):
    if len(str) > 256: #if string is longer than 256 characters, it must have a duplicate
        return False
    check = 0  # a vector of all 0's to initially check against
    
    for c in str:
        var = ord(c) - ord('a')  # subtract value of 'a' from each character so when it is shifted left with 1<<var  a = 0001 (2^0), b = 0010 (2^1), c = 0100 (2^2), etc
        if check&(1<<var): # and check with the shifted char value, if they are they have anything in common, then there is a duplicate character
            print ("Has duplicates") 
            return False
        else:
            check = check|1<<var  # or check with the shifted character to mark it as "seen"
    print("No duplicates")
    return True
                    
        
    
#test cases
findDuplicates("abcadef")
findDuplicates("abcdefg")

findDuplicatesBitArray("abbcdefg")
findDuplicatesBitArray("abcdefg")