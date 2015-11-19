package com.rostlab.eva;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Longes on 15.11.2015.
 */
public class ParameterNameValue
{
    private String name = "";
    private String value = "";

    public ParameterNameValue(String name, String value) {
        try {
            this.name = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            this.value = URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}