package ClassesDerivadas.Salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ClassesDerivadas.Ferramentas.Dinamite;
import ClassesDerivadas.Objetos.EstatuaHavan;

public class Havan extends Sala {
    private int contador = 0;

    public Havan() {
        super("Havan", 5);

        EstatuaHavan havan = new EstatuaHavan();
        ForoDeSP foro = new ForoDeSP();

        this.getObjetos().put("Estátua da Havan", havan);
        this.getPortas().put(foro.getNome(), foro);

    }

    public Havan(String nome, int repVisual) {
        super(nome, repVisual);
    }

    @Override
    public String textoDescricao() {
        StringBuilder descr = new StringBuilder();
        if (contador == 0) {
            descr.append("Você está na ").append(this.getNome()).append("\n");
            descr.append("\nAo seu redor há uma ").append(this.objetosDisponiveis().toString()).append("\n");
            descr.append("\nE portas para ").append(this.portasDisponiveis().toString()).append("\n");
            contador++;
        }
        return descr.toString();
    }

    @Override
    public int getImg(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);

        if (f instanceof Dinamite) {
            return 6;
        }
        return 5;
    }


    @Override
    public boolean usa(String ferramenta) {
        Ferramenta f = this.getMochila().usar(ferramenta);

        if (f == null) {
            return false;
        }
        if (f instanceof Dinamite) {
        	super.addVoto(15);
            return true;
        }
        return false;
    }   
}
