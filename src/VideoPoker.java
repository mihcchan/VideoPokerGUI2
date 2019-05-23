import java.io.IOException;

/**
 * Classe VideoPoker que contém apenas o método main para executar o jogo.
 *
 * @author Michelle Schmitt Gmurczyk - 9424315
 * @author Ana Clara Amorim Andrade - 10691992+
 *
 */
public class VideoPoker {

    /**
     * Método main responsável pela execução do jogo. e possui um laço, no qual cada iteração representa uma rodada do jogo.
     * Em cada rodada, o jogador define sua aposta e pode, até 3 vezes, descartar entre 0 e 5 cartas da mão e tirar novas do baralho.
     * Após cada rodada a combinação de cartas é verificada e seus créditos atualizados.
     * O jogo termina se o jogador ficar sem créditos para apostar ou se ele quiser (fechando o jogo).
     * No final das rodadas a quatidade de créditos acumulados é exibida.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        Baralho baralho = new Baralho();
        Creditos saldo = new Creditos();

        int[] numeroCartas;
        int[] naipesCartas;

        int aposta;
        int saldoAnterior;

        while(saldo.getSaldo()>0){

            VideoPokerGUI frame = new VideoPokerGUI(saldo);
            frame.validate();

            saldoAnterior = saldo.getSaldo();
            try {
                aposta = frame.getAposta();
            } catch (NumberFormatException e){
                aposta = -1;
            }


            saldo.setAposta(aposta);
            if (aposta == -1){
                ApostaLetraGUI apostaLetraGUI = new ApostaLetraGUI();
            }else if(aposta == 0 ){
                ApostaInvalidaGUI apostaInvalidaGUI = new ApostaInvalidaGUI();
            }else if(aposta > saldoAnterior){
                ApostaMaiorQueSaldoGUI apostaMaiorQueSaldoGUI = new ApostaMaiorQueSaldoGUI(saldo);
            }else{
                baralho.sortear();

                CheckboxGUI checkbox = new CheckboxGUI(baralho);


                String segundaVez = checkbox.getStringDasCheckbox();
                try{
                    baralho.sortear(segundaVez);

                } catch (NumberFormatException e) {
                } 



                CheckboxGUI checkbox2 = new CheckboxGUI(baralho);
                String terceiraVez = checkbox2.getStringDasCheckbox();
                try {
                    baralho.sortear(terceiraVez);


                } catch (NumberFormatException e) {

                }





                numeroCartas = baralho.getNumerosCartas();
                naipesCartas = baralho.getNaipesCartas();


                ResultadoGUI resultadoGUI = new ResultadoGUI(baralho, saldo, numeroCartas, naipesCartas);

                baralho.resetaCartasJáTiradas();
            }

            /*Limpa os panels*/
            frame.dispose();
            frame.removeAll();
        }
        
        FimDeJogoGUI fimDeJogoGUI = new FimDeJogoGUI();
    }

}
