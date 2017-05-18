package com.example.c515.myweather.util;

import android.text.TextUtils;

import com.example.c515.myweather.db.City;
import com.example.c515.myweather.db.County;
import com.example.c515.myweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by C515 on 2017/5/11.
 */
public class Utility {
    /*
    * 解析和处理服务器返回的省级数据
    * */
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces=new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));      //出过错
                    province.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /*
    * 解析和处理服务器返回的市级数据
    * */
    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCiyies=new JSONArray(response);
                for (int i=0;i<allCiyies.length();i++){
                    JSONObject cityObject=allCiyies.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();}
        }
        return false;
    }
    /*
     *解析和处理服务器返回的县级数据
    * */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties=new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.save();
                }
                return true;
            }catch (JSONException E){
                E.printStackTrace();
            }
        }
        return false;

    }

}
