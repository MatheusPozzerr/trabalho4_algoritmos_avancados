package dominio;

public class CalculaDistancia {

    public CalculaDistancia() {

    }

    public float calculaDistanciaCidades(Cidade cidadeOrigem, Cidade cidadeDestino) {
        return (float)(Math.sqrt(Math.pow(cidadeOrigem.getLatitude() - cidadeDestino.getLatitude(), 2)
                + Math.pow(cidadeOrigem.getLongitude() - cidadeDestino.getLongitude(), 2)));
    }
}
