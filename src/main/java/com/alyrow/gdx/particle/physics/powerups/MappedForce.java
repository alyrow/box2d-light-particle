package com.alyrow.gdx.particle.physics.powerups;

import com.alyrow.gdx.particle.physics.PhysicForce;
import com.alyrow.gdx.particle.physics.PhysicParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Vector2;

import java.util.zip.Deflater;

public class MappedForce extends PhysicForce {

    protected Vector2[][] map;
    protected int pad = 0;
    protected PhysicParticle particle;

//    public MappedForce(float[][][] fls) {
//        map = new Vector2[fls.length][fls[0].length];
//        for (int i = 0; i < fls.length; i++)
//            for (int j = 0; j < fls[0].length; j++)
//                map[i][j] = new Vector2(fls[i][j][0], fls[i][j][1]);
//    }

    //public MappedForce(Vector2[][] map) {
    //  this.map = map;
    //}

    public MappedForce(PhysicForce initForce, boolean enablePad, Camera camera) {
        if (enablePad) pad = 50;
        particle = new PhysicParticle(0, 0, null, camera);
        map = new Vector2[Gdx.graphics.getWidth() + 2 * pad][Gdx.graphics.getHeight() + 2 * pad];
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++) {
                particle.x = i;
                particle.y = j;
                map[i][j] = new Vector2(initForce.getForce(particle));
            }

    }

    public void addForce(PhysicForce force) {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++) {
                particle.x = i;
                particle.y = j;
                map[i][j] = map[i][j].add(new Vector2(force.getForce(particle)));
            }
    }

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        try {
            return map[(int) particle.x + pad][(int) particle.y + pad];
        } catch (IndexOutOfBoundsException ignored) {
            return Vector2.Zero;
        }
    }

    /**
     * <b> <u> A Data Packing Tool. </u> </b>
     *
     * <p>
     * A simple to use and complex to understand tool to pack
     * 2 information pieces into a byte matrix:
     * </p>
     *
     * <ol>
     *     <li> <p>
     *         A <u>header</u> metadata which contains
     *         custom data, such as center coordinates,
     *         magnitude, bounds and similar properties.
     *     </p> </li>
     *     <li> <p>
     *         The main <u>map</u> info which contains
     *         force vectors as a matrix of Vector2.
     *     </p> </li>
     * </ol>
     *
     * <p>
     * This byte matrix afterwards can be converted to a
     * pixmap to be stored as an image. Note while saving
     * it's recommended to use {@link Deflater.NO_COMPRESSION}.
     * </p>
     *
     * <p>
     * The data structure is as follows:
     * </p>
     *
     * <ul>
     *     <li> <p>
     *         The <code> width:w </code> is twice that
     *         of the provided map! <br />
     *         <code> maw = map[0].length // maw -> map width</code>
     *         <code> w = 2 * maw </code>
     *     </p> </li>
     *     <li> <p>
     *         The <code> height:h </code> comprises of
     *         <ol>
     *             <li> <p>
     *                 A <code> height:mdh </code> enough to store
     *                 completely the header given in as header's
     *                 length < mdh * w. <br />
     *                 Such a value, mdh, is calculated as <br />
     *                 <code> (int) Math.ceil(header.length / (double) w) </code>
     *             </p> </li>
     *             <li> <p>
     *                 A height of <code> 1 </code>, just to give a
     *                 row of complete zeroes, acting as a separator
     *                 between the header and map data.
     *             </p> </li>
     *             <li> <p>
     *                 The <code> height:mah </code> of the provided map. <br />
     *                 <code> map.length </code>
     *             </p> </li>
     *         </ol>
     *     </p> </li>
     * </ul>
     *
     * <table>
     *   <thead>
     *     <tr>
     *       <th>Area</th>
     *       <th>Data</th>
     *       <th>Info</th>
     *     </tr>
     *    </thead>
     *    <tbody>
     *      <tr>
     *        <td> <code> mdh*w </code> </td>
     *        <td> Header </td>
     *        <td> Information used by a specific force. </td>
     *      </tr>
     *      <tr>
     *        <td> <code> mah*w </code> </td>
     *        <td> Separator </td>
     *        <td> An indicator of separation between metadata and the map data </td>
     *      </tr>
     *      <tr>
     *        <td> <code> 1 * w </code> </td>
     *        <td> Map data </td>
     *        <td> A matrix of forces </td>
     *      </tr>
     *      <tr>
     *      </tr>
     *   </tbody>
     * </table>
     */
    public static int[][] packNicely(byte[] header, Vector2[][] map) {

        int maw = map.length;
        int w = 2 * maw;
        int mdh = (int) Math.ceil(header.length / (double) w);
        int mah = map[0].length;
        int h = mah + mdh + 1;

        int[][] data = new int[w][h];

        for (int j = 0, c = 0; j < mdh + 1; j++)
            for (int i = 0; i < w; i++, c++)
                data[i][j] = c < header.length ? header[c] : 0;

        for (int i = 0; i < maw; i++) {
            for (int j = mdh + 1, maj = 0; j < h; j++, maj++) {
                data[i][j] /* */ = (int) map[i][maj].x;
                data[i + maw][j] = (int) map[i][maj].y;
            }
        }

        return data;
    }

    public static Vector2[][] unpackNicelyGetMap(int[][] data) {

        int hj = 0;
        for (hj = 0; hj < data[0].length; hj++) {
            boolean test = true;
            for (int i = 0; i < data.length; i++) {
                if (data[i][hj] != 0) {
                    test = false;
                    break;
                }
            }
            if (test) break;
        }

        int maw = data.length / 2;
        int mah = data[0].length - hj - 1;
        Vector2[][] map = new Vector2[maw][mah];

        for (int i = 0; i < maw; i++)
            for (int j = 0, k = hj + 1; j < mah; j++, k++)
                map[i][j] = new Vector2(data[i][k], data[i + maw][k]);

        return map;
    }

    public static Pixmap toPixmap(int[][] data) {
        Pixmap map = new Pixmap(data.length, data[0].length, Pixmap.Format.RGBA8888);

        for (int i = 0; i < map.getWidth(); i++)
            for (int j = 0; j < map.getHeight(); j++)
                map.drawPixel(i, j, data[i][j]);

        return map;
    }

    public static int[][] toData(Pixmap map) {
        int[][] data = new int[map.getWidth()][map.getHeight()];

        for (int i = 0; i < map.getWidth(); i++)
            for (int j = 0; j < map.getHeight(); j++)
                data[i][j] = map.getPixel(i, j);

        return data;
    }

}
