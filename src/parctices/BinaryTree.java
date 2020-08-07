package parctices;


import dataStructure.Node;
import dataStructure.TreeNode;
import dataStructure.TreeNodeP;

import java.util.*;


public class BinaryTree {
    /*
    Height of Binary Tree.
     */
    public int findHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight = 1+ findHeight(root.left);
        int rightHeight = 1+ findHeight(root.right);

        return Math.max(leftHeight, rightHeight);
    }

    /*
    The tree is or not balanced.
     */
    public boolean isBalanced(TreeNode root){
        if(root == null){
            return true;
        }

        boolean leftIsBalanced = isBalanced(root.left);
        boolean rightIsBalanced = isBalanced(root.right);
        if(!leftIsBalanced || !rightIsBalanced){
            return false;
        }

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        if(Math.abs(leftHeight - rightHeight)> 1){
            return false;
        }else
            return true;
    }

    /*
    In-Order Traversal of Binary Tree (recursion)
     */
    List<Integer> list = new ArrayList<>();
    public List<Integer> InOrderTraversal1(TreeNode root){
       InOrderTraversal1Helper(root);
       return list;
    }
    private void InOrderTraversal1Helper(TreeNode root){
        if(root == null){
            return;
        }
        InOrderTraversal1Helper(root.left);
        list.add(root.key);
        InOrderTraversal1Helper(root.right);
    }

    /*
    Pre_Order Traversal of Binary Tree (recursion)
     */
    public List<Integer> preOrderRecursion(TreeNode root){
        List<Integer> result = new ArrayList<>();
        preOrderRecursionHelper(root, result);
        return result;
    }

    private void preOrderRecursionHelper(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        result.add(root.key);
        preOrderRecursionHelper(root.left, result);
        preOrderRecursionHelper(root.right, result);
    }

    /*
    In-Order Traversal of Binary Tree (iterative)
    using a stack to store the TreeNode elements in the tree.
     */
    /*
    public List<Integer> InOrderTraversal2(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        while(!deque.isEmpty() || cur != null){
            if(cur != null){
                deque.offerFirst(cur);
                cur = cur.left;
            }else{
                cur = deque.pollFirst();
                list.add(cur.key);
                cur = cur.right;
            }
        }
        return list;
    }

     */

    public List<Integer> InOrderTraversal2(TreeNode root){
        List<Integer>  list = new ArrayList<>();
        Deque<TreeNode> dq = new ArrayDeque<>();
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        while(!dq.isEmpty() || cur != null){
            if(cur != null){
                dq.addFirst(cur);
                cur = cur.left;
            }else{
                cur = dq.pollFirst();
                list.add(cur.key);
                cur = cur.right;
            }
        }
        return list;
    }

    /*
    Pre-order traversal of Binary Tree(iterative)
     */

    public List<Integer> PreOrderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        deque.offerFirst(cur);
        while(!deque.isEmpty()){
            cur = deque.peekFirst();
            if(pre == null || cur == pre.left || cur == pre.right){
                //the situation of continue go down
                if(cur.left != null){
                    deque.offerFirst(cur.left);
                }else if(cur.right != null){
                    deque.offerFirst(cur.right);
                }else{
                    list.add(cur.key);
                    deque.pollFirst();
                }
            }else if(pre == cur.left){
                // go back from left child
                // check the right child.
                if(cur.right != null){
                    deque.offerFirst(cur.right);
                }else{
                    list.add(cur.key);
                    deque.pollFirst();
                }
            }else{
                //go back from right child
                //do NOT need to discovery other children nodes
                list.add(cur.key);
                deque.pollFirst();
            }
            pre = cur;
        }
        return list;
    }

    /*
    Symmetric Binary Tree
    check if a given binary tree is symmetric
     */

    public boolean isSymmetric(TreeNode root){
        if(root == null){
            return true;
        }else{
            TreeNode left = root.left;
            TreeNode right = root.right;
            return isSymmetricHelper(left, right);
        }
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }else  if(left == null || right == null){
            return false;
        }else{
            if(left.key != right.key){
                return false;
            }else{
                return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
            }
        }
    }

    /*
    Delete In Binary Search Tree
    return the new root of the new tree.
     */
    public  TreeNode deleteTree(TreeNode root, int key){
        if(root == null){
            return root;
        }
        if(key < root.key){
            root.left = deleteTree(root.left, key);
            return root;
        }else if(key > root.key){
            return deleteTree(root.right, key);
        }
        // the root deleted without right child node
        if(root.right == null){
            return root.left;
        }
        // the root deleted without left child node
        if(root.left == null){
            return root.right;
        }
        // the right child node of root deleted without left child node.
        if(root.right.left == null){
            root.right.left = root.left;
            return root.right;
        }
        // find the most left child node of root.right node
        TreeNode smallest = findSmallestNode(root.right);
        smallest.left = root.left;
        smallest.right = root.right;
        return smallest;
    }

    private TreeNode findSmallestNode(TreeNode root){
        TreeNode prev = root;
        while(root.left != null){
            prev = root;
            root = root.left;
        }
        prev.left = root.right;
        return root;
    }

    //Time : O(n)
    //Space : O(height)
    public boolean isBST2(TreeNode root){
        return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTHelper(TreeNode root, Integer lBound, Integer rBound){
        if(root == null){
            return true;
        }
        //child value cannot equal to parent value.
        if(root.key <= lBound || root.key >= rBound){
            return false;
        }
        return isBSTHelper(root.left, lBound, root.key) && isBSTHelper(root.right, root.key, rBound);
    }

    public boolean isBST(TreeNode root){
        if(root == null){
            return true;
        }
        boolean leftIsBST = isBST(root.left);
        boolean rightIsBST = isBST(root.right);

        if(!leftIsBST || !rightIsBST){
            return false;
        }

        if(root.left != null){
            TreeNode leftSide = root.left;
            while(leftSide!= null){
                if(leftSide. key >= root.key){
                    return false;
                }
                // make sure that the biggest node in left is bigger than root
                leftSide = leftSide.right;
            }
        }

        if(root.right != null){
            TreeNode rightSide = root.right;
            while(rightSide != null){
                if(rightSide.key < root.key){
                    return false;
                }
                // make sure the smallest node in right is smaller than root
                rightSide = rightSide.left;
            }
        }
        return true;
    }

    /*
    Lowest Common Ancestor II
    Given two nodes in a binary tree (with parent pointer available),
    find their lowest common ancestor.
     */
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two){
        int oneLength = getLength(one);
        int twoLength = getLength(two);
        int diff = oneLength - twoLength;
        //adjust the two nodes to the same level.
        while(diff != 0){
            if(diff<0){
                two = two.parent;
                diff++;
            }else{
                one = one.parent;
                diff--;
            }
        }
        return lowestCommonAncestorHelper(one, two);
    }

    private int getLength(TreeNodeP node){
        int length = 0;
        while(node.parent != null){
            length++;
            node = node.parent;
        }
        return length;
    }

    private TreeNodeP lowestCommonAncestorHelper(TreeNodeP one, TreeNodeP two){
        while(one != null && two!= null){
            if(one == two){
                return one;
            }else{
                one = one.parent;
                two = two.parent;
            }
        }
        return null;
    }

    /*
    Lowest Common Ancestor IV
    Given K nodes in a binary tree, find their lowest common ancestor.
    Because we don't want to change the original tree, so we new the TreeNode: leftReturn, rightReturn.
    We can see the removeEmptyNode(TreeNode root) method, which we have to chang the original tree.
    So we cannot new TreeNode in removeEmptyNode(TreeNode root) method.
     */

    public TreeNode lowestCommonAncestor4(TreeNode root, List<TreeNode> nodes){
        Set<TreeNode> set = getSet(nodes);
        return lowestCommonAncestor4Helper(root, set);
    }

    private Set<TreeNode> getSet(List<TreeNode> nodes){
        return new HashSet<>(nodes);
    }

    private TreeNode lowestCommonAncestor4Helper(TreeNode root, Set<TreeNode> set){
        if(root == null){
            return null;
        }
        if(set.contains(root)){
            return root;
        }

        TreeNode leftReturn = lowestCommonAncestor4Helper(root.left, set);
        TreeNode rightReturn = lowestCommonAncestor4Helper(root.right, set);
        if(leftReturn!= null && rightReturn != null){
            // add the new node into set is not necessary
            //set.add(root);
            return root;
        }else if(leftReturn == null && rightReturn == null){
            return null;
        }else {
            return leftReturn != null ? leftReturn : rightReturn;
        }
    }

    /*
    Check If Binary Tree Is Completed
    Check if a given binary tree is completed.
    A complete binary tree is one in which every level of the binary tree is completely filled
    except possibly the last level. Furthermore, all nodes are as far left as possible.
     */
    public boolean isCompleted(TreeNode root){
        if(root == null){
            return true;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean flag = false;

        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(cur.left == null){
                flag = true;
            }else if(flag){
                return false;
            }else{
                queue.add(cur.left);
            }
            if(cur.right == null){
                flag = true;
            }else if(flag){
                return false;
            }else{
                queue.add(cur.right);
            }
        }
        return true;
    }

    /*
    Binary Tree Path Sum To Target III
    Given a binary tree in which each node contains an integer number.
    Determine if there exists a path (the path can only be from one node to itself or to any of its descendants),
    the sum of the numbers on the path is the given target number.
     */

    public boolean existBinaryTreePathSum(TreeNode root, int target){
        if(root == null){
            return false;
        }
        List<TreeNode> path = new ArrayList<>();
        int sum = 0;
        return existBinaryTreePathSumHelper(path, root, target);
    }

    private boolean existBinaryTreePathSumHelper(List<TreeNode> path, TreeNode root, int target){
        path.add(root);
        int sum = 0;
        // we cannot use this way to check the sum
        // because the this way contains the upper level nodes
//        sum = sum + root.key;
//        if(sum == target){
//            return true;
//        }
        for(int i = path.size() - 1; i >=0; i--){
            sum = sum + path.get(i).key;
            if(sum == target){
                return true;
            }
        }

        if(root.left != null && existBinaryTreePathSumHelper(path,root.left,target)){
            return true;
        }
        if (root.right != null && existBinaryTreePathSumHelper(path, root.right,target)){
            return true;
        }

        path.remove(path.size() -1 );
        return false;
    }

    /*
    Closest Number In Binary Search Tree
    DFP-recursion
     */

    public int closest(TreeNode root, int target){
        int result = root.key;
         result = closestHelper(root, result,target);
         return result;
    }

    private int closestHelper(TreeNode root, int result, int target){
       if(Math.abs(target - root.key) < Math.abs(target - result)){
           result = root.key;
       }
       if(root.left != null){
           int leftResult = closestHelper(root.left, result,target);
           result = Math.abs(target - result) < Math.abs(target - leftResult)? result: leftResult;
       }
       if(root.right != null){
           int rightResult = closestHelper(root.right,result,target);
           result = Math.abs(target - result) < Math.abs(target - rightResult)? result: rightResult;
       }
       return result;
    }

    //remove the all nodes just have one child.
    //caution: we cannot new a leftNode and rightNode, we have to change the values of original root.left and root.right
    public TreeNode removeEmptyNode(TreeNode root){
        if(root == null){
            return root;
        }

        root.left = removeEmptyNode(root.left);
        root.right = removeEmptyNode(root.right);

      if(root.left != null && root.right != null){
          return root;
      }else if(root.left == null&& root.right == null){
          return root;
      }else {
          return root.left != null ? root.left : root.right;
      }
    }


    public  boolean SymmetricTree(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }else if(left == null || right ==null){
            return false;
        }else if(left.key != right.key){
            return false;
        }
            return SymmetricTree(left.left, right.right) && SymmetricTree(left.right, right.left);
    }

    //Time O(4*log_2(n)) => O(n^2)
    public  boolean IdenticalTree(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }else if(left == null || right ==null){
            return false;
        }else if(left.key != right.key){
            return false;
        }
        return (IdenticalTree(left.left, right.right) && IdenticalTree(left.right, right.left)) ||
                (IdenticalTree(left.left, right.left)) && IdenticalTree(left.right, right.right);
    }


    public boolean isIdentical(TreeNode one, TreeNode two){
        if(one == null && two == null){
            return true;
        }else if(one == null || two == null){
            return false;
        }else if(one.key != two.key){
            return false;
        }
        return isIdentical(one.left,two.left) && isIdentical(one.right, two.right);

    }

    /*
    Binary Tree Zigzag Level Order Traversal
    Given a binary tree, return the zigzag level order traversal of its nodes' values.
     (ie, from left to right, then right to left for the next level and alternate between).
     */
    public List<List<Integer>> zigzalevelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Deque<TreeNode> deque = new ArrayDeque<>();
        boolean lr = true;
        deque.addLast(root);

        while(!deque.isEmpty()){
            int length = deque.size();
            List<Integer> tmpResult = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if(lr){
                    TreeNode node = deque.pollFirst();
                    tmpResult.add(node.key);
                    if(node.left != null) deque.offerLast(node.left);
                    if(node.right!= null) deque.offerLast(node.right);
                }else{
                    TreeNode node = deque.pollLast();
                    tmpResult.add(node.key);
                    if(node.right != null) deque.offerFirst(node.right);
                    if(node.left != null) deque.offerFirst(node.left);
                }
            }
            lr = !lr;
            result.add(new ArrayList<>(tmpResult));
        }
        return result;
    }


    /*
    Binary Tree Level Order Traversal
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);

        while(!deque.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = deque.size();
            for(int i = 0; i < size; i++){
                TreeNode node = deque.pollFirst();
                list.add(node.key);
                if(node.left != null) deque.offerLast(node.left);
                if(node.right != null) deque.offerLast(node.right);
            }
            result.add(new ArrayList<>(list));
        }
        return result;
    }


    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return root;
        //TreeNode newHead;
        return trimBSTHelper(root, null, L, R);
    }

    private TreeNode trimBSTHelper(TreeNode root, TreeNode newHead, int L, int R){
        if(root == null) return newHead;
        if(root.key >= L && root.key <= R){
            if(newHead == null) {
                newHead = new TreeNode(root.key);
            }else{
                addNode(newHead, root.key);
            }
            if(root.key != L) newHead = trimBSTHelper(root.left, newHead, L, R);
            if(root.key != R) newHead = trimBSTHelper(root.right, newHead, L, R);
        }else if(root.key < L){
            newHead = trimBSTHelper(root.right, newHead, L ,R);
        }else{
            // root.val >=R
            newHead=trimBSTHelper(root.left, newHead, L, R);
        }
        return newHead;
    }


    private void addNode(TreeNode root, int node){
        if(node < root.key){
            if(root.left != null){
                addNode(root.left, node);
            }else{
                root.left = new TreeNode(node);
                return;
            }
        }else{
            if(root.right != null){
                addNode(root.right, node);
            }else{
                root.right = new TreeNode(node);
                return;
            }
        }
    }


}
