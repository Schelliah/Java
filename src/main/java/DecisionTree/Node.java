package DecisionTree;
import java.util.*;

public class Node  {
    private String id;
    private String content;
    private Edge rightEdge;
    private Edge leftEdge;

    Node(String id,String content){
        this.id=id;
        this.content=content;
    }

    Node(String[] stringNode){
        this.id=stringNode[0];
        this.content=stringNode[1];
    }

    String getId(){
        return id;
    }

    String getcontent(){
        return content;
    }

    void setRightEdge(Edge rightedge){
        this.rightEdge=rightedge;
    }

    void setLeftEdge(Edge leftedge){
        this.leftEdge=leftedge;
    }

    Edge getRightEdge(){
        return rightEdge;
    }

    Edge getLeftEdge(){
        return leftEdge;
    }

    boolean isLeafNode(){
        return (content.charAt(content.length()-1)!='?');
    }



}