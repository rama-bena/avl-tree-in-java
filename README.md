### Mencoba membuat AVL tree yang rapi

- using intellij idea
- using java 1.8

## Node.java
- atribut :

int data, tinggi;

Node pKiri,pKanan, pInduk;

- method : 

Node();

Node(int);

## AVL.java
- Atribut :

Node root;

- Method :

AVL();

void insertToAVL(int data);

void delteInAVL(int data);

void searchInAVL(int data);

void tampilkan(Node input);

private Node insertNode(int data);

private void rebalencing(Node node);

private String keseimbangan(Node node);

private Node putarKiri(Node n1);

private Node putarKanan(Node n1);

private void memberiTinggi(Node node);

private int cekTinggi(Node input);

private boolean haveOneChild(Node node);

private Node deleteTwoChild(Node node);

private Node nodeTerbesarDiSubTreeKiri(Node node);

private Node deleteOneChild(Node node);

private Node deleteInLeaf(Node node);

private Node SearchData(int data);

## MainClass.java
terdapat main classnya