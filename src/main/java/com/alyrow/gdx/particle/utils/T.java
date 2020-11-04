package com.alyrow.gdx.particle.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class T<Holder extends T.Transformer> {

    private AffineTransform t;
    private Holder holder;

    public T() {
        t = new AffineTransform();
    }

    public T(AffineTransform Tx) {
        t = new AffineTransform(t);
    }

    public T(float m00, float m10, float m01, float m11, float m02, float m12) {
        t = new AffineTransform(m00, m10, m01, m11, m02, m12);
    }

    public T(float[] flatmatrix) {
        t = new AffineTransform(flatmatrix);
    }

    public T(double m00, double m10, double m01, double m11, double m02, double m12) {
        t = new AffineTransform(m00, m10, m01, m11, m02, m12);
    }

    public T(double[] flatmatrix) {
        t = new AffineTransform(flatmatrix);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getType() {
        return t.getType();
    }


    public double getDeterminant() {
        return t.getDeterminant();
    }


    public void getMatrix(double[] flatmatrix) {
        t.getMatrix(flatmatrix);
    }


    public double getScaleX() {
        return t.getScaleX();
    }


    public double getScaleY() {
        return t.getScaleY();
    }


    public double getShearX() {
        return t.getShearX();
    }


    public double getShearY() {
        return t.getShearY();
    }


    public double getTranslateX() {
        return t.getTranslateX();
    }


    public double getTranslateY() {
        return t.getTranslateY();
    }

    public T<Holder> translate(double tx, double ty) {
        t.translate(tx, ty);
        return this;
    }


    public T<Holder> rotate(double theta) {
        t.rotate(theta);
        return this;
    }


    public T<Holder> rotate(double theta, double anchorx, double anchory) {
        t.rotate(theta, anchorx, anchory);
        return this;
    }


    public T<Holder> rotate(double vecx, double vecy) {
        t.rotate(vecx, vecy);
        return this;
    }


    public T<Holder> rotate(double vecx, double vecy, double anchorx, double anchory) {
        t.rotate(vecx, vecy, anchorx, anchory);
        return this;
    }


    public T<Holder> quadrantRotate(int numquadrants) {
        t.quadrantRotate(numquadrants);
        return this;
    }


    public T<Holder> quadrantRotate(int numquadrants, double anchorx, double anchory) {
        t.quadrantRotate(numquadrants, anchorx, anchory);
        return this;
    }


    public T<Holder> scale(double sx, double sy) {
        t.scale(sx, sy);
        return this;
    }


    public T<Holder> shear(double shx, double shy) {
        t.shear(shx, shy);
        return this;
    }


    public T<Holder> setToIdentity() {
        t.setToIdentity();
        return this;
    }


    public T<Holder> setToTranslation(double tx, double ty) {
        t.setToTranslation(tx, ty);
        return this;
    }


    public T<Holder> setToRotation(double theta) {
        t.setToRotation(theta);
        return this;
    }


    public T<Holder> setToRotation(double theta, double anchorx, double anchory) {
        t.setToRotation(theta, anchorx, anchory);
        return this;
    }


    public T<Holder> setToRotation(double vecx, double vecy) {
        t.setToRotation(vecx, vecy);
        return this;
    }


    public T<Holder> setToRotation(double vecx, double vecy, double anchorx, double anchory) {
        t.setToRotation(vecx, vecy, anchorx, anchory);
        return this;
    }


    public T<Holder> setToQuadrantRotation(int numquadrants) {
        t.setToQuadrantRotation(numquadrants);
        return this;
    }


    public T<Holder> setToQuadrantRotation(int numquadrants, double anchorx, double anchory) {
        t.setToQuadrantRotation(numquadrants, anchorx, anchory);
        return this;
    }


    public T<Holder> setToScale(double sx, double sy) {
        t.setToScale(sx, sy);
        return this;
    }


    public T<Holder> setToShear(double shx, double shy) {
        t.setToShear(shx, shy);
        return this;
    }


    public T<Holder> setTransform(AffineTransform Tx) {
        t.setTransform(Tx);
        return this;
    }


    public T<Holder> setTransform(double m00, double m10, double m01, double m11, double m02, double m12) {
        t.setTransform(m00, m10, m01, m11, m02, m12);
        return this;
    }


    public T<Holder> concatenate(AffineTransform Tx) {
        t.concatenate(Tx);
        return this;
    }


    public T<Holder> preConcatenate(AffineTransform Tx) {
        t.preConcatenate(Tx);
        return this;
    }


    public AffineTransform createInverse() throws NoninvertibleTransformException {
        return t.createInverse();
    }


    public T<Holder> invert() throws NoninvertibleTransformException {
        t.invert();
        return this;
    }


    public Point2D transform(Point2D ptSrc, Point2D ptDst) {
        return t.transform(ptSrc, ptDst);
    }


    public T<Holder> transform(Point2D[] ptSrc, int srcOff, Point2D[] ptDst, int dstOff, int numPts) {
        t.transform(ptSrc, srcOff, ptDst, dstOff, numPts);
        return this;
    }


    public T<Holder> transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
        t.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        return this;
    }


    public T<Holder> transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
        t.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        return this;
    }


    public T<Holder> transform(float[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
        t.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        return this;
    }


    public T<Holder> transform(double[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) {
        t.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        return this;
    }


    public Point2D inverseTransform(Point2D ptSrc, Point2D ptDst) throws NoninvertibleTransformException {
        return t.inverseTransform(ptSrc, ptDst);
    }


    public T<Holder> inverseTransform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws NoninvertibleTransformException {
        t.inverseTransform(srcPts, srcOff, dstPts, dstOff, numPts);
        return this;
    }


    public Point2D deltaTransform(Point2D ptSrc, Point2D ptDst) {
        return t.deltaTransform(ptSrc, ptDst);
    }


    public T<Holder> deltaTransform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) {
        t.deltaTransform(srcPts, srcOff, dstPts, dstOff, numPts);
        return this;
    }


    public Shape createTransformedShape(Shape pSrc) {
        return t.createTransformedShape(pSrc);
    }


    public String toString() {
        return t.toString();
    }


    public boolean isIdentity() {
        return t.isIdentity();
    }
    
    
    public AffineTransform execute() {
        return t;
    }


    public Holder getHolder() {
        holder.transform(t);
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public interface Transformer {
        void transform(AffineTransform t);
    }

}
