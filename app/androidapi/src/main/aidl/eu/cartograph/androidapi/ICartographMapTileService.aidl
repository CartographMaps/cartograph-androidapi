package eu.cartograph.androidapi;

import eu.cartograph.androidapi.MapTileRequest;
import eu.cartograph.androidapi.MapTileResponse;
import eu.cartograph.androidapi.MapListResponse;

interface ICartographMapTileService {

    // Get an identifier for the service (must be unique, is not shown to the user)
    String getServiceUid();

    // Get a service name (shown to the user)
    String getServiceName();

    // Get the url which opens the service app (via intent://)
    String getServiceOpenUrl();

    // Get a list of maps provided by the service
    MapListResponse getMapList();

    // Request a map tile from the map with the given mapKey
    MapTileResponse getMapTile(String mapKey, in MapTileRequest tile);

    // Request to open the map with the given mapKey
    boolean openMap(String mapKey);

    // Close the map with the given mapKey
    void closeMap(String mapKey);
}