# language: pt
Funcionalidade: Cadastro de Servidor
    Como usuário gestor do sistema, eu gostaria de poder cadastrar os servidores no sistemas.

  Contexto: 
    Dado que eu estou logado no sistema como gestor

  Cenário: Sucesso ao cadastrar bem com todas as informações
    Dado um servidor cadastrado com todas as informações
    E que não existe outro servidor cadastrado com o mesmo CPF
    Quando eu tento cadastrar este servidor
    Então o sistema salva o servidor sem nenhum erro

  Cenário: Sucesso ao cadastrar bem somente com as informações essenciais
    Dado um servidor a ser cadastrado somente com as informações essenciais
    E que não existe outro servidor cadastrado com o mesmo CPF
    Quando eu tento cadastrar este servidor
    Então o sistema salva o servidor sem nenhum erro

  Cenário: Falha ao cadastrar sem informações essenciais
    Dado um servidor a ser cadastrado sem informações essenciais
    E que não existe outro servidor cadastrado com o mesmo CPF
    Quando eu tento cadastrar este servidor
    Então o servidor não é salvo

  Cenário: Sucesso ao alterar servidor
    Dado que existe um servidor recém cadastrado
    Quando tento alterar esse servidor
    Então a alteração do bem é bem sucedida
    E eu consigo conferir os dados do servidor alterado