package com.example.randomwriter;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomwriterController {

    private final AtomicLong counter = new AtomicLong();
    private Scanner inputFile;
    private Map<Queue<String>,ArrayList<String >> dic;
    private String content;

    @RequestMapping("/randomwriter")
    public Randomwriter randomwriter(@RequestParam(value="N", defaultValue="3") String N,
                 @RequestParam(value="words", defaultValue="0") String words) throws Exception {
        init();
        process(Integer.parseInt(N));
        start(Integer.parseInt(words));
        return new Randomwriter(counter.incrementAndGet(),content);
    }

    public void init() throws IOException{
        String fileName;
        fileName="C:\\Users\\Administrator\\IdeaProjects\\randomwriter\\src\\main\\resources\\static\\hamlet.txt";
        inputFile=new Scanner(new File(fileName));
        //System.out.println(inputFile.next());
        //out=new PrintWriter("123
        //out.println("123");
        //out.close();
        //inputFile=new Scanner(Paths.get(fileName));
        //while (in.hasNextLine()){
        //    out.println("1");
        //    out.print(in.nextLine());
        //}
        //in.close();
        //File file=new File(fileName);
    }

    public void process(int N){
        String sub;
        dic=new HashMap<Queue<String>, ArrayList<String>>();
        Queue<String> key=new LinkedList<>();
        ArrayList<String> value;
        System.out.println(inputFile.hasNext());
        for (int i=0;i<N-1;i++){
            key.add(inputFile.next());
        }
        while(inputFile.hasNext()){
            sub=inputFile.next();
            value=dic.getOrDefault(key,new ArrayList<>());
            value.add(sub);
            dic.put(key,value);
            key=new LinkedList<>(key);
            key.remove();
            key.add(sub);
        }
        //System.out.println(dic.size());
    }

    public Queue<String> getStart(){
        long nr= (int) (System.currentTimeMillis()%dic.size());
        int i=0;
        for(Queue<String> queue: dic.keySet()){
            if(i==nr) return queue;
            i++;
        }
        return null;
    }

    public void start(int N){
        while(true) {
            Queue<String> queue = getStart();
            if (N==0) break;
            for (String s:queue) System.out.print(s+" ");
            StringBuilder sbd=new StringBuilder();
            while (N!=0){
                //System.out.println(N);
                Queue<String> sub=new LinkedList<>(queue);
                ArrayList<String> arrayList = dic.get(sub);
                int nr = (int) (System.currentTimeMillis() % arrayList.size());
                String val = arrayList.get(nr);
                sbd.append(val).append(" ");
                sub.remove();
                sub.add(val);
                queue=sub;
                N--;
            }
            content=sbd.toString();
        }
    }
}