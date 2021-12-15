package intermediate.Tree;

import java.util.*;
import java.util.List;

import java.io.*;
import java.io.FileNotFoundException;
import java.text.BreakIterator;


public class DecisionTree  {

    static int getIndexSourceNode(List<String[]> edgeList, List<String[]> nodeList){
        int index;
        boolean hasId;
        for (int i=0; i<nodeList.size();i++){
            String nodeId=nodeList.get(i)[0];
            hasId=false;
            for(int j=0; j<edgeList.size();j++){
                String[] stringEdge=edgeList.get(j);
                if (nodeId.equals(stringEdge[1])){
                    hasId=true;
                }


            }
            if(!hasId){
                index=i;
                return index;
            }
        }
        System.out.println("Source Node not found");
        return -1;
    }

    static int getIndexNode(List<String[]> nodeList, String Id){
        for (int i=0; i<nodeList.size();i++){
            if(nodeList.get(i)[0].equals(Id)) {
                return i;
            }
        }
        System.out.println(Id + " Node not found");
        return -1;
    }

    static Node buildTree(String[] stringNode,List<String[]> edgeList, List<String[]> nodeList){
        Node node = new Node(stringNode);
        if(node.isLeafNode()){
            return node;
        }
        boolean firstEdge=false;
        for(int j=0; j<edgeList.size();j++){
            String[] stringEdge=edgeList.get(j);
            if (node.getId().equals(stringEdge[0])){
                int index= getIndexNode(nodeList, stringEdge[1]);
                Edge edge = new Edge(node,buildTree(nodeList.get(index),edgeList,nodeList), stringEdge[2]);  //recursion
                if(!firstEdge){
                    node.setLeftEdge(edge);
                    firstEdge=true;
                }else{
                    node.setRightEdge(edge);
                }
            }
        }
        return node;

    }

    static boolean compare(String str1,String str2){  
        return str1.toUpperCase().equals(str2.toUpperCase());
    }

    static void goThroughTree(Node node){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println(node.getcontent());
            if(node.isLeafNode()){
                break;
            } else{
            String left  =node.getLeftEdge().getAnswer();
            String right = node.getRightEdge().getAnswer();
            System.out.println("Answer: " + left + " or " + right);
            String answer =input.next();
            if (compare(node.getLeftEdge().getAnswer(), answer )){
                node=node.getLeftEdge().getEndNode();
            } 
            else if(compare(node.getRightEdge().getAnswer(), answer)){
                node=node.getRightEdge().getEndNode();
            } else {System.out.println("Wrong input");}
            } 
        }  
        input.close();
    }

    static void getTree(List<String[]> edgeList, List<String[]> nodeList){
        int index = getIndexSourceNode(edgeList, nodeList);
        String[] stringSourceNode=nodeList.get(index);
        Node tree=buildTree(stringSourceNode,edgeList, nodeList);
        goThroughTree(tree);

        
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(System.getProperty("user.dir")); //get current working directory
        try {   
        FileInputStream file = new FileInputStream("src/main/resources/intermediate/decision-tree-data.txt");
        Scanner sc = new Scanner(file);
        List<String[]> edgeList = new LinkedList<String[]>();
        List<String[]> nodeList = new LinkedList<String[]>();

        //sc.useDelimiter(", ");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineElements = line.split(", ");
            if(lineElements.length==3){    //line is a edge
                edgeList.add(lineElements);
            } else
            nodeList.add(lineElements);
        }
        sc.close();
        getTree(edgeList,nodeList);

        } catch (FileNotFoundException error) {
        error.printStackTrace();
       }
       
    }

}