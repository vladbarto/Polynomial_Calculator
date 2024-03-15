package Data_Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monom {
    private Float coeficient;
    private Integer grad;

    public Monom(Float coeficient, Integer grad) {
        this.coeficient = coeficient;
        this.grad = grad;
    }
    public static Monom buildMonom(String element) {
        String C = new String(); // C - string pentru coeficient
        String G = new String(); // G - string pentru grad
        int pozitiaLuiX = element.indexOf("X");
        if (-1 == pozitiaLuiX) {
            pozitiaLuiX = element.indexOf("x");
        }
        if (-1 == pozitiaLuiX) {
            G = "0";
        } else {
            int pozitiaPuterii = element.indexOf("^");
            if (!G.equals("0") && -1 == pozitiaPuterii) {
                G = "1";
            } else {
                G = element.substring(pozitiaPuterii + 1, element.length());
            }
        }
        Pattern p = Pattern.compile( "([+-]?\\d*\\.?\\d*)[xX]?\\^?\\d*");
        Matcher m = p.matcher( element );
        if (m.find()) {
            C = m.group(1);
            if(C.equals("+")) C = "1.0";
            if(C.equals("-")) C = "-1.0";
            if(C=="") C = "1.0";
        }
        else C = element;

        Monom monom = new Monom(Float.parseFloat(C), Integer.parseInt(G));
        return monom;
    }

    public Float getCoeficient(){
        return coeficient;
    }
    public Integer getGrad(){
        return grad;
    }
}
