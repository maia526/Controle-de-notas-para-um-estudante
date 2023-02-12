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
    public boolean adcionarMateria(Materia materia){
        return materias.add(materia);
    }
    public boolean excluirMateria(String nome){
        for (Materia materia  : materias){
            if (materia.getNome().equals(nome))
                return materias.remove(materia);
        }
        return false;
    }
    public boolean incluirNota(double nota, String nomeMateria){
        for (Materia materia: materias){
            if (materia.getNome().equals(nomeMateria)){
                return materia.adcionaNota(nota);
            }
        }
        return false;
    }
    public boolean recalcularMedia(String materia){
        for (Materia m : materias){
            if (m.getNome().equals(materia)){
                return m.recalcularMedia();
            }
        }
        return false;
    }
    public boolean terminaPeriodo(String nomeMateria){
        for (Materia materia : materias){
            if (materia.getNome().equals(nomeMateria)) {
                if (materia.getMedia() >= materia.getMediaMinima())
                    materia.setPassou(true);
                return materia.setEncerrada(true);
            }
        }
        return false;
    }
    public boolean calculaCr(){
        double soma = 0;
        int divisor = 0;
        for (Materia materia : materias){
            soma = sum(materia.getMedia() * materia.getPeso(), soma);
            divisor = (int) sum(materia.getPeso(), divisor);
        }
        this.cr = soma/divisor;
        return true;        //todo: essa função tinha que ser boolean?
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
