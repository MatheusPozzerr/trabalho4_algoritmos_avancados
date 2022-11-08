package operacao;

import dominio.CalculaDistancia;
import dominio.Cidade;
import dominio.Individuo;

import java.util.*;

public class Algoritmo {
    private static final Random random = new Random();
    private int numMaxGeracoes = 1000000000;
    private Cidade[] cidadesVovo;
    private float melhorAptidao = Float.MAX_VALUE;
    private int contadorConvergencia = 0;

    public Algoritmo(Cidade[] cidadesVovo) {
        this.cidadesVovo = cidadesVovo;
    }

    public void executar() {
        int numGeracoes = 0;
        Individuo individuo = geraIndividuo(cidadesVovo.length);


        while (true) {
            calculaAptidao(individuo);            

            if (numGeracoes == numMaxGeracoes) {
                System.out.println("Numero maximo de geracoes foi alcancado");
                printaCidadesFinais(individuo);
                break;
            }

            individuo = this.mutacao(individuo, numGeracoes);
            numGeracoes++;
        }
    }

    private void calculaAptidao(Individuo individuo) {
        float distanciaCaminho = 0;
        CalculaDistancia calculaDistancia = new CalculaDistancia();
        for (int i = 0; individuo.getNumeroCidade().length > i; i++) {
            if (i == individuo.getNumeroCidade().length - 1) {
                distanciaCaminho += calculaDistancia.calculaDistanciaCidades(
                        cidadesVovo[individuo.getNumeroCidade()[0]],
                        cidadesVovo[individuo.getNumeroCidade()[individuo.getNumeroCidade().length - 1]]);
            } else {
                distanciaCaminho = calculaDistancia.calculaDistanciaCidades(cidadesVovo[individuo.getNumeroCidade()[i]],
                        cidadesVovo[individuo.getNumeroCidade()[i + 1]]) + distanciaCaminho;
            }
        }
        individuo.setAptidao(distanciaCaminho);
    }

    private Individuo geraIndividuo(int n) {
        Individuo individuoInicial = new Individuo(n);
        List<Integer> listaCidades = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listaCidades.add(i);
        }
        for (int i = 0; i < individuoInicial.getNumeroCidade().length; i++) {
            Random random = new Random();
            int alunoIndex = random.nextInt(listaCidades.size());
            individuoInicial.getNumeroCidade()[i] = listaCidades.remove(alunoIndex);
        }

        return individuoInicial;
    }

    private Individuo mutacao(Individuo individuo, int numGeracoes) {
        Individuo verificaMutacao = new Individuo(individuo.getNumeroCidade().length);
        for(int i = 0; i <individuo.getNumeroCidade().length; i++){
            verificaMutacao.getNumeroCidade()[i] = individuo.getNumeroCidade()[i];
        }
        int posicao1 = random.nextInt(verificaMutacao.getNumeroCidade().length);
        int posicao2 = random.nextInt(verificaMutacao.getNumeroCidade().length);
        if (posicao2 == posicao1) {
            posicao2 = random.nextInt(verificaMutacao.getNumeroCidade().length);
        }
        int aux = verificaMutacao.getNumeroCidade()[posicao1];
        verificaMutacao.getNumeroCidade()[posicao1] = verificaMutacao.getNumeroCidade()[posicao2];
        verificaMutacao.getNumeroCidade()[posicao2] = aux;
        calculaAptidao(verificaMutacao);
        if(verificaMutacao.getAptidao() < melhorAptidao){
            System.out.println("Novo melhor caminho encontrado: " + verificaMutacao.getAptidao() + "; Geracao: " + numGeracoes);
            melhorAptidao = verificaMutacao.getAptidao();
            return verificaMutacao;
        }
        return individuo;
    }

    private void printaCidadesFinais(Individuo individuo) {
        for (int i = 0; i < individuo.getNumeroCidade().length; i++) {
            if (i == individuo.getNumeroCidade().length - 1) {
                System.out.print(individuo.getNumeroCidade()[i]);
            }
            else{
            System.out.print(individuo.getNumeroCidade()[i]+ " -> ");
            }
        }
        System.out.println();
    }
}
