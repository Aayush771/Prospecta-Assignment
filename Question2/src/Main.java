import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("input.csv"));
        Map<String,String> map = new LinkedHashMap<>();
        while ((line = br.readLine()) != null)   //returns a Boolean value
        {
            String[] employee = line.replaceAll(" ","").split(",");    // use comma as separator

            for(String i:employee){
                String[] str = i.split(":");
                map.put(str[0],str[1]);
            }
            for(String j:map.keySet()){
                String str = map.get(j);
                if(str.contains("=")){
                    String str1 =  str.replace("=","").replaceAll(" ","");

                    String[] str2 =  str1.split("\\+");
                    if(!map.containsKey(str2[0]) && !map.containsKey(str2[1])){
                        int value = Integer.parseInt(str2[0])+Integer.parseInt(str2[1]);
                        map.put(j,value+"");
                    }
                    else if(map.containsKey(str2[0]) && map.containsKey(str2[1])){
                        String a = map.get(str2[0]);
                        String b = map.get(str2[1]);
                        int value = Integer.parseInt(a)+Integer.parseInt(b);
                        map.put(j,value+"");
                    }
                    else if(map.containsKey(str2[1]) && !map.containsKey(str2[0])){
                        String a = map.get(str2[1]);
                        int value = Integer.parseInt(a)+Integer.parseInt(str2[0]);
                        map.put(j,value+"");
                    }
                }
            }

        }
        File csvFile = new File("output.csv");
        PrintWriter printWriter = new PrintWriter(csvFile);
        for (String data : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(data+":");
            sb.append(map.get(data));
            sb.append(",");
            printWriter.write(sb.toString());
        }
        printWriter.close();
    }
}
/*
1.How will you tackle the challenge above?
 To solve this problem first I need to read the data from csv file then with
 the use of Maps in java i will store all the data in map the i will resolve all
 dependencies of value in map then with the help of printwriter in java i will
 create the new output file and print the output in that csv file.

2.What type of errors you would you check for?
there are mainly two types of runtime exception
1.File not found exception
2.IO exception

3.How might a user break your code?
this code can be broken when data is given in random order.


*/