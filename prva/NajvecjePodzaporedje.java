public class NajvecjePodzaporedje{
    int elementi [];
    int start;
    int end;
    int sum;
    public NajvecjePodzaporedje(int elementi[]){
        this.elementi = elementi;
    }

    public void grobaSila(){
        System.out.println("groba sila  ");
        int maxVsota = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;

        for(int i = 0; i != this.elementi.length; i++ ){
            int temp = 0;
            for(int j = i; j != this.elementi.length; j++){
                temp += this.elementi[j];
                if(maxVsota < temp){
                    maxVsota = temp;
                    start = i;
                    end = j;
                }
            }
        }
        this.start = start;
        this.end = end;
        this.sum = maxVsota;
    }

    public static int [] najvecjeZaporedje(int [] elementi, int zacetek, int konec){
        System.out.println("najvecje zaporedje");
        if ( zacetek == konec){
            return new int[]{zacetek, konec, elementi[zacetek]};
        }

        int sredina = (zacetek + konec) /2;    
        
        int [] levaStran = najvecjeZaporedje( elementi, zacetek, sredina );
        int [] desnaStran = najvecjeZaporedje( elementi, sredina+1, konec );
        int [] presek = najvecjeSredinskoZaporedje( elementi,zacetek, sredina, konec);
        
        if(levaStran[2] >= desnaStran[2] && levaStran[2] >=presek[2]){
            return levaStran;
        }else if(desnaStran[2] >= levaStran[2] && desnaStran[2] >= presek[2]){
            return desnaStran;
        }else{
            return presek;
        }

    }

    public static int[] najvecjeSredinskoZaporedje(int []elementi, int zacetek, int sredina, int konec){
        System.out.println("najvecje sredinsko");
        int maxLevaVsota = Integer.MIN_VALUE;
        int maxDesnaVsota = Integer.MIN_VALUE;
        int levaVsota = 0;
        int desnaVsota = 0;
        int leviKonec = sredina;
        int desniZacetek = sredina + 1;

        for (int i = sredina; i >= zacetek; i--) {
            levaVsota += elementi[i];
            if (levaVsota > maxLevaVsota) {
                maxLevaVsota = levaVsota;
                leviKonec = i;
            }
        }

        for (int i = sredina + 1; i <= konec; i++) {
            desnaVsota += elementi[i];
            if (desnaVsota > maxDesnaVsota) {
                maxDesnaVsota = desnaVsota;
                desniZacetek = i;
            }
        }

        return new int[]{leviKonec, desniZacetek, maxLevaVsota + maxDesnaVsota};
    }

    public void deliVladaj(){
        System.out.println("deli vladaj");
        int max[] = najvecjeZaporedje(elementi, 0, elementi.length-1);
        this.start = max[0];
        this.end = max[1];
        this.sum = max[2];
    }

    public void kadanovAlgoritem(){
        System.out.println("kadanov algoritem");
        int global = 0;
        int current = 0;
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for(int i = 0 ; i != elementi.length; i++){
            if(elementi[i] > elementi[i] + current){
                current = elementi[i];
                tempStart = i;
            }else{
                current += elementi[i];
            }

            if(current > global){
                global = current;
                start = tempStart;
                end = i;
            }
        }
        this.start = start;
        this.end = end;
        this.sum = global;

    }

    public void izpisiNajvecjePodzaporedje() {
        System.out.println(this.start + "," + this.end + "," + this.sum);
    }

}