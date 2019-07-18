package ClassesDerivadas.Salas;

import java.util.List;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Mochila;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.FaixaDePresidente;

public class SenadoDeBrasilia extends Sala {

    public SenadoDeBrasilia() {
        super("Senado", 15);
        FaixaDePresidente faixa = new FaixaDePresidente();
        this.getFerramentas().put(faixa.getDescricao(), faixa);
    }

    public SenadoDeBrasilia(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
    	verifica();
        StringBuilder descr = new StringBuilder();
        descr.append("\n Você está no ").append(this.getNome()).append("\n");
       
        if (super.getVotos() > 50) {
            descr.append("\n Parabéns você é o novo presidente com " + super.getVotos() + ", reivindique o que é de seu direito: a ")
                    .append(this.ferramentasDisponiveis().toString()).append(" Presidencial");
        }else {
        	descr.append("\n Você não alcançou seu objetivo de se tornar o pr�ximo presidente.");
        	descr.append("\n Agora você terá 4 anos de trevas até a próxima eleição!!!");
        	descr.append(super.getVotos());
        }
        return descr.toString();
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);
        if (f instanceof FaixaDePresidente) {
            return 16;
        }
        return 15;
    }


    @Override
    public boolean usa(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);
        if (f instanceof FaixaDePresidente) {
            return true;
        }
        return false;
    }

    @Override
    public Sala sai(String porta) {
        return super.sai(porta);
    }
    
    public void verifica() {
    	Mochila aux = super.getMochila();
    	List<String> conteudo = aux.inventario();
    	
    	if(conteudo.contains("Ursinho")) {
    		super.addVoto(8);
    	} else {
    		super.perdeVoto(10);
    	}
   
    	if(conteudo.contains("Megafone")) {
    		super.addVoto(8);
    	} else {
    		super.perdeVoto(7);
    	}
    	if(conteudo.contains("Bíblia")) {
    		super.addVoto(23);
    	}
    }
}
