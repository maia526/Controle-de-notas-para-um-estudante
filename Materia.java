import java.util.*;

import static java.lang.Double.sum;

public class Materia {
    private String nome;
    private int peso;
    private ArrayList notas;
    private boolean encerrada;
    private double mediaMinima;
    private double media;
    private boolean passou;

    public Materia(String nome, int peso, double mediaMinima) {
        this.nome = nome;
        this.peso = peso;
        this.notas = new ArrayList<>();
        this.encerrada = false;
        this.mediaMinima = mediaMinima;
        this.passou = false;
    }
    public double calculaSomaNotas(){
        double soma = 0;
        for (Object nota : notas){
            soma = sum(soma, (double)nota);
        }
        return soma;
    }
    public void recalcularMedia(){
        media = calculaSomaNotas() / notas.size();
    }

    public double getMediaMinima() {
        return mediaMinima;
    }

    public double getMedia() {
        return media;
    }

    public boolean isPassou() {
        return passou;
    }

    public void setPassou(boolean passou) {
        this.passou = passou;
    }

    public boolean adcionaNota(double nota){
        return notas.add(nota);
    }
    public boolean setEncerrada(boolean encerrada) {
        return this.encerrada = encerrada;
    }
    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public ArrayList getNotas() {
        return notas;
    }
    @Override
    public String toString() {
        if (this.encerrada)
            return "\nMateria:" + nome +
                    "\n\tpeso = " + peso +
                    "\n\tnotas = " + notas +
                    "\n\tmédia = " + media +
                    "\n\taprovado = " + passou +
                    "\n------------------------------------------";
        else
            return "\nMateria:" + nome +
                    "\n\tpeso = " + peso +
                    "\n\tnotas = " + notas +
                    "\n\tmédia = " + media +
                    "\n------------------------------------------";
    }
}
