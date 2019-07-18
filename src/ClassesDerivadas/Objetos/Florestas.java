package ClassesDerivadas.Objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;
import ClassesDerivadas.Ferramentas.Foice;

public class Florestas extends Objeto {
    public Florestas() {
        super("Floresta muito densa", "Caminho na floresta aberto");
    }

    @Override
    public boolean usar(Ferramenta f) {
        if(f instanceof Foice) {
           return true;
        }
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
