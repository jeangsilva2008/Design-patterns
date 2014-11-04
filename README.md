Design-patterns
===============


Encapsulando o comportamento
Ao invés de mantermos as regras espalhadas pela nossa aplicação, podemos encapsulá-las em classes cujas responsabilidades sejam realizar os cálculos. Para isso, podemos criar as classes ICMS e ISS cada um com seu respectivo método para cálcular o valor do imposto de acordo com o orçamento.

      public class ICMS {

          public double calculaICMS(Orcamento orcamento) {
              return orcamento.getValor() * 0.1;
          }

      }
      public class ISS {

          public double calculaISS(Orcamento orcamento) {
              return orcamento.getValor() * 0.06;
          }

      }
Agora temos as duas classes que separam a responsabilidade dos cálculos de impostos, com isso, podemos utilizá-las na classe CalculadorDeImpostos da seguinte maneira:

       public class CalculadorDeImpostos {

          public void realizaCalculo(Orcamento orcamento, String imposto) {

          if( "ICMS".equals(imposto) ) {

            double icms = new ICMS().calculaICMS(orcamento);
            System.out.println(icms); // imprimirá 50.0

          } else if( "ISS".equals(imposto) ) {

            double iss = new ISS().calculaISS(orcamento);
            System.out.println(iss); // imprimirá 30.0

            }
          }
      }
Agora o código está melhor, mas não significa que esteja bom. Um ponto extremamente crítico desse código é o fato de que quando quisermos adicionar mais um tipo diferente de cálculo de imposto em nosso calculador, teremos que alterar essa classe adicionando mais um bloco de if, além de criarmos a classe que encapsulará o cálculo do novo imposto. Parece bastante trabalho.

Eliminando os condicionais com polimorfismo e o pattern Strategy
O que queremos em nosso código é não realizar nenhum condicional, ou seja, não termos mais que fazer ifs dentro do CalculadorDeImpostos. Dessa forma, não devemos mais receber a String com o nome do imposto, no qual realizamos os ifs. Mas como escolheremos qual o imposto que deve ser calculado?

Uma primeira possibilidade é criar dois métodos separados na classe CalculadorDeImposto. Um para o ICMS e outro para o ISS, dessa forma teremos:

      public class CalculadorDeImpostos {

          public void realizaCalculoICMS(Orcamento orcamento) {

            double icms = new ICMS().calculaICMS(orcamento);
            System.out.println(icms);

          }

          public void realizaCalculoISS(Orcamento orcamento) {

            double iss = new ISS().calculaISS(orcamento);
            System.out.println(iss);

          }

      }
No entanto, agora só transferimos o problema dos vários ifs para vários métodos. O que não resolve o problema. O próximo passo para conseguirmos melhorar essa solução é termos um único método, genérico, que consegue realizar o cálculo para qualquer imposto, sem fazer nenhum if dentro dele.

      public class CalculadorDeImpostos {

          public void realizaCalculo(Orcamento orcamento) {

            double icms = new ICMS().calculaICMS(orcamento); 
            // Mas e se quisermos outro imposto?

            System.out.println(icms);

          }

      }
Agora estamos presos ao ICMS. Precisamos que nosso código fique flexível o bastante para utilizarmos diferentes impostos na realização do cálculo. Uma possibilidade para resolvermos esse problema é, ao invés de instanciarmos o imposto que desejamos dentro do método, recebermos uma instância do Imposto que queremos utilizar, como no código seguinte:

      public class CalculadorDeImpostos {

          public void realizaCalculo(Orcamento orcamento, Imposto imposto) {

            double valor = imposto.calcula(orcamento); 

            System.out.println(valor);

          }

      }
No entanto, não temos o tipo Imposto em nossa aplicação e além disso, nesse tipo precisamos passar uma instância de ISS e ICMS. Para isso, podemos criar uma interface chamada Imposto e fazermos as classes ISS e ICMS a implementar.

      public interface Imposto {
          double calcula(Orcamento orcamento);
      }

      public class ICMS implements Imposto {

          public double calcula(Orcamento orcamento) {
              return orcamento.getValor() * 0.1;
          }

      }

      public class ISS implements Imposto {

          public double calcula(Orcamento orcamento) {
              return orcamento.getValor() * 0.06;
          }

      }
E agora o nosso CalculadorDeImposto está pronto para ser utilizado e flexível o bastante para receber diferentes tipos (ou "estratégias") de impostos. Um código que demonstra essa flexibilidade é o seguinte:

      public class TesteDeImpostos {

          public static void main(String[] args) {
              Imposto iss = new ISS();
              Imposto icms = new ICMS();

              Orcamento orcamento = new Orcamento(500.0);

          CalculadorDeImposto calculador = new CalculadorDeImposto();

          // Calculando o ISS
          calculador.realizaCalculo(orcamento, iss);

          // Calculando o ICMS        
          calculador.realizaCalculo(orcamento, icms);
          }
      }
Agora, com um único método em nosso CalculadorDeImposto, podemos realizar o cálculo de diferentes tipos de impostos, apenas recebendo a estratégia do tipo do imposto que desejamos utilizar no cálculo.

Quando utilizamos uma hierarquia, como fizemos com a interface Imposto e as implementações ICMS e ISS, e recebemos o tipo mais genérico como parâmetro, para ganharmos o polimorfismo na regra que será executada, simplificando o código e sua evolução, estamos usando o Design Pattern chamado Strategy.

Repare que a criação de uma nova estratégia de cálculo de imposto não implica em mudanças no código escrito acima! Basta criarmos uma nova classe que implementa a interface Imposto, que nosso CalculadorDeImposto conseguirá calculá-lo sem precisar de nenhuma alteração!
