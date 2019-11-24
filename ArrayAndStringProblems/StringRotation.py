def StringRotation(str1, str2):
    if( len(str1) != len(str2)):
        print("Not a rotation")
        return False
    else:
        str3 = str1 + str1
        if(str2 in str3):
            print("Is a rotation")
            return True
    print("Not a rotation")
    return False
    
StringRotation("waterbottle", "erbottlewat")