class AVL {
    Node root;
    AVL(){
        root = null;
    }

    void insertNode(int dt){
        Node input = new Node(dt);
        Node temp = root;
        Node prev = null;
        if(root == null){
            root = input;
            return;
        }

        while(temp != null){
            prev = temp;
            if(input.data < temp.data)
                temp = temp.pKiri;
            else
                temp = temp.pKanan;
        }
        input.pInduk = prev;

        if(input.data < prev.data )
            prev.pKiri = input;
        else
            prev.pKanan = input;

        temp = input;
        while(temp != null){
            if(keseimbangan(temp).equals("imbang")){
                hitungTinggi(temp);
            }
            else if(keseimbangan(temp).equals("left left")){
                temp = putarKiri(temp);
            }
            else if(keseimbangan(temp).equals("right right")){
                temp = putarKanan(temp);
            }
            else if(keseimbangan(temp).equals("left right")){
                Node temp2 = putarKanan(temp.pKiri);
                temp = putarKiri(temp2.pInduk);
            }
            else if(keseimbangan(temp).equals("right left")){
                Node temp2 = putarKiri(temp.pKanan);
                temp = putarKanan(temp2.pInduk);
            }
            temp = temp.pInduk;
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
        if(parrent == null)
            root = n2;
        else if(parrent.pKiri == n1)
            parrent.pKiri = n2;
        else
            parrent.pKanan = n2;

        hitungTinggi(n1);
        hitungTinggi(n2);
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
        return n2;
    }

    private void hitungTinggi(Node input){
        input.tinggi = Math.max(tinggi(input.pKiri) , tinggi(input.pKanan)) + 1;
    }

    private int tinggi(Node input){
        if(input == null) return 0;
        return input.tinggi;
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
