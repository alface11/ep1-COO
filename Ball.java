import java.awt.*;

/**
    Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
    instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {
    private double coordenadaX;
    private double coordenadaY;
    private double width;
    private double height;
    private Color color;
    private double speed;

    private int directionX = 1;
    private int directionY = 1;

    private final double initialPositionX;
    private final double initialPositionY;

    /**
        Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
        (em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
        aleatóriamente pelo construtor.

        @param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
        @param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
        @param width largura do retangulo que representa a bola.
        @param height altura do retangulo que representa a bola.
        @param color cor da bola.
        @param speed velocidade da bola (em pixels por millisegundo).
    */

    public Ball(double cx, double cy, double width, double height, Color color, double speed){
        this.coordenadaX = cx;
        this.coordenadaY = cy;
        this.initialPositionX = cx;
        this.initialPositionY = cy;
        this.width = width;
        this.height = height;
        this.color = color;
        this.speed = speed;
    }


    /**
        Método chamado sempre que a bola precisa ser (re)desenhada.
    */

    public void draw(){

        GameLib.setColor(color);
        GameLib.fillRect(coordenadaX, coordenadaY, width, height);
    }

    /**
        Método chamado quando o estado (posição) da bola precisa ser atualizado.

        @param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
    */

    public void update(long delta){
        coordenadaX += directionX * speed * delta;
        coordenadaY += directionY * speed * delta;
    }

    /**
        Método chamado quando detecta-se uma colisão da bola com um jogador.

        @param playerId uma string cujo conteúdo identifica um dos jogadores.
    */

    public void onPlayerCollision(String playerId){
        if (playerId.equals("Player 1")) directionX = 1;
        else if (playerId.equals("Player 2")) directionX = -1;
    }

    /**
        Método chamado quando detecta-se uma colisão da bola com uma parede.

        @param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
    */

    public void onWallCollision(String wallId){
        if (wallId.equals("Bottom")) directionY = -1;
        else if (wallId.equals("Top")) directionY = 1;
        else if (wallId.equals("Right")) directionX = -1;
        else if (wallId.equals("Left")) directionX = 1;
    }

    /**
        Método que verifica se houve colisão da bola com uma parede.

        @param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
        @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
    */

    public boolean checkCollision(Wall wall){

        if (wall.getId().equals("Bottom")) {
            if (coordenadaY + height / 2 >= wall.getCy() - wall.getHeight() / 2) return true;
        } else if (wall.getId().equals("Top")) {
            if (coordenadaY - height / 2 <= wall.getCy() + wall.getHeight() / 2) return true;
        } else if (wall.getId().equals("Left")) {
            if (coordenadaX - width / 2 <= wall.getCx() + wall.getWidth() / 2) return true;
        } else if (wall.getId().equals("Right")) {
            if (coordenadaX + width / 2 >= wall.getCx() - wall.getWidth() / 2) return true;
        }

        return false;
    }

    /**
        Método que verifica se houve colisão da bola com um jogador.

        @param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
        @return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
    */	

    public boolean checkCollision(Player player){
        if (player.getId().equals("Player 2")) {
            if (coordenadaX + width / 2 >= player.getCx() - player.getWidth() / 2 && coordenadaX - width / 2 <= player.getCx() + player.getWidth() / 2) {
                if (player.getCy() - player.getHeight() / 2 <= coordenadaY + height / 2 && coordenadaY - height / 2 <= player.getCy() + player.getHeight() / 2) return true;
            }
        } else if (player.getId().equals("Player 1")) {
            if (coordenadaX - width / 2 <= player.getCx() + player.getWidth() / 2 && coordenadaX + width / 2 >= player.getCx() - player.getWidth() / 2) {
                if (player.getCy() - player.getHeight() / 2 <= coordenadaY + height / 2 && coordenadaY - height / 2 <= player.getCy() + player.getHeight() / 2) return true;
            }
        }

        return false;
    }

    /**
        Método que devolve a coordenada x do centro do retângulo que representa a bola.
        @return o valor double da coordenada x.
    */

    public double getCx(){

        return coordenadaX;
    }

    /**
        Método que devolve a coordenada y do centro do retângulo que representa a bola.
        @return o valor double da coordenada y.
    */

    public double getCy(){

        return coordenadaY;
    }

    /**
        Método que devolve a velocidade da bola.
        @return o valor double da velocidade.

    */

    public double getSpeed(){

        return speed;
    }

}
