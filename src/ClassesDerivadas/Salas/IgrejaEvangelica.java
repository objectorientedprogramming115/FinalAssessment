package ClassesDerivadas.Salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.Biblia;


public class IgrejaEvangelica extends Sala {
    private int contador = 0;

    public IgrejaEvangelica() {
        super("Igreja", 7);

        CristoRedentor cristo = new CristoRedentor();
        this.getPortas().put(cristo.getNome(), cristo);

    }

    public IgrejaEvangelica(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
        StringBuilder descr = new StringBuilder();
        if (contador == 0) {
            descr.append("Você está na ").append(this.getNome()).append("\n");
            descr.append("\nHá uma imensa quantidade de fiéis a te escutar.").append("\n");
            descr.append("\nE há portas para ").append(this.portasDisponiveis().toString()).append("\n");
            contador++;
        }
        return descr.toString();
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);

        if (f instanceof Biblia) {
            return 8;
        }
        return 7;
    }


    @Override
    public boolean usa(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);
        StringBuilder descr = new StringBuilder();
        if (f instanceof Biblia) {
            descr.append("\nVocê converteu mais fiéis para votar. GLÓRIA A DEUXX!!!");
            super.novoTexto(descr.toString());
            super.addVoto(45);
            return true;
        }
        return false;
    }

    @Override
    public Sala sai(String porta) {
        return super.sai(porta);
    }
}
