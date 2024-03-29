package org.pngquant;

import java.awt.image.BufferedImage;

/**
 * Starting point for the library. Holds configuration. Equivalent of liq_attr* in libimagequant.
 */
public class PngQuant extends org.pngquant.LiqObject {

    /**
     * Single instance can be "recycled" for many remappings.
     */
    public PngQuant() {
        handle = liq_attr_create();
    }

    public PngQuant(PngQuant other) {
        handle = liq_attr_copy(other.handle);
    }

    private static native long liq_attr_create();

    private static native long liq_attr_copy(long orig);

    private static native void liq_attr_destroy(long handle);

    /**
     * 1-shot quantization and remapping with current settings.
     *
     * @param bufimg b
     * @return 8-bit indexed image or null on failure
     * see quantize()
     */
    public BufferedImage getRemapped(BufferedImage bufimg) {
        try {
            org.pngquant.Image liqimg = new org.pngquant.Image(this, bufimg);
            BufferedImage remapped = getRemapped(liqimg);
            liqimg.close();
            return remapped;
        } catch (org.pngquant.PngQuantException e) {
            return null;
        }
    }

    /**
     * @param liqimg liqimg
     * @return remapped image or null on failure
     */
    public BufferedImage getRemapped(org.pngquant.Image liqimg) {
        org.pngquant.Result result = quantize(liqimg);
        if (result == null) {
            return null;
        }
        BufferedImage remapped = result.getRemapped(liqimg);
        result.close();
        return remapped;
    }

    /**
     * Performs quantization (chooses optimal palette for the given Image).
     * Returned object can be used to customize remapping and reused to remap other images to the same palette.
     *
     * @param img img
     * @return null on failure
     * @see <a href="http://pngquant.org/lib/#liq_quantize_image">...</a>
     */
    public org.pngquant.Result quantize(Image img) {
        try {
            return new Result(this, img);
        } catch (PngQuantException e) {
            return null;
        }
    }

    /**
     * Remapped images won't use more than given number of colors (may use less if setQuality() is used)
     *
     * @param colors colors
     * @return bool
     * @see <a href="http://pngquant.org/lib/#liq_set_max_colors">...</a>
     */
    public native boolean setMaxColors(int colors);

    /**
     * Equivalent of setQuality(target/2, target)
     *
     * @param target target
     * @return bool
     * @see <a href="http://pngquant.org/lib/#liq_set_quality">...</a>
     */
    public native boolean setQuality(int target);

    /**
     * Quality in range 0-100. Quantization will fail if minimum quality cannot
     * be achieved with given number of colors.
     *
     * @param max max
     * @param min min
     * @return bool
     * @see <a href="http://pngquant.org/lib/#liq_set_quality">...</a>
     */
    public native boolean setQuality(int min, int max);

    /**
     * Speed in range 1 (slowest) and 11 (fastest). 3 is the optimum.
     * Higher speeds quantize quicker, but at cost of quality and sometimes larger images.
     *
     * @param speed s
     * @return r
     * @see <a href="http://pngquant.org/lib/#liq_set_speed">...</a>
     */
    public native boolean setSpeed(int speed);

    /**
     * Reduces color precision by truncating number of least significant bits.
     * Slightly improves speed and helps generating images for low-fidelity displays/textures.
     *
     * @param bits b
     * @return b
     * @see <a href="http://pngquant.org/lib/#liq_set_min_posterization">...</a>
     */
    public native boolean setMinPosterization(int bits);

    @Override
    public void close() {
        if (handle != 0) {
            liq_attr_destroy(handle);
            handle = 0;
        }
    }
}
