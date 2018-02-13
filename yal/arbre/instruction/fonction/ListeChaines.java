package yal.arbre.instruction.fonction;

import java.util.ArrayList;

public class ListeChaines {

    public static ListeChaines Instance;

    private ArrayList<String> stringList;

    public static ListeChaines getInstance() {
        if (Instance == null ){

            Instance = new ListeChaines();
        }
        return Instance;
    }

    private ListeChaines() {
        this.stringList = new ArrayList<>();
    }

    public void add(String s){
        stringList.add(s);
    }

    public int size(){
        return stringList.size();
    }

    public String at(int x){
        return stringList.get(x);
    }

    public int position(String s){
        return stringList.indexOf(s);
    }
}
