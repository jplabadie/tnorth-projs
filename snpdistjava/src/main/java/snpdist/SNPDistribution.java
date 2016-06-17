package snpdist;

import java.io.File;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/25/16.
 *
 * @author jlabadie
 */
public interface SNPDistribution {

    String getSnpDensityRanges(File matrix, int window_size, int step_size);
}
