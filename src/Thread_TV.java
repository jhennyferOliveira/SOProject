import javax.swing.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.concurrent.Semaphore;

class tvThread  {
    public static Semaphore mutex = new Semaphore(1);
    public static Semaphore tv = new Semaphore(1);
    public static int quant_assistindo = 0;
    public static int canal_atual = 0;
    public static int n_canais;
    public static int[] requisicao_canal;
    public static Semaphore[] quant_requisicoes;
    public static ArrayList<Hospede> hospedes  = new ArrayList<Hospede>();
    public static View gui;

    static void down(Semaphore s) {
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void up(Semaphore s) {
        s.release();
    }

    static class Hospede extends Thread {
        private String id;
        private int position;
        private int canal_favorito;
        private int tempo_assistir;
        private int tempo_descansar;

        public Hospede (String id, int canal_favorito, int tempo_assistir, int tempo_descansar) {
            this.id = id;
            this.canal_favorito = canal_favorito;
            this.tempo_assistir = tempo_assistir;
            this.tempo_descansar = tempo_descansar;
            this.position = hospedes.size();
        }

        void mudar_canal() {
            down(mutex);
            int fav_canal = this.canal_favorito - 1;
            if (canal_atual != this.canal_favorito) {
                if (requisicao_canal[fav_canal] == 0) {
                    requisicao_canal[fav_canal] += 1;
                    up(mutex);
                    down(tv);
                    down(mutex);
                    canal_atual = this.canal_favorito;
                    gui.labelTextoCanal.setText(String.valueOf(canal_atual));
                    gui.labelTextoCanal.setVisible(true);
                    gui.labelTextoCanal.repaint();
                    quant_requisicoes[fav_canal].release(requisicao_canal[fav_canal] - 1);
                    requisicao_canal[fav_canal] = 0;
                }
                else {
                    requisicao_canal[fav_canal] += 1;
                    up(mutex);
                    down(quant_requisicoes[fav_canal]);
                    down(mutex);
                }
            }
            up(mutex);
        }

        void simula_assistir(int tempo) {
        	gui.addLog(this.id + " Está assistindo.");
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < this.tempo_assistir * 1000) {
            	long time2 = System.currentTimeMillis();
            	while (System.currentTimeMillis() - time2 < 300) {};
            	gui.setImage(this.position, "/assets/assistindo1.png");            		
            	while (System.currentTimeMillis() - time2 < 600) {};
            	gui.setImage(this.position, "/assets/assistindo2.png");
            };
            int final_time = (int)((System.currentTimeMillis() - time)/1000);
            gui.addLog(String.format("%s assitiu por %d segundos", this.id, final_time));
        }

        void simula_descansar(int tempo) {
            gui.addLog(String.format("%s Está descansando", this.id));
            long time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < this.tempo_descansar * 1000) {
            	long time2 = System.currentTimeMillis();
            	while (System.currentTimeMillis() - time2 < 300) {};
            	gui.setImage(this.position, "/assets/dormindo2.png");            		
            	while (System.currentTimeMillis() - time2 < 600) {};
            	gui.setImage(this.position, "/assets/dormindo1.png");
            };
            int final_time = (int)((System.currentTimeMillis() - time)/1000);
            gui.addLog(String.format("%s descansou por %d segundos", this.id, final_time));
        }

        void assistir() {
            mudar_canal();
            quant_assistindo += 1;
            simula_assistir(this.tempo_assistir);
            down(mutex);
            quant_assistindo -= 1;
            if (quant_assistindo == 0) up(tv);
            up(mutex);
        }


        void descansar(){
            simula_descansar(this.tempo_descansar);
        }


        public void run() {
            while (true) {
                this.assistir();
                this.descansar();
            }
        }
    }


    static void init_reqs() {
        quant_requisicoes =  new Semaphore[n_canais];
        requisicao_canal = new int[n_canais];
        for(int i = 0; i < n_canais; i++) {
            quant_requisicoes[i] = new Semaphore(0);
            requisicao_canal[i] = 0;
        }
    }



    public static void criaHospede() {
        String identificador = gui.campoId.getText();
        int canalFavorito = Integer.parseInt(gui.campoCanalFavorito.getText());
        int tempoAssistindo = Integer.parseInt(gui.campoTempoAssistindo.getText());
        int tempoDormindo = Integer.parseInt(gui.campoTempoDormindo.getText());
        Hospede hospede = new Hospede(identificador, canalFavorito, tempoAssistindo, tempoDormindo);
        hospede.setPriority(1);
        hospede.start();
        hospedes.add(hospede);
    }
    
    public static void main(String[] args) {
        gui = new View();
        int resposta = gui.mostraFormularioNumeroDeCanais();
        if (resposta == -1) return;
        n_canais = resposta;
        gui.setLayout(null);
        gui.setUpComponents();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.setSize(1000, 1000);
        gui.setLocationRelativeTo( null );
        gui.setResizable(false);

        init_reqs();

    }

}



