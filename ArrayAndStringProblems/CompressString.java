/*
CompressString.java
Creator: Andrew Risse
This class takes a string as input and compresses it using the number of repeated
characters. Ex: "aabbbbbbcccd" compresses to a2b6c3d1. If the compressed version
of the string would not be shorter than the uncompressed normal string, it 
returns the uncompressed string. Uses StringBuilder class to avoid and O(n^2) time
due to string concatenation.
Assumption: the string only contains uppercase
and lowercase letters. 
*/

package com.andrewrisse.maven;

import java.lang.StringBuilder;

public class CompressString {
    public String compressString(String str){
        int compressLength = checkCompressLength(str);
        if(compressLength >= str.length()) return str; // normal string is equal or shorter lenth than compressed verdsion
        StringBuilder compress = new StringBuilder(compressLength);
        int countChars = 0;
        for(int i = 0; i < str.length(); i++){
            countChars ++;
            if((i + 1 == str.length()) || str.charAt(i) != str.charAt(i+1)){ //if the next character is different or we reached the end of the string, add the character and its count
                compress.append(str.charAt(i));
                compress.append(countChars);
                countChars = 0;
            }
           
        }
        return compress.toString();
    }

    //calculates how many characters are needed for the compressed version of the string
    private int checkCompressLength(String str){
        int compressionCount = 0;
        int totalLength = 0;
        for (int i = 0; i < str.length(); i++){
            compressionCount ++;
            if( i + 1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
                totalLength += 1 + String.valueOf(compressionCount).length();//the character itself plus its count
                compressionCount = 0;
            }
        }
        return totalLength;
    }
    public static void main(String[] args){
        CompressString compressor = new CompressString();
        System.out.println(compressor.compressString("aabbbbbbcccd"));
    }
}
