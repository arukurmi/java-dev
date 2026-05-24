package games.printpatterns;

public class PatternDemo {
    public static void main(String[] args){
        PatternPrinter printer1 = new PatternPrinter(PatternEnum.NORMAL_STAIRS, 1);
        PatternPrinter printer2 = new PatternPrinter(PatternEnum.PYRAMID, 1);
        PatternPrinter printer3 = new PatternPrinter(PatternEnum.INVERTED_STAIRS, 1);
        PatternPrinter printer4 = new PatternPrinter(PatternEnum.NUMBERED_STAIRS, 1);
        PatternPrinter printer5 = new PatternPrinter(PatternEnum.NORMAL_STAIRS, 11);
        PatternPrinter printer6 = new PatternPrinter(PatternEnum.PYRAMID, 11);
        PatternPrinter printer7 = new PatternPrinter(PatternEnum.INVERTED_STAIRS, 11);
        PatternPrinter printer8 = new PatternPrinter(PatternEnum.NUMBERED_STAIRS, 11);
        PatternPrinter printer9 = new PatternPrinter(PatternEnum.NORMAL_STAIRS, 8);
        PatternPrinter printer10 = new PatternPrinter(PatternEnum.PYRAMID, 8);
        PatternPrinter printer11 = new PatternPrinter(PatternEnum.INVERTED_STAIRS, 8);
        PatternPrinter printer12 = new PatternPrinter(PatternEnum.NUMBERED_STAIRS, 8);

        printer1.printPattern();
        printer2.printPattern();
        printer3.printPattern();
        printer4.printPattern();
        printer5.printPattern();
        printer6.printPattern();
        printer7.printPattern();
        printer8.printPattern();
        printer9.printPattern();
        printer10.printPattern();
        printer11.printPattern();
        printer12.printPattern();
    }
}
