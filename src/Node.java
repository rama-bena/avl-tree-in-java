class Node {
    int data;
    int tinggi;
    Node pKiri;
    Node pKanan;
    Node pInduk;

    Node(){
        data = tinggi = 0;
        pKanan = pKiri = pInduk = null;
    }
    Node(int data){
        this.data = data;
        tinggi = 1;
        pKanan = pKiri = pInduk = null;
    }
}
