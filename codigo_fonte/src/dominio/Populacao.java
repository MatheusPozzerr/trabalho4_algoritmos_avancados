package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Populacao {
    private int tamPopulacao;
    private int n;
    private Individuo[] individuos;
    

    public Populacao(int tamPop, int n) {
        this.tamPopulacao = tamPop;
        this.n = n;
        this.individuos = instanciaIndividuos();
    }


    public int getTamPopulacao() {
        return tamPopulacao;
    }

    public void setTamPopulacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }

    public void setIndividuos(Individuo[] individuos) {
        this.individuos = individuos;
    }

    private Individuo[] instanciaIndividuos() {
        Individuo[] individuos = new Individuo[tamPopulacao];
        for(int i=0; i<individuos.length; i++) {
            individuos[i] = new Individuo(n);
        }
        return individuos;
    }

    public static Populacao geraPopulacao(int tamPop, int n) {
        Populacao populacao = new Populacao(tamPop, n);
        for(Individuo individuo : populacao.individuos) {
            List<Integer> listaCidades = new ArrayList<>();
            for(int i=0; i < n; i++) {
                listaCidades.add(i);
            }
            for(int i=0; i < individuo.getNumeroCidade().length; i++) {
                Random random = new Random();
                int alunoIndex = random.nextInt(listaCidades.size());
                individuo.getNumeroCidade()[i] = listaCidades.remove(alunoIndex);
            }
        }
        return populacao;
    }
}
