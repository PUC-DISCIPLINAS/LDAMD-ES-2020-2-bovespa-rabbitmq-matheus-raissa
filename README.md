![rabbitmq](https://user-images.githubusercontent.com/30940498/93145199-200c8080-f6c2-11ea-9301-eea15d5b0a5b.gif)

<h1 align="center">
    <span href="">🔗 Corretora de Valores com Rabbit MQ </span>
</h1>
<p align="center">🚀 MOM são sistemas que permitem o envio de mensagens entre entidades de um sistema distribuído </p>

[![Generic badge](https://img.shields.io/github/issues/PUC-ES-LDAMD/bovespa-rabbitmq-matheus-raissa)](https://shields.io/)
[![lastcommit](https://img.shields.io/github/last-commit/PUC-ES-LDAMD/bovespa-rabbitmq-matheus-raissa)](https://shields.io/)
[![best_language](https://img.shields.io/github/languages/top/PUC-ES-LDAMD/bovespa-rabbitmq-matheus-raissa)](https://shields.io/)

### 💻 Sobre o Projeto

O projeto proposto a ser desenvolvido é um sistema para uma bolsa de valores, como a Bovespa, utilizando o RabbitMQ.

### ⌨️ Instruções para Execução 

> Etapa 1 - Executar a bolsa de valores

* Dê dois cliques no arquivo "StockExchange.jar";
* Digite o endereço do servidor ou mantenha como "localhost";
* Clique em "Abrir Negociações";

> Etapa 2 - Executar uma corretora 

* Dê dois cliques no arquivo "Brocker.jar";
* Digite o nome do Broker ou mantenha como 'BKR1';
* Selecione uma ação da bovespa no combobox ou mantenha na primeira opção;
* Digite a quantidade de lotes a serem negociados para esta ação;
* Digite o preço de cada lote a ser negociado para esta ação;
* Clique no botão 'Comprar' para abrir uma oferta de compra ou clique no botão 'Vender' para abrir uma oferta de venda desta ação; 

> Etapa 3 - Acompanhar ações 

* Clique no botão 'Abrir Visualizador';
* Digite o endereço do servidor ou mantenha como 'localhost';
* Selecione uma ação da bovespa no combobox ou mantenha na primeira opção;
* Clique no botão 'Acompanhar' para ver todas as negociações;

> Etapa 4 - Encaminhar mensagens 

* O envio de mensagens acontece automaticamente quando a bolsa de valores recebe qualquer mensagem;

> Etapa 5 - Realizar transação 

* Assim como a etapa 4, a etapa 5 também acontece automaticamente quando  uma nova oferta é publicada no livro de ofertas;

### ⚙️ Estrutura do Sistema

### Classes 

> Entities  
```
* BrokerConnection - é uma thread que realiza as publicações no canal "BROKER".
* BrokerReceive - é uma thread que recebe as informações do canal "BOLSADEVALORES" baseado em um determinado tópico.
* StockConnection - é uma thread que realiza as publicações no canal "BOLSADEVALORES".
* StockReceive - é uma thread que recebe as informações do canal "BROKER" baseado em um determinado tópico.
```
> GUI
```
* BrokerGUI - é uma interface que permite realizar novas negociações e acompanhar as negociações em andamento. 
* StockGUI - é uma interface que permite iniciar e acompanhar as negociações em andamento. 
```

> Utils 
```
* AssetList - onde é realizado a leitura do arquivo que contém a lista de ações da Bovespa.
* OfferBook - onde é armazenado as ofertas e as correspondências de compra e venda. 
* Transaction - onde é realizado as transações da aplicação. 
```

### Operações 
```
$ BrokerReceive.run() - gera um JLabel, insere um JPanel na interface para ser exibido em "BrokerGUI".
```

```
$ StockReceive.run() - gera um JLabel, insere um JPanel na interface de "StockGUI" e emite uma mensagem para a "BOLSADEVALORES" armazená-la em OfferBook.
```

```
$ BrokerGUI.viewer() - gera e exibe o visualizador de negociações acompanhado pelo "BROKER".
```

```
$ AssetList.load() - realiza a leitura de um arquivo e retorna uma lista com os códigos de todos os ativos. 
```

```
$ OfferBook.store() - recebe uma oferta e armazena dentro da lista.
```

```
$ OfferBook.matchOffers() - recebe a última oferta salva e busca na lista uma oferta correspondente para realizar uma transação entre elas.
```

```
$ Transaction.store() - recebe duas ofertas e realiza a transação entre elas.
```

### 💡 Diagrama UML

> O diagrama de classes é fundamental, pois, através da sua representação conseguimos mapear de forma clara a estrutura do sistema.

![diagram-rabbitmq-final](https://user-images.githubusercontent.com/30940498/93935143-8f095b00-fcfa-11ea-895c-026407691dac.png)

### 💡 Diagrama de Componentes

> O diagrama de componentes apresenta os diferentes componentes de um sistema, além de possíveis dependências entre tais elementos.

![component_diagram](https://user-images.githubusercontent.com/30940498/93944897-39d64500-fd0c-11ea-830d-9eabbba753e8.png)

### 💡 Diagrama de Sequência

> O diagrama de sequência é uma solução dinâmica utilizada porque incide especificamente sobre linhas da vida, ou os processos e objetos que vivem simultaneamente, e as mensagens trocadas entre eles para desempenhar uma função antes do término da linha de vida.

![diagrama_sequencia](https://user-images.githubusercontent.com/30940498/93939190-cda21400-fd00-11ea-8a2c-5f2bd4bc2fb8.png)

### :busts_in_silhouette: Alunos

* Matheus Santos Rosa Carneiro - [mcarneirobug](https://github.com/mcarneirobug)
* Raissa Carolina Vilela da Silva - [raissavilela](https://github.com/raissavilela)

### 📝 Professor responsável

* Hugo Bastos de Paula - [hugodepaula](https://github.com/hugodepaula)

### 🛠 Tecnologias

<h1 align="center"> 	
<a href="https://www.rabbitmq.com/"><img height="32" width="32" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/rabbitmq.svg" /></a> 
<a href="https://www.java.com/pt_BR/"><img height="32" width="32" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/java.svg" /></a> 
</h1>

### ✋🏻 Dependências

- RabbitMQ
   - [Windows](https://www.rabbitmq.com/install-windows.html)
   - [Linux](https://www.rabbitmq.com/install-debian.html)
- [amqp-client-5.9.0.jar](https://www.rabbitmq.com/java-client.html)
- [SLF4J API](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)
- [SLF4J Simple](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)
- [Erlang](https://www.erlang.org/downloads)

<h4 align="center"> 
	🚧 Corretora de Valores 🚀 em desenvolvimento... 🚧
</h4>
