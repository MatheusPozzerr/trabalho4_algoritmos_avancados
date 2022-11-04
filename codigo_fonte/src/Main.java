
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import dominio.Cidade;
import operacao.Algoritmo;
import utils.LeitorArquivo;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        Cidade[] cidades = LeitorArquivo.leArquivo(args[0]);
        Algoritmo algoritmo = new Algoritmo(cidades);
        algoritmo.executar();
    }
}
