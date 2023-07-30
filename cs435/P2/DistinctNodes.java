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

public class DistinctNodes {
    public static class edgesMapper extends Mapper<Object, Text, Text, NullWritable> {
        private Text edge ;
        
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String node1 = itr.nextToken();
            String node2 = itr.nextToken();
            edge = new Text(node1+node2);
            context.write(edge, NullWritable.get());
        }
    }

    public static class verticesMapper extends Mapper<Object, Text, Text, NullWritable> {
        private Text node ;
        
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while(itr.hasMoreTokens()){
                String a = itr.nextToken();
                node = new Text(a);
                context.write(node,NullWritable.get());
            }
        }
    }

    public static class CountReducer extends Reducer<Text, NullWritable, IntWritable, NullWritable>{
        private Set<String> distinctNodes ;
        @Override
        protected void setup(Context context) {
            distinctNodes = new HashSet<String>();
        }
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) {
            distinctNodes.add(key.toString());
        }
        
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            IntWritable number = new IntWritable(distinctNodes.size());
            context.write(number, NullWritable.get());
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf1 = new Configuration();
        Job job1 = Job.getInstance(conf1, "edges");
        job1.setJarByClass(DistinctNodes.class);
        job1.setMapperClass(DistinctNodes.edgesMapper.class);
        job1.setReducerClass(DistinctNodes.CountReducer.class);
        job1.setNumReduceTasks(1);
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(NullWritable.class);
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        job1.waitForCompletion(true);

        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "vertices");
        job2.setJarByClass(DistinctNodes.class);
        job2.setMapperClass(DistinctNodes.verticesMapper.class);
        job2.setReducerClass(DistinctNodes.CountReducer.class);
        job2.setNumReduceTasks(1);
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(NullWritable.class);
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job2, new Path(args[0]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}
