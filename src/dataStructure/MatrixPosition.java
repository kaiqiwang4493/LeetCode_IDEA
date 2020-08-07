package dataStructure;

import java.util.Comparator;

public class MatrixPosition implements Comparable<MatrixPosition> {
     int key;
     int x;
     int y;

    public MatrixPosition(int key, int x, int y){
        this.key = key;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
         String string = this.key +","+this.x+","+this.y;
        return string;
    }

    public MatrixPosition(MatrixPosition input){
        this.key = input.key;
        this.x = input.x;
        this.y = input.y;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(MatrixPosition o) {
        if(this.key == o.key){
            return 0;
        }else{
            return this.key<o.key ? -1 : 1;
        }
    }
}
