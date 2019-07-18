import ClassesBasicas.Mochila;
import ClassesBasicas.Sala;
import ClassesDerivadas.Salas.*;
import ExcessoesDerivadas.EntradaIlegal;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Engine {
	private String[] tokens;
	private App janP;
	private Sala salaInicial;
	private Mochila mochila;
	private Sala salaCorrente;
	private boolean fim;

	public Engine(App jp) {
		criaFluxo();
		janP = jp;
		mochila = new Mochila();
		salaInicial.entra(mochila);
		salaCorrente = salaInicial;
		fim = false;
	}

	private void criaFluxo() {

		//Cria as salas
		Sala monte = new Monte();
		Sala casa = new CasaDoDaciolo();
		Sala havan = new Havan();
		Sala igreja = new IgrejaEvangelica();
		Sala foro = new ForoDeSP();
		Sala cristoRedendor = new CristoRedentor();
		Sala debate = new SalaDeDebate();
		Sala eleicao = new SenadoDeBrasilia();

		monte.getPortas().put(casa.getNome(), casa);

		casa.getPortas().put(igreja.getNome(), igreja);
		casa.getPortas().put(havan.getNome(), havan);

		havan.getPortas().put(casa.getNome(), casa);
		havan.getPortas().put(foro.getNome(), foro);

		igreja.getPortas().put(casa.getNome(), casa);		
		igreja.getPortas().put(cristoRedendor.getNome(), cristoRedendor);

		foro.getPortas().put(cristoRedendor.getNome(), cristoRedendor); 
		foro.getPortas().put(havan.getNome(), havan); 

		cristoRedendor.getPortas().put(foro.getNome(), foro); 
		cristoRedendor.getPortas().put(debate.getNome(), debate); 
		cristoRedendor.getPortas().put(igreja.getNome(), igreja);

		debate.getPortas().put(cristoRedendor.getNome(), cristoRedendor);
		debate.getPortas().put(eleicao.getNome(), eleicao);

		eleicao.getPortas().put(debate.getNome(), debate);

		salaInicial = monte;
	}

	public void joga(String acao) throws EntradaIlegal, ArrayIndexOutOfBoundsException {
		acao = acao.toUpperCase();
		String aux = acao.substring(0, 1);
		String aux2 = acao.toLowerCase();
		acao = aux + aux2.substring(1);

		if (fim) {
			fimDeJogo();
			return;
		}


		tokens = acao.split(" ");

		switch (tokens[0]) {
		case "Pega":
			ignoraCaixaAlta();
			try {
				if (salaCorrente.pega(tokens[1])) {
					if (ehEspecial()) {
						pegaFerramentasEspeciais();
					}
					if(tokens[1].equals("Megafone") || tokens[1].equals("Ursinho")) {
						janP.exibeTexto(salaCorrente.getNovo());
					}
					janP.exibeTexto("\nOk! " + tokens[1] + " na mochila.\n");
					limpaFerramentasUsadas();
				} else {
					janP.exibeTexto("\n" + tokens[1] + " não encontrado." + "\n");
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				janP.exibeTexto("\nÉ necessário escolher uma ferramenta!");
			} finally {
				if(salaCorrente.getFerramentas().isEmpty()) {
					janP.exibeTexto("\n Não há mais ferramentas para pegar");
				}else {
					janP.exibeTexto("\nFerramentas ao redor: " + salaCorrente.getFerramentas().keySet());
					janP.exibeTexto("\n" + salaCorrente.textoDescricao() + "\n");
				}
			}
			break;
		case "Inventario":
			janP.exibeTexto("\n Conteúdo da mochila: " + mochila.inventario() + "\n");
			break;
		case "Usa":
			ignoraCaixaAlta();
			try {
				if (salaCorrente.usa(tokens[1])) {
					janP.setImagem(salaCorrente.getImg(tokens[1]));
					if(salaCorrente.getNome().equals("Debate") || salaCorrente.getNome().equals("Igreja") || salaCorrente.getNome().equals("Cristo")) {
						janP.exibeTexto(salaCorrente.getNovo());
					}else {
						janP.exibeTexto("\nFeito!");
					}
				} else {
					janP.exibeTexto("\nNão é possível usar " + tokens[1] + " nesta sala");
				}
			} catch (EntradaIlegal e) {
				fim = true;
				fimDeJogo();
			} catch (ArrayIndexOutOfBoundsException f) {
				janP.exibeTexto("\nÉ necessário escolher uma ferramenta!");
			}
			janP.exibeTexto("\nFerramentas ao redor: " + salaCorrente.getFerramentas().keySet());
			break;
		case "Sai":
			ignoraCaixaAlta();
			try {
				Sala novaSala = salaCorrente.sai(tokens[1]);
				if (novaSala == null) {
					janP.exibeTexto("\nSala desconhecida ...\n");
				} else {
					novaSala.entra(mochila);
					salaCorrente = novaSala;
					janP.setImagem(salaCorrente.getRepVisual());
					janP.limpaTela();
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				janP.exibeTexto("\nÉ necessário escolher uma sala!");
			} finally {
				janP.exibeTexto("\n" + salaCorrente.textoDescricao() + "\n");
			}
			break;
		case "Start":
			janP.exibeTexto("\n" + salaCorrente.textoDescricao() + "\n");
			janP.setImagem(1);
			break;
		default:
			janP.exibeTexto("\nComando desconhecido: " + tokens[0] + "\n");
			break;
		}
	}

	private void fimDeJogo() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fim de jogo !!");
		alert.setHeaderText("Parabéns !!");
		alert.setContentText("Voce não conseguiu  !!\nFIM DE JOGO");
		alert.showAndWait();
	}

	private boolean ehEspecial() {
		boolean EhEspecial = false;

		if (tokens[1].equals("Dinamite"))
			EhEspecial = true;

		if (tokens[1].equals("Megafone"))
			EhEspecial = true;

		if (tokens[1].equals("Ursinho"))
			EhEspecial = true;

		if (tokens[1].equals("Faixa"))
			EhEspecial = true;

		return EhEspecial;
	}

	private void pegaFerramentasEspeciais() {
		switch (tokens[1]) {
		case "Dinamite":
			janP.setImagem(salaCorrente.getImg(tokens[1]));
			break;

		case "Megafone":
			janP.setImagem(salaCorrente.getImg(tokens[1]));
			break;

		case "Ursinho":
			janP.setImagem(salaCorrente.getImg(tokens[1]));
			break;

			//            case "Faixa":
			//            	janP.setImagem(salaCorrente.getImg(tokens[1]));
			//                break;

		default:
			break;
		}
	}

	private void limpaFerramentasUsadas() {
		switch (tokens[1]) {
		case "Lampião":
			salaCorrente.getFerramentas().remove("Lampião");
			break;
		case "Foice":
			salaCorrente.getFerramentas().remove("Foice");
			break;
		case "Dinamite":
			salaCorrente.getFerramentas().remove("Dinamite");
			break;
		case "Bíblia":
			salaCorrente.getFerramentas().remove("Bíblia");
			break;
		case "Megafone":
			salaCorrente.getFerramentas().remove("Megafone");
			break;
		case "Ursinho":
			salaCorrente.getFerramentas().remove("Ursinho");
			break;
		case "Faixa":
			salaCorrente.getFerramentas().remove("Faixa");
			break;
		default:
			System.err.println("Ops! Você encontrou uma Exception!");
			break;
		}
	}

	private void ignoraCaixaAlta() throws ArrayIndexOutOfBoundsException {
		try {
			tokens[1] = tokens[1].toUpperCase();
			String aux3 = tokens[1].substring(0, 1);
			String aux4 = tokens[1].toLowerCase();
			tokens[1] = aux3 + aux4.substring(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.getMessage();
		}
	}

}
