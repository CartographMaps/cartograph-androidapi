package eu.cartograph.androidapi;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class MapTileResponse implements Parcelable {
    final static int CURRENT_VERSION = 1;

    private int mErrorCode = 0; // everything other than "0" is interpreted as an error
    private String mErrorMessage = null;
    private byte[] mTileData = null; // Warning: This must be smaller than 1MB (Android AIDL restriction).

    public final static int DATA_FORMAT_RASTER = 0; // raw png, jpeg, etc. data
    public final static int DATA_FORMAT_RGB = 1;    // RGB,RBG,RGB,... array
    public final static int DATA_FORMAT_RGBA = 1;   // RGBA,RGBA,RGBA,... array

    private int mDataFormat = DATA_FORMAT_RGBA;

    public MapTileResponse(byte[] tileData, int dataFormat) {
        mTileData = tileData;
        mDataFormat = dataFormat;
    }

    public MapTileResponse(int errorCode, String errorMessage) {
        mErrorCode = errorCode;
        mErrorMessage = errorMessage;
    }

    public MapTileResponse(Parcel in) {
        int currentVersion = in.readInt();
        mErrorCode = in.readInt();
        if (mErrorCode != 0) {
            mErrorMessage = in.readString();
        }
        else {
            mTileData = new byte[in.readInt()];
            in.readByteArray(mTileData);
            mDataFormat = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(CURRENT_VERSION);

        // Make sure that an error is raised when no tile data is available
        if (mTileData == null && mErrorCode == 0) {
            mErrorCode = -1;
            mErrorMessage = "Tile data is null";
        }
        dest.writeInt(mErrorCode);
        if (mErrorCode != 0) {
            if (mErrorMessage == null) {
                mErrorMessage = "Unknown error";
            }
            dest.writeString(mErrorMessage);
        }
        else {
            dest.writeInt(mTileData.length);
            dest.writeByteArray(mTileData);
            dest.writeInt(mDataFormat);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapTileResponse> CREATOR = new Creator<MapTileResponse>() {
        @Override
        public MapTileResponse createFromParcel(Parcel in) {
            return new MapTileResponse(in);
        }

        @Override
        public MapTileResponse[] newArray(int size) {
            return new MapTileResponse[size];
        }
    };

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public int getDataFormat() {
        return mDataFormat;
    }

    public byte[] getTileData() {
        return mTileData;
    }
}
