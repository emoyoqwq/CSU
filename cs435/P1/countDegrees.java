import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.*;
import java.util.*;


public class countDegrees {
    public static class inDegree extends Mapper<Object, Text, Text,  IntWritable> {
        private Text node1;
        private Text node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] arr = value.toString().split(" ");
            node1 = new Text(arr[0]);
            node2 = new Text(arr[1]);
            IntWritable temp = new IntWritable(1);
            context.write(node2,temp);
        }
    }

    public static class outDegree extends Mapper<Object, Text, Text, IntWritable> {
        private Text node1;
        private Text node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] arr = value.toString().split(" ");
            node1 = new Text(arr[0]);
            node2 = new Text(arr[1]);
            IntWritable temp = new IntWritable(1);
            context.write(node1,temp);
        }
    }

    public static class CountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
        private TreeMap<String, Integer> output;
        private TreeMap<String, Integer> sortedOutput;
        
        @Override
        protected void setup(Context context) {
            output = new TreeMap<String, Integer>();
            sortedOutput = new TreeMap<String, Integer>();
        }
        
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) {
            int temp =0;
            for(IntWritable a : values){
                temp += a.get();
            }
            output.put(key.toString(),temp);
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            
            
            int count =1;
            List<Entry<String, Integer>> sortedOutput = new ArrayList<Entry<String, Integer>>(output.entrySet());
            Collections.sort(sortedOutput,new Comparator<Map.Entry<String,Integer>>() {
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            for(Entry<String,Integer> entry : sortedOutput){
                context.write(new Text(entry.getKey()), new IntWritable(entry.getValue()));
                count++;
                if(count > 100){
                    break;
                }
            }
        }
            
    }
    public static void main(String[] args) throws Exception {
        Configuration conf1 = new Configuration();
        Job job1 = Job.getInstance(conf1, "job1");
        job1.setJarByClass(countDegrees.class);
        job1.setMapperClass(countDegrees.inDegree.class);
        job1.setReducerClass(countDegrees.CountReducer.class);
        job1.setNumReduceTasks(1);
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(IntWritable.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        job1.waitForCompletion(true);

        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "job2");
        job2.setJarByClass(countDegrees.class);
        job2.setMapperClass(countDegrees.outDegree.class);
        job2.setReducerClass(countDegrees.CountReducer.class);
        job2.setNumReduceTasks(1);
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(IntWritable.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job2, new Path(args[0]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}
