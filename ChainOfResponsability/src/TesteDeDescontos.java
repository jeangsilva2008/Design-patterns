
public class TesteDeDescontos {

	public static void main(String[] args) {
		
		CalculadorDeDesconto calculador = new CalculadorDeDesconto();
		
		Orcamento orcamento = new Orcamento(500);
		
		orcamento.adicionaItem(new Item("caneta", 600));
		orcamento.adicionaItem(new Item("lapis", 20));
		
		double descontoFinal = calculador.calcula(orcamento);
		
		System.out.println(descontoFinal);
	}
	
	
}
