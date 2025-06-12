package controller;

import java.util.List;
import model.Inscricao;
import repository.InscricaoRepository;
import services.InscricaoService; 

public class InscricaoController {

    private InscricaoService inscricaoService = new InscricaoService();
    private InscricaoRepository inscricaoRepository = new InscricaoRepository(); 

    public boolean registrarNovaInscricao(String nomeInscrito, int loteId, String formaPagamento) {
        Inscricao inscricaoCriada = inscricaoService.criarNovaInscricao(nomeInscrito, loteId, formaPagamento);
        return inscricaoCriada != null; 
    }
    
    public List<Inscricao> listarTodasAsInscricoes() {
        return inscricaoRepository.listarInscricoes();
    }
}