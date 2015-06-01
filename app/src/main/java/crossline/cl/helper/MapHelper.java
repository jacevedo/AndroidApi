package crossline.cl.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import crossline.cl.object.PetObject;
import crossline.cl.object.Place;
import crossline.cl.object.google.objects.FindAddress;

/**
 * Created by jacevedo on 02-01-15.
 */
public class MapHelper
{
    public FindAddress getLatLng(String address)
    {
       // String parameter = String.format("?address=%1&sensor=true", place.getAddress());
        String url = "http://maps.googleapis.com/maps/api/geocode/json";

        List<NameValuePair> parameter = new ArrayList();
        parameter.add(new BasicNameValuePair("address", address));
        parameter.add(new BasicNameValuePair("sensor", "true"));



        HttpResponseHelper helper = new HttpResponseHelper();
        String json = helper.makeHttpRequest(url,"GET",parameter);

        Gson gson = new Gson();
        Type findAddressType = new TypeToken<FindAddress>() {
        }.getType();

        FindAddress findAddress = gson.fromJson(json,findAddressType);

        return findAddress;
    }
}
