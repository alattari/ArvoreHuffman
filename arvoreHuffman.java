abstract class arvoreHuffman implements Comparable<arvoreHuffman> {
    public final int frequencia; // frequencia da arvore
    public arvoreHuffman(int freq) { 
    	frequencia = freq; 
    	}
 
    // comparando as frequencias
    public int compareTo(arvoreHuffman arvore) {
        return frequencia - arvore.frequencia;
    }
}
