import java.lang.Exception;

public class Consumer extends Thread{
    //variables that consumers need
    private int id;
    private int count;
    Buffer buff;

    public Consumer(Buffer buff, int count, int id){
        this.buff = buff;
        this.count = count;
        this.id = id;
    }

    @Override
    public void run(){
        int variable;
        while(count > 0){
            synchronized(buff){
                try{
                    //check if the buff is empty
                    if(buff.isEmpty()){
                        // System.out.printf("\033[0;4mConsumer: %s at time %s\033[0;0m\n", buff.getList(), Coordinator.getTime());
                        this.buff.wait();
                    }
                    else{
                        variable = buff.remove();
                        this.buff.notify();             //notify the waiting producer thread
                        System.out.printf("\033[0;4mConsumer %3d consumed %3d from index %d at time\033[0;0m %s\033[0;0m\n",
                                        id, variable, buff.getOutIndex()-1, Coordinator.getTime());
                        count --; 
                    }   
                }
                catch(InterruptedException e){
                    System.out.println("out of bound");
                }
            }
        }
    }
    //let first part of consumers thread deal with remainder
    public void addCount(int n){
        count += n;
    }
}

