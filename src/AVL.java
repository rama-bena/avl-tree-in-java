public class AVL {
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
            if(Math.abs(tinggi(temp.pKiri) - tinggi(temp.pKanan)) <=  1){
                temp.tinggi = Math.max(tinggi(temp.pKiri) , tinggi(temp.pKanan)) + 1;
            }
            // left left (outer)
            else if(tinggi(temp.pKiri) > tinggi(temp.pKanan) && tinggi(temp.pKiri.pKiri) >= tinggi(temp.pKiri.pKanan)){
                temp = putarKiri(temp);
            }
            // right right (outer)
            else if(tinggi(temp.pKanan) > tinggi(temp.pKiri) && tinggi(temp.pKanan.pKanan) >= tinggi(temp.pKanan.pKiri)){
                temp = putarKanan(temp);
            }
            //left right (inner)
            else if(tinggi(temp.pKiri) > tinggi(temp.pKanan) && tinggi(temp.pKiri.pKanan) > tinggi(temp.pKiri.pKiri)){
                Node temp2 = putarKanan(temp.pKiri);
                temp = putarKiri(temp2.pInduk);
            }

            // right left (inner)
            else if(tinggi(temp.pKanan) > tinggi(temp.pKiri) && tinggi(temp.pKanan.pKiri) > tinggi(temp.pKanan.pKanan)){
                Node temp2 = putarKiri(temp.pKanan);
                temp = putarKanan(temp2.pInduk);
            }
            temp = temp.pInduk;
        }
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
