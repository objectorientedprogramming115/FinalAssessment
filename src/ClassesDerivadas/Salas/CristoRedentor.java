package ClassesDerivadas.Salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.Dinamite;
import ClassesDerivadas.Ferramentas.Lampiao;
import ClassesDerivadas.Objetos.EstatuaCristo;

public class CristoRedentor extends Sala {
    private int contador = 0;

    public CristoRedentor() {
        super("Cristo", 10);
        EstatuaCristo cristo = new EstatuaCristo();
        this.getObjetos().put("Cristo Redentor", cristo);  
        SalaDeDebate debate = new SalaDeDebate();
        this.getPortas().put(debate.getNome(), debate);
    }

    public CristoRedentor(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
        StringBuilder descr = new StringBuilder();
        if (contador == 0) {
            descr.append("Você está no ").append(this.getNome()).append("\n");
            descr.append("\n Parece meio escuro por aqui");
            contador++;
        } else {
            descr.append("\nHá portas para ").append(this.portasDisponiveis().toString());
            
        }
        return descr.toString();
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);

        if (f instanceof Lampiao) {
            return 11;
        }
        return 10;
    }

    @Override
    public boolean usa(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);
        StringBuilder aux = new StringBuilder();
        if (f == null) {
            return false;
        }
        if (f instanceof Lampiao) {
        	aux.append("Veja, um caminho se abriu para ").append(this.portasDisponiveis().toString());
        	super.novoTexto(aux.toString());
            return true;
        }
        if (f instanceof Dinamite) {
        	perdeVoto(15);
        	return true;
        }

        return false;
    }

    @Override
    public Sala sai(String porta) {
        return super.sai(porta);
    }
    
    @Override
    public void perdeVoto(int qtd) {
    	super.perdeVoto(qtd);
    }
}
