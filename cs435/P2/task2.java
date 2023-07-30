import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.FileSystem;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import java.util.Arrays;
import java.lang.Math;

import javax.imageio.metadata.IIOInvalidTreeException;

public class task2 {
    
    public static TreeMap<String,String> adjacencyList = new TreeMap<String, String>();
    public static TreeMap<String,String> finalPath = new TreeMap<String, String>();
    
    public static class adjacencyMapper extends Mapper<Object, Text, Text, Text> {
        private Text node1;
        private Text node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] arr = value.toString().split(" ");
            node1 = new Text(arr[0]);
            node2 = new Text(arr[1]);
            context.write(node1,node2);
            context.write(node2,node1);
        }
    }

    public static class adjacencyReducer extends Reducer<Text, Text, Text, Text>{
        private TreeMap<String,String> list;
        @Override
        protected void setup(Context context) {
            list = new TreeMap<String,String>();
        }
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) {
            String s = "";
            for(Text t: values){
                s+=t.toString()+"~";
            }
            list.put(key.toString(),s);
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for(Entry<String,String> entry: list.entrySet()){
                context.write(new Text(entry.getKey()), new Text(entry.getValue()));
            }
        }
    }





    public static class oneLengthMapper extends Mapper<Object, Text, Text, Text> {
        private Text node1;
        private Text node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String s = itr.nextToken();
            String b = itr.nextToken();
            node1=new Text(s);
            node2=new Text(b);
            context.write(node1, node2);
        }
    }
    public static class oneLengthReducer extends Reducer<Text, Text, Text, Text>{
        private TreeMap<String,String> list ;
        private TreeMap<String,String> oneLengthPath;
        @Override
        protected void setup(Context context) {
            list = new TreeMap<String,String>();
            oneLengthPath = new TreeMap<String,String>();
        }
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) {
            String s = "";
            for(Text t: values){
                s+=t.toString();
            }
            list.put(key.toString(),s);
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for(Entry<String,String> entry : list.entrySet()){
                String node1 = entry.getKey();
                String node2 = entry.getValue();
                String[] arr = node2.split("~");
                String path;
                for(int i=0;i<arr.length;i++){
                    if(node1.compareTo(arr[i])<0){
                        path=node1+"~"+arr[i];
                        if(!oneLengthPath.containsKey(path)){
                            oneLengthPath.put(path,node1+"~"+arr[i]+"~");
                        }
                    }
                    else{
                        path=arr[i]+"~"+node1;
                        if(!oneLengthPath.containsKey(path)){
                            oneLengthPath.put(path,arr[i]+"~"+node1+"~");
                        }
                    }
                    
                }
            }
            for(Entry<String,String> entry: oneLengthPath.entrySet()){
                context.write(new Text(entry.getKey()), new Text(entry.getValue()));
            }
        }
    }



    public static class newPathMapper extends Mapper<Object, Text, Text, Text> {
        private Text node1;
        private Text node2;
        private TreeMap<String,String> list;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String s = itr.nextToken();
            String b = itr.nextToken();
            node1=new Text(s);
            node2=new Text(b);
            context.write(node1, node2);
        }
    }

    public static class newPathReducer extends Reducer<Text, Text, Text, Text>{
        private TreeMap<String,String> list;
        private TreeMap<String,String> newList;
        private TreeMap<String,String> adjacencyList;
        @Override
        protected void setup(Context context) throws IOException{
            list = new TreeMap<String,String>();
            newList = new TreeMap<String,String>();
            adjacencyList= new TreeMap<String,String>();
            Path pt=new Path("/P2/output/adjList/part-r-00000");//Location of file in HDFS
            FileSystem fs = FileSystem.get(new Configuration());
            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line=br.readLine();
            while (line != null){
                StringTokenizer itr = new StringTokenizer(line);
                String a = itr.nextToken();
                String b = itr.nextToken();
                adjacencyList.put(a,b);
                line=br.readLine();
            }
        }
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) {
            String s="";
            for(Text t: values){
                s+=t.toString();
            }
            list.put(key.toString(),s);
        }
        
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            String newKey;
            String samePath;
            String newPath;
            
            for(Entry<String,String> entry : list.entrySet()){
                String arr[] = entry.getKey().split("~");
                String source=arr[0];
                String destination=arr[1];
                String path = entry.getValue();
                
                String[] sourceChoice = adjacencyList.get(source).split("~");
                String[] desChoice = adjacencyList.get(destination).split("~");
                for(int i=0;i<sourceChoice.length;i++){
                    if(!path.contains(sourceChoice[i])){
                        newKey= sourceChoice[i]+"~"+destination;
                        samePath =destination+"~"+sourceChoice[i];
                        newPath = sourceChoice[i]+"~"+path;
                        if(!newList.containsKey(newKey)&& !newList.containsKey(samePath)){
                            newList.put(newKey,newPath);
                        }
                    }
                }
                for(int i=0;i<desChoice.length;i++){
                    if(!path.contains(desChoice[i])){
                        newKey= source+"~"+desChoice[i];
                        samePath =desChoice[i]+"~"+source;
                        newPath = path+desChoice[i]+"~";
                        if(!newList.containsKey(newKey) && !newList.containsKey(samePath)){
                            newList.put(newKey,newPath);
                        }
                    }
                }
            }
            for(Entry<String,String> entry: newList.entrySet()){
                String[] arr = entry.getKey().split("~");
                String a = arr[0];
                String b = arr[1];
                String path = a+"~"+b;
                String anotherPath = b+"~"+a;
                if(!list.containsKey(path)&& !list.containsKey(anotherPath)){
                    list.put(entry.getKey(),entry.getValue());
                }
            }
            for(Entry<String,String> entry : list.entrySet()){
                context.write(new Text(entry.getKey()), new Text(entry.getValue()));
            }
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
    public static class verticesReducer extends Reducer<Text, NullWritable, IntWritable, NullWritable>{
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




    public static class geoMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
        private int vertices;
        @Override
        protected void setup(Context context) throws IOException{
            Path pt=new Path("/P2/output/vertices/part-r-00000");//Location of file in HDFS
            FileSystem fs = FileSystem.get(new Configuration());
            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line=br.readLine();
            vertices = Integer.parseInt(line);
        }
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String node1 = itr.nextToken();
            String[] node2 = itr.nextToken().split("~");
            int pathLength = node2.length - 1;
            context.write(new IntWritable(vertices), new IntWritable(pathLength));
        }
    }
    public static class geoReducer extends Reducer<IntWritable, IntWritable, DoubleWritable, NullWritable>{
        private int count=0;
        private int vertices = 0;
        @Override
        protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) {
            for(IntWritable a : values){
                count += a.get();
            }
            vertices = key.get();
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            double result = 2/(vertices*(vertices-1.0))*count;
            context.write(new DoubleWritable(result), NullWritable.get());
        }
    }



    public static class twoLengthMapper extends Mapper<Object, Text, Text, Text> {
        private Text node1;
        private Text node2;
        private TreeMap<String,String> list;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String s = itr.nextToken();
            String b = itr.nextToken();
            node1=new Text(s);
            node2=new Text(b);
            context.write(node1, node2);
        }
    }
    public static class twoLengthReducer extends Reducer<Text, Text, Text, Text>{
        private TreeMap<String,String> list;
        private TreeMap<String,String> newList;
        private TreeMap<String,String> adjacencyList;
        @Override
        protected void setup(Context context) throws IOException{
            list = new TreeMap<String,String>();
            newList = new TreeMap<String,String>();
            adjacencyList= new TreeMap<String,String>();
            Path pt=new Path("/P2/output/adjList/part-r-00000");//Location of file in HDFS
            FileSystem fs = FileSystem.get(new Configuration());
            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line=br.readLine();
            while (line != null){
                StringTokenizer itr = new StringTokenizer(line);
                String a = itr.nextToken();
                String b = itr.nextToken();
                adjacencyList.put(a,b);
                line=br.readLine();
            }
        }
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) {
            String s="";
            for(Text t: values){
                s+=t.toString();
            }
            list.put(key.toString(),s);
        }
        
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            String newKey;
            String samePath;
            String newPath;
            for(Entry<String,String> entry : list.entrySet()){
                String arr[] = entry.getKey().split("~");
                String source=arr[0];
                String destination=arr[1];
                String path = entry.getValue();
                String pathArr[] = path.split("~");
                String angle1 = pathArr[0];
                String angle2 = pathArr[1];
                String[] sourceChoice = adjacencyList.get(source).split("~");
                String[] desChoice = adjacencyList.get(destination).split("~");
                for(int i=0;i<sourceChoice.length;i++){
                    if(!path.contains(sourceChoice[i])){
                        newKey= sourceChoice[i]+"~"+destination;
                        samePath =destination+"~"+sourceChoice[i];
                        newPath = sourceChoice[i]+"~"+path;
                        if(!newList.containsKey(newKey)&& !newList.containsKey(samePath)){
                            newList.put(newKey,newPath);
                        }
                    }
                }
                for(int i=0;i<desChoice.length;i++){
                    if(!path.contains(desChoice[i])){
                        newKey= source+"~"+desChoice[i];
                        samePath =desChoice[i]+"~"+source;
                        newPath = path+desChoice[i]+"~";
                        if(!newList.containsKey(newKey) && !newList.containsKey(samePath)){
                            newList.put(newKey,newPath);
                        }
                    }
                }
            }
            for(Entry<String,String> entry : newList.entrySet()){
                context.write(new Text(entry.getKey()), new Text(entry.getValue()));
            }
        }
    }





    public static class triangleMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1=itr.nextToken();
            node2=itr.nextToken();
            String sortedKey="";
            String[] arr = node2.split("~");
            Arrays.sort(arr);
            for(int i=0;i<arr.length;i++){
                sortedKey += arr[i];
            }
            context.write(new Text(sortedKey), new IntWritable(1));
        }
    }
    public static class triangleReducer extends Reducer<Text, IntWritable, IntWritable, NullWritable>{
        private Set<String> distinctNodes ;
        private int count;
        @Override
        protected void setup(Context context) {
            distinctNodes = new HashSet<String>();
        }
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) {
            count = 0;
            for(IntWritable t : values){
                count += t.get();
            }
            if(count == 3){
                distinctNodes.add(key.toString());
            }
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            IntWritable number = new IntWritable(distinctNodes.size());
            context.write(number, NullWritable.get());
        }
    }






    public static class coefficientMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
        private String node;
        int triangle;
        @Override
        protected void setup(Context context) throws IOException{
            Path pt=new Path("/P2/output/triangles/part-r-00000");//Location of file in HDFS
            FileSystem fs = FileSystem.get(new Configuration());
            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line=br.readLine();
            triangle = Integer.parseInt(line);
        }
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node=itr.nextToken();
            context.write(new IntWritable(triangle), new IntWritable(1));
        }
    }
    public static class coefficientReducer extends Reducer<IntWritable, IntWritable, DoubleWritable, NullWritable>{
        private int triangleNumber;
        private int triplets;
        @Override
        protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) {
            for(IntWritable t : values){
                triplets += t.get();
            }
            triangleNumber = key.get();
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            DoubleWritable result = new DoubleWritable(3.0*triangleNumber/triplets);
            context.write(result, NullWritable.get());
        }
    }





    public static class LRandomMapper extends Mapper<Object, Text, IntWritable, Text> {
        private int vertices;
        @Override
        protected void setup(Context context) throws IOException{
            Path pt=new Path("/P2/output/vertices/part-r-00000");//Location of file in HDFS
            FileSystem fs = FileSystem.get(new Configuration());
            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line=br.readLine();
            vertices = Integer.parseInt(line);
        }
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String node1 = itr.nextToken();
            String node2 = itr.nextToken();
            String edge = node1+node2;
            context.write(new IntWritable(vertices), new Text(edge));
        }
    }
    public static class LRandomReducer extends Reducer<IntWritable, Text, DoubleWritable, NullWritable>{
        private double edges=0.0;
        private int vertices;
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) {
            for(Text a : values){
                edges++;
            }
            vertices = key.get();
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            double result = Math.log(vertices)/Math.log(edges/vertices);
            context.write(new DoubleWritable(result), NullWritable.get());
        }
    }




    public static class CRandomMapper extends Mapper<Object, Text, IntWritable, Text> {
        private int vertices;
        @Override
        protected void setup(Context context) throws IOException{
            Path pt=new Path("/P2/output/vertices/part-r-00000");//Location of file in HDFS
            FileSystem fs = FileSystem.get(new Configuration());
            BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
            String line;
            line=br.readLine();
            vertices = Integer.parseInt(line);
        }
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String node1 = itr.nextToken();
            String node2 = itr.nextToken();
            String edge = node1+node2;
            context.write(new IntWritable(vertices), new Text(edge));
        }
    }
    public static class CRandomReducer extends Reducer<IntWritable, Text, DoubleWritable, NullWritable>{
        private double edges=0.0;
        private int vertices;
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) {
            for(Text a : values){
                edges++;
            }
            vertices = key.get();
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            double result = edges/vertices/vertices;
            context.write(new DoubleWritable(result), NullWritable.get());
        }
    }






    public static class triplesMapper extends Mapper<Object, Text, Text, NullWritable> {
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            String node = itr.nextToken();
            context.write(new Text(node), NullWritable.get());
        }
    }
    public static class triplesReducer extends Reducer<Text, NullWritable, IntWritable, NullWritable>{
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






    public static class finalOneMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 2){
                context.write(new Text("One"), new IntWritable(1));
            }
        }
    }

    public static class finalTwoMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 3){
                context.write(new Text("Two"), new IntWritable(2));
            }
        }
    }

    public static class finalThreeMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 4){
                context.write(new Text("Three"), new IntWritable(3));
            }
        }
    }

    public static class finalFourMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 5){
                context.write(new Text("Four"), new IntWritable(4));
            }
        }
    }

    public static class finalFiveMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 6){
                context.write(new Text("Five"), new IntWritable(5));
            }
        }
    }

    public static class finalSixMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 7){
                context.write(new Text("Six"), new IntWritable(6));
            }
        }
    }

    public static class finalSevenMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 8){
                context.write(new Text("Seven"), new IntWritable(7));
            }
        }
    }

    public static class finalEightMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            if(arr.length == 9){
                context.write(new Text("Eight"), new IntWritable(8));
            }
        }
    }

    public static class totalMapper extends Mapper<Object, Text, Text, IntWritable> {
        private String node1;
        private String node2;
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            node1 = itr.nextToken();
            node2 = itr.nextToken();
            String[] arr = node2.split("~");
            context.write(new Text("total"), new IntWritable(arr.length-1));
        }
    }

    public static class geoPathReducer extends Reducer<Text, IntWritable, IntWritable, NullWritable>{
        private int num= 0;
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) {
            for(IntWritable t : values){
                num += t.get();
            }
        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            context.write(new IntWritable(num), NullWritable.get());
        }
    }




    


    public static void main(String[] args) throws Exception {
        Configuration conf25 = new Configuration();
        Path adjacencyList = new Path("/P2/output/adjList");
        Job job25 = Job.getInstance(conf25, "adjacencyList");
        job25.setJarByClass(task2.class);
        job25.setMapperClass(task2.adjacencyMapper.class);
        job25.setReducerClass(task2.adjacencyReducer.class);
        job25.setNumReduceTasks(1);
        job25.setMapOutputKeyClass(Text.class);
        job25.setMapOutputValueClass(Text.class);
        job25.setOutputKeyClass(Text.class);
        job25.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job25,new Path(args[0]));
        FileOutputFormat.setOutputPath(job25, adjacencyList);
        job25.waitForCompletion(true);
        
        Configuration conf = new Configuration();
        Job job1 = Job.getInstance(conf, "length1");
        Path jobOne = new Path("/P2/output/output1");
        job1.setJarByClass(task2.class);
        job1.setMapperClass(task2.oneLengthMapper.class);
        job1.setReducerClass(task2.oneLengthReducer.class);
        job1.setNumReduceTasks(1);
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(Text.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job1, adjacencyList);
        FileOutputFormat.setOutputPath(job1,jobOne);
        job1.waitForCompletion(true);

        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "length2");
        Path jobTwo = new Path("/P2/output/output2");
        job2.setJarByClass(task2.class);
        job2.setMapperClass(task2.newPathMapper.class);
        job2.setReducerClass(task2.newPathReducer.class);
        job2.setNumReduceTasks(1);
        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Text.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job2, jobOne);
        FileOutputFormat.setOutputPath(job2, jobTwo);
        job2.waitForCompletion(true);
        
        Configuration conf3 = new Configuration();
        Job job3 = Job.getInstance(conf3, "length3");
        Path jobThree = new Path("/P2/output/output3");
        job3.setJarByClass(task2.class);
        job3.setMapperClass(task2.newPathMapper.class);
        job3.setReducerClass(task2.newPathReducer.class);
        job3.setNumReduceTasks(1);
        job3.setMapOutputKeyClass(Text.class);
        job3.setMapOutputValueClass(Text.class);
        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job3, jobTwo);
        FileOutputFormat.setOutputPath(job3, jobThree);
        job3.waitForCompletion(true);

        Configuration conf4 = new Configuration();
        Job job4 = Job.getInstance(conf4, "length4");
        Path jobFour = new Path("/P2/output/output4");
        job4.setJarByClass(task2.class);
        job4.setMapperClass(task2.newPathMapper.class);
        job4.setReducerClass(task2.newPathReducer.class);
        job4.setNumReduceTasks(1);
        job4.setMapOutputKeyClass(Text.class);
        job4.setMapOutputValueClass(Text.class);
        job4.setOutputKeyClass(Text.class);
        job4.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job4, jobThree);
        FileOutputFormat.setOutputPath(job4, jobFour);
        job4.waitForCompletion(true);

        Configuration conf5 = new Configuration();
        Job job5 = Job.getInstance(conf5, "length5");
        Path jobFive = new Path("/P2/output/output5");
        job5.setJarByClass(task2.class);
        job5.setMapperClass(task2.newPathMapper.class);
        job5.setReducerClass(task2.newPathReducer.class);
        job5.setNumReduceTasks(1);
        job5.setMapOutputKeyClass(Text.class);
        job5.setMapOutputValueClass(Text.class);
        job5.setOutputKeyClass(Text.class);
        job5.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job5, jobFour);
        FileOutputFormat.setOutputPath(job5, jobFive);
        job5.waitForCompletion(true);

        Configuration conf6 = new Configuration();
        Job job6 = Job.getInstance(conf6, "length6");
        Path jobSix = new Path("/P2/output/output6");
        job6.setJarByClass(task2.class);
        job6.setMapperClass(task2.newPathMapper.class);
        job6.setReducerClass(task2.newPathReducer.class);
        job6.setNumReduceTasks(1);
        job6.setMapOutputKeyClass(Text.class);
        job6.setMapOutputValueClass(Text.class);
        job6.setOutputKeyClass(Text.class);
        job6.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job6, jobFive);
        FileOutputFormat.setOutputPath(job6, jobSix);
        job6.waitForCompletion(true);

        Configuration conf7 = new Configuration();
        Job job7 = Job.getInstance(conf7, "length7");
        Path jobSeven = new Path("/P2/output/output7");
        job7.setJarByClass(task2.class);
        job7.setMapperClass(task2.newPathMapper.class);
        job7.setReducerClass(task2.newPathReducer.class);
        job7.setNumReduceTasks(1);
        job7.setMapOutputKeyClass(Text.class);
        job7.setMapOutputValueClass(Text.class);
        job7.setOutputKeyClass(Text.class);
        job7.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job7, jobSix);
        FileOutputFormat.setOutputPath(job7, jobSeven);
        job7.waitForCompletion(true);

        Configuration conf8 = new Configuration();
        Job job8 = Job.getInstance(conf8, "length8");
        Path jobEight = new Path("/P2/output/output8");
        job8.setJarByClass(task2.class);
        job8.setMapperClass(task2.newPathMapper.class);
        job8.setReducerClass(task2.newPathReducer.class);
        job8.setNumReduceTasks(1);
        job8.setMapOutputKeyClass(Text.class);
        job8.setMapOutputValueClass(Text.class);
        job8.setOutputKeyClass(Text.class);
        job8.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job8, jobSeven);
        FileOutputFormat.setOutputPath(job8, jobEight);
        job8.waitForCompletion(true);

        Configuration conf9 = new Configuration();
        Job job9 = Job.getInstance(conf9, "vertices");
        Path jobNine = new Path("/P2/output/vertices");
        job9.setJarByClass(task2.class);
        job9.setMapperClass(task2.verticesMapper.class);
        job9.setReducerClass(task2.verticesReducer.class);
        job9.setNumReduceTasks(1);
        job9.setMapOutputKeyClass(Text.class);
        job9.setMapOutputValueClass(NullWritable.class);
        job9.setOutputKeyClass(IntWritable.class);
        job9.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job9, new Path(args[0]));
        FileOutputFormat.setOutputPath(job9, jobNine);
        job9.waitForCompletion(true);

        Configuration conf10 = new Configuration();
        Job job10 = Job.getInstance(conf10, "geo");
        Path jobTen = new Path("/P2/output/geo");
        job10.setJarByClass(task2.class);
        job10.setMapperClass(task2.geoMapper.class);
        job10.setReducerClass(task2.geoReducer.class);
        job10.setNumReduceTasks(1);
        job10.setMapOutputKeyClass(IntWritable.class);
        job10.setMapOutputValueClass(IntWritable.class);
        job10.setOutputKeyClass(DoubleWritable.class);
        job10.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job10, jobEight);
        FileOutputFormat.setOutputPath(job10, jobTen);
        job10.waitForCompletion(true);

        Configuration conf26 = new Configuration();
        Job job26 = Job.getInstance(conf26, "all length 2");
        Path jobLengthTwo = new Path("/P2/output/lengthTwo");
        job26.setJarByClass(task2.class);
        job26.setMapperClass(task2.twoLengthMapper.class);
        job26.setReducerClass(task2.twoLengthReducer.class);
        job26.setNumReduceTasks(1);
        job26.setMapOutputKeyClass(Text.class);
        job26.setMapOutputValueClass(Text.class);
        job26.setOutputKeyClass(Text.class);
        job26.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job26, jobOne);
        FileOutputFormat.setOutputPath(job26, jobLengthTwo);
        job26.waitForCompletion(true);

        Configuration conf11 = new Configuration();
        Job job11 = Job.getInstance(conf11, "triangle");
        Path jobEleven = new Path("/P2/output/triangles");
        job11.setJarByClass(task2.class);
        job11.setMapperClass(task2.triangleMapper.class);
        job11.setReducerClass(task2.triangleReducer.class);
        job11.setNumReduceTasks(1);
        job11.setMapOutputKeyClass(Text.class);
        job11.setMapOutputValueClass(IntWritable.class);
        job11.setOutputKeyClass(IntWritable.class);
        job11.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job11, jobLengthTwo);
        FileOutputFormat.setOutputPath(job11,jobEleven);
        job11.waitForCompletion(true);

        Configuration conf12 = new Configuration();
        Job job12 = Job.getInstance(conf12, "cluster coefficient");
        Path jobTwelve = new Path("/P2/output/coe");
        job12.setJarByClass(task2.class);
        job12.setMapperClass(task2.coefficientMapper.class);
        job12.setReducerClass(task2.coefficientReducer.class);
        job12.setNumReduceTasks(1);
        job12.setMapOutputKeyClass(IntWritable.class);
        job12.setMapOutputValueClass(IntWritable.class);
        job12.setOutputKeyClass(DoubleWritable.class);
        job12.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job12, jobLengthTwo);
        FileOutputFormat.setOutputPath(job12, jobTwelve);
        job12.waitForCompletion(true);

        Configuration conf13 = new Configuration();
        Path jobThirteen = new Path("/P2/output/Lrandom");
        Job job13 = Job.getInstance(conf13, "Lrandom");
        job13.setJarByClass(task2.class);
        job13.setMapperClass(task2.LRandomMapper.class);
        job13.setReducerClass(task2.LRandomReducer.class);
        job13.setNumReduceTasks(1);
        job13.setMapOutputKeyClass(IntWritable.class);
        job13.setMapOutputValueClass(Text.class);
        job13.setOutputKeyClass(DoubleWritable.class);
        job13.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job13, new Path(args[0]));
        FileOutputFormat.setOutputPath(job13,jobThirteen);
        job13.waitForCompletion(true);

        Configuration conf14 = new Configuration();
        Path fourteen = new Path("/P2/output/Crandom");
        Job job14 = Job.getInstance(conf14, "Crandom");
        job14.setJarByClass(task2.class);
        job14.setMapperClass(task2.CRandomMapper.class);
        job14.setReducerClass(task2.CRandomReducer.class);
        job14.setNumReduceTasks(1);
        job14.setMapOutputKeyClass(IntWritable.class);
        job14.setMapOutputValueClass(Text.class);
        job14.setOutputKeyClass(DoubleWritable.class);
        job14.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job14, new Path(args[0]));
        FileOutputFormat.setOutputPath(job14,fourteen);
        job14.waitForCompletion(true);

        Configuration conf15 = new Configuration();
        Path triples = new Path("/P2/output/triples");
        Job job15 = Job.getInstance(conf15, "triples");
        job15.setJarByClass(task2.class);
        job15.setMapperClass(task2.triplesMapper.class);
        job15.setReducerClass(task2.triplesReducer.class);
        job15.setNumReduceTasks(1);
        job15.setMapOutputKeyClass(Text.class);
        job15.setMapOutputValueClass(NullWritable.class);
        job15.setOutputKeyClass(IntWritable.class);
        job15.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job15,jobLengthTwo);
        FileOutputFormat.setOutputPath(job15,triples);
        job15.waitForCompletion(true);

        Configuration conf16 = new Configuration();
        Path finalOne = new Path("/P2/output/finalOne");
        Job job16 = Job.getInstance(conf16, "finalOne");
        job16.setJarByClass(task2.class);
        job16.setMapperClass(task2.finalOneMapper.class);
        job16.setReducerClass(task2.geoPathReducer.class);
        job16.setNumReduceTasks(1);
        job16.setMapOutputKeyClass(Text.class);
        job16.setMapOutputValueClass(IntWritable.class);
        job16.setOutputKeyClass(IntWritable.class);
        job16.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job16,jobEight);
        FileOutputFormat.setOutputPath(job16,finalOne);
        job16.waitForCompletion(true);

        Configuration conf17 = new Configuration();
        Path finalTwo = new Path("/P2/output/finalTwo");
        Job job17 = Job.getInstance(conf17, "finalTwo");
        job17.setJarByClass(task2.class);
        job17.setMapperClass(task2.finalTwoMapper.class);
        job17.setReducerClass(task2.geoPathReducer.class);
        job17.setNumReduceTasks(1);
        job17.setMapOutputKeyClass(Text.class);
        job17.setMapOutputValueClass(IntWritable.class);
        job17.setOutputKeyClass(IntWritable.class);
        job17.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job17,jobEight);
        FileOutputFormat.setOutputPath(job17,finalTwo);
        job17.waitForCompletion(true);

        Configuration conf24 = new Configuration();
        Path finalThree = new Path("/P2/output/finalThree");
        Job job24 = Job.getInstance(conf24, "finalThree");
        job24.setJarByClass(task2.class);
        job24.setMapperClass(task2.finalThreeMapper.class);
        job24.setReducerClass(task2.geoPathReducer.class);
        job24.setNumReduceTasks(1);
        job24.setMapOutputKeyClass(Text.class);
        job24.setMapOutputValueClass(IntWritable.class);
        job24.setOutputKeyClass(IntWritable.class);
        job24.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job24,jobEight);
        FileOutputFormat.setOutputPath(job24,finalThree);
        job24.waitForCompletion(true);

        Configuration conf18 = new Configuration();
        Path finalFour = new Path("/P2/output/finalFour");
        Job job18 = Job.getInstance(conf18, "finalFour");
        job18.setJarByClass(task2.class);
        job18.setMapperClass(task2.finalFourMapper.class);
        job18.setReducerClass(task2.geoPathReducer.class);
        job18.setNumReduceTasks(1);
        job18.setMapOutputKeyClass(Text.class);
        job18.setMapOutputValueClass(IntWritable.class);
        job18.setOutputKeyClass(IntWritable.class);
        job18.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job18,jobEight);
        FileOutputFormat.setOutputPath(job18,finalFour);
        job18.waitForCompletion(true);

        Configuration conf19 = new Configuration();
        Path finalFive = new Path("/P2/output/finalFive");
        Job job19 = Job.getInstance(conf19, "finalFive");
        job19.setJarByClass(task2.class);
        job19.setMapperClass(task2.finalFiveMapper.class);
        job19.setReducerClass(task2.geoPathReducer.class);
        job19.setNumReduceTasks(1);
        job19.setMapOutputKeyClass(Text.class);
        job19.setMapOutputValueClass(IntWritable.class);
        job19.setOutputKeyClass(IntWritable.class);
        job19.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job19,jobEight);
        FileOutputFormat.setOutputPath(job19,finalFive);
        job19.waitForCompletion(true);

        Configuration conf20 = new Configuration();
        Path finalSix = new Path("/P2/output/finalSix");
        Job job20 = Job.getInstance(conf20, "finalSix");
        job20.setJarByClass(task2.class);
        job20.setMapperClass(task2.finalSixMapper.class);
        job20.setReducerClass(task2.geoPathReducer.class);
        job20.setNumReduceTasks(1);
        job20.setMapOutputKeyClass(Text.class);
        job20.setMapOutputValueClass(IntWritable.class);
        job20.setOutputKeyClass(IntWritable.class);
        job20.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job20,jobEight);
        FileOutputFormat.setOutputPath(job20,finalSix);
        job20.waitForCompletion(true);

        Configuration conf21 = new Configuration();
        Path finalSeven = new Path("/P2/output/finalSeven");
        Job job21 = Job.getInstance(conf21, "finalSeven");
        job21.setJarByClass(task2.class);
        job21.setMapperClass(task2.finalSevenMapper.class);
        job21.setReducerClass(task2.geoPathReducer.class);
        job21.setNumReduceTasks(1);
        job21.setMapOutputKeyClass(Text.class);
        job21.setMapOutputValueClass(IntWritable.class);
        job21.setOutputKeyClass(IntWritable.class);
        job21.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job21,jobEight);
        FileOutputFormat.setOutputPath(job21,finalSeven);
        job21.waitForCompletion(true);

        Configuration conf22 = new Configuration();
        Path finalEight = new Path("/P2/output/finalEight");
        Job job22 = Job.getInstance(conf22, "finalEight");
        job22.setJarByClass(task2.class);
        job22.setMapperClass(task2.finalEightMapper.class);
        job22.setReducerClass(task2.geoPathReducer.class);
        job22.setNumReduceTasks(1);
        job22.setMapOutputKeyClass(Text.class);
        job22.setMapOutputValueClass(IntWritable.class);
        job22.setOutputKeyClass(IntWritable.class);
        job22.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job22,jobEight);
        FileOutputFormat.setOutputPath(job22,finalEight);
        job22.waitForCompletion(true);

        Configuration conf23 = new Configuration();
        Path total = new Path("/P2/output/total");
        Job job23 = Job.getInstance(conf23, "total");
        job23.setJarByClass(task2.class);
        job23.setMapperClass(task2.totalMapper.class);
        job23.setReducerClass(task2.geoPathReducer.class);
        job23.setNumReduceTasks(1);
        job23.setMapOutputKeyClass(Text.class);
        job23.setMapOutputValueClass(IntWritable.class);
        job23.setOutputKeyClass(IntWritable.class);
        job23.setOutputValueClass(NullWritable.class);
        FileInputFormat.addInputPath(job23,jobEight);
        FileOutputFormat.setOutputPath(job23,total);
        System.exit(job23.waitForCompletion(true) ? 0 : 1);
    }
}
