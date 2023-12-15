import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.lang.Math;

public class Sudoku {
    private char[] gridValues = new char[81];
    private char[] acceptedValues = {' ','1','2','3','4','5','6','7','8','9'};

    public Sudoku() {
        Arrays.fill(this.gridValues,' ');
    }

    private boolean rowCheck() {
        // check rows
        Integer n=0;
        Integer rowCounter=0;

        ArrayList<Character> rowHandled = new ArrayList<>();

        for (char val : this.gridValues) {
            String strVal = Character.toString(val);                

            if (rowHandled.contains(val)) {
                System.out.println("Row "+rowCounter+" has multiple "+val+"'s!");
                return false;
            }
            
            if (" ".equals(strVal) == false) {
                rowHandled.add(val);
            }

            if (n.equals(8)) {
                //System.out.print("Handling row " + rowCounter);
                //System.out.println();
                rowCounter +=1;
                n=0;
                rowHandled = new ArrayList<Character>();
            } else {
                n+=1;
            }
        }
        return true;
    }

    private boolean columnCheck() {
        int colCounter = 0;
        int index=0;

        while (colCounter < 9) {
            int colN=0;
            ArrayList<Character> col = new ArrayList<>();
            //System.out.println("Handling col " + colCounter);

            while (colN<9) {
                char cVal = this.gridValues[index];
                
                //System.out.println("Added " + cVal);

                if (col.contains(cVal)) {
                    System.out.println("Column "+colCounter+" has multiple "+cVal+"'s!");
                    return false;
                }

                if (Character.toString(cVal).equals(" ") == false) {
                    col.add(cVal);
                }

                index+=9;
                colN += 1;
            }

            colCounter +=1;
            index = colCounter;
        }
        return true;
    }

    private boolean gridCheck() {
        int gridN = 0;
        int index = 0;

        Integer[] increments = {0,1,2,9,10,11,18,19,20};
        while (gridN < 9) {
            ArrayList<Character> gridHandled = new ArrayList<Character>();
            //System.out.println("Handling grid starting at " + gridN);

            for (Integer inc : increments) {
                //System.out.println("Adding " + this.gridValues[index+inc]);
                gridHandled.add(this.gridValues[index+inc]);
            }

            Set<Character> gridSet = new HashSet<Character>();
        
            for(Character val : gridHandled) {
                if(gridSet.add(val) == false && Character.toString(val).equals(" ") == false) {
                    Integer rem = index%9;
                    Integer x = Math.floorDiv(index,9);
                    Integer y = rem;
                    
                    System.out.println("Block at ("+x+", "+y+") has multiple "+val+"'s!");
                    return false;
                }
            }

            gridN += 1;
            if (gridN % 3 == 0) {
                index += 21;
            } else {
                index += 3;
            }
            //System.out.println("Index set to " + index);
        }
        return true;
    }

    public void print() {
        Integer n = 0;
        Integer gridN = 0;
        Integer rCount = 0;

        String sepString = "#".repeat(4*9);
        String divString = "---+---+---#---+---+---#---+---+---#";

        System.out.print("#");
        System.out.println(sepString);
        System.out.print("#");

        for (char val : this.gridValues) {
            if (n.equals(9)) {
                if (rCount.equals(2)) {
                    System.out.print("#");
                    System.out.println();
                    System.out.print(sepString);
                    System.out.print("#");
                    
                    rCount = 0;
                } else {
                    System.out.println("#");
                    System.out.print("#");
                    System.out.print(divString);
                    rCount +=1;
                }
                System.out.println();

                //System.out.print("#");
                n=0;
            }
            if (gridN.equals(3) && n.equals(9) == false) {
                System.out.print("#");
                gridN = 0;
            } else if (n.equals(0) == false) {
                System.out.print("|");
            }
            System.out.print(" "+val+" ");
            gridN+=1;
            n+=1;
        }
        System.out.println("#");
        System.out.print("#");
        System.out.println(sepString);
    }

    public void set(int i, int j, char c) {
        Integer gridNum;

        if (0<i && i>8) {
            System.out.println("Trying to access illegal cell ("+i+", "+j+")!");
            return;
        } else if (0<j && j>8) {
            System.out.println("Trying to access illegal cell ("+i+", "+j+")!");
            return;
        }

        // String.valueOf(rowHandled).contains(strVal))
        String cStr = Character.toString(c);
        if (String.valueOf(this.acceptedValues).contains(cStr) == false) {
            System.out.println("Trying to set illegal character "+c+" to ("+i+", "+j+")!");
            return;
        }

        if (i==0) {
            gridNum = j;
        } else {
            gridNum = 9*i+j;
        }
        
        this.gridValues[gridNum] = c;
    
            
    }

    public boolean check() {
        if (rowCheck() == false || columnCheck() == false|| gridCheck() == false) {
            return false;
        }
        return true;
    }
}
