package parctices;

import dataStructure.LinkNode;
import dataStructure.TreeNode;

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

    /*


    */
    public LinkNode addTwoNumber(LinkNode l1, LinkNode l2){
        //coner case ,one of the node is null, then we return the not null node.
        if(l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }

        int length1 = getLinkLength(l1);
        int length2 = getLinkLength(l2);
        //make sure the l1 length >= l2;
        if(length1 < length2){
            LinkNode tmp = new LinkNode(l2.value);
            tmp.next = l2.next;
            l2 = l1;
            l1 = tmp;
        }

        int[] carry = new int[Math.max(length1, length2) + 1];
        int carryIndex =1;
        LinkNode l1Index = l1;
        LinkNode l2Index = l2;

        //change the index of longer linkList, let two index of linkedList start at the same position.
        for (int i = 0; i <Math.max(length1, length2)-Math.min(length1, length2) ; i++) {
            l1Index = l1Index.next;
            carryIndex++;
        }

        while(l1Index != null && l2Index != null){
            int sum = l1Index.value + l2Index.value;
            if(sum >= 10){
                l1Index.value = sum %10;
                carry[carryIndex-1] = 1;
            }else{
                l1Index.value = sum;
            }
            l1Index = l1Index.next;
            l2Index = l2Index.next;
            carryIndex++;
        }

        LinkNode newHead = null;
        if(carry[0] != 0 || l1.value + carry[1] >= 10){
            newHead = new LinkNode(1);
            newHead.next = l1;
        }

        l1Index = l1;
        for (int i = 1; i < carry.length; i++) {
           if(carry[i] != 0){
               l1Index.value = ++l1Index.value % 10;
           }
           l1Index = l1Index.next;
        }

        return newHead == null ? l1 : newHead;
    }

    private int getLinkLength(LinkNode node){
        int length = 0;
        while(node !=null){
            length++;
            node= node.next;
        }
        return length;
    }

}
