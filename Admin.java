import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Double.sum;

public class Admin {
    private ArrayList<Materia> materias;
    private double cr;
    public Admin(){
        this.materias = new ArrayList<>();
    }
    public Materia encotraMateria(String nome){
        for (Materia materia : materias){
            if (materia.getNome().equals(nome))
                return materia;
        }
        return null;
    }
    public boolean adcionarMateria(Materia materia){
        return materias.add(materia);
    }
    public boolean excluirMateria(String nomeMateria){
        Materia materia = encotraMateria(nomeMateria);
        if (materia == null)
            return false;
        else
            return materias.remove(materia);
    }
    public boolean incluirNota(double nota, String nomeMateria){
        Materia materia = encotraMateria(nomeMateria);
        if (materia == null)
            return false;
        else
            return materia.adcionaNota(nota);
    }
    public boolean recalcularMedia(String nomeMateria){
        Materia materia = encotraMateria(nomeMateria);
        if (materia == null)
            return false;
        else{
            materia.recalcularMedia();
            return true;
        }
    }
    public boolean terminaPeriodo(String nomeMateria){
        Materia materia = encotraMateria(nomeMateria);
        if (materia == null)
            return false;
        else{
            if (materia.getMedia() >= materia.getMediaMinima())
                materia.setPassou(true);
            return materia.setEncerrada(true);
        }
    }
    public void calculaCr(){
        double soma = 0;
        double divisor = 0;
        for (Materia materia : materias){
            soma = sum(materia.getMedia() * materia.getPeso(), soma);
            divisor = sum(materia.getPeso(), divisor);
        }
        this.cr = soma/divisor;
    }
    public boolean escreveTxt(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("notas.txt"));
            writer.write("CR:" + cr);
            for (Materia materia : materias){
                writer.write(materia.toString());
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
}

//ideias:
//todo: fazer o programa ler o arquivo txt e pegar as informações dele toda vez que for rodado (tipo, roda o programa pela segunda vez e ele lê o txt e armazena as informações que estão lá
