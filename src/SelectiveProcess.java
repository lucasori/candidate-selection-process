import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SelectiveProcess {
    public static void main(String[] args) {

        String [] candidates = {"Felipe", "Marcia", "Julia", "Paulo", "Augusto", "Monica", "Fabricío", "Mirela",
                "Daniela"};

        var selectedCandidates =  selectCandidates(candidates);
        printSelectedCandidate(selectedCandidates);

        for (String candidate : selectedCandidates) {
            if (Objects.nonNull(candidate)){
                contactingCandidate(candidate);
            }
        }

    }

    static void printSelectedCandidate(String [] candidates){

        for (String candidate : candidates) {
            System.out.printf("\nCandidato selecionado %s", candidate);
        }

        System.out.println();
    }

    static String [] selectCandidates(String [] candidates){

        String [] selectedCandidates = new String[5];

        int countSelectedCandidates = 0;
        int currentCandidate = 0;
        double baseSalary = 2000.0;

        while (countSelectedCandidates < 5 && currentCandidate < candidates.length) {
            String candidate = candidates[currentCandidate];
            double intendedSalary = intendedValue();

            System.out.printf("O candidato %s solicitou este valor de salário %f \n", candidate, intendedSalary);

            if (baseSalary >= intendedSalary){
                System.out.printf("O candidato %s foi selecionado para vaga \n", candidate);

                if (Objects.nonNull(candidates[currentCandidate])) {
                    selectedCandidates[countSelectedCandidates] = candidates[currentCandidate];
                    countSelectedCandidates++;
                }

            }

            currentCandidate++;
        }

        return selectedCandidates;
    }

    static double intendedValue(){
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }

    static void analyzeCandidate(double intendedSalary){

        double baseSalary = 2000.0;

        if (baseSalary > intendedSalary){
            System.out.println("Ligar para o candidato");
        }else if (baseSalary == intendedSalary){
            System.out.println("Ligar para o candidato com contra proposta");
        }else {
            System.out.println("Aguardando o resultado dos demais candidatos");
        }
    }

    static void contactingCandidate(String candidate){

        int attemptsMade = 1;
        boolean keepTrying = true;
        boolean answered = false;

        do {
            answered = answer();
            keepTrying = !answered;

            if (keepTrying){
                attemptsMade++;
            }else {
                System.out.println("\nContato realizado com sucesso");
            }
        }while (keepTrying && attemptsMade < 3);

        if (answered){
            System.out.printf("Conseguimos contato com o candidato %s na %d\n", candidate, attemptsMade);
        }else {
            System.out.printf("Não conseguimos contato com o candidato %s número máximo de tentativas %d\n",
                    candidate, attemptsMade);
        }
    }

    static boolean answer(){
        return new Random().nextInt(3) == 1;
    }
}
