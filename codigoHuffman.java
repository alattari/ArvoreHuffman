import java.io.RandomAccessFile;
import java.util.PriorityQueue;

public class codigoHuffman {
    // entrada de um array com as frequencias, preenchida com os codigos de caracteres(prefixo)
    public static arvoreHuffman montaArvore(int[] charFreqs) {
        PriorityQueue<arvoreHuffman> arvore = new PriorityQueue<arvoreHuffman>();
        
        for (int i = 0; i < charFreqs.length; i++){// cria as folhas para cada caracter que aparece
            if (charFreqs[i] > 0)
                arvore.offer(new folhaHuffman(charFreqs[i], (char)i));//inserir elemento na lista de prioridade
        	
        } 
 
        assert arvore.size() > 0; //verificando
        
        while (arvore.size() > 1) {
        	// fica no loop ate ter 1 arvore sobrando
            // remove os nós com menor frequencia
            arvoreHuffman a = arvore.poll();
            arvoreHuffman b = arvore.poll();
            
            arvore.offer(new noHuffman(a, b));// coloca na arvore um novo nó e reinsere na fila
        }
        return arvore.poll();
    }
    
    public static void printaCodigo(arvoreHuffman arvore, StringBuffer prefixo) {
        assert arvore != null;
        if (arvore instanceof folhaHuffman) {
            folhaHuffman folha = (folhaHuffman)arvore;
 
            // printa o caracter, a frequencia, e o codigo dessa folha(o prefixo)
            System.out.println(folha.valor + "\t        " + folha.frequencia + "\t        " + prefixo);
 
        } else if (arvore instanceof noHuffman) {
            noHuffman no = (noHuffman)arvore;
 
            // percorrendo a esquerda
            prefixo.append('0');
            printaCodigo(no.esquerda, prefixo);
            prefixo.deleteCharAt(prefixo.length()-1);
 
            // percorrendo a direita
            prefixo.append('1');
            printaCodigo(no.direita, prefixo);
            prefixo.deleteCharAt(prefixo.length()-1);
        }
    }
    
    public static void main(String[] args) {
    	
		try{
		RandomAccessFile r = new RandomAccessFile("","r");//preencher com o diretorio
        int[] charFreqs = new int[256];
        
        while (r.length() > r.getFilePointer())// le cada caracter e guarda a frequencia 
        	{
        	
            charFreqs[r.read()] ++;
        
        // constroi arvore
        arvoreHuffman arvore = montaArvore(charFreqs);
            
        // printando
        System.out.println("Caracter\tFrequencia\tPrefixo");
        printaCodigo(arvore, new StringBuffer());
        }
	}catch(Exception e){
        e.printStackTrace();
        System.out.print(e.getLocalizedMessage());
}
    }
}
