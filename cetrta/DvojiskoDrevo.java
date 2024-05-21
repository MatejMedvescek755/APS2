public class DvojiskoDrevo {
    Node start = null;
    public DvojiskoDrevo(){}
    boolean brisanje = false;
    
    void vstavi(int kljuc) {
        start = vstaviRekurzivno(start, kljuc);
    }

    private Node vstaviRekurzivno(Node parent, int kljuc) {
        if (parent == null) {
            return new Node(kljuc);
        }
        if (kljuc < parent.key) {
            parent.left = vstaviRekurzivno(parent.left, kljuc);
        } else if (kljuc > parent.key) {
            parent.right = vstaviRekurzivno(parent.right, kljuc);
        } else {
            parent.pojavnost++;
        }
        return parent;
    }

    void brisi(int kljuc) {
        start = brisiRekurzivno(start, kljuc);
        System.out.println(brisanje+"");
    }

    private Node brisiRekurzivno(Node parent, int kljuc) {
        if (parent == null) {
            brisanje = false;
            return null;
        }
        if (kljuc < parent.key) {
            parent.left = brisiRekurzivno(parent.left, kljuc);
        } else if (kljuc > parent.key) {
            parent.right = brisiRekurzivno(parent.right, kljuc);
        } else {
            if (parent.pojavnost > 1) {
                parent.pojavnost--;
            } else {
                if (parent.left == null) {
                    return parent.right;
                } else if (parent.right == null) {
                    return parent.left;
                }
                parent.key = najdiMax(parent.left);
                parent.left = brisiRekurzivno(parent.left, parent.key);
            }
        }
        brisanje = true;
        return parent;
    }

    private int najdiMax(Node parent) {
        int max = parent.key;
        while (parent.right != null) {
            max = parent.right.key;
            parent = parent.right;
        }
        return max;
    }


    void najdi(int kljuc) {
        if (start == null) {
            System.out.println("//");
            return;
        }
        
        Node result = search(start, kljuc);
        
        System.out.println(printPath(start, kljuc));
        
    }
    
    private Node search(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
    
        if (key < node.key) {
            return search(node.left, key);
        }
        return search(node.right, key);
    }
    
    private String printPath(Node node, int key) {
        String path = "";
        Node current = node;
        while (current != null && current.key != key) {
            path += current.key + " -> ";
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            path += "//";
        } else {
            path += current.key;
        }
        return path;
    }

    public void premiPregledIzpis(){
        if (start == null) {
            System.out.println("empty");
        }else{
            preOrder(start);
        }
        System.out.println();
    }

    public void vmesniPregledIzpis(){
        if (start == null) {
            System.out.println("empty");
        }else{
            inorder(start);
        }
        System.out.println();
    }

    public void obratniPregledIzpis(){
        if (start == null) {
            System.out.println("empty");
        }else{
            postOrder(start);
        }
        System.out.println();
    }

    public void inorder(Node n){
        if(n == null){
            return;
        }
        inorder(n.left);
        System.out.print(n.key+"/"+n.pojavnost+" | ");
        inorder(n.right);
    }
    
    public void preOrder(Node n){
        if (n == null) {
            return;
        }
        System.out.print(n.key+"/"+n.pojavnost+" | ");
        preOrder(n.left);
        preOrder(n.right);
    }

    public void postOrder(Node n){
        if (n == null) {
            return;
        }
        postOrder(n.left);
        postOrder(n.right);
        System.out.print(n.key+"/"+n.pojavnost+" | ");
    }

    void rotacijaDesno(int kljuc) {
        start = rotateRight(start, kljuc);
    }

    private Node rotateRight(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = rotateRight(node.left, key);
        } else if (key > node.key) {
            node.right = rotateRight(node.right, key);
        } else {
            Node newRoot = node.left;
            node.left = newRoot.right;
            newRoot.right = node;
            return newRoot;
        }
        return node;
    }

    public void rotacijaLevo(int kljuc) {
        Node parent = null;
        Node current = start;
        while (current != null && current.key != kljuc) {
            parent = current;
            if (kljuc < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) {
            return;
        }
        leftRotate(parent, current);
    }

    private void leftRotate(Node parent, Node node) {
        Node pivot = node.right;
        Node pivotLeft = pivot.left;
    
        pivot.left = node;
        node.right = pivotLeft;
    
        if (parent != null) {
            if (parent.left == node) {
                parent.left = pivot;
            } else {
                parent.right = pivot;
            }
        } else {
            start = pivot;
        }
    }


}

class Node{
    int key;
    Node left, right;
    int pojavnost;

    public Node(int item){
        key = item;
        left = right = null;
        pojavnost = 1;
    }
    public void addLeft(Node left){
        this.left = left;
    }

    public void addRight(Node right){
        this.right = right;

    }
    
}