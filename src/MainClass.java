import java.util.Scanner;

class MainClass {
    public static void main(String[] args) {
        System.out.println("AVL Tree");
        System.out.println("----------");
        System.out.println();
        AVL myAVL = new AVL();
        Scanner baca = new Scanner(System.in);
        while (true){
            System.out.println("Menu : ");
            System.out.printf("1. Insert\n").printf("2. Delete\n").printf("Masukkan pilihan anda : ");
            int pilihan = baca.nextInt();
            System.out.println();
            int data;

            switch (pilihan){
                case 1 :
                    System.out.printf("Masukkan data yg ingin di insert : ");
                    data = baca.nextInt();
                    myAVL.insertToAVL(data);
                    break;
                case 2 :
                    System.out.printf("Masukkan data yg ingin di delete : ");
                    data = baca.nextInt();
                    myAVL.deleteInAVL(data);
                    break;
                default:
                    return;
            }
            System.out.println("\n\nTampilan Tree : ");
            myAVL.tampilkan(myAVL.root);
            System.out.println();
        }
    }

}

