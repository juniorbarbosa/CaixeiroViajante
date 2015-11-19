# CaixeiroViajante

O trabalho do bimestre consiste em resolver o problema conhecido como CAIXEIRO VIAJANTE.


Imagine a seguinte situação:

Um vendedor deve sair de uma cidade origem, por exemplo, Foz do Iguaçu e deve chegar até uma cidade destino, por exemplo, Curitiba.

Existem N caminhos que ele pode percorrer para chegar ao seu destino. O algoritmo deve mostrar o MENOR caminho entre ORIGEM e DESTINO.

O algoritmo deve pedir no MENU as seguintes opções:

INSIRA NOME DA CIDADE: Nesta opção, você deve informar apenas o nome de uma cidade, nada além disto.
LIGAÇÃO ENTRE CIDADES: Nesta opção, a lista de cidades já informada deve ser exibida, e o usuário deve informar o número (CHAVE) correspondente de duas cidades, um número por vez e informar a distância entre as cidades. Com isso, o código deve amarrar uma cidade com a outra.
PERCORRER CAMINHO: Nesta opção, a lista de cidades já informada deve ser exibida, o usuário vai informar o número (CHAVE) de duas cidades, a 1º sendo a ORIGEM e a 2º sendo o DESTINO. O algoritmo deve percorrer todas as ligações entre as cidades e ao final mostrar apenas o nome das cidades e a distância do menor caminho, no formato: FOZ -> CURITIBA (150)
Imagine o seguinte cenário:


CIDADES
FOZ	 1
CASCAVEL	 2
LARANJEIRAS
 3
IRATI	 4
CURITIBA	 5


LIGAÇÃO E DISTÂNCIA
50	  1	  2
100	  2	  4
10
  4	  5
150	  1	  5


Neste caso, temos 2 caminhos para sair de FOZ e chegar em CURITIBA

1º caminho: FOZ -> CASCAVEL -> IRATI -> CURITIBA (160)

2º caminho: FOZ -> CURITIBA (150)

Neste exemplo, o 2º caminho é o menor e ele é que deve ser mostrado.
