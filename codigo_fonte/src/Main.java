
import dominio.Cidade;
import dominio.Individuo;
import dominio.Populacao;
import utils.LeitorArquivo;

public class Main {

    public static void main(String[] args) {
        // if(args.length != 6) {
        //     System.err.println("Argumentos faltando. Informe o nome do arquivo, a taxa de crossover, a taxa de mutacao, " +
        //             "o numero maximo de geracoes, o tamanho da populacao e se sera usado o elitismo");
        //     return;
        // }
        // int tamPopulacao = Integer.parseInt(args[4]);
        // boolean elitismo = Boolean.parseBoolean(args[5]);

        // if(tamPopulacao % 2 == 0 && elitismo) {
        //     System.err.println("Tamanho da populacao nao pode ser par se realiza o elitismo");
        //     return;
        // }
        // if(tamPopulacao % 2 == 1 && !elitismo) {
        //     System.err.println("Tamanho da populacao nao pode ser ímpar se não realiza o elitismo");
        //     return;
        // }
        Cidade[] cidades = LeitorArquivo.leArquivo(args[0]);
        // Populacao populacao = new Populacao(5, cidades.length);
        // Populacao populacao2 = populacao.geraPopulacao(5, cidades.length);
        // Individuo[] populacaoGerada = populacao2.getIndividuos();
        // for(int i = 0; i < populacaoGerada.length; i++){
        // for(int cidadesGeradas : populacaoGerada[i].getNumeroCidade()){
        //     System.out.println(cidadesGeradas + "Testando");
        //     }
        // }
        // Algoritmo algoritmo = new Algoritmo(Double.parseDouble(args[1]), Double.parseDouble(args[2]),
        //         Integer.parseInt(args[3]), tamPopulacao, elitismo, preferenciaAlunos);
        // algoritmo.executar();
    }
}