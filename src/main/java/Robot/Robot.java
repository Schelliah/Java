package Robot;
import java.util.*;


@FunctionalInterface
interface Command{
    void execute();
}

public class Robot {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final String STRING_NORTH = "North";
    public static final String STRING_EAST = "East";
    public static final String STRING_SOUTH = "South";
    public static final String STRING_WEST = "West";

    int direction;
    int x;
    int y;
    ArrayList<Command> commandList;


    public Robot(){
        direction=NORTH;
        x=0;
        y=0;
        commandList=new ArrayList<Command>();
    }

    public Robot(int xx,int yy,String givenDirection){
        setDirecion(givenDirection);
        x=xx;
        y=yy;
        commandList=new ArrayList<Command>();
    }


    private void setDirecion(String stringDirection){
        stringDirection=stringFormat(stringDirection);
        switch(stringDirection){
            case STRING_NORTH:
                direction=NORTH;
                break;
            case STRING_EAST:
                direction=EAST;
                break;
            case STRING_SOUTH:
                direction=SOUTH;
                break;
            case STRING_WEST:
                direction=WEST;
                break;
        }
    }

    private String getDirection(){
        switch(direction){
            case NORTH:
                return STRING_NORTH;
            case EAST:
                return STRING_EAST;
            case SOUTH:
                return STRING_SOUTH;
            case WEST:
                return STRING_WEST;
        }
        return "(Direction not found)";
    }

    private void move(int i ){
        switch(direction){
            case NORTH:
                y=y+i;
                break;
            case EAST:
                x=x+i;
                break;
            case SOUTH:
                y=y-i;
                break;
            case WEST:
                x=x-i;
                break;
        }
    }

    void forward(){
        Command command = () -> move(1);
        commandList.add(command);
    }

    void forward(int moves){
        Command command = () -> forwardInt(moves);
        commandList.add(command);
    }

    void forwardInt(int moves){
        if (0<moves && 4>moves) { move(moves);
        } else {
            System.out.println("Wrong amount of moves");
        }
    }

    void backward(){
        Command command = () -> move(-1);
        commandList.add(command);
    }

    void turnRight(){
        Command command = () -> turnRightNow();
        commandList.add(command);
    }

    void turnLeft(){
        Command command = () -> turnLeftNow();
        commandList.add(command);
    }
    

    void turnRightNow(){
        if(direction==WEST){
            direction=NORTH;
        }else{
            direction=direction+1;
        }
    }

    void turnLeftNow(){
        if(direction==NORTH){
            direction=WEST;
        }else{
            direction=direction-1;
        }
    }

    void execute(){
        commandList.forEach(command -> command.execute());
        commandList.clear();
    }

    
    void printState(){
        System.out.println("Now facing " + getDirection() + " at " + "(" + x+ ","+ y+ ")");
    }


    private static String stringFormat(String str){
        str=str.toLowerCase();
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static void main(String[] args) {
    Robot myFirstRobot = new Robot(0, 1, "East");
    Robot mySecondRobot = new Robot(1, 0, "West");
    myFirstRobot.turnLeft();
    myFirstRobot.forward();
    mySecondRobot.backward();
    
    myFirstRobot.execute();
    mySecondRobot.execute();;
    myFirstRobot.printState(); // Now facing "North" at (0,2)
    mySecondRobot.printState(); // Now facing "West" at (2,0)
             
    }

    

}