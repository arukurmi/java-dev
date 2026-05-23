package basics;
import java.util.*;

public class Main {
    public static void mainPrint(String[] args){
        for(int i = 0; i<args.length; i++){
            System.out.println(args[i]);
        }
    }

    public static void main(String[] args) {
        mainPrint(new String[]{"They", "not", "like", "us"});
    }
}