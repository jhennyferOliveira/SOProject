

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Components extends JFrame implements ActionListener {
    private ImageIcon tvIcon;
    private JLabel labelTV;
    private JLabel labelCanal;
    private JPanel panel;
    private JPanel cardPanel;
    private JLabel labelPerson;
    private JLabel labelPersonSleeping;
    private ImageIcon personWatchingIcon;
    private ImageIcon personSleepingIcon;
    private ArrayList<JPanel> cards  = new ArrayList<JPanel>();
    private ArrayList<JLabel> guestsTimeSleeping  = new ArrayList<JLabel>();
    private ArrayList<JLabel> guestsTimeWatching  = new ArrayList<JLabel>();
    private ArrayList<JLabel> guestsFavoriteChannel  = new ArrayList<JLabel>();
    private JLabel guestNameLabel;
    private JPanel panelStackInfo;
    private JLabel channel;
    private JLabel timeWatching;
    private ImageIcon timeWatchingIcon;
    private ImageIcon timeSleepingIcon;
    private ImageIcon remoteControlIcon;
    private JLabel timeSleeping;
    private JLabel timeSleepingLabel;
    private JLabel timeWatchingLabel;
    private JLabel channelLabel;
    private JButton insertGuestButton;
    JTextField fieldSleepingTime;
    JTextField fieldWatchingTime;
    JTextField fieldFavoriteChannel;
    private int countHospedes;

    public void setUpComponents() {
        panel = new JPanel();
        panel.setBounds(50, 200, 900, 550);
        panel.setLayout(new GridLayout(2,5,32,32));
        add(panel);
        setUpImageTV();
        setUpLabelCanal();
        setUpCards();
        setUpButton();
        labelTV.add(labelCanal);
    }

    public void setUpImageTV() {
        tvIcon = new ImageIcon(getClass().getResource("/assets/tv.png"));
        Image image = tvIcon.getImage();
        Image newTvImage = image.getScaledInstance(150, 140,  java.awt.Image.SCALE_SMOOTH);
        tvIcon = new ImageIcon(newTvImage);
        labelTV = new JLabel();
        labelTV.setText("");
        labelTV.setBounds(450,-10,200,200);
        labelTV.setIcon(tvIcon);
        add(labelTV);

    }

    public void setUpLabelCanal() {
        labelCanal = new JLabel();
        labelCanal.setText("5");
        labelCanal.setLayout(null);
        labelCanal.setBounds(47,100,20,30);
        labelCanal.setFont(labelCanal.getFont().deriveFont(28f));
        labelCanal.setVisible(false);
        add(labelCanal);
    }


    public void setUpCards() {
        for (int i = 0; i<10; i++) {
            cardPanel = new JPanel();
            cardPanel.setLayout(null);
            cardPanel.setSize(101, 120);
            cardPanel.setBackground(new Color(0xD4D2D2));
            cardPanel.setName(Integer.toString(i + 1));
            cardPanel.setVisible(true);
            cardPanel.add(setUpDormindo(i));
//            cardPanel.add(setUpPersonWatching(i));
            cardPanel.add(setUpGuestNameLabel(i));
            cardPanel.add(setUpInfoStack());
            cardPanel.setVisible(false);
            cards.add(cardPanel);
            panel.add(cardPanel);

        }
    }


    public JLabel setUpPersonWatching(int i){
        labelPerson = new JLabel();
        personWatchingIcon = new ImageIcon(getClass().getResource("/assets/assistindo.png"));
        Image image = personWatchingIcon.getImage();
        Image newPersonImage = image.getScaledInstance(64, 72,  java.awt.Image.SCALE_SMOOTH);
        personWatchingIcon = new ImageIcon(newPersonImage);
        labelPerson.setText("");
        labelPerson.setBounds(43,25,64,72);
        labelPerson.setIcon(personWatchingIcon);
        labelPerson.setName(Integer.toString(i+1));
//        personImageLabels.add(labelPerson);
        return  labelPerson;


    }

    public JLabel setUpDormindo(int i) {

        labelPersonSleeping = new JLabel();
        personSleepingIcon = new ImageIcon(getClass().getResource("/assets/dormindo2.png"));
        Image image = personSleepingIcon.getImage();
        Image newPersonImage = image.getScaledInstance(94, 92,  Image.SCALE_SMOOTH);
        personSleepingIcon = new ImageIcon(newPersonImage);
        labelPersonSleeping.setText("");
        labelPersonSleeping.setBounds(44,7,94,92);
        labelPersonSleeping.setIcon(personSleepingIcon);
        labelPersonSleeping.setName(Integer.toString(i+1));
        return  labelPersonSleeping;

    }

    public JLabel setUpGuestNameLabel(int i) {
        guestNameLabel = new JLabel();
        guestNameLabel.setText("H" + Integer.toString(i+1) );
        guestNameLabel.setLayout(null);
        guestNameLabel.setBounds(62,105,50,30);
        guestNameLabel.setHorizontalTextPosition(JLabel.CENTER);
        guestNameLabel.setFont(guestNameLabel.getFont().deriveFont(18f));
        return guestNameLabel;
    }

    public JPanel setUpInfoStack() {
        panelStackInfo = new JPanel();
        panelStackInfo.setBounds(30, 130, 100, 120);
        panelStackInfo.setLayout(new GridLayout(3,2,21,10));
        panelStackInfo.setBackground(new Color(0xD4D2D2));

        timeWatchingIcon = new ImageIcon(getClass().getResource("/assets/tvGuest.png"));
        Image timeWathingImage = timeWatchingIcon.getImage();
        Image newTimeWatchingImage = timeWathingImage.getScaledInstance(28, 28,  Image.SCALE_SMOOTH);
        timeWatchingIcon = new ImageIcon(newTimeWatchingImage);

        remoteControlIcon = new ImageIcon(getClass().getResource("/assets/remoteControl.png"));
        Image controlImage = remoteControlIcon.getImage();
        Image newControlImage = controlImage.getScaledInstance(24, 30,  Image.SCALE_SMOOTH);
        remoteControlIcon = new ImageIcon(newControlImage);

        timeSleepingIcon = new ImageIcon(getClass().getResource("/assets/sleepingIcon.png"));
        Image timeSleepingImage = timeSleepingIcon.getImage();
        Image newTimeSleepingImage = timeSleepingImage.getScaledInstance(25, 25,  Image.SCALE_SMOOTH);
        timeSleepingIcon = new ImageIcon(newTimeSleepingImage);

        // labels that have image icon
        timeWatching = new JLabel();
        timeWatching.setText("");
        timeWatching.setHorizontalTextPosition(JLabel.CENTER);
        timeWatching.setIcon(timeWatchingIcon);

        timeSleeping = new JLabel();
        timeSleeping.setText("");
        timeSleeping.setHorizontalTextPosition(JLabel.CENTER);
        timeSleeping.setIcon(timeSleepingIcon);


        channel = new JLabel();
        channel.setText("");
        channel.setHorizontalTextPosition(JLabel.CENTER);
        channel.setIcon(remoteControlIcon);

        // labels without image
        channelLabel = new JLabel();
        channelLabel.setText("2");
        channelLabel.setFont(channelLabel.getFont().deriveFont(18f));
        guestsFavoriteChannel.add(channelLabel);

        timeWatchingLabel = new JLabel();
        timeWatchingLabel.setText("25");
        timeWatchingLabel.setFont(timeWatchingLabel.getFont().deriveFont(18f));
        guestsTimeWatching.add(timeWatchingLabel);

        timeSleepingLabel = new JLabel();
        timeSleepingLabel.setText("30");
        timeSleepingLabel.setFont(timeSleepingLabel.getFont().deriveFont(18f));
        guestsTimeSleeping.add(timeSleepingLabel);

        // adding elements to stack
        panelStackInfo.add(channel);
        panelStackInfo.add(channelLabel);
        panelStackInfo.add(timeWatching);
        panelStackInfo.add(timeWatchingLabel);
        panelStackInfo.add(timeSleeping);
        panelStackInfo.add(timeSleepingLabel);

        return panelStackInfo;
    }

    public void setUpButton(){
        insertGuestButton = new JButton();
        insertGuestButton.setBounds(400, 850, 200, 50);
        insertGuestButton.setText("Inserir Hóspede");
        insertGuestButton.addActionListener(this);
        add(insertGuestButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertGuestButton){
            if (countHospedes<10){
                showForm();
                showData();
            } else {
                showErrorMessage();
            }

        }

    };

    public void showErrorMessage() {
        JOptionPane.showMessageDialog(null,"Número máximo de hóspedes atingido.", "Erro ao cadastrar Hóspede", JOptionPane.PLAIN_MESSAGE );
    }

    public void showForm() {
        countHospedes++;
        fieldSleepingTime = new JTextField();
        fieldWatchingTime = new JTextField();
        fieldFavoriteChannel = new JTextField();

        Object[] fields = {
            "Tempo assistindo", fieldWatchingTime,
                "Tempo dormindo", fieldSleepingTime,
                "Canal Favorito", fieldFavoriteChannel
        };

        JOptionPane.showConfirmDialog(null, fields,"Informacões do hóspede", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public void showData() {
        JPanel guest = cards.get(countHospedes-1);
        guest.setVisible(true);

        JLabel guestChannel = guestsFavoriteChannel.get(countHospedes-1);
        guestChannel.setText(fieldFavoriteChannel.getText());

        JLabel guestTimeSleeping = guestsTimeSleeping.get(countHospedes-1);
        guestTimeSleeping.setText(fieldSleepingTime.getText());

        JLabel guestTimeWatching = guestsTimeWatching.get(countHospedes-1);
        guestTimeWatching.setText(fieldWatchingTime.getText());

    }

    public static void main(String[] args) {
        Components gui = new Components();
        gui.setLayout(null);
        gui.setUpComponents();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.setSize(1000, 1000);
        gui.setResizable(false);

    }
    
}
