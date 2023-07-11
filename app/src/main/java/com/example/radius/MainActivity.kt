package com.example.radius

import android.os.Bundle
import android.widget.CheckBox
//import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var data: TextView
    private lateinit var radioGroupPropertyType: RadioGroup
    private lateinit var radioGroupNumberOfRooms: RadioGroup
    private lateinit var checkBoxSwimmingPool: CheckBox
    private lateinit var checkBoxGardenArea: CheckBox
    private lateinit var checkBoxGarage: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = findViewById(R.id.data)
        radioGroupPropertyType = findViewById(R.id.radio_group_property_type)
        radioGroupNumberOfRooms = findViewById(R.id.radio_group_number_of_rooms)
        checkBoxSwimmingPool = findViewById(R.id.checkbox_swimming_pool)
        checkBoxGardenArea = findViewById(R.id.checkbox_garden_area)
        checkBoxGarage = findViewById(R.id.checkbox_garage)

        // Assuming you have the JSON data stored in a variable called 'jsonData'
        val jsonData = """
            {
                "propertyType": "Apartment",
                "numberOfRooms": "1 to 3 Rooms",
                "facilities": ["Swimming Pool", "Garden Area"]
            }
        """.trimIndent()

        // Parse the JSON data into a JSON object
        val jsonObject = JSONObject(jsonData)

        // Get the property type from the JSON object
        val propertyType = jsonObject.getString("propertyType")

        // Get the number of rooms from the JSON object
        val numberOfRooms = jsonObject.getString("numberOfRooms")

        // Get the facilities from the JSON object as a JSON array
        val facilitiesArray = jsonObject.getJSONArray("facilities")

        // Convert the JSON array to a list of strings
        val facilitiesList = mutableListOf<String>()
        for (i in 0 until facilitiesArray.length()) {
            facilitiesList.add(facilitiesArray.getString(i))
        }

        // Set the values in the XML layout
        data.text = propertyType

        // Set the selected property type radio button based on the JSON data
        when (propertyType) {
            "Apartment" -> radioGroupPropertyType.check(R.id.radio_apartment)
            "Condo" -> radioGroupPropertyType.check(R.id.radio_condo)
            "Boat House" -> radioGroupPropertyType.check(R.id.radio_boat_house)
            "Land" -> radioGroupPropertyType.check(R.id.radio_land)
        }

        // Set the selected number of rooms radio button based on the JSON data
        when (numberOfRooms) {
            "1 to 3 Rooms" -> radioGroupNumberOfRooms.check(R.id.radio_1_to_3_rooms)
            "No Rooms" -> radioGroupNumberOfRooms.check(R.id.radio_no_rooms)
        }

        // Set the checked state of the checkboxes based on the JSON data
        checkBoxSwimmingPool.isChecked = facilitiesList.contains("Swimming Pool")
        checkBoxGardenArea.isChecked = facilitiesList.contains("Garden Area")
        checkBoxGarage.isChecked = facilitiesList.contains("Garage")
    }
}
