package parctices;

import com.sun.source.tree.Tree;
import dataStructure.MatrixPosition;
import dataStructure.Node;
import dataStructure.TreeNode;
import dataStructure.Pair;


import java.net.Inet4Address;
import java.security.KeyPair;
import java.util.*;

public class BFS {

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j != grid[0].length - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (j == grid[0].length - 1 && i != grid.length - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (j != grid[0].length - 1 && i != grid.length - 1) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }


    /*
    Kth Smallest Number In Sorted Matrix
    solution: using BFS-2,
    Time: O(klogk);
     */

    public int kthSmallest(int[][] matrix, int k) {
        if (k == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        if (k > m * n) {
            System.out.println("Out of Bound");
            return -1;
        }
        PriorityQueue<MatrixPosition> pq = new PriorityQueue<>();
        boolean[][] read = new boolean[m][n];
        MatrixPosition tmp = new MatrixPosition(matrix[0][0], 0, 0);
        pq.add(new MatrixPosition(tmp));
        for (int i = 0; i < k; i++) {
            tmp = pq.poll();
            int x = tmp.getX();
            int y = tmp.getY();
            if (y + 1 < n) {
                if (!read[x][y + 1]) {
                    read[x][y + 1] = true;
                    pq.add(new MatrixPosition(matrix[x][y + 1], x, y + 1));
                }
            }
            if (x + 1 < m) {
                if (!read[x + 1][y]) {
                    read[x + 1][y] = true;
                    pq.add(new MatrixPosition(matrix[x + 1][y], x + 1, y));
                }
            }
        }
        return tmp.getKey();
    }

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        Deque<Integer> rotten = new ArrayDeque<>();
        int fresh = 0;
        int times = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    rotten.addLast(i * n + j);
                } else if (grid[i][j] == 1) {
                    fresh++;
                }

            }
        }

        while (!rotten.isEmpty() && fresh > 0) {
            int numberOfRotten = rotten.size();
            times++;
            for (int i = 0; i < numberOfRotten; i++) {
                int position = rotten.pollFirst();
                int r = position / n;
                int c = position % n;
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    addRottenOranges(grid, r - 1, c, rotten);
                    fresh--;
                }
                if (r + 1 < m && grid[r + 1][c] == 1) {
                    addRottenOranges(grid, r + 1, c, rotten);
                    fresh--;
                }
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    addRottenOranges(grid, r, c - 1, rotten);
                    fresh--;
                }
                if (c + 1 < n && grid[r][c + 1] == 1) {
                    addRottenOranges(grid, r, c + 1, rotten);
                    fresh--;
                }
            }

        }
        if (fresh != 0) return -1;
        return times;
    }

    private void addRottenOranges(int[][] grid, int r, int c, Deque<Integer> rotten) {
        grid[r][c] = 2;
        rotten.addLast(r * grid[0].length + c);
    }


    /*
    Word Ladder
    Given two words (beginWord and endWord), and a dictionary's word list,
    find the length of shortest transformation sequence from beginWord to endWord, such that:
     */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int L = beginWord.length();

        Map<String, List<String>> allComboDict = new HashMap<>();

        // creat the new graph.
        wordList.forEach(word -> {
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, L);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });

        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = (String) node.getK();
            int level = (int) node.getV();
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, L);
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair<>(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }


    /*
    Binary Tree Vertical Order Traversal
    Given a binary tree, return the vertical order traversal of its nodes' values.
    (ie, from top to bottom, column by column).
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        int position = 0;
        queue.offerLast(new Pair<>(root, position));

        while (!queue.isEmpty()) {
            Pair pair = queue.pollFirst();
            TreeNode node = (TreeNode) pair.getK();
            position = (int) pair.getV();
            if (node != null) {
                if (!map.containsKey(position)) {
                    map.put(position, new ArrayList<Integer>());
                }
                map.get(position).add(node.key);
                queue.offerLast(new Pair<>(node.left, position - 1));
                queue.offerLast(new Pair<>(node.right, position + 1));
            }
        }

        List<Integer> sortedKey = new ArrayList<>(map.keySet());
        Collections.sort(sortedKey);
        for (int k : sortedKey) {
            result.add(map.get(k));
        }
        return result;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        int[] result = new int[numCourses];
        int position = 0;
        Map<Integer, List<Integer>> depends = new HashMap<>();
        Map<Integer, Integer> needStudy = new HashMap<>();
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            boolean hasDepend = false;
            for (int j = 0; j < prerequisites.length; j++) {
                if (i == prerequisites[j][0]) {
                    hasDepend = true;
                    if (!depends.containsKey(prerequisites[j][1])) {
                        List<Integer> dependsCourses = new ArrayList<>();
                        dependsCourses.add(prerequisites[j][0]);
                        depends.put(prerequisites[j][1], dependsCourses);
                    } else {
                        depends.get(prerequisites[j][1]).add(prerequisites[j][0]);
                    }
                    if (!needStudy.containsKey(i)) {
                        needStudy.put(i, 1);
                    } else {
                        needStudy.put(i, needStudy.get(i) + 1);
                    }
                }
            }
            if (hasDepend == false) {
                q.offerLast(i);
            }
        }
        while (!q.isEmpty()) {
            int course = q.pollFirst();
            result[position] = course;
            position++;
            if (depends.containsKey(course)) {
                List<Integer> list = depends.get(course);
                for (int dependCourse : list) {
                    if (needStudy.containsKey(dependCourse)) {
                        needStudy.put(dependCourse, needStudy.get(dependCourse) - 1);
                        if (needStudy.get(dependCourse) == 0) {
                            needStudy.remove(dependCourse);
                            q.addLast(dependCourse);
                        }
                    }
                }
            }
        }
        if (!needStudy.isEmpty()) {
            return new int[0];
        } else {
            return result;
        }


    }


    /*
    clone graph
    Given a reference of a node in a connected undirected graph.
    Return a deep copy (clone) of the graph.
    solution: focus on the way how to clone the node. And to provent to clone the same node twice, we using a HashMap to
    store the original node and clone node. if there is the clone node, we use the it directly. If not, we create a clone node.
    When we creating a clone node, we consider how to clone the neighbor node.
    The return value for the method is a cloned node.
     */
    private HashMap<Node, Node> visted = new HashMap<>();

    public Node cloneGraph(Node node){
        if(node == null){
            return node;
        }
        // If the node was already visited before
        // Return the clone from the visited dictionary.
        if(visted.containsKey(node)){
            return visted.get(node);
        }
        //Creat a clone for the given node.
        //Node that we don't have cloned neighbors as of now, hence[]
        Node cloneNode = new Node(node.val, new ArrayList<>());

        visted.put(node, cloneNode);

        for(Node neighbor : node.children){
            cloneNode.children.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

}
