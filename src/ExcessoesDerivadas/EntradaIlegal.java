package ExcessoesDerivadas;

public class EntradaIlegal extends RuntimeException {
    public EntradaIlegal(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
