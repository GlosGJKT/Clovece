import java.awt.*;

public class BarvaFigurky {

    int start;
    int cil;

    BarvaFigurky(int start, int pocetPoli){
        this.start = start;
        int cil = start - 1;
        if (start==0) {cil = pocetPoli - 1;}
    }

    public int getStart(){
        return start;
    }

    public int getCil(){
        return cil;
    }
}
