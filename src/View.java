import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
    Frame - janela em que a view se encontra
    Panel - containers que são colocados dentro da view a fim de organizar os componentes
    JLabel - apresentar uma label na tela
    *OBS: Para mostrar uma imagem, é necessário criar uma label e adicionar uma ImageIcon a ela, caso não
    queira mostrar a label é só deixar o texto em branco: " "

 */
public class View extends JFrame implements ActionListener {
    private JLabel labelIconeTV;
    private JPanel panel;
    private ArrayList<JPanel> cards  = new ArrayList<JPanel>();
    private ArrayList<JLabel> hospedesTempoDormindo  = new ArrayList<JLabel>();
    private ArrayList<JLabel> hospedesTempoAssistindo  = new ArrayList<JLabel>();
    private ArrayList<JLabel> hospedesCanalFavorito  = new ArrayList<JLabel>();
    private ArrayList<JLabel> hospedesId  = new ArrayList<JLabel>();
    private JButton botaoInserirHospede;
    public JLabel labelTextoCanal;
    public JTextField campoTempoDormindo;
    public JTextField campoTempoAssistindo;
    public JTextField campoCanalFavorito;
    public JTextField fieldNumberOfChannels;
    public JTextField campoId;
    private int contaHospedes;

    private ImageIcon resizeImage(ImageIcon icone, int width, int height) {
        Image imagem = icone.getImage();
        Image novoIconeTV = imagem.getScaledInstance(width, height,  Image.SCALE_SMOOTH);
        icone = new ImageIcon(novoIconeTV);
        return icone;
    }

    public void setUpComponents() {
        // panel que mais externo responsavel por configurar o layout dos cards dos hospedes
        panel = new JPanel();
        panel.setBounds(50, 200, 900, 550);
        panel.setLayout(new GridLayout(2,5,32,32));
        add(panel); // adding panel into the frame
        configuraImagemTV();
        configuraLabelCanal();
        configuraCards();
        configuraBotaoInserirHospede();
        labelIconeTV.add(labelTextoCanal);
    }

    public void configuraImagemTV() {
        ImageIcon iconeTV = new ImageIcon(getClass().getResource("/assets/tv.png"));
        iconeTV = resizeImage(iconeTV,150,140);
        labelIconeTV = new JLabel();
        labelIconeTV.setText("");
        labelIconeTV.setBounds(450,-10,200,200);
        labelIconeTV.setIcon(iconeTV);
        add(labelIconeTV);
    }

    public void configuraLabelCanal() {
        labelTextoCanal = new JLabel();
        labelTextoCanal.setText("5");
        labelTextoCanal.setLayout(null);
        labelTextoCanal.setBounds(47,100,20,30);
        labelTextoCanal.setFont(labelTextoCanal.getFont().deriveFont(28f));
        labelTextoCanal.setVisible(false);
        add(labelTextoCanal);
    }

    public void configuraCards() {
        for (int i = 0; i<10; i++) { // cria todos os cards da tela
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(null);
            cardPanel.setSize(101, 120);
            cardPanel.setBackground(new Color(0xD4D2D2));
            cardPanel.setName(Integer.toString(i + 1));
            cardPanel.setVisible(true);
            cardPanel.add(configuraImagemHospedeDormindo(i));
            cardPanel.add(configuraStackDeInformacoesDoHospede()); // adiciona a stack que contem canal preferido, tempo assist, etc ao cardPanel
            cardPanel.setVisible(false); // deixa os cards invisiveis, quando o usuario digitar um novo hospede ele fica visivel
            cards.add(cardPanel); // adiciona o card em um array para que possamos acessa-lo futuramente
            panel.add(cardPanel); // adiciona o cardPanel no panel que foi criado anteriormente na funcao setUpComponents() para organizar o layout
        }
    }

    // esse i serve para que depois seja possivel acessar o JLabel inserido no array, em que cada posicao corresponde a um hospede
    // essa funcao apenas retorna a imagem da pessoa dormindo ao cardPanel
    public JLabel configuraImagemHospedeDormindo(int i) {
        JLabel labelIconePessoaDormindo = new JLabel();
        ImageIcon iconePessoaDormindo = new ImageIcon(getClass().getResource("/assets/dormindo2.png"));
        iconePessoaDormindo = resizeImage(iconePessoaDormindo, 94,92);
        labelIconePessoaDormindo.setText("");
        labelIconePessoaDormindo.setBounds(44,7,94,92);
        labelIconePessoaDormindo.setIcon(iconePessoaDormindo);
        labelIconePessoaDormindo.setName(Integer.toString(i+1));
        return labelIconePessoaDormindo;

        // tamanho imagem pessoa assistindo: width: 64, height: 72
        // posicao imagem pessoa assistindo: x:43, y:25, width:64, height:72)
    }

    // monta a stack de informacao do card
    public JPanel configuraStackDeInformacoesDoHospede() {
        JPanel panelStackInfo = new JPanel();
        panelStackInfo.setBounds(40, 105, 100, 140);
        panelStackInfo.setLayout(new GridLayout(4,2,21,10)); // configura o layout da informacao do hospede em 4 linhas e 2 colunas, espaco horizontal de 21 e vertical de 10
        panelStackInfo.setBackground(new Color(0xD4D2D2));

        // cria os icones
        ImageIcon iconeId = new ImageIcon(getClass().getResource("/assets/id-pass.png"));
        iconeId = resizeImage(iconeId, 27,24);

        ImageIcon iconeTempoAssistindo = new ImageIcon(getClass().getResource("/assets/tvGuest.png"));
        iconeTempoAssistindo = resizeImage(iconeTempoAssistindo, 27,24);

        ImageIcon iconeControleRemoto = new ImageIcon(getClass().getResource("/assets/remoteControl.png"));
        iconeControleRemoto = resizeImage(iconeControleRemoto, 20,30);

        ImageIcon iconeTempoDormindo = new ImageIcon(getClass().getResource("/assets/sleepingIcon.png"));
        iconeTempoDormindo = resizeImage(iconeTempoDormindo, 28,28);

        // adiciona os icones a label para que possam ser mostrados na tela, labels apenas para apresentar as imagens
        JLabel labelIconeId = new JLabel();
        labelIconeId.setText("");
        labelIconeId.setHorizontalTextPosition(JLabel.CENTER);
        labelIconeId.setIcon(iconeId);

        JLabel labelIconeTempoAssistindo = new JLabel();
        labelIconeTempoAssistindo.setText("");
        labelIconeTempoAssistindo.setHorizontalTextPosition(JLabel.CENTER);
        labelIconeTempoAssistindo.setIcon(iconeTempoAssistindo);

        JLabel labelIconeTempoDormindo = new JLabel();
        labelIconeTempoDormindo.setText("");
        labelIconeTempoDormindo.setHorizontalTextPosition(JLabel.CENTER);
        labelIconeTempoDormindo.setIcon(iconeTempoDormindo);

        JLabel labelIconeControleRemoto = new JLabel();
        labelIconeControleRemoto.setText("");
        labelIconeControleRemoto.setHorizontalTextPosition(JLabel.CENTER);
        labelIconeControleRemoto.setIcon(iconeControleRemoto);

        // configura labels que mostram o texto relacionado a cada icone
        JLabel labelTextoId = new JLabel();
        labelTextoId.setText("2");
        labelTextoId.setFont(labelTextoId.getFont().deriveFont(18f));
        hospedesId.add(labelTextoId);

        JLabel labelTextoCanal = new JLabel();
        labelTextoCanal.setText("2");
        labelTextoCanal.setFont(labelTextoCanal.getFont().deriveFont(18f));
        hospedesCanalFavorito.add(labelTextoCanal);

        JLabel labelTextoTempoAssistindo = new JLabel();
        labelTextoTempoAssistindo.setText("25");
        labelTextoTempoAssistindo.setFont(labelTextoTempoAssistindo.getFont().deriveFont(18f));
        hospedesTempoAssistindo.add(labelTextoTempoAssistindo);

        JLabel labelTextoTempoDormindo = new JLabel();
        labelTextoTempoDormindo.setText("30");
        labelTextoTempoDormindo.setFont(labelTextoTempoDormindo.getFont().deriveFont(18f));
        hospedesTempoDormindo.add(labelTextoTempoDormindo);

        // adiciona os icones e labels ao panel stack
        panelStackInfo.add(labelIconeId);
        panelStackInfo.add(labelTextoId);
        panelStackInfo.add(labelIconeControleRemoto);
        panelStackInfo.add(labelTextoCanal);
        panelStackInfo.add(labelIconeTempoAssistindo);
        panelStackInfo.add(labelTextoTempoAssistindo);
        panelStackInfo.add(labelIconeTempoDormindo);
        panelStackInfo.add(labelTextoTempoDormindo);
        return panelStackInfo;
    }

    public void configuraBotaoInserirHospede(){
        botaoInserirHospede = new JButton();
        botaoInserirHospede.setBounds(400, 850, 200, 50);
        botaoInserirHospede.setText("Inserir Hóspede");
        botaoInserirHospede.addActionListener(this);
        add(botaoInserirHospede);
    }

    // acao do botao de inserir hospede
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoInserirHospede){  // se quem enviou a acao foi o insertGuestButton
            if (contaHospedes<10){
                mostraFormulario(); // mostra o formulario em que o usuario pode cadastrar os dados do hospede
                mostraDadosObtidosPeloFormularioNoCard(); // mostra o card com as informacoes que o usuario inseriu
            } else { // caso o usuario tente cadastrar mais de 10 hospedes sera exibida uma mensagem dizendo que ele ja atingiu o limite maximo de hospedes.
                mostraMensagemDeErro();
            }
        }
    }

    // mensagem de erro quando o usuario tenta cadastrar mais de 10 hospedes
    public void mostraMensagemDeErro() {
        JOptionPane.showMessageDialog(null,"Número máximo de hóspedes atingido.",
                "Erro ao cadastrar Hóspede", JOptionPane.PLAIN_MESSAGE );
    }

    // mostra o formulario em que o usuario pode cadastrar os dados do hospede
    public void mostraFormulario() {
        contaHospedes++; // contagem para nao deixar o usuario cadastrar mais que 10 usuarios
        campoId = new JTextField();
        campoTempoDormindo = new JTextField();
        campoTempoAssistindo = new JTextField();
        campoCanalFavorito = new JTextField();
        Object[] fields = {
                "id", campoId,
                "Canal Favorito", campoCanalFavorito,
                "Tempo assistindo", campoTempoAssistindo,
                "Tempo dormindo", campoTempoDormindo,
        };
        JOptionPane.showConfirmDialog(null, fields,"Informacões do hóspede", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        tvThread.criaHospede();
    }

    // apresenta o formulario para o usuario adicionar o numero de canais
    public void mostraFormularioNumeroDeCanais() {
        fieldNumberOfChannels = new JTextField();
        Object[] fields = {
                "Número de canais", fieldNumberOfChannels,
        };
        JOptionPane.showConfirmDialog(null, fields,"Quantidade de canais", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    // mostra o card na tela com todas as informacoes que foram inseridas pelo usuario
    public void mostraDadosObtidosPeloFormularioNoCard() {
        JPanel cardHospede = cards.get(contaHospedes-1);
        cardHospede.setVisible(true);
        JLabel canalHospede = hospedesCanalFavorito.get(contaHospedes-1);
        canalHospede.setText(campoCanalFavorito.getText());
        JLabel tempoDormindo = hospedesTempoDormindo.get(contaHospedes-1);
        tempoDormindo.setText(campoTempoDormindo.getText());
        JLabel tempoAssistindo = hospedesTempoAssistindo.get(contaHospedes-1);
        tempoAssistindo.setText(campoTempoAssistindo.getText());
        JLabel id = hospedesId.get(contaHospedes-1);
        id.setText(campoId.getText());
    }
}
