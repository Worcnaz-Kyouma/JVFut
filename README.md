# JavaFUT, a API Rest que não lhe deixa na mão  :soccer:

### Descrição
O sistema serve para que você possa registrar seus Jogadores e seus Pagamentos! Para que nada passe em branco hein. :money_with_wings: :money_with_wings: :money_with_wings:

# Documentação
OBS: Todas as rotas começam com o URI fragment "api", desta forma: "http://localhost:8080/api/jogadores/". Na documentação esta abreviado para facilitar

## Entity: Jogador
### Criar Jogador

-   **Endpoint**: `/jogadores`
-   **Método**: `POST`
-   **Descrição**: Cria um novo jogador.
-   **Corpo da Requisição**:
    `{
      "nome": "Pele",
      "email": "nicolasaprado028@example.com",
      "dataNasc": "YYYY-MM-DD"
    }` 
    
### Consultar Jogador Individualmente

-   **Endpoint**: `/jogadores/{codJogador}`
-   **Método**: `GET`
-   **Descrição**: Retorna um jogador da API, utilizando o codJogador

### Consultar Todos os Jogadores

-   **Endpoint**: `/jogadores`
-   **Método**: `GET`
-   **Descrição**: Retorna uma lista de todos os jogadores e seus pagamentos.
 
 ### Atualizar Informações de um Jogador

-   **Endpoint**: `/jogadores/{codJogador}`
-   **Método**: `PUT`
-   **Descrição**: Atualiza os dados de um jogador, deve-se enviar todos os campos, tirando, obviamente, a listagem de pagamentos.
-   **Corpo da Requisição** :
    `{
      "nome": "Novo pele",
      "email": "pele@example.com",
      "dataNasc": "YYYY-MM-DD"
    }` 

### Excluir Jogador

-   **Endpoint**: `/jogadores/{codJogador}`
-   **Método**: `DELETE`
-   **Descrição**: Deleta um Jogador pelo seu ID.

## Entity: Jogador
### Criar Pagamento

-   **Endpoint**: `/pagamentos`
-   **Método**: `POST`
-   **Descrição**: Registra um novo pagamento para um jogador.
-   **Corpo da Requisição**:
    `{
      "ano": 2024,
      "mes": 1,
      "valor": 10,
      "jogador": {
  			"codJogador": 45
  		}
    }`

### Buscar Pagamento Individualmente

-   **Endpoint**: `/pagamentos/{codPagamento}`
-   **Método**: `GET`
-   **Descrição**: Retorna um pagamento da API, utilizando o codPagamento.

### Consultar Todos os Pagamentos

-   **Endpoint**: `/pagamentos/`
-   **Método**: `GET`
-   **Descrição**: Retorna uma lista de todos os pagamentos.

### Atualizar Informações de Pagamento

-   **Endpoint**: `/pagamentos/{codPagamento}`
-   **Método**: `PUT`
-   **Descrição**: Atualiza as informações de um pagamento existente, deve-se enviar todos os campos.
-   **Corpo da Requisição**:
    `{
      "ano": 2024,
      "mes": 1,
      "valor": 10,
      "jogador": {
        "codJogador": 18
      }
    }` 

### Excluir Pagamento

-   **Endpoint**: `/pagamentos/{codPagamento}`
-   **Método**: `DELETE`
-   **Descrição**: Deleta um pagamento pelo seu ID.
