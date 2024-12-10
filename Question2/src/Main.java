import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        

          Map<String,String> map = readCSVFile("Question2\\input.csv");
       
            for(String j:map.keySet()){
                String str = map.get(j);
                if(str.contains("=")){
                    String str1 =  str.replace("=","").replaceAll(" ","");

                    String[] str2 = str1.split("[+\\-*/]");

                    
                    if(!map.containsKey(str2[0]) && !map.containsKey(str2[1])){
                        int a = Integer.parseInt(str2[0]);
                        int b = Integer.parseInt(str2[1]);
                        int value = evaluateFormula(a,b,str1);
                        map.put(j,value+"");
                    }
                    else if(map.containsKey(str2[0]) && map.containsKey(str2[1])){
                        Integer a = Integer.parseInt(map.get(str2[0]));

                        Integer b = Integer.parseInt(map.get(str2[1]));
                        int value =evaluateFormula(a,b,str1);
                        map.put(j,value+"");
                    }
                    else if(map.containsKey(str2[1]) && !map.containsKey(str2[0])){
                        Integer a = Integer.parseInt(map.get(str2[1]));
                        Integer b = Integer.parseInt(str2[0]);
                        int value = evaluateFormula(b,a,str1);
                        map.put(j,value+"");
                    }
                }
            

        }

  //      System.out.println(map);

        writeCSVFile(map, "Question2\\output.csv");
        
    }

    public static Map<String, String> readCSVFile(String filePath) throws IOException {
        Map<String, String> spreadsheet = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 1;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                char clmLtr = 'A';

                for (String value : values) {
                    String cellId = clmLtr + String.valueOf(row);
                    spreadsheet.put(cellId, value.trim());
                    clmLtr++;
                }
                row++;
            }
        }
        return spreadsheet;
    }
    public static Integer evaluateFormula(Integer a, Integer b, String formula) {
      
             Integer result = 0;
         
            if (formula.contains("-")) {
                result = a - b;                
            } else if (formula.contains("+")) {
                result = a + b;
            } else if (formula.contains("*")) {
                result = a * b;            
            } else if (formula.contains("/")) {
                result = a / b;               
            }
        

        return result;
    }

    public static void writeCSVFile(Map<String, String> csvMap, String filePath) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : csvMap.entrySet()) {
                String cellId = entry.getKey();
                String value = entry.getValue();
                pw.println(cellId + "," + value);
            }
        }
    }
}
