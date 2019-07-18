package ClassesDerivadas.Ferramentas;

import ClassesBasicas.Ferramenta;
import ExcessoesDerivadas.EntradaIlegal;

public class Dinamite extends Ferramenta {
    private int quantidade = 0;

    public Dinamite() {
        super("Dinamite"); 
        this.quantidade++;
    }


    public int getQuantidade() {
        return this.quantidade;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao();
    }

    public boolean usar() {
    	if(quantidade<1) {
    		throw new EntradaIlegal("Não é possível pegar quantidades negativas!!!");
    	}else {
    		this.quantidade--;
    		return super.usar();
    	}
    }
}

//    public void setQuantidade(int quantidade) {
//        if (quantidade <= 0)
//            throw new EntradaIlegal("Não é possível pegar quantidades negativas!!!");
//
//        this.quantidade = quantidade;
//    }