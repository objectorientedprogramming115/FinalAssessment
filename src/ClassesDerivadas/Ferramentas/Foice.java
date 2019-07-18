package ClassesDerivadas.Ferramentas;

import ClassesBasicas.Ferramenta;

public class Foice extends Ferramenta {

    public Foice() {
        super("Foice");
    }

    public String getDescricao() {
        return super.getDescricao();
    }

    public boolean usar() {
        return super.usar();
    }
}
