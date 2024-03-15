package Data_Models;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {
    private HashMap<Integer, Float> polinom; // de forma <grad, total_coef>
    private int ordin;
    public Polinom(){
        polinom = new HashMap<Integer, Float>();
        ordin = 0;
    }
    public Polinom(String P){
        polinom = new HashMap<Integer, Float>();
        ordin = 0;
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+(\\.\\d+)?[xX]\\^\\d+)|" +
                "(?:\\d+(\\.\\d+)?[xX])|" +
                "(?:\\d+(\\.\\d+)?)|" +
                "(?:[xX]\\^\\d+)|" +
                "(?:[xX])))");
        Matcher matcher = pattern.matcher(P);
        while (matcher.find()) {
            Monom monom = Monom.buildMonom(matcher.group(1));
            addTerm(monom);
        }
    }

    public HashMap<Integer, Float> getPolinom() {
        return polinom;
    }

    public int getOrdin() {
        return ordin;
    }

    public void setPolinom(HashMap<Integer, Float> polinom) {
        this.polinom = polinom;
    }

    public void setOrdin(int ordin) {
        this.ordin = ordin;
    }

    @Override
    public String toString() {
        return "Polinom{" +
                "polinom=" + polinom +
                '}';
    }

    public void addTerm(Monom monom) {
        Integer grad = monom.getGrad();
        Float coef = monom.getCoeficient();
        int ok = -1;
        for (Integer i : polinom.keySet()) {
            if (i == grad)
                ok = i; // in ok retinem gradul, daca acesta coincide cu cel al monomului dat
            // sau -1 daca monomul dat are un grad care nu exista
            if (i > ordin)
                ordin = i;
        }
        if (ok == -1) {
            polinom.put(grad, coef);
            if (grad > ordin)
                ordin = grad;
        } else {
            Float var = polinom.get(ok);
            var += coef;
            polinom.remove(ok);
            polinom.put(ok, var);
        }
    }
}