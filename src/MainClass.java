import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        System.out.println("AVL Tree");
        AVL myAVL = new AVL();
        Scanner baca = new Scanner(System.in);
        int data;
        while (true){
            System.out.print("Masukkan data : ");
            data = baca.nextInt();
            myAVL.insertNode(data);
            System.out.printf("memasukkan %d\n", data); myAVL.tampilkan(myAVL.root);
        }
    }
}

