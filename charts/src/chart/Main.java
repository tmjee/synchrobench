package chart;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.String.format;

public class Main {

   public static void main(String[] args) throws Exception {

       Map<String, Data> data = new TreeMap<String, Data>();

       File f = new File(args[0]);
       BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f))) ;
       try {
           String l = r.readLine();
           String threads = null;
           String bench = null;
           Double ops  = null;
           while (l != null) {
               l = l.trim();
               if (l.startsWith("Number of threads:")) {
                   threads = format("%03d", Integer.parseInt(l.split(":")[1].trim()));
               } else if (l.startsWith("Benchmark:")) {
                    bench = l.split(":")[1].trim();
               } else if (l.startsWith("Throughput (ops/s):")) {
                   String tmp = l.split(":")[1].trim();
                   ops = Double.parseDouble(tmp);

                   if (!data.containsKey(threads)) {
                        data.put(threads, new Data(threads));
                   }
                   Data d = data.get(threads);
                   if (bench.endsWith("ConcurrentSkipListSet")) {
                       d.conc = ops;
                   } else if(bench.endsWith("BaselineSet")) {
                       d.baseline = ops;
                   } else if (bench.endsWith("SynchronizedSet")) {
                       d.sync = ops;
                   } else if (bench.endsWith("MyConcurrentSkipListSet")) {
                       d.myconc = ops;
                   }

                   threads = null;
                   bench = null;
                   ops = null;
               }
               l = r.readLine();
           }
       }finally {
           r.close();
       }

        for(Data d : data.values()) {
            System.out.println("Threads\tBaseline\tConc\tSync");
            System.out.println(format("%s\t%f\t%f\t%f", d.getThreads(), d.getBaseline(), d.getConc(), d.getSync()));
        }



           Map<String, Object> d = new HashMap<String, Object>();
           d.put("data", data);
           Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
           conf.setClassLoaderForTemplateLoading(Thread.currentThread().getContextClassLoader(), "");

           Template template = conf.getTemplate(args[2]);
           template.process(d, new PrintWriter(System.out));
           File out = new File(args[1]);
           PrintWriter pw =new PrintWriter(new FileOutputStream(out));
           try {
              template.process(d, pw);
           } finally {
               pw.close();
           }
   }



    public static class Data {
        private String threads;
        private double conc;
        private double myconc;
        private double sync;
        private double baseline;

        public Data(String threads) {
            this.threads = threads;
        }

        public double getConc() { return conc; }
        public double getSync() { return sync;}
        public double getBaseline() { return baseline; }
        public String getThreads() { return threads; }
        public double getMyConc() { return myconc; }
    }

}
