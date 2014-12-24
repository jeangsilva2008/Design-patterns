
public class DescontoPorMaisDeQuinhentos implements Desconto{
	
	
	private Desconto proximo;	

	
	public double desconta(Orcamento orcamento){
		 if(orcamento.getValor() > 500){
			 return orcamento.getValor() * 0.01;
		 }else{
			 return proximo.desconta(orcamento);
		 }
	}

	
	@Override
	public void setProximo(Desconto proximo) {
		this.proximo = proximo;
		
	}
	

}
