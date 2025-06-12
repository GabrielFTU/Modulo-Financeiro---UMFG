package services;

import java.time.LocalDate;

import model.Inscricao;
import model.Lote;
import model.Pagamento;
import model.Pagamento.StatusPagamento;
import repository.InscricaoRepository;
import repository.LoteRepository;

public class InscricaoService {
    private InscricaoRepository inscricaoRepository = new InscricaoRepository();
    private LoteRepository loteRepository = new LoteRepository();


    public Inscricao criarNovaInscricao(String nomeInscrito, int loteId, String formaPagamento) {
        Lote loteSelecionado = loteRepository.buscarPorId(loteId);
        if (loteSelecionado == null) {
            System.err.println("ERRO DE NEGÓCIO: Lote com ID " + loteId + " não foi encontrado.");
            return null; 
        }
        double valorPago = loteSelecionado.getValor();

        Inscricao novaInscricao = new Inscricao(0, nomeInscrito, loteSelecionado, LocalDate.now());
        Pagamento novoPagamento = new Pagamento(0, valorPago, formaPagamento, StatusPagamento.PENDENTE);

        novaInscricao.setPagamento(novoPagamento);
        inscricaoRepository.salvar(novaInscricao);
        
        return novaInscricao;
    }
}