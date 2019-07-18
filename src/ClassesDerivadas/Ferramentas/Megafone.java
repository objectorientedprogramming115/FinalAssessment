package ClassesDerivadas.Ferramentas;

import ClassesBasicas.Ferramenta;
import ExcessoesDerivadas.EntradaIlegal;

public class Megafone extends Ferramenta {
    private String estado;

    public Megafone() {
        super("Megafone");
        this.estado = "DESLIGADO";
    }

    @Override
    public String getDescricao() {
        return super.getDescricao();
    }

    @Override
    public boolean usar() {
        return super.usar();
    }

    public void setEstado(String estado) {
        if (!estado.equals("LIGADO") && !estado.equals("DESLIGADO"))
            throw new EntradaIlegal("Operação inválida");

        if (estado.equals(this.estado))
            throw new EntradaIlegal("O megafone já se econtra nesse estado!");

        if (estado.equals("LIGADO"))
            this.estado = "LIGADO";
        else if (estado.equals("DESLIGADO"))
            this.estado = "DESLIGADO";
    }

    public String getEstado() {
        return (estado.equals("LIGADO")) ? "LIGADO" : "DESLIGADO";
    }
}
