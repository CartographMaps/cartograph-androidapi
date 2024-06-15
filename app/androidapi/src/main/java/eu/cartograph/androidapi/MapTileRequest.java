package eu.cartograph.androidapi;

import android.os.Parcel;
import android.os.Parcelable;

public class MapTileRequest implements Parcelable {
    final static int CURRENT_VERSION = 1;

    private long mX, mY; // slippy tile coordinates
    private int mZoom;
    private int mTileSize = 256; // pixels, usually 256

    private double mBoundingBoxTop, mBoundingBoxLeft, mBoundingBoxBottom, mBoundingBoxRight; //lat,lon,lat,lon

    public void setCoordinate(long x, long y, int zoom) {
        mX = x;
        mY = y;
        mZoom = zoom;
    }

    public void setTileSize(int size) {
        mTileSize = size;
    }

    public void setBoundingBox(double top, double left, double bottom, double right) {
        mBoundingBoxTop = top;
        mBoundingBoxLeft = left;
        mBoundingBoxBottom = bottom;
        mBoundingBoxRight = right;
    }

    public long getX() {
        return mX;
    }
    public long getY() {
        return mY;
    }
    public int getZoom() {
        return mZoom;
    }
    public int getTileSize() {
        return mTileSize;
    }

    public double getBoundingBoxTop() {
        return mBoundingBoxTop;
    }

    public double getBoundingBoxLeft() {
        return mBoundingBoxLeft;
    }

    public double getBoundingBoxBottom() {
        return mBoundingBoxBottom;
    }

    public double getBoundingBoxRight() {
        return mBoundingBoxRight;
    }

    public MapTileRequest() {

    }

    public MapTileRequest(Parcel in) {
        int currentVersion = in.readInt();

        mX = in.readLong();
        mY = in.readLong();
        mZoom = in.readInt();
        mTileSize = in.readInt();
        mBoundingBoxTop = in.readDouble();
        mBoundingBoxLeft = in.readDouble();
        mBoundingBoxBottom = in.readDouble();
        mBoundingBoxRight = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(CURRENT_VERSION);
        dest.writeLong(mX);
        dest.writeLong(mY);
        dest.writeInt(mZoom);
        dest.writeInt(mTileSize);
        dest.writeDouble(mBoundingBoxTop);
        dest.writeDouble(mBoundingBoxLeft);
        dest.writeDouble(mBoundingBoxBottom);
        dest.writeDouble(mBoundingBoxRight);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapTileRequest> CREATOR = new Creator<MapTileRequest>() {
        @Override
        public MapTileRequest createFromParcel(Parcel in) {
            return new MapTileRequest(in);
        }

        @Override
        public MapTileRequest[] newArray(int size) {
            return new MapTileRequest[size];
        }
    };
}
