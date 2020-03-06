package parctices;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {
    /*
    Flood Fill
     */

    public int[][] flodFill(int[][] image, int sr, int sc, int newColor){
        int N = image.length;
        int M = image[0].length;
        Integer[] position = {sr,sc};
        int oldColor = image[sr][sc];

        Queue<Integer[]> queue = new ArrayDeque<>();
        queue.add(position);

        while(queue.size() != 0){
            position = queue.poll();
            Integer x = position[0];
            Integer y = position[1];
           // if(image[x][y] == oldColor)
                image[x][y] = newColor;
            //up
            if(y-1 >=0 && (image[x][y-1] == oldColor|| image[x][y-1] == newColor)){
                queue.add(new Integer[]{x, y - 1});
            }
            //down
            if(y+1 < M && (image[x][y+1] == oldColor || image[x][y+1] == newColor)){
                queue.add(new Integer[]{x, y + 1});
            }
            //left
            if(x-1 >= 0 && (image[x-1][y]== oldColor || image[x-1][y]== newColor)){
                queue.add(new Integer[]{x-1, y});
            }
            //right
            if(x+1 < N && (image[x+1][y] == oldColor || image[x+1][y] == newColor)){
                queue.add(new Integer[]{x+1, y});
            }
        }
        return image;
    }
}
