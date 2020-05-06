# MVP-evernote

[![N|Linkdin](https://github.com/paulosoujava/MVP-evernote/blob/master/imagens/Captura%20de%20Tela%202020-05-06%20%C3%A0s%2008.00.40.png)](https://www.linkedin.com/in/paulo-oliveira-92127b1a0/)

MVP
Model–view–presenter (MVP) é uma derivação do padrão de software model-view-controller (MVC), usado também para construir principalmente interfaces gráficas.
Em MVP a camada Presenter assume a função de mediadora (executada pelo Controller em MVC). Além disso, a View é responsável por manipular os eventos UI (como mouseDown, keyDown, etc.), que era o trabalho da Controller. Finalmente, a Model se torna estritamente um modelo de domínio.

Descrição do padrão
MVP é um padrão de desenho de interface de usuário projetado para facilitar os testes unitários automatizados e melhorar a separação de interesses em lógica de apresentação:
* Model é uma interface que define o modelo de dados que será exibido ou alterado na interface do usuário.
* View é uma interface que exibe de fato os dados (o modelo) e guia os comandos do usuário (eventos) à camada Presenter para atuar sobre os dados.
* A interface Presenter atua sobre a Model e a View. Ela recupera os dados dos repositórios (modelo), e os formata para exibi-los na View.

Fonte: https://pt.wikipedia.org/wiki/Model-view-presenter


Introdução
Uma das características fundamentais de um bom teste unitário é ser isolado. Para que ele execute rápido, fornecendo feedback ao desenvolvedor, ele deve ser isolado. Quando digo isolado é que ele deve ser isolado de dependências externas: acesso à rede, ao sistema de arquivos, a banco de dados etc. É aí que os mocks podem nos ajudar e que o Mockito entra em ação.

Imagine que você precisa testar um código que faz acesso à camada de persistência por meio de um DAO/Repository. Para esse código funcionar, em produção, é necessário que algum mecanismo de persistência (um banco de dados relacional, por exemplo) esteja disponível. Para o código de testes de unidade isso é impraticável: ele vai ficar lento, mais complexo, vai perder o isolamento.

A solução para o caso anterior é simular o acesso à camada de persistência. Daí o uso de mocks. De maneira geral, para facilitar o entendimento, mocks são objetos criados para simular, de forma controlada, determinados comportamentos de objetos reais. Por forma controlada entenda: como bem quisermos ou desejarmos.

O Mockito, como veremos a seguir, nos auxilia na tarefa de criação de mocks. Vamos, então, conhecer um pouco mais dele.

Mockito e configuração básica
O Mockito é um framework de mocking para uso em testes de unidade na linguagem Java. Ele possui uma API clara e simples. A curva de aprendizado é suave e ele possui uma documentação bastante útil, recheada de exemplos.

Entre as principais características do Mockito estão:

O setup para a criação de um mock, diferentemente de outros frameworks, é bastante simples;
Possibilidade de criar mocks tanto de classes concretas como de interfaces;
Criação de mocks por meio de anotações: @Mock;
Facilidade na verificação de erros: a verificação de testes que falham é simples, bastando olhar o stack trace disponibilizado;
Permite verificar a quantidade de vezes que um método foi chamado;
Permite a verificação por meio de matchers de argumentos (anyObject(), anyString() …);
Permite a verificação da ordem das chamadas de um método.

Fote: http://www.codeatest.com/mockito-isolamento-testes-unidade/
