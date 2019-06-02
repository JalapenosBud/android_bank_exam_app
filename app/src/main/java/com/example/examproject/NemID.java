package com.example.examproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NemID {

    Map<String, String> nem_id_card = new HashMap<>();

    public NemID() {
        nem_id_card.put("0005", "125672");
        nem_id_card.put("0017", "105090");
        nem_id_card.put("0252", "405280");
        nem_id_card.put("0487", "885260");
        nem_id_card.put("8017", "524211");
    }

    public void validateLogin()
    {
        //show key in tv
        //show card on small image
        //enter value
        //if matches
        //login
    }
}
