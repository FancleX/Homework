# HomeWork
 This assignment asks you to use and modify a binary search tree (BST) implementation in order to determine whether a tree is balanced.

In completing this assignment, you will:

Apply what you have learned about how binary search trees represent and store data
Implement tree traversal algorithms for determining the structure of a tree
Gain an understanding of additional tree-related concepts
Debugging/Error Note:

If you run into errors/bugs/don't understand the output that Codio is giving you, please post in the Discussion Forum and a TA will assist you!  Please do NOT email Codio as they will not review any errors you are getting.

Background

As seen in the lessons, it is important for a BST to be balanced so that its operations can be O(log n) and that it does not devolve into a LinkedList, for which most operations are O(n).

In the lesson, we saw the use of a self-balancing implementation known as a Red-Black Tree; in this part of the assignment, you will implement a different approach to determining whether the BST is balanced.

Getting Started

Start by downloading BinarySearchTree.java.

In the recent lessons, we implemented a tree in which the nodes had int elements. Here, we have provided you with a generic implementation of the BinarySearchTree. You will see that nodes have elements of any type E, and that we have replaced the int comparators (e.g. >, <, ==, etc.) with the Java Object’s compareTo function. This allows us to create a BST where the Nodes are any type of object.

The implementation that we have provided ensures that all elements in the tree are distinct, i.e. have different values.

Activity

First, implement the findNode method in BinarySearchTree.java. Given a value that is stored in the BST, it returns the corresponding Node that holds it. If the value does not exist in this BST, this method should return null.

Then, implement the depth method. Given a value, this method should return the “depth” of its Node, which is the number of ancestors between that node and the root, including the root but not the node itself. The depth of the root is defined to be 0; the depth of its two children (if any) is defined to be 1; the depth of the root’s grandchildren (if any) is defined to be 2; and so on. If the value is null or does not exist in this BST, this method should return -1.

Next, implement the height method. Given a value, this method should return the “height” of its Node, which is the greatest number of nodes between that node and any descendant node that is a leaf, including the leaf but not the node itself. The height of a leaf node (i.e., one which has no children) is defined to be 0. If the input value is null or does not exist in this BST, this method should return -1.

The image below shows a binary search tree with the height of each node indicated to its left:

A binary search tree with each node's height indicated to its left.

In the diagram above, the height of the node labeled 6 is 2, because there are two other nodes (labeled 4 and 2) from that node to a leaf, which is labeled 2. The height of the node labeled 16 is 2, because the maximum number of nodes between it and a leaf is 2; note that we don't consider the leaf labeled 20 because we're looking for the maximum number of nodes to a leaf.

Next, implement the isBalanced(Node) method. Given a Node, return true if the absolute value of the difference in heights of its left and right children is 0 or 1, and return false otherwise. If the Node is null or does not exist in this BST, this method should return false.

As an example, in the diagram above, the node labeled 16 should be considered balanced, since the height of its left child (labeled 10) is 1, and the height of its right child (labeled 20) is 0, and |1 - 0| <= 1.

Note that if a Node's child is null, then the height of that child should be considered as -1. In the diagram above, the node labeled 6 should not be considered balanced, because its left child (labeled 4) has a height of 1, and its right child is null, meaning its height is -1. Since the difference is |1 - (-1)| = 2 and that is greater than 1, this node should be considered unbalanced.

Finally, implement isBalanced() so that it returns true if isBalanced(Node) returns true for all Nodes in the tree. This method then allows the user of the BST to determine whether the BST is balanced, using the methods you’ve implemented above. Note that the root being balanced does not imply that the entire tree is balanced (see hint below).

Please do not change the signatures of these five methods, and do not create any additional .java files for your solution. You can, of course, add to or modify the BinarySearchTree class and the inner Node class if you’d like, but if you need additional classes, please define them in BinarySearchTree.java. Also, please make sure your BinarySearchTree class is in the default package, i.e. there is no “package” declaration at the top of the source code.

Helpful Hints

For findNode, keep in mind that binary search trees are necessarily stored such that, for any node, smaller values are in its left subtree and larger ones are in its right subtree; this is key to writing an efficient method to locate a node by its value, and you can then use it in other methods. 

For depth and height, consider the tree traversal techniques discussed in the lesson and think about how you can use them for navigating a node’s ancestors and successors. If you have trouble understanding the difference between “depth” and “height,” there is a good explanation at https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height

As mentioned above, isBalanced( ) should not simply return true just because isBalanced(root) is true. That is, even if the root is balanced, the tree may not be, as shown in the diagram above. In this case, the heights of the root's left and right children are equal; however, the node labeled 6 is not balanced, therefore the tree is not balanced.
