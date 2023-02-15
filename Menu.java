import java.util.Scanner;

public class Menu {
    private Admin admin;

    public Menu(Admin admin) {
        this.admin = admin;
    }

    public int leInt(){
        Scanner input = new Scanner(System.in);
        int i;
        try {
            i = input.nextInt();
        }
        catch (Exception e){
            System.out.println("Erro na leitura do inteiro.");;
            i = -1;
        }
        return i;
    }

    public String leString(){
        Scanner input = new Scanner(System.in);
        String s;
        try {
            s = input.nextLine();
        }
        catch (Exception e){
            System.out.println("Erro na leitura da string.");
            s = null;
        }
        return s;
    }

    public double leDouble(){
        Scanner input = new Scanner(System.in);
        double d;
        try {
            d = input.nextDouble();
        }
        catch (Exception e){
            System.out.println("Erro na leitura do double.");
            d = -1;
        }
        return d;
    }

    public String retornaNomeMateria(){
        String nomeMateria = null;
        boolean ativo = true;
        while (ativo){
            System.out.print("Nome: ");
            nomeMateria = leString();
            if (nomeMateria != null){
                ativo = false;

            }
        }
        return nomeMateria.toUpperCase();
    }

    public int retornaPeso(){
        int peso = -1;
        int valorMinimo = 0;
        boolean ativo = true;
        while (ativo) {
            System.out.print("Peso: ");
            peso = leInt();
            if (peso > valorMinimo){
                ativo = false;
            }
        }
        return peso;
    }

    public double retornaMadiaMinima(){
        double mediaMinima = 0;
        int valorMinimo = 0;
        boolean ativo = true;
        while (ativo){
            System.out.print("Media minima: ");
            mediaMinima = leDouble();
            if (mediaMinima > valorMinimo)
                ativo = false;
        }
        return mediaMinima;
    }
    public double retornaNota(){
        double nota = 0;
        int valorMinimo = 0;
        boolean ativo = true;
        while (ativo){
            System.out.print("Nota: ");
            nota = leDouble();
            if (nota > valorMinimo)
                ativo = false;
        }
        return nota;
    }

    public Materia geraNovaMateria(){
        String nomeMateria = retornaNomeMateria();
        int peso = retornaPeso();
        double mediaMinima = retornaMadiaMinima();

        Materia materia = new Materia(nomeMateria, peso, mediaMinima);
        return materia;
    }

    public void imprimeMenu(){
        System.out.print("-----Menu-----\n(1)Incluir nova matéria\n(2)Excluir matéria\n(3)Incluir nota\n(4)Encerrar período\n(5)Imprimir relatório\n");
    }

    public void recebeOpcao(){
        String nomeMateria;
        double nota;
        int opc;
        imprimeMenu();

        boolean ativo = true;
        while (ativo) {
            boolean acerto = false;
            opc = leInt();
            switch (opc) {
                case 1:
                    acerto = admin.adcionarMateria(geraNovaMateria());
                    if (acerto)
                        System.out.print("\nMatéria adcionada com sucesso.\n");
                    else System.out.print("\nHouve um erro na adição da matéria no sistema.\n");
                    break;
                case 2:
                    nomeMateria = retornaNomeMateria();
                    acerto = admin.excluirMateria(nomeMateria);
                    if (acerto){
                        System.out.print("\nMatéria excluida com sucesso.\n");
                        admin.calculaCr();
                    }
                    else System.out.print("\nHouve um erro na exclusão da matéria no sistema.\n");
                    break;
                case 3:
                    nomeMateria = retornaNomeMateria();
                    nota = retornaNota();
                    acerto = admin.incluirNota(nota, nomeMateria);
                    if (acerto) {
                        System.out.print("\nNota adcionada com sucesso.\n");
                        admin.recalcularMedia(nomeMateria);
                        admin.calculaCr();
                    }
                    else System.out.print("\nHouve um erro na adição da nota no sistema.\n");
                    break;
                case 4:
                    nomeMateria = retornaNomeMateria();
                    acerto = admin.terminaPeriodo(nomeMateria);
                    if (acerto)
                        System.out.print("\nPeríodo encerrado com sucesso.\n");
                    else System.out.print("\nHouve um erro no encerramento do período.\n");
                    break;
                case 5:
                    acerto = admin.escreveTxt();
                    if (acerto)
                        System.out.print("\nRelatório gerado com sucesso. Cheque o arquivo txt para acessá-lo.\n");
                    else System.out.print("\nHouve um erro na geração do arquivo txt.\n");
                    break;
                case 0:
                    ativo = false;
                    break;
            }
        }
    }
}
