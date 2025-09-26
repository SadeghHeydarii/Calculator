import javax.script.ScriptException;
import java.util.Arrays;

public class method {
    public static String[] calculate(String[] str, int size) throws ScriptException {

        String[] newStr = new String[50];

        // iterate on array
        for(int i=0; i<size; i++){
            // calculate multiply or divide of two number if find "x" or "/", set a new array and continue finding
            if(str[i].equals("x")){
                for(int j=0; j<i-1; j++){
                    newStr[j] = str[j];
                }
                newStr[i-1] = String.valueOf(Double.parseDouble(str[i-1])*Double.parseDouble(str[i+1]));
                for(int j=i; j<size-2; j++){
                    newStr[j] = str[j+2];
                }
                str = newStr.clone();
                Arrays.fill(newStr,null);
                size -= 2;
                i--;
            }
            else if(str[i].equals("/")){
                for(int j=0; j<i-1; j++){
                    newStr[j] = str[j];
                }
                newStr[i-1] = String.valueOf(Double.parseDouble(str[i-1])/Double.parseDouble(str[i+1]));
                for(int j=i; j<size-2; j++){
                    newStr[j] = str[j+2];
                }
                str = newStr.clone();
                Arrays.fill(newStr,null);
                size -= 2;
                i--;
            }
        }

        // iterate again on array
        for(int i=0; i<size; i++){
            // calculate sum or minus of two number if find "+" or "-", set a new array and continue finding
            if(str[i].equals("+")){
                for(int j=0; j<i-1; j++){
                    newStr[j] = str[j];
                }
                newStr[i-1] = String.valueOf(Double.parseDouble(str[i-1])+Double.parseDouble(str[i+1]));
                for(int j=i; j<size-2; j++){
                    newStr[j] = str[j+2];
                }
                str = newStr.clone();
                Arrays.fill(newStr,"");
                size -= 2;
                i--;
            }
            else if(str[i].equals("-")){
                for(int j=0; j<i-1; j++){
                    newStr[j] = str[j];
                }
                newStr[i-1] = String.valueOf(Double.parseDouble(str[i-1])-Double.parseDouble(str[i+1]));
                for(int j=i; j<size-2; j++){
                    newStr[j] = str[j+2];
                }
                str = newStr.clone();
                Arrays.fill(newStr,null);
                size -= 2;
                i--;
            }
        }
        // return result
        return str;
    }
}