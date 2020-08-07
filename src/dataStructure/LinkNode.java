package dataStructure;

public class LinkNode {
    public int value;
    public LinkNode next;

    public LinkNode(int value){
        this.value = value;
        next = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkNode tmp = this;
        while(tmp != null){
            sb.append(tmp.value);
            sb.append(" ");
            tmp = tmp.next;
        }
        return sb.toString();
    }
}
