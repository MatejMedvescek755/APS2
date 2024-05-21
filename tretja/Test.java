public class Test {
    public static void main(String[] args) {
        ZgoscenaTabela ht = new ZgoscenaTabela(11, 13, 5, 7);

        ht.vstavi(19);
        ht.vstavi(11);
        ht.vstavi(23);
        ht.vstavi(19);
        ht.vstavi(29);
        ht.vstavi(17);
        ht.vstavi(2);
        ht.vstavi(33);
        ht.vstavi(99);
        ht.vstavi(129);
        ht.brisi(11);
        ht.vstavi(17);
        ht.brisi(17);
        ht.vstavi(2);
        ht.brisi(2);
        ht.vstavi(11);
        ht.izpisi();
        ht.steviloSovpadanj();
        System.out.println("--");
        System.out.println(ht.najdi(129));
        System.out.println("--");
        ht.brisi(129);
        System.out.println(ht.najdi(129));
        ht.steviloSovpadanj();
        System.out.println("--");
        ht.vstavi(119);
        ht.vstavi(211);
        ht.vstavi(123);
        ht.vstavi(159);
        ht.vstavi(129);
        ht.vstavi(217);
        ht.vstavi(12);
        ht.vstavi(233);
        ht.vstavi(299);
        ht.vstavi(429);
        ht.izpisi();
        System.out.println("--");
        ht.steviloSovpadanj();
    }
}

class ZgoscenaTabela{
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
        int i = 0;
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
                sovpadanja++;
                i++;
                index = altZgoscevalnaFunkcija(zgoscevalnaFunkcija(k), i);
                if (i == m) {
                    resize();
                    kvadratnoPrenaslavljanje(k); // After resizing, re-insert the element
                    sovpadanja++;
                    return;
                }
            }
        }
    }
    

    public void resize(){
        m = 2 * m + 1;
        int[] newArray = new int[m];
    
        // Copy elements from the old array to the new array
        for (int value : array) {
            if (value != 0) {
                int index = zgoscevalnaFunkcija(value);
                int i = 0;
                while (newArray[index] != 0) {
                    i++;
                    index = altZgoscevalnaFunkcija(zgoscevalnaFunkcija(value), i);
                }
                newArray[index] = value;
            }
        }
        array = newArray;
    }


}