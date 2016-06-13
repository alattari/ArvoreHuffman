package huffmanTeste;

class folhaHuffman extends arvoreHuffman {
    public final char valor; // valor da folha
 
    public folhaHuffman(int freq, char val) {
        super(freq);
        valor = val;
    }
}
