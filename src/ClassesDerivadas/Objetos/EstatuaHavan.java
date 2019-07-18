package ClassesDerivadas.Objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;

public class EstatuaHavan extends Objeto {

    public EstatuaHavan() {
        super("Estátua inteira", "Estátua destruída");
    }

    @Override
    public boolean usar(Ferramenta f) {
        return false;
    }

    @Override
    public boolean isAcaoOk() {
        return super.isAcaoOk();
    }

    @Override
    public void setAcaoOk(boolean acaoOk) {
        super.setAcaoOk(acaoOk);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao();
    }
}
