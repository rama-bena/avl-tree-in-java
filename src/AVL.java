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
                temp = putarKiri(temp);
            }

            // right left (inner)
            else if(tinggi(temp.pKanan) > tinggi(temp.pKiri) && tinggi(temp.pKanan.pKiri) > tinggi(temp.pKanan.pKanan)){
                Node temp2 = putarKiri(temp.pKanan);
                temp = putarKanan(temp);
            }
            temp = temp.pInduk;
        }
    }

    private Node putarKiri(Node input){
        Node parrent = input.pInduk;
        Node k1 = input;
        Node k2 = input.pKiri;

        k1.pKiri = k2.pKanan;
        if(k2.pKanan != null) k2.pKanan.pInduk = k1;

        k2.pKanan = k1;
        k1.pInduk = k2;

        k2.pInduk = parrent;
        if(parrent == null)
            root = k2;
        else if(parrent.pKiri == k1)
            parrent.pKiri = k2;
        else
            parrent.pKanan = k2;

        hitungTinggi(k1);
        hitungTinggi(k2);
        return k2;
    }

    private Node putarKanan(Node input){
        Node parrent = input.pInduk;
        Node k1 = input;
        Node k2 = input.pKanan;

        k1.pKanan = k2.pKiri;
        if(k2.pKiri != null)
            k2.pKiri.pInduk = k1;

        k2.pKiri = k1;
        k1.pInduk = k2;

        k2.pInduk = parrent;
        return k2;
    }

    void hitungTinggi(Node input){
        input.tinggi = Math.max(tinggi(input.pKiri) , tinggi(input.pKanan)) + 1;
    }

    int tinggi(Node input){
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
