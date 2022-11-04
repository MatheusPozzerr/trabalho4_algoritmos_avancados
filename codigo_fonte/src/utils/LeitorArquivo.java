package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dominio.Cidade;

public class LeitorArquivo {
    public static Cidade[] leArquivo(String nomeArquivo) {
        String pathToFile = Paths.get(nomeArquivo).toString();
        //Pattern pattern = Pattern.compile(".*(\\d+(?:\\.\\d+)?)\\s+(\\d+(?:\\.\\d+)?)\\s+(\\w+).*");
        Cidade[] cidades = null;
        try(BufferedReader fileReader = new BufferedReader(new FileReader(pathToFile))) {
            String linha;
            int numeroLinha = 0;
            while((linha = fileReader.readLine()) != null) {
                if(linha.isEmpty()) break;
                if (numeroLinha == 0) {
                    numeroLinha++;
                    int n = Integer.parseInt(linha);
                    cidades = new Cidade[n];
                } else {
                    String[] variaveisCidade = linha.split(" ");
                    //Matcher matcher = pattern.matcher(linha);
                        Cidade cidade = new Cidade(Float.parseFloat(variaveisCidade[0]), Float.parseFloat(variaveisCidade[1]), variaveisCidade[2]);
                        cidades[numeroLinha-1] = cidade;
                        numeroLinha++;
                }
            }
        } catch(NumberFormatException ex) {
            System.err.println("Nao foi possivel ler o numero de pares do arquivo: "+pathToFile);
            return null;
        } catch(Exception ex) {
            System.err.println("Nao foi possivel ler string do arquivo: "+pathToFile);
            System.out.println(ex);
            return null;
        }

        return cidades;
    }
}
