package GUI;
import Data_Models.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class Model {
    private Polinom polP1;
    private Polinom polP2;
    private Polinom Rez;
    private Polinom Cat;

    public Model(){
        Rez = new Polinom();
        Cat = new Polinom();
    }
    public void setModel(String P1, String P2){
        polP1 = new Polinom(P1);
        polP2 = new Polinom(P2);
    }
    public static String polinomToString(Polinom P){
        String exp = new String();
        for(Integer i: P.getPolinom().keySet()){
            Float coef = P.getPolinom().get(i);
            if(coef > 0) exp += "+";
            if(coef == 1.0) exp += "";
            if(coef == -1.0 && i > 0) exp += "-";
            if(i.equals(0)){
                if(coef == 0.0)
                    exp += "";
                else exp += Float.toString(coef);
            } else if(i.equals(1)){
                if(coef!=1.0 && coef != -1.0 && coef != 0.0)
                    exp += Float.toString(coef);
                if(coef != 0.0) exp += "x";
            } else {
                if(coef != 1.0 && coef != -1.0 && coef != 0.0)
                    exp += Float.toString(coef);
                if (coef != 0.0) exp += "x^" + Integer.toString(i);
            }
        }
        return exp;
    }
    public String Adunare(){
        Rez.getPolinom().clear(); Rez.setOrdin(0);
        for(Integer term: polP1.getPolinom().keySet()){
            Monom monom = new Monom(polP1.getPolinom().get(term), term );
            Rez.addTerm(monom);
        }
        for(Integer term: polP2.getPolinom().keySet()){
            Monom monom = new Monom(polP2.getPolinom().get(term), term );
            Rez.addTerm(monom);
        }
        return polinomToString(Rez);
    }
    public String Scadere(){
        Rez.getPolinom().clear(); Rez.setOrdin(0);
        for(Integer term: polP1.getPolinom().keySet()){
            Monom monom = new Monom(polP1.getPolinom().get(term), term );
            Rez.addTerm(monom);
        }
        for(Integer term: polP2.getPolinom().keySet()){
            Monom monom = new Monom((-1)*polP2.getPolinom().get(term), term );
            Rez.addTerm(monom);
        }
        return polinomToString(Rez);
    }
    public String Inmultire(){
        Rez.getPolinom().clear(); Rez.setOrdin(0);
        for(Integer i: polP1.getPolinom().keySet()){
            for(Integer j: polP2.getPolinom().keySet()){
                Monom monom = new Monom(polP1.getPolinom().get(i)*polP2.getPolinom().get(j), i+j);
                Rez.addTerm(monom);
            }
        }
        return polinomToString(Rez);
    }
    public static Polinom ScadereAsPol(Polinom first, Polinom second){
        Polinom Res = new Polinom();
        Res.getPolinom().clear(); Res.setOrdin(0);
        for(Integer term: first.getPolinom().keySet()){
            Monom monom = new Monom(first.getPolinom().get(term), term );
            Res.addTerm(monom);
        }
        for(Integer term: second.getPolinom().keySet()){
            Monom monom = new Monom((-1)*second.getPolinom().get(term), term );
            Res.addTerm(monom);
        }
        return Res;
    }
    private static Polinom InmultireAsPol(Polinom first, Polinom second){
        Polinom Res = new Polinom();
        Res.getPolinom().clear(); Res.setOrdin(0);
        for(Integer i: first.getPolinom().keySet()){
            for(Integer j: second.getPolinom().keySet()){
                Monom monom = new Monom(first.getPolinom().get(i)*second.getPolinom().get(j), i+j);
                Res.addTerm(monom);
            }
        }
        return Res;
    }
    public String Impartire(){
        Rez.getPolinom().clear(); Rez.setOrdin(0); // vom folosi Rez pe post de polinom pentru Rest
        Cat.getPolinom().clear(); Cat.setOrdin(0);
        int ordMax1 = polP1.getOrdin();
        int ordMax2 = polP2.getOrdin();
        if(ordMax2 == 0)
            if(ordMax1 == 0)
                return "Eroare 2";
            else return "Eroare";
        while( ordMax2 <= ordMax1 ){
            Monom monom = new Monom(polP1.getPolinom().get(ordMax1) / polP2.getPolinom().get(ordMax2),
                    ordMax1 - ordMax2);
            Cat.addTerm(monom);
            Polinom aux = new Polinom();
            aux.getPolinom().clear();
            aux.addTerm(monom);
            Rez = InmultireAsPol(polP2, aux);
            polP1.setPolinom(ScadereAsPol(polP1, Rez).getPolinom());

            ordMax1--;
        }
        return "Cât: "+ polinomToString(Cat) + " || Rest: " + polinomToString(polP1);
    }
    public String Integrare(){
        Rez.getPolinom().clear(); Rez.setOrdin(0);
        for(Integer i: polP1.getPolinom().keySet()){
            Monom monom = new Monom(polP1.getPolinom().get(i)/(i+1), i+1 );
            Rez.addTerm(monom);
        }
        return polinomToString(Rez);
    }
    public String Derivare(){
        Rez.getPolinom().clear(); Rez.setOrdin(0);
        Monom monom;
        boolean constante = false, variabile = false;
        for(Integer i: polP1.getPolinom().keySet()){
            if(i > 0) {
                monom = new Monom(polP1.getPolinom().get(i) * i, i - 1);
                variabile = true;
            }
            else {
                monom = new Monom(0.0f, 0);
                constante = true;
            }
            Rez.addTerm(monom);
        }
        if(true == constante && false == variabile)
            return "0";
        return polinomToString(Rez);
    }
}
