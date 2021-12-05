/**
 * 构建者模式
 * 分离复杂对象的构建和表示
 * 同样的构建过程可以创建不同的表示
 *
 *
 * @author Qh
 * @version 1.0
 * @date 2021-12-03 21:36
 */
public class BuilderTest {

    public static void main(String[] args) {
        ComplexTerrainBuilder complexTerrainBuilder = new ComplexTerrainBuilder();
        Terrain terrain = complexTerrainBuilder.buildWall().buildFort().buildMine().build();

        Person person = new Person.PersonBuilder().basicInfo(1, "qinhao",18)
                .score(100)
                .weight(120)
                .loc("cd", "138")
                .build();
    }
}
