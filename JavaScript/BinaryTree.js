/******************************** 
BinaryTree.js
********************************/

class Node {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {

    constructor() {
        this.root = null;
    }

    //creates a complete binary tree
    insertNode(val) {
        var newNode = new Node(val);
        if (this.root === null) this.root = newNode;
        else this.insertNodeHelper(this.root, newNode);
    }

    insertNodeHelper(node, newNode) {
        if (newNode.val < node.val) {
            if (node.left === null) node.left = newNode;
            else this.insertNodeHelper(node.left, newNode);
        }
        else {
            if (node.right === null) node.right = newNode;
            else this.insertNodeHelper(node.right, newNode);
        }
    }

}

//Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
function levelOrder(root) {

    var queue = [];
    var level = [];
    var result = [];
    var innerArr = [];

    queue.push(root);

    //copy all nodes on in this level to a new queue
    while (queue.length > 0) {
        queue.forEach(function (node) {
            level.push(node);
        });
        queue = []; //empty queue

        //for each node in the level, add its value to an array, then add its children to the queue
        level.forEach(function (node) {
            if (node !== null) {
                innerArr.push(node.val);
                if (node.left !== null) queue.push(node.left);
                if (node.right !== null) queue.push(node.right);
            }
        });

        result.push(innerArr);//add the array of nodes at this level to the result array
        innerArr = [];
        level = [];
    }
    return result;
}

//Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//Iterative solution
function isSymmetric(root) {
    queue = [];
    var node1;
    var node2;
    if (root === null) return true;
    if (root.left === null && root.right === null) return true;
    if (root.left === null || root.right === null) return false;
    queue.push(root.left);
    queue.push(root.right);

    while (queue.length > 0) {
        node1 = queue.shift();
        node2 = queue.shift();
        if (node1 === null && node2 === null) continue;
        if (node1 === null || node2 === null) return false;
        if (node1.val !== node2.val) return false;

        queue.push(node1.left);
        queue.push(node2.right);
        queue.push(node1.right);
        queue.push(node2.left);
    }
    return true;
}
//Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//Recursive solution
function isSymmetricRecursive(root) {
    function isMirror(node1, node2) {
        if (node1 === null && node2 === null) return true;
        if (node1 !== null && node2 !== null && node1.val === node2.val) {
            return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
        }
        return false;
    }
    return isMirror(root, root);
}

//Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
//For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the 
//two subtrees of every node never differ by more than 1.
function sortedArrayToBST(arr) {

    function helper(left, right) {
        if (left > right) return null;
        var middle = Math.floor((left + right) / 2);  //always choose left middle node
        var root = new Node(arr[middle]);
        root.left = helper(left, middle - 1);
        root.right = helper(middle + 1, right);
        return root;
    }
    return helper(0, arr.length - 1);
}

/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/
//recursive version
function isValidBST(root) {

    function helper(node, lower, upper) {
        if (node === null) return true;

        if (lower !== null && lower >= node.val) return false;
        if (upper !== null && upper <= node.val) return false;

        if (!helper(node.right, node.val, upper)) return false;
        if (!helper(node.left, lower, node.val)) return false;

        return true;
    }
    return helper(root, null, null);
}

/*********** Tree Traversals Recursive ***********/

function preOrderTraversal(node) {
    result = [];
    function preOrderTraversalHelper(node, result) {
        if (node !== null) {
            if (node.val !== null) result.push(node.val);
            preOrderTraversalHelper(node.left, result);
            preOrderTraversalHelper(node.right, result);
        }
    }
    preOrderTraversalHelper(node, result);
    return result;
}

function inOrderTraversal(node) {
    var result = [];
    function inOrderTraversalHelper(node, result) {
        if (node !== null) {
            inOrderTraversalHelper(node.left, result);
            if (node.val !== null) result.push(node.val);
            inOrderTraversalHelper(node.right, result);
        }
    }
    inOrderTraversalHelper(node, result);
    return result;
}

function postOrderTraversal(node) {
    result = [];
    function postOrderTraversalHelper(node, result) {
        if (node !== null) {
            postOrderTraversalHelper(node.left, result);
            postOrderTraversalHelper(node.right, result);
            if (node !== null) result.push(node.val);
        }
    }
    postOrderTraversalHelper(node, result);
    return result;
}

/*********** Tree Traversals Iterative ***********/

function preOrderTraversalIterative(root) {
    var queue = [];
    var result = [];
    var node;
    queue.push(root);
    while (queue.length > 0) {
        node = queue.shift();
        result.push(node.val);
        if (node.left !== null) queue.push(node.left);
        if (node.right !== null) queue.push(node.right);
    }
    return result;
}

function inOrderTraversalIterative(root) {
    var stack = [];
    var result = [];
    var current = root;
    while (current !== null || stack.length > 0) {
        while (current !== null) {
            stack.push(current);
            current = current.left;
        }
        current = stack.pop();
        result.push(current.val);
        current = current.right;
    }
    return result;
}

/*
Algorithm for postOrderTraversalIterative is confusing, this algorithm is copied from https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/:
1.1 Create an empty stack
2.1 Do following while root is not NULL
    a) Push root's right child and then root to stack.
    b) Set root as root's left child.
2.2 Pop an item from stack and set it as root.
    a) If the popped item has a right child and the right child 
       is at top of stack, then remove the right child from stack,
       push the root back and set root as root's right child.
    b) Else print root's data and set root as NULL.
2.3 Repeat steps 2.1 and 2.2 while stack is not empty.
*/
function postOrderTraversalIterative(root) {
    var stack = [];
    var result = [];
    stack.push(root.right);
    stack.push(root);
    root = root.left;
    while (stack.length > 0) {
        while (root !== null) {
            stack.push(root.right);
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        if (root !== null && root.right !== null && stack[stack.length - 1] === root.right)//If the popped item has a right child and the right child is at top of stack
        {
            stack.pop();
            stack.push(root);
            root = root.right;
        }
        else {
            if (root !== null) result.push(root.val);
            root = null;
        }
    }
    return result;
}

/* Iterative solution for finding max depth
iven a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node. */
function maxDepth(root) {
    var stack = [];
    var depthStack = [];
    var currentDepth = 0;
    var depth = 0;

    if (root === null) {
        return 0;
    }

    stack.push(root);
    depthStack.push(1);

    while (stack.length !== 0) {
        root = stack.pop();
        currentDepth = depthStack.pop();
        if (root !== null) {
            depth = Math.max(depth, currentDepth);
            stack.push(root.left);
            stack.push(root.right);
            depthStack.push(currentDepth + 1);
            depthStack.push(currentDepth + 1);
        }
    }
    return depth;
};


//DRIVER CODE
console.log("Level Order [3,9,20,null,null,15,7]: ");
var btree = new Node(3);
btree.left = new Node(9);
btree.right = new Node(20);
btree.right.left = new Node(15);
btree.right.right = new Node(7);
console.log(levelOrder(btree)); //levelOrder

console.log("Sorted Array to BST [-10, -3, 0, 5, 9]: ");
var sortedArr = [-10, -3, 0, 5, 9];
console.log(sortedArrayToBST(sortedArr));//Sorted array to BST

notBST = new Node(5);
notBST.left = new Node(1);
notBST.right = new Node(4);
notBST.right.left = new Node(3);
notBST.right.right = new Node(6);
console.log("Is valid BST [5, 1, 4, null, null, 3, 6]: ");
console.log(isValidBST(notBST)); //Is valid BST -- should return false


traversalsTree = new Node(1);
traversalsTree.right = new Node(2);
traversalsTree.right.left = new Node(3);
console.log("preOrderTraversal (recursive) [1, null, 2, 3]: ");
console.log(preOrderTraversal(traversalsTree)); //pre Order Traversal


console.log("inOrderTraversal (recursive) [1, null, 2, 3]: ");
console.log(inOrderTraversal(traversalsTree)); //In Order Traversal

console.log("postOrderTraversal (recursive) [1, null, 2, 3]: ");
console.log(postOrderTraversal(traversalsTree)); //Post Order Traversal

console.log("preOrderTraversalIterative [1, null, 2, 3]: ");
console.log(preOrderTraversalIterative(traversalsTree)); //Pre Order Traversal Iterative

console.log("inOrderTraversalIterative [1, null, 2, 3]: ");
console.log(inOrderTraversalIterative(traversalsTree)); //In Order Traversal Iterative

console.log("postOrderTraversalIterative [1, null, 2, 3]: ");
console.log(postOrderTraversalIterative(traversalsTree)); //Post Order Traversal Iterative

console.log("postOrderTraversalIterative [3,9,20,null,null,15,7]: ");
console.log(postOrderTraversalIterative(btree)); //Post Order Traversal Iterative

console.log("Max depth of [8, 10, 12, 5, 3, 6] is: " + maxDepth(btree));//max depth

var depthTree = new Node(1);
depthTree.left = new Node(2);
depthTree.right = new Node(3);
depthTree.left.left = new Node(4);
depthTree.right.left = new Node(5);
depthTree.left.left.left = new Node(6);
console.log("Max depth of [1,2,3,4,null,5,null,6] is: " + maxDepth(depthTree));//max depth




var symmetricTree = new Node(1);
symmetricTree.left = new Node(2);
symmetricTree.right = new Node(2);
symmetricTree.left.left = new Node(3);
symmetricTree.left.right = new Node(4);
symmetricTree.right.left = new Node(4);
symmetricTree.right.right = new Node(3);
console.log("isSymmetric iterative: [1,2,2,3,4,4,3]: ");
console.log(isSymmetric(symmetricTree));
console.log("isSymmetric recursive: [1,2,2,3,4,4,3]: ");
console.log(isSymmetricRecursive(symmetricTree));