# Relatório Compilador Mini-Java #

---

**Membros:**
  1. André Araújo
  1. Carlos Augusto Paiva
  1. Iuri Matsuura
  1. Samir Saraiva

---

## Divisão dos Módulos ##
> O desenvolvimento do trabalho foi dividido e implementado durante toda a disciplina, cada implementação do projeto foi feita de acordo com os requisitos de cada acompanhamento. Os módulos foram implementados na linguagem JAVA.

## Iteração 1 – Analisador Léxico e Sintático ##
  * Módulo concluído
  * Dificuldades: Como já havia uma gramática especificada no livro texto, houve uma pequena dificuldade para nos adaptarmos com isto e especificarmos os tokens na fase de construção do analisador léxico. Na análise sintática, mais uma vez precisávamos fazer uma adaptação da gramática do livro, fazendo de forma que não houvesse ambiguidades e lookaheads de tamanhos adequados.
  * Responsáveis: André Araújo e Iuri Matsuura.

## Iteração 2 – Árvore Sintática Abstrata, Verificação de Tipos e Tabela de Símbolos ##
  * Módulo concluído
  * Dificuldades: Utilizamos o padrão Visitor para facilitar a implementação da construção da tabela de símbolos e da verificação de tipos. Nesta fase não encontramos muitas dificuldades. O que nos chamou um pouco de atenção foi que precisávamos de uma estrutura de dados que facilitasse a manipulação da tabela de símbolos.
  * Responsáveis: André Araújo, Carlos Augusto, Iuri Matsuura, Samir Saraiva

## Iteração 3 – Registros de Ativação e Código Intermediário ##
  * Módulo concluído
  * Dificuldades: Tivemos um pouco de dificuldade para adequar a classe frame específica da máquina, para a qual construiríamos o compilador, arquitetura Mips. Na construção do código intermediário, precisamos pensar de uma forma que não estamos acostumados. Uma programação de baixo nível, observando o comportamento arquitetônico do computador, fez com que tivéssemos um pouco de dificuldade para levarmos em consideração detalhes que estamos acostumados a abstrair.
  * Responsáveis: André Araújo, Carlos Augusto, Iuri Matsuura, Samir Saraiva

## Iteração 4 – Árvore Canônica, Blocos Básicos, Traços e Seleção de Instruções ##
  * Módulo concluído
  * Dificuldades: O livro nos ajudou consideravelmente com a representação canônica, blocos básicos e traços. Na etapa de Seleção de Instruções, a implementação do jouette foi de suma importância. Utilizamos no projeto o algoritmo Maximal Munch que tem como saída as instruções de máquina. Não foi uma iteração com muitas dificuldades.
  * Responsáveis: André Araújo, Carlos Augusto, Iuri Matsuura, Samir Saraiva

## Iteração 5 – Análise de Longevidade e Alocação de Registradores ##
  * Módulo parcialmente concluído
  * Dificuldades: Conseguimos finalizar o processo de criação do grafo de controle de fluxo, para acharmos o tempo de longevidade de cada variável, mas não conseguimos completar os testes para esta etapa. Na etapa de alocação de registradores a construção do grafo de interferência é algo que despendeu bastante trabalho, pois precisávamos de um grafo de controle de fluxo funcionando perfeitamente, já que a construção seria feita utilizando informações provenientes dele; tivemos problemas com o tempo que, pois foi necessário fazer reparos na etapa anterior, pouco testada, para que esta funcionasse.
  * Responsáveis: André Araújo, Carlos Augusto, Iuri Matsuura, Samir Saraiva

## Integração ##
  * Módulo concluído
  * Dificuldades: Foi difícil conciliar o tempo de toda a equipe para chegarmos a esta etapa com a boa participação de todos os membros da equipe, com as avaliações na etapa final do semestre, e membros de semestres diferentes e horários diferentes, ficou complicado nos reunirmos para finalizarmos a etapa anterior, com testes variados, pois não é viável que o programador teste o seu próprio código. Contudo fizemos a integração de todo o código, com erros e alguns detalhes específicos da última iteração. Concluímos dizendo que o trabalho cumpriu o seu papel, que é fazer com que os alunos coloquem em prática os conhecimentos adquiridos em sala de aula; conseguimos uma melhor compreensão aplicando a teoria vista no trabalho.
  * Responsável: Carlos Augusto e Samir Saraiva