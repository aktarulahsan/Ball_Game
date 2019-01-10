/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ballgame;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author User
 */
public class Ball {

    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    private static final int DIAMETER = 30;
    private Game game;

    public Ball(Game game) {
        this.game = game;

    }

    void move() {
        boolean changeDirection = true;
        if (x + xa < 0) {
            xa = game.speed;
        } else if (x + xa > game.getWidth() - DIAMETER) {
            xa = -game.speed;
        } else if (y + ya < 0) {
            ya = game.speed;
        } else if (y + ya > game.getHeight() - DIAMETER) {
            //ya = -1;
            game.gameOver();
        } else if (collision()) {
            ya = -game.speed;
            y = game.racquet.getTopY() - DIAMETER;
            game.speed++;
            Sound.BALL.play();
        } else {
            changeDirection = false;
        }
        if (changeDirection) {
            //Sound.BALL.play();
        }

        x = x + xa;
        y = y + ya;
    }

    private boolean collision() {
        return game.racquet.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, 30, 30);
    }
}