import java.util.Arrays;
import java.util.Scanner;

public class Parameters {
    public static void main(String args[]) {

      Scanner scanner = new Scanner(System.in);

      String[] longInputArray = new String[9999];
      int count = 0;

      while (true) {
        String input = scanner.nextLine();

        if (input.isEmpty()) {
          break;
        }
        
        longInputArray[count] = input;
        count++;
      }
      scanner.close();
      String[] inputArray = new String[count];
      for(int i = 0;i < count;i++) {
        inputArray[i] = longInputArray[i];
      }

      Integer longestArg=0;


        for (String arg : inputArray) {
            if (arg != null && arg.length() > longestArg) {
                longestArg = arg.length();
            }
        }
       
        Arrays.sort(inputArray);

        Integer rowNum = inputArray.length+1;

        Integer indexColWidth = (Integer.toString(rowNum).length())+2;
        Integer cntColWidth = longestArg+2;

         String div = String.format(
            "#%s+%s#",
            "-".repeat(indexColWidth),                
            "-".repeat(cntColWidth)
        );


        // roof
        System.out.println("#".repeat(indexColWidth+cntColWidth+3));

        Integer i = 0;
        for (String rowContent : inputArray) {
          if (rowContent != null) {
            i += 1;

            Integer iSpacerLen = indexColWidth-(Integer.toString(i).length());
            Integer cntSpacerLen = cntColWidth-(rowContent.length());

            String indexColSpacer = " ".repeat(iSpacerLen-1);
            String cntColSpacer = " ".repeat(cntSpacerLen-1);

            String row = String.format(
                "#%s%d | %s%s#",
                indexColSpacer,
                i,
                rowContent,
                cntColSpacer
            );
            System.out.println(row);
            
            if ( i.equals(rowNum-1) ) {
                continue;
            }
            System.out.println(div);
        }
        }
        //floor
        System.out.println("#".repeat(indexColWidth+cntColWidth+3));
    }
}

