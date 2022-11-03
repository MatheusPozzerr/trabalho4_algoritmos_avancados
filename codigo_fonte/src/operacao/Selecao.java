package operacao;

import dominio.Individuo;
import dominio.Populacao;

import java.util.List;
import java.util.Random;

public class Selecao {
    private static final Random random = new Random();

    public static Individuo elitismo(Populacao populacao) {
        Individuo melhor = null;
        float menorAptidao = -1;
        for(int i=0; i < populacao.getIndividuos().length; i++){
            if((melhor == null) || populacao.getIndividuos()[i].getAptidao() < menorAptidao) {
                melhor = populacao.getIndividuos()[i];
                menorAptidao = populacao.getIndividuos()[i].getAptidao();
            }
        }
        Individuo escolhido = new Individuo(melhor.getNumeroCidade().length);
        escolhido.setNumeroCidade(melhor.getNumeroCidade().clone());
        return escolhido;
    }

    public static Individuo torneio(List<Individuo> individuosDisponiveis) {
        int aleatorio1 = random.nextInt(individuosDisponiveis.size());
        Individuo opcao1 = individuosDisponiveis.remove(aleatorio1);
        if(individuosDisponiveis.size() == 0) return opcao1;
        int aleatorio2 = random.nextInt(individuosDisponiveis.size());
        Individuo opcao2 = individuosDisponiveis.remove(aleatorio2);

        if(opcao1.getAptidao() < opcao2.getAptidao()) {
            individuosDisponiveis.add(opcao2);
            return opcao1;
        } else {
            individuosDisponiveis.add(opcao1);
            return opcao2;
        }
    }
}
