package br.jus.trt.app.platao.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.business.facade.ServidorCrudFacade;
import br.jus.trt.lib.qbe.api.Filter;
import br.jus.trt6.lib.common_web.action.CrudActionBase;

/**
 * Clase de controle para atender às requisições da tela de cadastro de
 * servidor.
 *
 * @author augusto
 *
 */
@SuppressWarnings("serial")

@Named
@ConversationScoped
public class ServidorAction extends CrudActionBase<Servidor, Long, ServidorCrudFacade> {

    @Inject
    private Logger log;
    
    @Inject
    private Conversation conversation;

    @Inject
    private CidadeUfController cidadeUfController;

    @Override
    @PostConstruct
    public void init() {
        super.init();

        log.entry();
        conversation.begin();
    }

    @Override
    protected void configSearch(Filter<? extends Servidor> filter) {
        super.configSearch(filter);
        
        // configurando consulta para realização de fetch das dependências
        log.entry();
        filter.addFetch("cidade.uf"); 
    }

    @Override
    protected void preLoad(Servidor entidade) {
        super.preLoad(entidade);

        // prepara a lista de CidadeUf
        log.entry(entidade);
        cidadeUfController.setSelectedUf(entidade.getCidade().getUf());
        cidadeUfController.loadCidades();
    }

    @Override
    protected void configLoad(Servidor entidade, Filter<Servidor> loadFilter) {
        super.configLoad(entidade, loadFilter);
        log.entry();
        loadFilter.addFetch("cidade.uf");
    }

    public CidadeUfController getCidadeUfController() {
        return cidadeUfController;
    }

}
