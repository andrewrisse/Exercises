/*
LinkedListNode.java
Creator: Andrew Risse
Java class for a singly linked list.
Methods: 
-- public void createRandomList(int length, int range): creates a singly linked list of specified length and values within specified range
-- public void printList(): prints the linked list
-- public int length(): return the length of the list
-- insert(LinkedListNode newNode): inserts a new node at the end of the list
-- public static boolean deleteMiddleNode(LinkedListNode n): delete a node somewhere in the middle of the list (not on the ends)
-- public static LinkedListNode partition(LinkedListNode node, int k): Partition the list such that the 1st partition has nodes < k, and 2nd has nodes >= k, nodes in partitions do not have to be in order
-- public static LinkedListNode combineLists(LinkedListNode l1, LinkedListNode l2): combine to lists into one
-- public static LinkedListNode removeDups(LinkedList list): returns a linked list with duplicate nodes removed O(n)
-- public static LinkedListNode removeDupsNoBuffer(LinkedList list): returns a linked list with dupliacte nodes removed, but does not use 
buffer/hash table to store node data, O(n^2) time, O(1) space
-- public LinkedListNode returnKthLast(int k): returns the kth to last node in the list
-- public static int printKthLastRecursive(LinkedListNode head, int k): print the kth to last node using recursion
-- public static LinkedListNode sumLists(LinkedListNode n1, LinkedListNode n2): sums two lists together when input is formatted with digits listed backwards: 2->4->6 is 642
-- public static LinkedListNode sumLists(LinkedListNode n1, LinkedListNode n2, int carryOver): helper method called by sumLists with two arguements, this method adds a third argument carryOver
-- public static LinkedListNode sumListsForward(LinkedListNode list1, LinkedListNode list2): sums two lists together when input is formatted with digits listed forward: 2->4->6 is 246
-- private static PassUp sumListsForwardHelper(LinkedListNode n1, LinkedListNode n2): helper method for sumListsForward
-- public static LinkedListNode insertFront(LinkedListNode n, int data): insert a node with specified data to the front of the list
-- private static LinkedListNode padZeroes(LinkedListNode head, int numZeroes): add a specified number of zeroes to the front of the list, used for summing lists
-- public boolean recursivePalindrome(LinkedListNode list): recursively checks to see if a list is a palindrome
-- private PalinResult recursivePalindrome(LinkedListNode list, PalinResult result, int length): helper method for recursivePalindrome
-- public boolean iterativePalindrome(LinkedListNode list): checks if a list is a palindrome iteratively
-- public LinkedListNode intersection(LinkedListNode l1, LinkedListNode l2): determines if two lists intersect and returns that node where they intersect
-- private LengthTailResult lengthAndTail (LinkedListNode list): returns the tail node of a list and the length of the list, used for intersection
-- public static LinkedListNode loop(LinkedListNode list): check if a list has a loop and return the node where the loop starts
Class:
private static class PassUp: wrapper class used to sum lists and pass back values recursively
private class PalinResult: wrapper class used to pass data in recursivePalindrome
private class LengthTailResult: wrapper class used to return tail node and length of list for intersection
*/

package com.andrewrisse.maven;

import java.util.Random;
import java.util.HashSet;
import java.util.Stack;

public class LinkedListNode{
    LinkedListNode next = null;
    int data;

    public LinkedListNode(){ //Constructor
    }
    public LinkedListNode(int d){ //Constructor
        data = d;
    }
    
     //create a list of specifid length with values in specified range
     public void createRandomList(int length, int range){
        Random rand = new Random();
        this.data = rand.nextInt(range);
        for(int i = 1; i < length; i++){
            LinkedListNode node = new LinkedListNode(rand.nextInt(range));
            this.insert(node);
        }
    }

       //print the list
       public void printList(){
        LinkedListNode current = this;
            while(current != null){
                System.out.print(current.data + "->");
                current = current.next;
            }
            System.out.print("\n");
    }

    //return the length of the list
    public int length(){
        int len = 0;
        LinkedListNode n = new LinkedListNode();
        n = this;
        while (n != null){
            len ++;
            n = n.next;
        }
        return len;
    }


    //insert a node to the end of a list, note: if used on a list that has been created but
    //no data was defined, the first node automatically created has data 0 and the insert method
    //will add the new node after that intial node with data 0
    public void insert(LinkedListNode newNode){ 
        
        LinkedListNode n = this;
        
            while(n.next != null){ // traverse through list to end
                n = n.next;
            }
            n.next = newNode;
        
    }

     //delete a node somewhere in the middle of the list (not on the ends)
     public static boolean deleteMiddleNode(LinkedListNode n){
        if(n == null || n.next == null){
            return false; // bad input
        }
        n.data = n.next.data;
        n.next = n.next.next;
        return true;
    }

    //Partition the list such that the 1st partition has nodes < k, and 2nd has nodes >= k
    //nodes in partitions do not have to be in order
    public static LinkedListNode partition(LinkedListNode node, int k){ 
        LinkedListNode startList = new LinkedListNode();
        LinkedListNode endList = new LinkedListNode();
        int firstStart = 1;
        int firstEnd = 1;
        
        while( node != null){
            
            LinkedListNode newNode = new LinkedListNode();
            if(node.data < k){
                if(firstStart == 1){
                    startList.data = node.data;
                    firstStart = 0;
                }
                else{
                    newNode.data = node.data;
                    startList.insert(newNode);
                }
            }
            else {
                if(firstEnd == 1){
                    endList.data = node.data;
                    firstEnd = 0;
                }
                else{
                    newNode.data = node.data;
                    endList.insert(newNode);
                }
            }
            node = node.next;
        }
        return combineLists(startList, endList);
        
    }
    
    //combine two lists
    public static LinkedListNode combineLists(LinkedListNode l1, LinkedListNode l2){
        LinkedListNode newList = new LinkedListNode();
        //reset 1st node data/next from 0 to l1.data/mext
        newList.data = l1.data; 
        l1 = l1.next;
        while( l1 != null){ // iterate throught each node in l1 and add to the new list
            LinkedListNode temp = new LinkedListNode();
            temp.data = l1.data;
            newList.insert(temp);
            l1 = l1.next;
        }
        while( l2 != null){// iterate throught each node in l2 and add to the new list
            LinkedListNode temp = new LinkedListNode();
            temp.data = l2.data;
            newList.insert(temp);
            l2 = l2.next;
        }
        return newList;
    }

    //remove duplicate nodes in list
    public static LinkedListNode removeDups(LinkedListNode list){
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode previous = null;
        LinkedListNode n = list;
        while(n != null){
            if(set.contains(n.data)){
                previous.next = n.next;
            }
            else{
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
        return list;
    }

    //remove duplicate nodes in list without a buffer/hash table to store node data
    public static LinkedListNode removeDupsNoBuffer(LinkedListNode list){
        LinkedListNode current = list;
        while(current != null){
            LinkedListNode runner = current;
            while(runner.next != null){
                if(current.data == runner.next.data){
                    runner.next = runner.next.next;
                }
                else{
                    runner = runner.next;
                }
            }
            current = current.next;
        }
        return list;
    }
    // returns the kth to last node  
    public LinkedListNode returnKthLast(int k){
        LinkedListNode current = this;
        LinkedListNode runner = this;
        while( k > 0 ){
            if(runner.next == null){
                return null; // k out of bounds
            }
            runner = runner.next; // move runner k nodes away from current
            k --;
        }
        while( runner != null){
            current = current.next;
            runner = runner.next;
        }
        return current;
    }

    //print kth to last node using recursion
    public static int printKthLastRecursive(LinkedListNode head, int k){
        if(head == null){
            return 0;
        }
        
        int counter = printKthLastRecursive(head.next, k) + 1;
        if ( counter == k ){
            System.out.println(k + " to last element is: " + head.data);
        }
        return counter;
    }
    
    //sums two lists together when input is formatted with digits listed backwards: 2->4->6 is 642
    public static LinkedListNode sumLists(LinkedListNode n1, LinkedListNode n2){
        LinkedListNode head = sumLists(n1,n2, 0);
        return head;
        
    }
    //helper method called by sumLists with two arguements, this method adds a third argument carryOver
    public static LinkedListNode sumLists(LinkedListNode n1, LinkedListNode n2, int carryOver){
        LinkedListNode newNode = new LinkedListNode();
        if(n1 == null && n2 == null && carryOver == 0){ //base case
            return null;
        }
        else{
                int sum = carryOver;
                if(n1 != null){
                    sum += n1.data; //add node 1's data to sum
                }
                if(n2 != null){
                    sum += n2.data; //add node 2's data to sum
                }
                int value = 0;
                value = sum % 10; //value of the sum without the carryOver
                newNode.data = value;
                newNode.next = sumLists(n1 == null ? null : n1.next, n2 == null ? null : n2.next,sum >= 10 ? 1: 0); //recurse, send a carryOver integer if sum was >=10
        }
        return newNode;
    }
    
    //wrapper class used to sum lists and pass back values recursively
    private static class PassUp{
        LinkedListNode node = null;
        int carryOver = 0; //carry over digit from math
    }

    //sums two lists together when input is formatted with digits listed forward: 2->4->6 is 246
    public static LinkedListNode sumListsForward(LinkedListNode list1, LinkedListNode list2){
        int length1 = list1.length();
        int length2 = list2.length();
        if(length1 > length2){ //if one list/number is longer than the other, need to pad the front with 0's to make them equal length
            list2 = padZeroes(list2, length1 - length2);
        }
        else if(length2 > length1){
            list1 = padZeroes(list1, length2 - length1);
        }
        
        PassUp finalResult = sumListsForwardHelper(list1, list2);
        //check if there was a carryOver from the last operation and if so, add it to the front of the list
        if(finalResult.carryOver == 0){
            return finalResult.node;
        }
        else{
            LinkedListNode finalAnswer = insertFront(finalResult.node, finalResult.carryOver);
            return finalAnswer;
        }
    }

    //helper method for sumListsForward
    private static PassUp sumListsForwardHelper(LinkedListNode n1, LinkedListNode n2){
        if(n1 == null && n2 == null){  //base case
            PassUp pass = new PassUp();
            return pass;
        }
        PassUp pass = sumListsForwardHelper(n1.next, n2.next); // recurse to end of lists
        int sum = n1.data + n2.data + pass.carryOver; //add data and carryOver
        LinkedListNode resultNode = insertFront(pass.node, sum % 10); //insert into front of list
        pass.node = resultNode;
        pass.carryOver = sum / 10; //save the carryOver
        return pass;
    
    }
    
    //insert a node with specified data to the front of the list
    public static LinkedListNode insertFront(LinkedListNode n, int data){
        LinkedListNode newNode = new LinkedListNode();
            newNode.data = data;
            newNode.next = n;
        return newNode;
    }
    
    //add a specified number of zeroes to the front of the list, used for summing lists
    private static LinkedListNode padZeroes(LinkedListNode head, int numZeroes){
        LinkedListNode newHead = head;
        for(int i = 0; i < numZeroes; i ++){
            newHead = insertFront(newHead, 0);
        }
        return newHead;
    }

    //wrapper class used to pass data in recursivePalindrome
    private class PalinResult{
        LinkedListNode node;
        boolean match;
    }

    //Recursively checks to see if a list is a palindrome
    public boolean recursivePalindrome(LinkedListNode list){
        PalinResult result = new PalinResult();
        int length = list.length();
        return recursivePalindrome(list, result, length).match;
    }
    
    //helper method for recursivePalindrome
    private PalinResult recursivePalindrome(LinkedListNode list, PalinResult result, int length){
        
        if(length <= 0){ //even length list
            result.node = list;
            result.match = true;
            return result;
        }
        else if (length == 1){ //odd length list
            result.node = list.next;
            result.match = true;
            return result;
        }

        result = recursivePalindrome(list.next, result, length - 2); //recurse to middle of list

        if(!result.match){ //return false through all levels of recursion if non match was found
            return result;
        }
        if(list.data == result.node.data){ //set the next node to be checked
            result.node = result.node.next;//this is the "back half" of the list
            result.match = true;
            return result;
        }
        else{
            result.match = false;
            return result;
        }
        
    }

    //Checks if a list is a palindrome iteratively
    public boolean iterativePalindrome(LinkedListNode list){
        int length = list.length();
        Stack<Integer> myStack = new Stack();
        int popInt;
        //push first half of nodes on to the stack(reverse the list)
        for(int i = 0; i < length/2; i++){
            myStack.push(list.data);
            list = list.next;
        }
        if(length % 2 > 0){ //odd number of items on stack, middle node doesn't matter
            list = list.next;
        }
        //pop off the stack and continue checking against rest of list
        while (list != null){  
            popInt = myStack.pop();
            if( popInt != list.data ){
                return false;
            }
            list = list.next;
        }
        return true;
    }

    // Determines if two nodes intersect and returns that node where the intersection starts
    public LinkedListNode intersection(LinkedListNode l1, LinkedListNode l2){
        if(l1 == null || l2 == null) return null;
        LengthTailResult result1 = lengthAndTail(l1);
        LengthTailResult result2 = lengthAndTail(l2);
        if(result1.tail != result2.tail){ //if the tails don't match, there is no intersection
            return null;
        }

        //if one list is longer than the other, skip the extra nodes in the longer one
        if(result1.length > result2.length){
            for(int i = 0; i < (result1.length - result2.length); i++){
                l1 = l1.next;
            }
        }
        if(result2.length > result1.length){
            for(int i = 0; i < (result2.length - result1.length); i++){
                l2 = l2.next;
            }
        }
        //look for where the lists intersect by finding the common node
        while(l1 != null & l2 != null){
            if( l1 == l2){
                return l1;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return null;
    }

    // returns the length of a list and th tail node, used for intersection
    private LengthTailResult lengthAndTail (LinkedListNode list){
        int length = 1;
        //go to end of the list
        while( list.next != null){ 
            length ++;
            list = list.next;
        }
        // store the tail node and length of the list
        LengthTailResult result = new LengthTailResult(); 
        result.tail = list;
        result.length = length;
        return result;
    }
    
    //wrapper class used to return tail node and length of list for intersection
    private class LengthTailResult{
        LinkedListNode tail;
        int length;
    }

    //check if a list has a loop and return the node where the loop starts
    public static LinkedListNode loop(LinkedListNode list){
        LinkedListNode runner = list;
        LinkedListNode slow = list;

        //runner moves twice as fast as slow
        while(slow != null && runner!= null){
            slow = slow.next;
            runner = runner.next.next;
            if(slow == runner){
                break; //nodes collided
            }
        }

        if(slow == null || runner == null) return null;// if they never collided, return null

        // return slow to head of list and move slow and runner one node at a time until they collide, then return that node
        slow = list;
        while(slow != runner){
            slow = slow.next;
            runner = runner.next;
        }
        return slow;
    }
    

    public static void main(String[] args){
       
        
        // Test deleteMiddleNode
        System.out.println("Demo deleteMiddleNode n3:");
        LinkedListNode n1 = new LinkedListNode();
        n1.data = 1;
        LinkedListNode n2 = new LinkedListNode();
        n2.data = 2;
        n1.next = n2;
        LinkedListNode n3 = new LinkedListNode();
        n3.data = 3;
        n2.next = n3;
        LinkedListNode n4 = new LinkedListNode();
        n4.data = 4;
        n3.next = n4;
        
        LinkedListNode n5 = new LinkedListNode();
        n5.data = 5;
        n4.next = n5;
        LinkedListNode n6 = new LinkedListNode();
        n6.data = 6;
        n5.next = n6;

        n1.printList();
        deleteMiddleNode(n3);
        n1.printList();
        

        
        // Test partition
        System.out.println("Demo partition:");
        
        n1.data = 6;
    
        n2.data = 2;
        n1.next = n2;
        
        n3.data = 3;
        n2.next = n3;
        
        n4.data = 4;
        n3.next = n4;
        
        
        n5.data = 5;
        n4.next = n5;
        
        n6.data = 6;
        n5.next = n6;

        LinkedListNode n7 = new LinkedListNode();
        n7.data = 1;
        n6.next = n7;
        
        
        n1.printList();
        n1 = partition(n1, 4);
        n1.printList();
        

        //Test removeDups
        System.out.println("Demo removeDups:");
        LinkedListNode list1 = new LinkedListNode();
        list1.createRandomList(20, 10);
        list1.printList();  
        list1 = removeDups(list1);
        list1.printList();  

        
        //Test removeDupsNoBuffer
        System.out.println("Demo removeDupsNoBuffer:");
        LinkedListNode list2 = new LinkedListNode();
        list2.createRandomList(20, 10);
        list2.printList();
        list2 = removeDups(list2);
        list2.printList();  

        //Test returnKthLast
        int k = 3;
        System.out.println("Demo returnKthLast:");
        LinkedListNode kthLast = list2.returnKthLast(k);
        System.out.print("Current list is: ");
        list2.printList();
        System.out.print(k + " to last node is: " + kthLast.data + "\n");

        //Test printKthLastRecursive
        System.out.print("Using recursive print method, ");
        printKthLastRecursive(list2,3);

        //Test for combineLists
        System.out.println("Demo combine lists:");
        list1.printList();
        list2.printList();
        list1 = combineLists(list1, list2);
        list1.printList();

        
        //Test sumLists
        System.out.println("Demo sumLists:");
        LinkedListNode number1 = new LinkedListNode();
        number1.createRandomList(4, 10);
        LinkedListNode number2 = new LinkedListNode();
        number2.createRandomList(3, 10);
        number1.printList();  
        number2.printList();  
        LinkedListNode sum = sumLists(number1, number2);
        sum.printList();

        //Test sumListsForward
        System.out.println("Demo sumListsForward:");
        LinkedListNode number3 = new LinkedListNode();
        number3.createRandomList(4, 10);
        LinkedListNode number4 = new LinkedListNode();
        number4.createRandomList(4, 10);
        number3.printList();  
        number4.printList();  
        LinkedListNode sum2 = sumListsForward(number3, number4);
        sum2.printList();

        //Test recursivePalindrome
        System.out.println("Demo recursivePalindrome:");
        n1.data = 1;
        n1.next = n2;
        n2.data = 2;
        n2.next = n3;
        n3.data = 3;
        n3.next = n4;
        n4.data = 4;
        n4.next = n5;
        n5.data = 3;
        n5.next = n6;
        n6.data = 2;
        n6.next = n7;
        n7.data = 1;
        n1.printList();
        if(n1.recursivePalindrome(n1)){
            System.out.println("Is a palindrome");
        }
        else{
            System.out.println("Is not a palindrome");
        }

        //Test iterative palindrome
        System.out.println("Demo iterativePalindrome:");
        n1.printList();
        if(n1.iterativePalindrome(n1)){
            System.out.println("Is a palindrome");
        }
        else{
            System.out.println("Is not a palindrome");
        }

        //Demo intersection
        n1.data = 1;
        n1.next = n2;
        n2.data = 2;
        n2.next = n3;
        n3.data = 3;
        n3.next = n4;
        n4.data = 4;
        n4.next = null;
        n5.data = 3;
        n5.next = n6;
        n6.data = 5;
        n6.next = n3;
        

        n1.printList();
        n2.printList();
        LinkedListNode intersectionNode = n1.intersection(n1, n5);
        System.out.print("Intersection node and following nodes are: ");
        intersectionNode.printList();

        //Test loop
        System.out.println("Demo loop:");
        n1.data = 1;
        n1.next = n2;
        n2.data = 2;
        n2.next = n3;
        n3.data = 3;
        n3.next = n4;
        n4.data = 4;
        n4.next = n5;
        n5.data = 5;
        n5.next = n3;
      
        LinkedListNode loopNode = loop(n1);
        if(loopNode != null){
            System.out.println("Loop start node is: " + loopNode.data);
        }
        else{
            System.out.println("No loop");
        }
    }
    
}