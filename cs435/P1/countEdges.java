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

public class countEdges {
    public static class TokenizerMapper extends Mapper<Object, Text, Text, NullWritable> {
        private Text node ;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                String a = itr.nextToken();
                String b = itr.nextToken();
                node = new Text( a + b );
                context.write(node, NullWritable.get());
            } 
        }
    }

    public static class CountReducer extends Reducer<Text, NullWritable, IntWritable, NullWritable>{
        private Set<String> distinctNodes ;
        int number=0;
        @Override
        protected void setup(Context context) {
            distinctNodes = new HashSet<String>();
        }
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) {
            distinctNodes.add(key.toString());
            number++;
        }   
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            IntWritable noOfNodes = new IntWritable(number);
            context.write(noOfNodes, NullWritable.get());
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "distinct nodes new");
        job.setJarByClass(countEdges.class);
        job.setMapperClass(countEdges.TokenizerMapper.class);
        job.setReducerClass(countEdges.CountReducer.class);
        job.setNumReduceTasks(1);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
