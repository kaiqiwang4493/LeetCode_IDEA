package parctices;

import dataStructure.LinkNode;

public class LinkList {
    /*
    Reverse Linked List (iterative)
     */
    public LinkNode reverse(LinkNode head){
        if(head == null){
            return head;
        }

        LinkNode cur = head;
        LinkNode pre = null;

        while(cur != null){
            //change the direction of LinkNode.
            LinkNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;

        }
        return pre;
    }


}
