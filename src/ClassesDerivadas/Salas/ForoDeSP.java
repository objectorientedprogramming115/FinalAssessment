package ClassesDerivadas.Salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.Ursinho;

public class ForoDeSP extends Sala {
    private int contador = 0;

    public ForoDeSP() {
        super("Foro",9);

        CristoRedentor cristo = new CristoRedentor();
        Ursinho urso = new Ursinho();
        
        this.getFerramentas().put(urso.getDescricao(), urso);
        this.getPortas().put(cristo.getNome(), cristo);
    }

    public ForoDeSP(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
        StringBuilder descr = new StringBuilder();
        if (contador == 0) {
            descr.append("Você está no ").append(this.getNome());
            descr.append("\nHá portas para ").append(this.portasDisponiveis().toString());
            descr.append("\nDe repente o Ciro Gomes aparece. \nEle te ofere um ").append(this.ferramentasDisponiveis().toString());
            descr.append("\nVocê pega? Ao pegar ou não influenciará nos seus votos");
            contador++;
        }
        return descr.toString();
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);
        StringBuilder descr = new StringBuilder();
        if (f instanceof Ursinho){
        	descr.append("Você aceitou o ursinho, seja bem-vindo à URSAL");
        	super.novoTexto(descr.toString());
            return 20;
        }
        return 9;
    }


    @Override
    public boolean usa(String ferramenta) {
        return false;
    }
    
    @Override
    public Sala sai(String porta) {
        return super.sai(porta);
    }
}
