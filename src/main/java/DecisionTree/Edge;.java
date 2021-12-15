package DecisionTree;

import java.util.*;

class Edge  {
    private Node start;
    private Node end;
    private String answer;

    Edge(Node start,Node end, String answer){
        this.start=start;
        this.end=end;
        this.answer=answer;
    }

    Edge(Node start, String answer){
        this.start=start;
        this.answer=answer;
    }

    public void setEnd(Node end) {
        this.end = end;
    }


    Node getStartNode(){
        return start;
    }

    Node getEndNode(){
        return end;
    }

    String getAnswer(){
        return answer;
    }



}