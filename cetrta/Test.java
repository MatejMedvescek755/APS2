class DvojiskoDrevo {
    Node start = null;
    int pojavnost[] = new int[5000];
    public DvojiskoDrevo(){}

    void vstavi(int kljuc) {
        start = insert(start, kljuc);
        if (pojavnost[kljuc] == 0) {
            pojavnost[kljuc] = 1;
        }else{
            pojavnost[kljuc]++;
        }
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    void brisi(int kljuc) {
        if (pojavnost[kljuc] > 1) {
            pojavnost[kljuc]--;
            System.out.println("true");
        } else {
            if (delete(start, kljuc) != null) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.key = minValue(node.right);
            node.right = delete(node.right, node.key);
        }
        return node;
    }

    private int minValue(Node node) {
        int minv = node.key;
        while (node.left != null) {
            minv = node.left.key;
            node = node.left;
        }
        return minv;
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
        System.out.print(n.key+"/"+pojavnost[n.key]+" | ");
        inorder(n.right);
    }
    
    public void preOrder(Node n){
        if (n == null) {
            return;
        }
        System.out.print(n.key+"/"+pojavnost[n.key]+" | ");
        preOrder(n.left);
        preOrder(n.right);
    }

    public void postOrder(Node n){
        if (n == null) {
            return;
        }
        postOrder(n.left);
        postOrder(n.right);
        System.out.print(n.key+"/"+pojavnost[n.key]+" | ");
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

    public Node(int item){
        key = item;
        left = right = null;
    }
    public void addLeft(Node left){
        this.left = left;
    }

    public void addRight(Node right){
        this.right = right;
    }
    
}