package dominio;

public class Individuo {
    int[] numeroCidade;
    float aptidao;

    public Individuo(int quantidadeCidades){
        this.numeroCidade = new int[quantidadeCidades];
    }

    public int[] getNumeroCidade() {
        return numeroCidade;
    }

    public void setNumeroCidade(int[] numeroCidade) {
        this.numeroCidade = numeroCidade;
    }

    public float getAptidao() {
        return aptidao;
    }

    public void setAptidao(float aptidao) {
        this.aptidao = aptidao;
    }

    
}
