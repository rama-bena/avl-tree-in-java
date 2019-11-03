class AVL {
    Node root;
    AVL(){
        root = null;
    }

    void insertToAVL(int data){
        Node node = insertNode(data);
        rebalencing(node);
    }

    private Node insertNode(int data){
        Node input = new Node(data);
        Node temp = root;
        Node prev = null;
        if(root == null){
            root = input;
            return root;
        }
        while(temp != null){
            prev = temp;
            if(input.data < temp.data)
                temp = temp.pKiri;
            else
                temp = temp.pKanan;
        }
        input.pInduk = prev;

        if(input.data < prev.data ) prev.pKiri = input;
        else prev.pKanan = input;
        return input;
    }

    private void rebalencing(Node node){
        while(node != null){
            if(keseimbangan(node).equals("imbang")){
                memberiTinggi(node);
            }
            else if(keseimbangan(node).equals("left left")){
                node = putarKiri(node);
            }
            else if(keseimbangan(node).equals("right right")){
                node = putarKanan(node);
            }
            else if(keseimbangan(node).equals("left right")){
                Node temp2 = putarKanan(node.pKiri);
                node = putarKiri(temp2.pInduk);
            }
            else if(keseimbangan(node).equals("right left")){
                Node temp2 = putarKiri(node.pKanan);
                node = putarKanan(temp2.pInduk);
            }
            node = node.pInduk;
        }
    }

    private String keseimbangan(Node node){
        if(Math.abs(tinggi(node.pKiri) - tinggi(node.pKanan)) <=  1)
            return "imbang";
        if(tinggi(node.pKiri) > tinggi(node.pKanan) && tinggi(node.pKiri.pKiri) >= tinggi(node.pKiri.pKanan))
            return "left left";
        if(tinggi(node.pKanan) > tinggi(node.pKiri) && tinggi(node.pKanan.pKanan) >= tinggi(node.pKanan.pKiri))
            return "right right";
        if(tinggi(node.pKiri) > tinggi(node.pKanan) && tinggi(node.pKiri.pKanan) > tinggi(node.pKiri.pKiri))
            return "left right";
        if(tinggi(node.pKanan) > tinggi(node.pKiri) && tinggi(node.pKanan.pKiri) > tinggi(node.pKanan.pKanan))
            return "right left";

        return "gagal";
    }

    private Node putarKiri(Node n1){
        Node parrent = n1.pInduk;
        Node n2 = n1.pKiri;

        n1.pKiri = n2.pKanan;
        if(n2.pKanan != null) n2.pKanan.pInduk = n1;

        n2.pKanan = n1;
        n1.pInduk = n2;

        n2.pInduk = parrent;
        if(parrent == null) root = n2;
        else if(parrent.pKiri == n1) parrent.pKiri = n2;
        else parrent.pKanan = n2;

        memberiTinggi(n1);
        memberiTinggi(n2);
        return n2;
    }

    private Node putarKanan(Node n1){
        Node parrent = n1.pInduk;
        Node n2 = n1.pKanan;

        n1.pKanan = n2.pKiri;
        if(n2.pKiri != null)
            n2.pKiri.pInduk = n1;

        n2.pKiri = n1;
        n1.pInduk = n2;

        n2.pInduk = parrent;
        if(parrent == null) root = n2;
        else if(parrent.pKiri == n1) parrent.pKiri = n2;
        else parrent.pKanan = n2;

        memberiTinggi(n1);
        memberiTinggi(n2);
        return n2;
    }

    private void memberiTinggi(Node node){
        node.tinggi = Math.max(tinggi(node.pKiri) , tinggi(node.pKanan)) + 1;
    }

    private int tinggi(Node input){
        if(input == null) return 0;
        return input.tinggi;
    }

    void deleteInAVL(int data){
        Node node = SearchData(data);
        Node prev;
        if(node == null) return;
        if(tinggi(node) == 1){
            prev = deleteInLeaf(node);
        }
        else if(haveOneChild(node))
            prev = deleteOneChild(node);
        else
            prev = deleteTwoChild(node);

        rebalencing(prev);
    }

    private boolean haveOneChild(Node node){
        return (node.pKiri == null) ^ (node.pKanan == null);
    }

    private Node deleteTwoChild(Node node) {
        Node pengganti = nodeTerbesarDiSubTreeKiri(node);
        Node prev;
        if(haveOneChild(pengganti)){
            prev = deleteOneChild(pengganti);
            node.data = pengganti.data;
        }
        else{
            prev = deleteInLeaf(pengganti);
            node.data = pengganti.data;
        }
        return prev;
    }

    private Node nodeTerbesarDiSubTreeKiri(Node node){
        node = node.pKiri;
        while(node.pKanan != null) node = node.pKanan;
        return node;
    }

    private Node deleteOneChild(Node node) {
        Node parrent = node.pInduk;
        Node child = (node.pKiri != null) ? node.pKiri : node.pKanan;
        if(node == root){
            child.pInduk = null;
            root = child;
        }
        else{
            if(parrent.pKanan == node) parrent.pKanan = child;
            else parrent.pKiri = child;

            child.pInduk = parrent;
        }
        return parrent;
    }

    private Node deleteInLeaf(Node node){
        Node parrent = node.pInduk;
        if(root == node) root = null;
        else{
            if(node.pInduk.pKiri == node) node.pInduk.pKiri = null;
            else node.pInduk.pKanan = null;
        }
        return parrent;
    }

    private Node SearchData(int data){
        Node temp = root;
        while(temp != null){
            if(temp.data == data) return temp;
            temp = (data > temp.data) ? temp.pKanan : temp.pKiri;
        }
        return null;
    }

    void tampilkan(Node input) {
        if (input == null) return;
        System.out.printf("%d : ", input.data);
        if (input.pKiri != null)
            System.out.printf("kiri=%d ", input.pKiri.data);
        if (input.pKanan != null)
            System.out.printf("kanan=%d ", input.pKanan.data);
        System.out.println();
        tampilkan(input.pKiri);
        tampilkan(input.pKanan);
    }
}
