package ClassesDerivadas.Salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.Dinamite;
import ClassesDerivadas.Ferramentas.Foice;
import ClassesDerivadas.Ferramentas.Lampiao;
import ClassesDerivadas.Objetos.Florestas;

public class Monte extends Sala {
    private int contador = 0;
    private boolean luz;

    public Monte() {
        super("Monte", 1);
        this.contador = 0;

        luz = false;
        Foice foice = new Foice();
        Lampiao lampiao = new Lampiao();
        Florestas floresta = new Florestas();

        this.getObjetos().put("Floresta", floresta);
        this.getFerramentas().put(foice.getDescricao(), foice);
        this.getFerramentas().put(lampiao.getDescricao(), lampiao);
    }

    public Monte(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
        StringBuilder descr = new StringBuilder();
        if (contador == 0) {
            descr.append("Você é o Cabo Daciolo, e após um encontro com Jesus");
            descr.append("\nGloria a Deux irmãos!");
            descr.append("\nVocê não recuperou total consciência");
            descr.append("\nVocê sabe que está no ").append(this.getNome());
            descr.append("\nApenas se lembra do seu sonho, onde há uma voz que diz");
            descr.append("\nque você precisa ser o próximo presidente do Brasil");
            descr.append("\nGloria a Deux!");
            descr.append("\nAo seu redor há: ").append(this.ferramentasDisponiveis().toString());
            descr.append("\nAlém de uma ").append(this.objetosDisponiveis().toString());
            contador++;
        } else {
            if (portasDisponiveis().size() > 0 && getDescricaoObjeto()) {
                descr.append("\nVeja, você abriu caminho para ").append(this.portasDisponiveis().toString());
            }
        }

        return descr.toString();
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);


        if (f instanceof Lampiao) {
            luz = true;
            return 2;
        }

        if (f instanceof Foice) {
            if (luz)
                return 3;

            return 2;
        }

        if (f instanceof Dinamite)
            return 17;


        return 1;
    }

    @Override
    public boolean usa(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);

        if (f == null) {
            return false;
        }
        if (f instanceof Lampiao) {
            luz = true;
            return true;
        }
        if (f instanceof Foice) {
            if (luz) {
                Florestas fl = (Florestas) this.getObjetos().get("Floresta");
                fl.setAcaoOk(true);

                CasaDoDaciolo casa = new CasaDoDaciolo();
                this.getPortas().put(casa.getNome(), casa);

                Dinamite dinamite = new Dinamite();
                this.getFerramentas().put(dinamite.getDescricao(), dinamite);

                return true;
            }
        }
        return false;
    }

    private boolean getDescricaoObjeto() {
        return this.getObjetos().get("Floresta").getDescricao().equals("Caminho na floresta aberto");
    }
}
