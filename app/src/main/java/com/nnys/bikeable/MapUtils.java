package com.nnys.bikeable;

import com.google.maps.android.PolyUtil;
import com.google.maps.model.LatLng;
import com.skobbler.ngx.SKCoordinate;

import java.util.ArrayList;
import java.util.List;

public class MapUtils {

    public static LatLng getModelLatLngFromGms(com.google.android.gms.maps.model.LatLng gmsLatLng){
        LatLng modelLatLng = new LatLng(gmsLatLng.latitude, gmsLatLng.longitude);
        return modelLatLng;
    }

    public static com.google.android.gms.maps.model.LatLng getGmsLatLngFromModel(LatLng modelLatLng){
        com.google.android.gms.maps.model.LatLng gmsLatLng =
                new com.google.android.gms.maps.model.LatLng(modelLatLng.lat, modelLatLng.lng);
        return gmsLatLng;
    }

    public static SKCoordinate getSKCoordinateFromGms(com.google.android.gms.maps.model.LatLng gmsLatLng){
        SKCoordinate skLatLng = new SKCoordinate(gmsLatLng.longitude, gmsLatLng.latitude);
        return skLatLng;
    }


    public static ArrayList<com.google.android.gms.maps.model.LatLng> getLstGmsLatLngFromModel (List<LatLng> modelLatLngArr){
        ArrayList<com.google.android.gms.maps.model.LatLng> result = new ArrayList<>();
        for (LatLng modelLatLng : modelLatLngArr){
            result.add(getGmsLatLngFromModel(modelLatLng));
        }
        return result;
    }

    public static void selectClickedRoute (AllRoutes allRoutes, com.google.android.gms.maps.model.LatLng clickLatLng){
        for (int i = 0; i < allRoutes.getNumRoutes(); i++){
            // TODO: change 100 to constant value
            BikeableRoute route = allRoutes.getAllRoutes().get(i);
            if (PolyUtil.isLocationOnPath(clickLatLng, route.routePolylineOptions.getPoints(), true, 100)){
                allRoutes.setSelectedRouteIndex(i);
                allRoutes.selectAndColorRoute(i);
                break;
            }
        }
    }

}
