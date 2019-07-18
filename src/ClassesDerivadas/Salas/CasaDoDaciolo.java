package ClassesDerivadas.Salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.Biblia;
import ClassesDerivadas.Ferramentas.Megafone;

public class CasaDoDaciolo extends Sala {
    private int contador = 0;

    public CasaDoDaciolo() {
        super("Casa", 4);
        Biblia biblia = new Biblia();
        Megafone megafone = new Megafone();

        IgrejaEvangelica igreja = new IgrejaEvangelica();
        Havan lojaHavan = new Havan();

        this.getFerramentas().put(biblia.getDescricao(), biblia);
        this.getFerramentas().put(megafone.getDescricao(), megafone);

        this.getPortas().put(igreja.getNome(), igreja);
        this.getPortas().put(lojaHavan.getNome(), lojaHavan);
    }

    public CasaDoDaciolo(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
        StringBuilder descr = new StringBuilder();
        if (contador == 0) {
            descr.append("Você está na sua casa.");
            descr.append("\nHá apenas uma ferramenta por perto: a Bíblia");
            descr.append("\nE portas para ").append(this.portasDisponiveis().toString());
            descr.append("\nEspere...");
            descr.append("\nmasoq?");
            descr.append("\nInesperadamente, você encontra Boulos e ele, gentilmente, oferece-lhe um Megafone");
            descr.append("\nAceita o Megafone? Se aceitar ou rejeitar influenciará nos seus votos");
            contador++;
        }
        return descr.toString();
    }


    @Override
    public boolean usa(String ferramenta) {
        return false;
    }

    @Override
    public Sala sai(String porta) {
        return super.sai(porta);
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);
        StringBuilder descr = new StringBuilder();
        if (f instanceof Megafone) {
            descr.append("\nSábia escolha padawan Daciolo");
            descr.append("\nCom essa ferramenta poderosa, massas ouvirão a palavra de Deux!");
            super.novoTexto(descr.toString());
            return 19;
        }
        return 4;
    }

}
