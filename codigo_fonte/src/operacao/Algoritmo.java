package operacao;

import dominio.CalculaDistancia;
import dominio.Cidade;
import dominio.Individuo;
import dominio.Populacao;

import java.util.*;
import java.util.Map.Entry;

public class Algoritmo {
    private static final Random random = new Random();
    private double taxaDeCrossover = 0.9;
    private double taxaDeMutacao = 0.1;
    private int numMaxGeracoes = 100;
    private int tamanhoPopulacao = 9;
    private boolean elitismo = true;
    private Cidade[] cidadesVovo;
    // private HashMap<Float, Integer> hash_map = new HashMap<Float, Integer>();

    public Algoritmo(Cidade[] cidadesVovo) {
        this.cidadesVovo = cidadesVovo;
    }

    public void executar() {
        int numGeracoes = 0;
        Populacao populacaoAtual = Populacao.geraPopulacao(tamanhoPopulacao, cidadesVovo.length);

        while(true) {
            for (Individuo individuo : populacaoAtual.getIndividuos()) {
                calculaAptidao(individuo);
            }
            printaPopulacao(populacaoAtual, numGeracoes, false);

            if(numGeracoes == numMaxGeracoes) {
                System.out.println("Numero maximo de geracoes foi alcancado");
                printaParesFinais(populacaoAtual);
                break;
            }
            if(numGeracoes != 0 && verificaConvergencia(populacaoAtual)) {
                System.out.println("Populacao atual alcancou convergencia de 95%");
                printaParesFinais(populacaoAtual);
                break;
            }

            Populacao populacaoIntermediaria = new Populacao(tamanhoPopulacao, cidadesVovo.length);

            if(elitismo) {
                System.out.println("----------Inicio elitismo------------");
                populacaoIntermediaria.getIndividuos()[0] = Selecao.elitismo(populacaoAtual);
                printaPopulacao(populacaoIntermediaria, numGeracoes, true);
                System.out.println("----------Fim elitismo------------");
            }

            int indiceCrossover = elitismo ? 1 : 0;
            int valorCorteCrossover = random.nextInt(100);
            if ((valorCorteCrossover / 100.0) < taxaDeCrossover) {
                List<Individuo> individuosDisponiveis = new ArrayList<>(Arrays.asList(populacaoAtual.getIndividuos()));
                System.out.println("----------Inicio torneios e crossovers------------");
                for (int i = indiceCrossover; i < populacaoIntermediaria.getIndividuos().length; i += 2) {
                    Individuo pai1 = Selecao.torneio(individuosDisponiveis);
                    Individuo pai2 = Selecao.torneio(individuosDisponiveis);
                    System.out.println("----------Fim torneio------------");
                    // crossover PBX
                    Individuo[] filhos = Crossover.PBX(pai1, pai2);
                    populacaoIntermediaria.getIndividuos()[i] = filhos[0];
                    populacaoIntermediaria.getIndividuos()[i + 1] = filhos[1];

                    printaPopulacao(populacaoIntermediaria, numGeracoes, true);
                    System.out.println("----------Fim crossover------------");
                }
            }
            else {
                System.out.println("----------Pulando etapa de crossover devido a taxa------------");
                for (int i = indiceCrossover; i < populacaoIntermediaria.getIndividuos().length; i++) {
                    populacaoIntermediaria.getIndividuos()[i] = populacaoAtual.getIndividuos()[i];
                }
                printaPopulacao(populacaoIntermediaria, numGeracoes, true);
                System.out.println("----------Fim selecao------------");
            }

            int valorCorteMutacao = random.nextInt(100);
            if ((valorCorteMutacao / 100.0) < taxaDeMutacao) {
                System.out.println("----------Inicio mutacao------------");
                Mutacao.mutacao(populacaoIntermediaria);
                printaPopulacao(populacaoIntermediaria, numGeracoes, true);
                System.out.println("----------Fim mutacao------------");
            }
            else {
                System.out.println("----------Pulando etapa de mutacao devido a taxa------------");
            }

            populacaoAtual = populacaoIntermediaria;
            numGeracoes++;
        }
    }

    private void calculaAptidao(Individuo individuo) {
        float distanciaCaminho = 0;
        CalculaDistancia calculaDistancia = new CalculaDistancia();
        for (int i = 0; individuo.getNumeroCidade().length < i; i++) {
            distanciaCaminho += calculaDistancia.calculaDistanciaCidades(cidadesVovo[individuo.getNumeroCidade()[i]],
                    cidadesVovo[individuo.getNumeroCidade()[i + 1]]);
        }
        distanciaCaminho += calculaDistancia.calculaDistanciaCidades(cidadesVovo[individuo.getNumeroCidade()[0]],
                cidadesVovo[individuo.getNumeroCidade()[individuo.getNumeroCidade().length - 1]]);
        individuo.setAptidao(distanciaCaminho);
    }

    // private boolean encontrouSolucao(Populacao populacao) {

    // for (int i = 0; i < populacao.getIndividuos().length; i++) {
    // hash_map.put(populacao.getIndividuos()[i].getAptidao(),
    // hash_map.get(populacao.getIndividuos()[i].getAptidao()) != null
    // ? hash_map.get(populacao.getIndividuos()[i].getAptidao()) + 1
    // : 1);
    // }
    // for (Entry<Float, Integer> entry : hash_map.entrySet()) {
    // if ((float) entry.getValue() / (float) populacao.getIndividuos().length >=
    // 0.9) {
    // return true;
    // }
    // }
    // return false;
    // }

    private boolean verificaConvergencia(Populacao populacao) {
        // Chave: resultado de aptidao
        // Valor: numero de individuos
        Map<Float, Integer> contagemPorAptidao = new HashMap<>();
        for (int i = 0; i < populacao.getIndividuos().length; i++) {
            float aptidaoAtual = (float) populacao.getIndividuos()[i].getAptidao();
            if (contagemPorAptidao.containsKey(aptidaoAtual)) {
                contagemPorAptidao.put(aptidaoAtual, contagemPorAptidao.get(aptidaoAtual) + 1);
            } else {
                contagemPorAptidao.putIfAbsent(aptidaoAtual, 1);
            }
        }

        Entry<Float, Integer> aptidaoConvergida = null;
        for (Entry<Float, Integer> entry : contagemPorAptidao.entrySet()) {
            Integer currentValue = entry.getValue();
            if (aptidaoConvergida == null || currentValue.compareTo(aptidaoConvergida.getValue()) > 0) {
                aptidaoConvergida = entry;
            }
        }

        float solucaoAptidao = Collections.min(contagemPorAptidao.keySet());

        return (aptidaoConvergida.getValue() >= ((int) (0.90 * populacao.getIndividuos().length))) &&
                (solucaoAptidao == aptidaoConvergida.getKey());
    }

    private void printaPopulacao(Populacao populacao, int numGeracoes, boolean intermediaria) {
        System.out.println();
        if (numGeracoes == 0)
            System.out.println("Populacao inicial:");
        else if (!intermediaria)
            System.out.println("Geracao " + numGeracoes + ":");
        for (int i = 0; i < populacao.getIndividuos().length; i++) {
            Individuo individuo = populacao.getIndividuos()[i];
            for (int j = 0; j < individuo.getNumeroCidade().length; j++) {
                System.out.print(individuo.getNumeroCidade()[j] + " ");
            }
            if (!intermediaria)
                System.out.println(" -> APTIDAO: " + individuo.getAptidao());
            else
                System.out.println();
        }
        System.out.println();
    }

    private void printaParesFinais(Populacao populacao) {
        int indiceMelhorAptidao = 0;
        for (int i = 1; i < populacao.getIndividuos().length; i++) {
            if (populacao.getIndividuos()[i].getAptidao() < populacao.getIndividuos()[indiceMelhorAptidao]
                    .getAptidao()) {
                indiceMelhorAptidao = i;
            }
        }

        int[] melhoresPares = populacao.getIndividuos()[indiceMelhorAptidao].getNumeroCidade();
        System.out.println("Pares:");
        for (int i = 0; i < melhoresPares.length; i++) {
            System.out.println("Aluno " + (i + 1) + " da manha forma par com aluno " + melhoresPares[i] + " da tarde");
        }
    }
}
