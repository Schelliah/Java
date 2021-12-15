
//implementation from scratch arraylist for integers
public class IntegerArrayList { 
    int SCALE = 2;
    int SIZE = 100;
    int[] array;
    int maxSize;
    int currentSize;

    public IntegerArrayList(){
        maxSize=SIZE;
        array = new int[maxSize];
        currentSize=0;
    }

    public IntegerArrayList(int givenSize){
        maxSize=givenSize;
        array = new int[maxSize];
        currentSize=0;
    }

    void add(int number){
        if (currentSize  < maxSize) {
            array[currentSize]=number;
            currentSize = currentSize +1;
        } else {
            maxSize=SCALE*maxSize;
            int[] array = new int[maxSize];
            array[currentSize]=number;
            currentSize = currentSize +1;
        }

    }

    void set(int index , int number) { //throwing exceptions/error message when higher index given than currentSize not implemented
        array[index] = number;

    }

    int get(int index){
        return array[index];
    }

    void print(){
        int[] copy=makecopyResized(array);
        System.out.println(Arrays.toString(copy));
    }

    int size(){
        return currentSize;
    }

    int[] makecopyResized(int[] array) {
        int[] copyArray = new int[currentSize];
        for (int i =0; i<currentSize;i++){
            copyArray[i] = this.array[i];
        }
        return copyArray;
    }

    int[] bubblesort(){
        int[] bubblesort=makecopyResized(array);
        for(int j =0; j< this.size() -1;j++){
            boolean hasSorted= false;
            for(int i =0; i< this.size() -1;i++){
                if(bubblesort[i]>bubblesort[i+1]){
                    int temp = bubblesort[i+1];
                    bubblesort[i+1]=bubblesort[i];
                    bubblesort[i]=temp;
                    hasSorted=true;
                }
            }
            if(hasSorted=false){
                break;
            }
        }
        return bubblesort;
    }
}