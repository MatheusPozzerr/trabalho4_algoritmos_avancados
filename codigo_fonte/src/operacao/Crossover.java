package operacao;

import dominio.Individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crossover {
    private static final Random random = new Random();

    public static Individuo[] PBX(Individuo pai1, Individuo pai2) {
        int numPosicoes = pai1.getNumeroCidade().length / 2;
        List<Integer> indicesSelecionados = new ArrayList<>();
        int[] cidadesFilho1 = new int[pai1.getNumeroCidade().length];
        int[] cidadesFilho2 = new int[pai1.getNumeroCidade().length];

        for(int i=0; i < numPosicoes; i++) {
            int proxPosicao = random.nextInt(pai1.getNumeroCidade().length);
            while(indicesSelecionados.contains(proxPosicao)) {
                proxPosicao = random.nextInt(pai1.getNumeroCidade().length);
            }
            cidadesFilho1[proxPosicao] = pai2.getNumeroCidade()[proxPosicao];
            cidadesFilho2[proxPosicao] = pai1.getNumeroCidade()[proxPosicao];

            indicesSelecionados.add(proxPosicao);
        }

        int indicePai1 = 0, indicePai2 = 0;
        int indiceFilho1 = 0, indiceFilho2 = 0;

        while(indiceFilho1 < cidadesFilho1.length && indicePai1 < pai1.getNumeroCidade().length) {
            if(cidadesFilho1[indiceFilho1] != 0) { // se posicao do filho ja foi preenchida antes
                indiceFilho1++;
                continue;
            }
            int valorAtualPai1 = pai1.getNumeroCidade()[indicePai1];
            if(filhoTemElementoDoPai(cidadesFilho1, valorAtualPai1)) {
                indicePai1++;
                continue;
            }
            cidadesFilho1[indiceFilho1] = valorAtualPai1;
            indicePai1++;
            indiceFilho1++;
        }

        while(indiceFilho2 < cidadesFilho2.length && indicePai2 < pai2.getNumeroCidade().length) {
            if(cidadesFilho2[indiceFilho2] != 0){ // se posicao do filho ja foi preenchida antes
                indiceFilho2++;
                continue;
            }
            int valorAtualPai2 = pai2.getNumeroCidade()[indicePai2];
            if(filhoTemElementoDoPai(cidadesFilho2, valorAtualPai2)) {
                indicePai2 ++;
                continue;
            }
            cidadesFilho2[indiceFilho2] = valorAtualPai2;
            indicePai2++;
            indiceFilho2++;
        }

        Individuo novoIndividuo1 = new Individuo(pai1.getNumeroCidade().length);
        novoIndividuo1.setNumeroCidade(cidadesFilho1);
        Individuo novoIndividuo2 = new Individuo(pai1.getNumeroCidade().length);
        novoIndividuo2.setNumeroCidade(cidadesFilho2);

        return new Individuo[]{novoIndividuo1, novoIndividuo2};
    }

    private static boolean filhoTemElementoDoPai(int[] filho, int elementoPai) {
        for(int i=0; i < filho.length; i++) {
            if(filho[i] == elementoPai) return true;
        }
        return false;
    }
}
