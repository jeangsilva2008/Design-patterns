
public class CalculadorDeImpostos {


	void realizaCalculo(Orcamento orcamento, Imposto impostoQualquer){
		double valor = impostoQualquer.calcula(orcamento);
		System.out.println(valor);
	}
}
