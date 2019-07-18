package ClassesDerivadas.Salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.Megafone;
import ClassesDerivadas.Ferramentas.Ursinho;

public class SalaDeDebate extends Sala {

    public SalaDeDebate() {
        super("Debate", 12);
        SenadoDeBrasilia senado = new SenadoDeBrasilia();
        this.getPortas().put(senado.getNome(), senado);
    }

    public SalaDeDebate(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
        StringBuilder descr = new StringBuilder();
            descr.append("Você está na ").append(this.getNome()).append("\n");
            descr.append("E está debatendo com seu principal adversário. \n");
            descr.append("Ninguém está prestando atenção nas suas conspiraçõees, o que fazer? \n");
            descr.append("\nHá portas para ").append(this.portasDisponiveis().toString());


        return descr.toString();
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);

        if (f instanceof Megafone) {
            return 14;
        }
        if (f instanceof Ursinho) {
            return 13;
        }
        return 12;
    }


    @Override
    public boolean usa(String ferramenta) {
    	StringBuilder descr = new StringBuilder();
        Ferramenta f = this.getMochila().usar(ferramenta);
        if (f == null) {
            return false;
        }
        if (f instanceof Megafone) { 
        	descr.append("\nParabénns! Pela força do argumento você derrotou o seu adversário! ");
    		descr.append("\nFINISH HIM!!!");
    		super.novoTexto(descr.toString());
            
            super.addVoto(10);
            return true;
        }
        if (f instanceof Ursinho) {
        	descr.append("\nCom o poder da mãe Rússia, você acabou com o fantasma do autoritarismo");
            descr.append("\nO oponente não assola mais o Brasil, e o país está livre");
            super.novoTexto(descr.toString());
            
            
            super.addVoto(10);
            return true;
        }
        return false;
    }

    @Override
    public Sala sai(String porta) {
        return super.sai(porta);
    } 
    		
}
