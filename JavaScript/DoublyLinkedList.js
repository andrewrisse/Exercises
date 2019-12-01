class Node {
    constructor(data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    constructor() {
        this.head = null;
        this.tail = null;
    }

    //Adds item to list
    append(item) {
        var node = new Node(item);
        if (!this.head) {
            this.head = node;
            this.tail = node;
        }
        else {
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
        }
    }
    //Returns node at given index
    get(index) {
        var temp = this.head;
        for (var i = 0; i < index; i++) {
            if (temp === null) {
                return -1;
            }
            temp = temp.next;
        }
        return temp;
    }

    //Add node to head of list with a given value
    addAtHead(val) {
        var node = new Node(val);
        node.next = this.head;
        this.head.prev = node;
        this.head = node;
    }

    //Add node to tailof list with a given value
    addAtTail(val) {
        var node = new Node(val);
        node.prev = this.tail;
        this.tail.next = node;
        this.tail = node;
    }

    //Add node at specific index of list with a given value
    addAtIndex(val, index) {//Invalid index
        if (index < 0) {
            console.log("Index out of bounds");
        }
        if (index === 0) { //Add to head
            this.addAtHead(val);
            return;
        }
        var node = new Node(val);
        var temp = this.head;
        while (index > 0 && temp !== null) { //find the current node at the index
            temp = temp.next;
            index--;
        }
        //check if index was out of range or tail of list
        if (temp === null) {
            if (index > 0) {
                console.log("Index out of range.");
                return;
            }
            this.addAtTail(val);
            return;
        }
        //add node at index
        node.prev = temp.prev;
        node.prev.next = node;
        node.next = temp;
        temp.prev = node;
    }

    //Delete node at specific index of list
    deleteAtIndex(index) {
        if (index < 0) {//Invalid index
            console.log("Index out of bounds");
        }
        var temp = this.head;
        var count = 0;
        if (index === 0) { //reset head node
            this.head = temp.next;
            return;
        }
        while (temp !== null && count < index) {//find the current node at the index
            temp = temp.next;
            count++;
        }
        if (temp === null) { //invalid index
            console.log("Index out of bounds");
            return;
        }
        //reset pointers to delete node
        temp.prev.next = temp.next;
        if (temp.next !== null) {//check if this is the tail node
            temp.next.prev = temp.prev;
        }

    }

    //Print the list on one line
    print() {
        var node = this.head;
        var str = "";
        while (node !== null) {
            str += node.data + " ";
            node = node.next;
        }
        console.log(str);
    }
}

/********  Tests  *********/

//Create a doubly linked list and add items
myList = new DoublyLinkedList();
myList.append(1);
myList.append(2);
myList.append(3);
myList.append(4);
myList.append(5);

console.log("List is: ");
myList.print();

//Test 'get' 
console.log("Get index 2: " + myList.get(2)); //(returns a node object)
console.log("Get index 6 (out of bounds): " + myList.get(6)); //invalid index

//Test addAtHead and addAtTail
console.log("Add 0 to head");
myList.addAtHead(0);
console.log("Add 6 to tail");
myList.addAtTail(6);

console.log("List is now: ");
myList.print();

//Test addAtIndex
console.log("Add 'A' at index 0 (first)");
myList.addAtIndex('A', 0); //first
console.log("Add 'B' at index 4 (middle-ish)");
myList.addAtIndex('B', 4); //middle
console.log("Add 'C' at index 9 (end)");
myList.addAtIndex('C', 9); //end
console.log("Add 'D' at index 11 (out of bounds)");
myList.addAtIndex('D', 11); //out of bounds

console.log("List is now: ");
myList.print();

//Test deleteAtIndex
console.log("Delete index 0 (first)");
myList.deleteAtIndex(0); //first
console.log("Delete index 3 (middle-ish)");
myList.deleteAtIndex(3); //middle
console.log("Delete index 7 (end)");
myList.deleteAtIndex(7); //end
console.log("Delete index 8 (out of bounds)");
myList.deleteAtIndex(8); //out of bounds

console.log("List is now: ");
myList.print();