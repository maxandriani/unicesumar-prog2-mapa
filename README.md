# MAPA - PROG2 - ADS - UNICESUMAR

Trabalho final da disciplina de programação II, do curso de Análise e Desenvolvimento de Sistemas da UniCesumar.

## Contexto

O projeto consiste no desenvolvimento de um conjunto de interfaces de Login e Cadastro de usuários.

O usuário deverá ser apresentado a uma tela de login contendo os campos "login" e "senha".

Ao preencher os dados, deverá ser consultada uma base MySQL:
    - Caso as credenciais sejam válidas, deverá ser apresentada a mensagem "Acesso autorizado" e inicializada a aplicação principal (fora do contexto);

    - Caso as credenciais não sejam válidas, deverá apresentar a mensagem acesso negado!

    - Se o usuário clicar no botão Cadastrar Usuário, uma nova tela com um formulário de cadastro deve ser exibida;

A tela de Cadastro de Usuários deverá ter os seguintes campos: Nome, Email, Login e Senha.

Ao clicar em cadastrar usuário, os dados devem ser persistidos no banco de dados, uma mensagem de cadastro concluído deve ser apresentada e a tela deverá ser fechada (sem fechar a aplicação).
