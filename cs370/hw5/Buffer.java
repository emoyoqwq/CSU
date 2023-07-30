import java.util.ArrayList;

public class Buffer{
    private ArrayList<Integer> list = null;
    final private int size;
    private int inIndex = 0;        //index where producers add element
    private int outIndex = 0;       //index where consumers consume elements
    private int inSum = 0;          //sum of produced elements
    private int outSum = 0;         //sum of consumed elements
    
    public Buffer(int size){
        this.size = size;
        //create bounded array
        this.list = new ArrayList<Integer>(this.size);
    }
    
    //check if the list is full
    public boolean isFull(){
        return (list.size() == size);
    }

    //check if the list is empty
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    //get index where producer thread add elements
    public int getInIndex(){
        if(inIndex > this.size){
            inIndex = inIndex % this.size;
        }
        return inIndex;
    }

    //get index where consumer thread consumes elements
    public int getOutIndex(){
        if(outIndex > this.size){
            outIndex = outIndex % this.size;
        }
        return outIndex;
    }

    //add method for producer
    public void add(int n){
        list.add(n);
        inSum += n;
        inIndex++;
    }

    //remove method for consumer
    public int remove(){
        int a = list.remove(0);
        outSum += a;
        outIndex++;
        return a;
    }

    //get sum of elements produced
    public int getInSum(){
        return inSum;
    }

    //get sums of elements consumed
    public int getOutSum(){
        return outSum;
    }
    // public ArrayList<Integer> getList(){
    //     return list;
    // }
  
    
}


