/**
 * Terrain:地形
 *
 * @author Qh
 * @version 1.0
 * @date 2021-12-03 21:45
 */
public class Terrain {
    Wall w;
    Fort f;
    Mine m;
}

/**
 * 墙
 */
class Wall {
    int x,y,w,h;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}

/**
 * 暗堡
 */
class Fort {
    int x, y, w, h;

    public Fort(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}


/**
 * 地雷
 */
class Mine {
    int x, y, w, h;

    public Mine(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}
