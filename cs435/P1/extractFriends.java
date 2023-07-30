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

public class extractFriends {
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] arr = value.toString().split(" ");
            node1 = arr[0];
            node2 = arr[1];
            if(node1.compareTo(node2)<0){
                context.write(new Text(node1+"-"+node2), new IntWritable(1));
            }
            else{
                context.write(new Text(node2+"-"+node1), new IntWritable(1));
            }
        }
    }

    public static class CountReducer extends Reducer<Text, IntWritable, Text, Text>{
        private TreeMap<String,Integer> output;
        private Text node1;
        private Text node2;
        @Override
        protected void setup(Context context) {
            output = new TreeMap<String,Integer>();
        }
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) {
            int temp =0;
            for(IntWritable a : values){
                temp += a.get();
            }
            output.put(key.toString(),temp);
        }
        
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for(Entry<String,Integer> entry : output.entrySet()){
                String[] arr = entry.getKey().toString().split("-");
                node1 = new Text(arr[0]);
                node2 = new Text(arr[1]);
                if(entry.getValue().compareTo(new Integer(1)) > 0){
                    context.write(node1,node2);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "extractFriends");
        job.setJarByClass(extractFriends.class);
        job.setMapperClass(extractFriends.TokenizerMapper.class);
        job.setReducerClass(extractFriends.CountReducer.class);
        job.setNumReduceTasks(1);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}