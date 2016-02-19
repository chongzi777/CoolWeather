package com.example.se7en.coolweather.util;

import android.text.TextUtils;

import com.example.se7en.coolweather.db.CoolWeatherDB;
import com.example.se7en.coolweather.model.City;
import com.example.se7en.coolweather.model.County;
import com.example.se7en.coolweather.model.Province;

/**
 * 解析查询省市县返回的xml数据，所用接口返回的城市信息格式为：“代号|城市，代号|城市”
 * Created by se7en on 2016/2/19.
 */
public class Utility {

    /**
     * 解析并处理服务器返回的省级数据
     * @param coolWeatherDB
     * @param response
     * @return
     */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces!=null && allProvinces.length>0){
                for (String p:allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);  //存储解析出来的省份信息
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 解析并处理服务器返回的市级数据
     * @param coolWeatherDB
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities!=null && allCities.length>0){
                for (String c:allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);  //存储解析出来的城市信息
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 解析并处理服务器返回的县级数据
     * @param coolWeatherDB
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if(allCounties!=null && allCounties.length>0){
                for (String c:allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);  //存储解析出来的县信息
                }
            }
            return true;
        }
        return false;
    }

}
