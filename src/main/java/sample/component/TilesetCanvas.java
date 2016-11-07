package sample.component;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.IgnisGlobals;
import sample.engine.Tileset;

/**
 * @author Fabien Steines
 */
public class TilesetCanvas extends Canvas {

    private final int cellSize;
    private Tileset linkedTileset;
    private int lastX;
    private int lastY;

    private int mouseStartX;
    private int mouseStartY;
    private int mouseEndX;
    private int mouseEndY;

    public TilesetCanvas() {

        this.lastX = -1;
        this.lastY = -1;
        this.cellSize = 32;

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, t -> {

            int x = (int)t.getX()/32;
            int y = (int)t.getY()/32;

            this.mouseStartX = x;
            this.mouseStartY = y;
            this.mouseEndX = x;
            this.mouseEndY = y;
            lastX = x;
            lastY = y;

            render();
        });

        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, t -> {

            int x = (int)t.getX()/32;
            int y = (int)t.getY()/32;

            if (x != lastX || y != lastY) {
                this.render();

                this.mouseEndX = x;
                this.mouseEndY = y;
                lastX = x;
                lastY = y;

                render();
            }

        });
    }

    public void render() {
        GraphicsContext g = this.getGraphicsContext2D();
        g.clearRect(0,0,this.getWidth(),this.getHeight());
        if (this.linkedTileset != null) {

            this.drawChessBackground(g);

            g.drawImage(this.linkedTileset.getTilesetImage(),0,0);

            int[] coords = IgnisGlobals.fixCoords(this.mouseStartX,this.mouseStartY,this.mouseEndX,this.mouseEndY);

            g.setFill(Color.RED);
            g.setGlobalAlpha(0.5);
            g.fillRect(coords[0] * this.cellSize,coords[1]  * this.cellSize,
                    (coords[2] - coords[0])  * this.cellSize,(coords[3] - coords[1]) * this.cellSize);
            g.setGlobalAlpha(1);

        }
    }

    private void drawChessBackground(GraphicsContext g) {
        for (int i = 0; i < this.getWidth() / 16; i++) {
            for (int j = 0; j < this.getHeight() / 16; j++) {

                if ((i + j) % 2 == 0) {
                    g.setFill(Color.WHITE);
                } else {
                    g.setFill(Color.LIGHTGRAY);
                }

                g.fillRect(i * 16, j * 16, 16, 16);
            }
        }
    }

    public void setTileset(Tileset tileset) {
        if (tileset != null) {
            this.setWidth(tileset.getTilesetImage().getWidth());
            this.setHeight(tileset.getTilesetImage().getHeight());
            this.linkedTileset =  tileset;
        } else {
            this.linkedTileset = null;
            this.setWidth(0);
            this.setHeight(0);
        }
    }

}
