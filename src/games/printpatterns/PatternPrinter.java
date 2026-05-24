package games.printpatterns;


public class PatternPrinter {
    private final int size;
    private final PatternEnum pattern;

    public PatternPrinter(PatternEnum pattern, int size){
        if(pattern == null){
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if(size < 1){
            throw new IllegalArgumentException("Size should be > 1");
        }
        this.pattern = pattern;
        this.size = size;
    }

    public void printPattern() {
        switch(this.pattern){
            case NORMAL_STAIRS:
                this.printNormalStairs();
                break;
            case PYRAMID:
                this.printPyramid();
                break;
            case INVERTED_STAIRS:
                this.printInvertedStairs();
                break;
            case NUMBERED_STAIRS:
                this.printNumberedStairs();
                break;
        }
        return;
    }

    private void printNormalStairs(){
        for(int i=1; i<=this.size; i++){
            for(int j=0; j<i; j++){
                System.out.print('*');
            }
            System.out.println();
        }
    }

    private void printPyramid(){
        for(int i=1; i<=this.size; i++){
            for(int j=0; j<this.size - i; j++){
                System.out.print(' ');
            }
            for(int j=0; j<2*i-1; j++){
                System.out.print('*');
            }
            System.out.println();
        }
    }

    private void printInvertedStairs(){
        for(int i=this.size; i>=1; i--){
            for(int j=0; j<i; j++){
                System.out.print('*');
            }
            System.out.println();
        }
    }

    private void printNumberedStairs(){
        for(int i=1; i<=this.size; i++){
            for(int j=1; j<=i; j++){
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
