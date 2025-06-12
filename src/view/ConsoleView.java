package view;

import controller.InscricaoController;
import controller.LoteController;
import model.Evento;
import model.Inscricao;
import model.Lote;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private InscricaoController inscricaoController = new InscricaoController();
    private LoteController loteController = new LoteController();
    private Scanner scanner = new Scanner(System.in);
    private Evento eventoPadrao = new Evento(1, "Congresso de Tecnologia 2025");

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n---- Menu Principal ----");
            System.out.println("1. Adicionar um novo lote");
            System.out.println("2. Adicionar uma nova inscrição");
            System.out.println("3. Listar todos os lotes");
            System.out.println("4. Listar todas as Inscricoes");
            System.out.println("5. Validar Pagamento de Inscrição"); 
            System.out.println("6. Sair"); 
            System.out.print("Escolha uma opção: ");
            
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Opção inválida. Por favor, digite um número.");
                opcao = 0; 
            }
            scanner.nextLine(); 

            switch (opcao) {
                case 1: adicionarLote(); break;
                case 2: adicionarInscricao(); break;
                case 3: listarLotes(); break;
                case 4: listarInscricoes(); break;
                case 5: validarPagamento(); break; 
                case 6: System.out.println("Saindo..."); break;
                default:
                    if (opcao != 0) { 
                        System.out.println("Opção inválida. Tente novamente.");
                    }
            }
        } while (opcao != 6);
    }
    
    private void adicionarLote() {
        System.out.println("\n---- Adicionar Novo Lote ----");

        System.out.println("AVISO: Este lote será associado ao evento: '" + eventoPadrao.getNome() + "'");

        System.out.print("Nome do Lote (ex: Lote 1, Lote Promocional): ");
        String nome = scanner.nextLine();

        System.out.print("Valor do lote (ex: 150,00): ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); 

        Lote novoLote = new Lote(0, nome, eventoPadrao, valor);
        loteController.adicionarLote(novoLote);

        System.out.println("\nSUCESSO: Lote '" + nome + "' cadastrado!");
    }

    private void adicionarInscricao() {
        System.out.println("\n---- Nova Inscrição ----");
        System.out.print("Nome do inscrito: ");
        String nomeInscrito = scanner.nextLine();

        System.out.println("\nLotes disponíveis:");
        listarLotes();

        System.out.print("\nDigite o ID do lote desejado: ");
        int loteId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe a forma de pagamento (Pix, Cartao, Boleto): ");
        String formaPagamento = scanner.nextLine();
        
        boolean sucesso = inscricaoController.registrarNovaInscricao(nomeInscrito, loteId, formaPagamento);

        if (sucesso) {
            System.out.println("\nSUCESSO: Inscrição para '" + nomeInscrito + "' adicionada!");
        } else {
            System.out.println("\nFALHA: Não foi possível realizar a inscrição. Verifique os dados.");
        }
    }

    private void listarLotes() {
        System.out.println("\n--- Lotes Cadastrados ---");
        List<Lote> lotes = loteController.listarLotes();
        if (lotes.isEmpty()) {
            System.out.println("Nenhum lote cadastrado.");
        } else {
            for (Lote lote : lotes) {
                System.out.println("ID: " + lote.getId() + " | Nome: " + lote.getNome() + " | Evento: " + lote.getEvento().getNome() + " | Valor: R$ " + lote.getValor());
            }
        }
    }

    private void listarInscricoes() {
        System.out.println("\n---- Inscrições Cadastradas ----");
        List<Inscricao> inscricoes = inscricaoController.listarTodasAsInscricoes();

        if (inscricoes.isEmpty()) {
            System.out.println("Nenhuma inscrição encontrada.");
        } else {
            System.out.printf("%-5s | %-20s | %-15s | %-10s | %-10s | %-10s%n",
                    "ID", "Inscrito", "Lote", "Valor Pago", "Forma Pgto", "Status");
            System.out.println(String.format("%80s", "").replace(' ', '-'));

            for (Inscricao inscricao : inscricoes) {
 
                System.out.printf("%-5d | %-20s | %-15s | R$ %-7.2f | %-10s | %-10s%n",
                        inscricao.getId(),
                        inscricao.getNomeInscrito(),
                        inscricao.getLote().getNome(),
                        inscricao.getPagamento().getValorPago(),      
                        inscricao.getPagamento().getFormaPagamento(), 
                        inscricao.getPagamento().getStatusPagamento() 
                );
            }
        }
    }
    private void validarPagamento() {
        System.out.println("\n---- Validar Pagamento ----");
        System.out.println("Funcionalidade em construção...");

    }
}