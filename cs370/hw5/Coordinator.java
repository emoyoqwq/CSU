import java.util.Random;
import java.lang.Exception;
import java.time.Instant;
import java.time.Duration;
import java.time.Clock;
import java.util.ArrayList;

class Coordinator{
    public static String getTime() {
        Clock offsetClock = Clock.offset(Clock.systemUTC(), Duration.ofHours(-9));
        Instant time = Instant.now(offsetClock);
        String timeString = time.toString();
        timeString = timeString.replace('T', ' ');
        timeString = timeString.replace('Z', ' ');
        
        return(timeString);
    }
    
    public static void main(String[] args){
        //read arguments: buffer size, number of elements, consumers, producers, seed
        int size = Integer.parseInt(args[0]);
        int elements = Integer.parseInt(args[1]);
        int numConsumers = Integer.parseInt(args[2]);
        int numProducers = Integer.parseInt(args[3]);
        int seed = Integer.parseInt(args[4]);
        
        //instance of buffer used by Producer and Consumer
        Buffer buffer = new Buffer(size);
        
        //object arraylist for Producer and Consumer
        ArrayList<Producer> proArr = new ArrayList<Producer>();
        ArrayList<Consumer> conArr = new ArrayList<Consumer>();
        
        //number of elements that each producer and consumer need to producer or consume
        int countProducer = elements / numProducers;
        int countConsumer = elements / numConsumers;
        
        //create n producers
        for(int i=0;i<numProducers;i++){
            proArr.add(new Producer(buffer, countProducer, i+1, seed));
        }
        
        //create m consumers
        for(int i=0;i<numConsumers;i++){
            conArr.add(new Consumer(buffer, countConsumer, i+1));
        }

        //disrtubute tasks for producers as evenly as poosible
        if(elements % numProducers != 0){
            int remainder = elements % numProducers;
            for(int i=0;i<remainder;i++){
                proArr.get(i).addCount(1);
            }
        }

        //disrtubute tasks for consumers as evenly as poosible
        if(elements % numConsumers != 0){
            int remainder = elements % numConsumers;
            for(int i=0;i<remainder;i++){
                conArr.get(i).addCount(1);
            }
        }
        
        //start producer threads
        for(int i=0;i<numProducers;i++){
            proArr.get(i).start();
        }

        //start consumer threads
        for(int i=0;i<numConsumers;i++){
            conArr.get(i).start();
        }

        //make sure producer threads finish first before main finishes
        for(int i=0;i<numProducers;i++){
            try{
                proArr.get(i).join();
            }   
            catch(InterruptedException e){
                System.out.println("out of bound");
            }
        }

        //make sure consumer threads finish before main finishes
        for(int i=0;i<numConsumers;i++){
            try{
                conArr.get(i).join();
            }   
            catch(InterruptedException e){
                System.out.println("out of bound");
            }
        }
        
        System.out.printf("\nProducer(s): Finished producing %3d items with checksum being %d\n", elements, buffer.getInSum());
        System.out.printf("\033[0;4mConsumer(s): Finsihed consuming %3d items with checksum being %d\033[0;0m\n", elements, buffer.getOutSum());
        
    }
}