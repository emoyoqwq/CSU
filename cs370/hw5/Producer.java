import java.util.Random;
import java.lang.Exception;

public class Producer extends Thread{
    //create variables that producers need
    Random randomWithSeed;
    private int id;
    private int count;
    Buffer buff;
    
    public Producer(Buffer buff, int count, int id, int seed){
        randomWithSeed = new Random(seed);
        this.id = id;
        this.count = count;
        this.buff = buff;
    }

    @Override
    public void run(){
        int variable;       //variable stores produced element
        while(count > 0){
            synchronized(buff){
                try{
                    if(buff.isFull()){          //check if the buff is filled
                        // System.out.printf("Producer: %s at time %s\n", buff.getList(), Coordinator.getTime());
                        this.buff.wait();
                        
                    }
                    else{
                        variable = randomWithSeed.nextInt(100);                 //generate random value
                        buff.add(variable);
                        this.buff.notify();                                     //notify waiting consumers
                        System.out.printf("Producer %3d inserted %3d  at  index %d at time %s\n", 
                                        id, variable, buff.getInIndex()-1, Coordinator.getTime());
                        count --;
                    }   
                }   
                catch(InterruptedException e){
                    System.out.println("out of bound");
                }
            }
        }
    }

    //let first part of producers thread deal with remainder
    public void addCount(int n){
        count += n;
    }
}