public class ZgoscenaTabela{
    int p,m,c1,c2;
    int array[];
    int sovpadanja = 0;
    public ZgoscenaTabela(int p, int m, int c1, int c2){
        this.p = p;
        this.m = m;
        this.c1 = c1;
        this.c2 = c2;
        this.array = new int[m];
        
    }

    public boolean najdi(int k){
        for (int i : array) {
            if ( i == k ) return true;
        }
        return false;
    }

    public void brisi(int k){
        for (int i = 0; i < array.length; i++) {
            if ( array[i] == k) {
                array[i] = 0;
            }
        }
    }

    public void izpisi(){
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) System.out.println(i+": "+array[i]);
        }
    }

    public void steviloSovpadanj(){
        System.out.println(sovpadanja);
    }

    public void vstavi(int kljuc){
        int index = zgoscevalnaFunkcija(kljuc);
        if(array[index] == 0){
            array[index] = kljuc;
        }else if (array[index] != kljuc) {
            kvadratnoPrenaslavljanje(kljuc);
        }  
    }

    public int zgoscevalnaFunkcija(int k){
        // (k * p) mod m
        k = (k * this.p) % this.m;
        return k;
    }

    public int altZgoscevalnaFunkcija(int value, int i){
        value = (value + this.c1 * i + c2 * (i*i))%m;
        return value;
    }

    public void kvadratnoPrenaslavljanje(int k){
        int i = 0;
        int index = altZgoscevalnaFunkcija(zgoscevalnaFunkcija(k), i);

        boolean inserted = false;
        
        while (!inserted) {
            if (array[index] == k || array[index] == 0) {
                inserted = true;
                array[index] = k;
            } else {
                i++;
                index = altZgoscevalnaFunkcija(zgoscevalnaFunkcija(k), i);
                sovpadanja++;
                if (i == m) {
                    resize();
                    kvadratnoPrenaslavljanje(k);
                    return;
                }
            }
        }
    }

    public void resize(){
        m = 2 * m + 1;
        int[] newArray = new int[m];
    
        for (int value : array) {
            if (value != 0) {
                int index = zgoscevalnaFunkcija(value);
                int i = 0;
                while (newArray[index] != 0 ) {
                    i++;
                    sovpadanja++;
                    index = altZgoscevalnaFunkcija(zgoscevalnaFunkcija(value), i);
                }
                newArray[index] = value;
            }
        }
        array = newArray;
    }
}