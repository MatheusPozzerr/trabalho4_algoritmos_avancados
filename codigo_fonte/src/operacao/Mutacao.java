package operacao;

import dominio.Individuo;
import dominio.Populacao;

import java.util.Random;

public class Mutacao {
    private static final Random random = new Random();

    public static void mutacao(Populacao populacaoIntermediaria) {
        int numeroTrocas = (int) (0.2 * populacaoIntermediaria.getTamPopulacao());
        for(int i = 0; i < numeroTrocas; i++){
            // Sorteia um individuo
            int individuoIndice = random.nextInt(populacaoIntermediaria.getTamPopulacao());
            Individuo individuoSelecionado = populacaoIntermediaria.getIndividuos()[individuoIndice];
            // Sorteia posicoes
            int posicao1 = random.nextInt(individuoSelecionado.getNumeroCidade().length);
            int posicao2 = random.nextInt(individuoSelecionado.getNumeroCidade().length);
            if (posicao2 == posicao1) {
                posicao2 = random.nextInt(individuoSelecionado.getNumeroCidade().length);
            }

            System.out.println("Cromossomo " + individuoIndice + " sofreu mutacao nas cargas de indices "
                    + posicao1 + " e " + posicao2);
            // Swap
            int aux = individuoSelecionado.getNumeroCidade()[posicao1];
            individuoSelecionado.getNumeroCidade()[posicao1] = individuoSelecionado.getNumeroCidade()[posicao2];
            individuoSelecionado.getNumeroCidade()[posicao2] = aux;
        }
    }
}
