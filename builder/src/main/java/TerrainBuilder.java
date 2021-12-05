/**
 * @author Qh
 * @version 1.0
 * @date 2021-12-03 21:48
 */
public interface TerrainBuilder {
    TerrainBuilder buildWall();
    TerrainBuilder buildFort();
    TerrainBuilder buildMine();
    Terrain build();
}
