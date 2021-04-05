# Programação  Orientada por Objetos  2020/2021

## Projeto de Época Normal e de Recurso - **Boats & Docks**

## Introdução

O Projeto final pretende que o aluno sistematize os conhecimentos adquiridos de maneira a estruturar corretamente o código desenvolvido tornando-o eficiente, robusto, reutilizável e modular, para assim poder construir uma solução aplicacional de acordo com o paradigma orientado por objetos, utilizando a linguagem de programação java. O objetivo passa pelo desenvolvimento de uma versão de um jogo de tabuleiro chamado **Boats & Docks**.

O jogo **Boats & Docks** é um jogo tradicional, jogado individualmente, cuja lógica de jogo consiste numa mistura de regras entre o jogo **Minesweeper** e o **Sudoku**, sendo o principal objetivo do jogo atracar todos os barcos nos respetivos portos, seguindo um conjunto de regras. Podem consultar uma versão jogável de um jogo ([Android](https://play.google.com/store/apps/details?id=com.frozax.tentsandtrees&hl=en_US&gl=US), [IOS](https://apps.apple.com/us/app/tents-and-trees-puzzles/id1279378379)) cuja dinâmica é semelhante. Contudo, as regras do jogo a desenvolver neste projeto divergem em diversos pontos.

O projeto será desenvolvido em **três fases distintas**:

- A primeira fase consiste na análise e desenho da aplicação, resultante da implementação das classes de domínio.
- A segunda fase tem foco na implementação das funcionalidades e lógica de jogo, em ambiente de consola.
- Por último, a terceira fase será dedicada à criação da interface gráfica com o utilizador.

Tenha particular atenção ao uso do paradigma de POO na modelação das classes, i.e., ao correto uso dos conceitos de encapsulamento, herança, classes abstratas, polimorfismo, interfaces, maximização da coesão (responsabilidade única), minimização do acoplamento, desenho orientado por responsabilidades, etc. Uma modelação bem pensada facilitará todo o processo de desenvolvimento e manutenção.
<p align="center">
<img src="https://github.com/JoaoCapinha/template-projeto-poo/blob/a3775d2db54e98f7c39f96a7b43ed98309c6cefc/enunciados/img/Imagem1.png" alt="Exemplo de tabuleiro do jogo Boats & Docks" style="zoom:30%;" />
</p>

## Estrutura

- **enunciados** - repositório que irá conter os enunciados do projeto.
- **dev** - repositório onde o aluno deverá manter todo o código-fonte do programa, com a respetiva implementação realizada.
- **docs** - repositório onde o aluno deverá colocar os documentos solicitados em cada uma das fases, como manuais técnicos.
- **contents** -repositório que  irá conter, em caso de necessidade, o conjunto de ficheiros disponibilizados pelo corpo docente para auxilio no desenvolvimento do projeto. Atualmente este repositório contêm:
  - **Levels** - pasta que contêm o conteúdo de 9 níveis de jogo. 3 de cada grau de dificuldade;
  - **LevelLoader** - Biblioteca Java que permite carregar os ficheiros dos níveis (pasta "*levels*") para um Objeto *ArrayList*. Deve ser incluída no projeto implementado;
  - **SampleLoadLevels** - Projeto Netbeans exemplificativo da utilização da biblioteca java *LevelLoader*;

## Considerações importantes

A primeira fase do projeto terá foco nas temáticas de: definição de classes, desenho de aplicações, herança de classes, polimorfismo e classes abstratas. **A entrega da 1ª fase do projeto será até às 23 horas de sábado, dia 8 de maio de 2021**.

A segunda fase do projeto terá foco na implementação da mecânica de jogo em ambiente de consola, na qual será arranjada as seguintes temáticas: coleções e genéricos, iteradores e processamento funcional, tratamento de erros e exceções, entrada e saída de dados (I/O) e serialização. **A entrega da 2ª fase do projeto será até às 23 horas de sábado, dia 29 de maio de 2021.**
