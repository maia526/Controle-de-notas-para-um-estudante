import java.util.Scanner;

public class Menu {
    private Admin admin;

    public Menu(Admin admin) {
        this.admin = admin;
    }

    public int leInt(){
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        return i;
    }

    public String leString(){
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        return s;
    }

    public double leDouble(){
        Scanner input = new Scanner(System.in);
        double d = input.nextDouble();
        return d;
    }

    public Materia geraNovaMateria(){
        System.out.print("Nome: ");
        String nome = leString();
        System.out.print("Peso: ");
        int peso = leInt();
        System.out.print("Media minima: ");
        double mediaMinima = leDouble();

        Materia materia = new Materia(nome, peso, mediaMinima);
        return materia;
    }

    public void imprimeMenu(){
        System.out.print("-----Menu-----\n(1)Incluir nova matéria\n(2)Excluir matéria\n(3)Incluir nota\n(4)Encerrar período\n(5)Imprimir relatório\n");
    }

    public void recebeOpcao(){
        imprimeMenu();
        int opc;

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
                    System.out.print("Nome: ");
                    String nome = leString();
                    acerto = admin.excluirMateria(nome);
                    if (acerto){
                        System.out.print("\nMatéria excluida com sucesso.\n");
                        admin.calculaCr();
                    }

                    else System.out.print("\nHouve um erro na exclusão da matéria no sistema.\n");
                    break;
                case 3:
                    System.out.print("Materia: ");
                    String materia = leString();
                    System.out.print("Nota: ");
                    double nota = leDouble();
                    acerto = admin.incluirNota(nota, materia);
                    if (acerto) {
                        System.out.print("\nNota adcionada com sucesso.\n");
                        admin.recalcularMedia(materia);
                        admin.calculaCr();
                    }
                    else System.out.print("\nHouve um erro na adição da nota no sistema.\n");
                    break;
                case 4:
                    System.out.print("Materia: ");
                    materia = leString();
                    acerto = admin.terminaPeriodo(materia);
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
